package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.exception.ChessException;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class ChessMatch {

// attributes
	private Board board;
	private Integer turn;
	private Color   currentPlayer;
	private boolean check;
	private boolean checkMate;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
// Constructor 
	public ChessMatch() {
		this.board = new Board(8, 8);
		this.turn = 1;
		this.currentPlayer = Color.WHITE;
		check = false;
		checkMate = false;
		initialSetup();
	}

// getters and setters
	public Integer getTurn() {
		return this.turn;
	} 
	
	public Color getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	public boolean getCheckMate() {
		return checkMate;
	}
	
	
// Methods
  public ChessPiece[][] getPieces() {
	ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
	
	for (int i=0; i<board.getRows(); i++) 
		for (int j=0; j<board.getColumns(); j++) 
			mat[i][j] = (ChessPiece) board.piece(i, j);
	
	 return mat;
  }
  
  public boolean[][] possibleMoves(ChessPosition sourcePosition){
	Position position = sourcePosition.toPosition();
	this.validateSourcePosition(position);
	return this.board.piece(position).possibleMoves();
  }
  
  public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
	Position source = sourcePosition.toPosition();
	Position target = targetPosition.toPosition();
	this.validateSourcePosition(source);
	this.validateTargetPosition(source, target);
	Piece capturedPiece = makeMove(source, target);
	
	if(this.testCheck(currentPlayer)) {
		this.undoMove(source, target, capturedPiece);
		throw new ChessException("You can't put yourself in check");
	}
	
	this.check = (this.testCheck(opponent(this.currentPlayer))) ? true : false;
	
	if(this.testCheckMate(this.opponent(this.currentPlayer)))
		this.checkMate = true;
	else
		this.nextTurn();
	
	return (ChessPiece)capturedPiece;
  }
	
  private Piece makeMove(Position source, Position target) {
	ChessPiece p = (ChessPiece)board.removePiece(source);
	p.increaseMoveCount();
	Piece capturedPiece = this.board.removePiece(target);
	this.board.placePiece(p, target);
	
	if(capturedPiece != null) {
		this.piecesOnTheBoard.remove(capturedPiece);
		this.capturedPieces.add(capturedPiece);
	}
	
	// Special move - castling king side rook
	if(p instanceof King && (target.getColumn() == source.getColumn() +2)) {
			Position sourceRook = new Position(source.getRow(), source.getColumn()+3);
			Position targetRook = new Position(source.getRow(), source.getColumn()+1);
			ChessPiece rook = (ChessPiece)this.board.removePiece(sourceRook);
			this.board.placePiece(rook, targetRook);
			rook.increaseMoveCount();
	}

	// Special move - castling queen side rook
	if(p instanceof King && (target.getColumn() == source.getColumn()-2)) {
			Position sourceRook = new Position(source.getRow(), source.getColumn()-4);
			Position targetRook = new Position(source.getRow(), source.getColumn()-1);
			ChessPiece rook = (ChessPiece)this.board.removePiece(sourceRook);
			this.board.placePiece(rook, targetRook);
			rook.increaseMoveCount();
	}

	
	return capturedPiece;
  }
  
  private void undoMove(Position source, Position target, Piece capturedPiece) {
	  ChessPiece p = (ChessPiece)this.board.removePiece(target);
	  p.decreaseMoveCount();
	  this.board.placePiece(p, source);
	  
	  // returning the captures piece to the target
	  if(capturedPiece != null){
		  this.board.placePiece(capturedPiece, target);
		  this.capturedPieces.remove(capturedPiece);
		  this.piecesOnTheBoard.add(capturedPiece);
	  }
	  
	// Special move - castling king side rook
	if(p instanceof King && (target.getColumn() == source.getColumn()+2)) {
			Position sourceRook = new Position(source.getRow(), source.getColumn()+3);
			Position targetRook = new Position(source.getRow(), source.getColumn()+1);
			ChessPiece rook = (ChessPiece)this.board.removePiece(targetRook);
			this.board.placePiece(rook, sourceRook);
			rook.decreaseMoveCount();
	}

	// Special move - castling queen side rook
	if(p instanceof King && (target.getColumn() == source.getColumn()-2)) {
			Position sourceRook = new Position(source.getRow(), source.getColumn()-4);
			Position targetRook = new Position(source.getRow(), source.getColumn()-1);
			ChessPiece rook = (ChessPiece)this.board.removePiece(targetRook);
			this.board.placePiece(rook, sourceRook);
			rook.decreaseMoveCount();
	}
	  
	return;	  
  }
	
  private void validateSourcePosition(Position position) {
	if(!this.board.thereIsAPiece(position)) 
		throw new ChessException("There is no piece on source position");
	
	if(this.currentPlayer != ((ChessPiece)this.board.piece(position)).getColor())
		throw new ChessException("The chosen piece is not yours!");
	
	if(!this.board.piece(position).isThereAnyPossibleMove())
		throw new ChessException("There is no possible moves for the chosen piece");
  }
	
  private void validateTargetPosition(Position source, Position target) {
	if (!this.board.piece(source).possibleMove(target))
		throw new ChessException("The chosen piece can't move to target position");	
  }
  
  private Color opponent(Color color) {
	  return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
  }
  
  private ChessPiece kingColor(Color color) {
	  List<Piece> list = this.piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
	  
	  for(Piece p : list)
		  if(p instanceof King)
			  return (ChessPiece) p;
	  
	  throw new IllegalStateException("There is no "+ color+" king on the board");
  }
  
  private boolean testCheck(Color color) {
	  Position kingPosition = kingColor(color).getChessPosition().toPosition();
	  List<Piece> opponentPieces = this.piecesOnTheBoard.stream().filter(x -> 
	  ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
	  
	  for(Piece p : opponentPieces) {
		  boolean[][] mat = p.possibleMoves();
		  
		  if(mat[kingPosition.getRow()][kingPosition.getColumn()])
			  return true;
	  }
	  return false;
  }
  
  private boolean testCheckMate(Color color) {
	  if(!testCheck(color))
		  return false;
	  
	  List<Piece> list = this.piecesOnTheBoard.stream().filter(x -> 
	  ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
	  
	  for(Piece p:list) {
		  boolean[][] mat = p.possibleMoves();
		  for(int i = 0; i<board.getRows(); i++) {
			  for(int j = 0; j<board.getColumns(); j++) {
				  if(mat[i][j]) {
					  Position source = ((ChessPiece)p).getChessPosition().toPosition();
					  Position target = new Position(i, j);
					  Piece capturedPiece = this.makeMove(source, target);
					  boolean testCheck = this.testCheck(color);
					  this.undoMove(source, target, capturedPiece);
					  if(!testCheck)
						  return false;
				  }
			  }
		  }	  
	  }
	  return true;
  }
  
  private void placeNewPiece(char column, int row, ChessPiece piece) {
	board.placePiece(piece, new ChessPosition(column, row).toPosition());
	this.piecesOnTheBoard.add(piece);
  }

  private void nextTurn() {
	  this.turn ++;
	  this.currentPlayer = (this.currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	  return;
  }
  
  private void initialSetup() {
	
    placeNewPiece('a', 1, new Rook(board, Color.WHITE));
    placeNewPiece('b', 1, new Knight(board, Color.WHITE));
    placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
    placeNewPiece('d', 1, new Queen(board, Color.WHITE));
    placeNewPiece('e', 1, new King(board, Color.WHITE,this));
    placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
    placeNewPiece('g', 1, new Knight(board, Color.WHITE));
    placeNewPiece('h', 1, new Rook(board, Color.WHITE));
    placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
    placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
    placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
    placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
    placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
    placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
    placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
    placeNewPiece('h', 2, new Pawn(board, Color.WHITE));
    
    placeNewPiece('a', 8, new Rook(board, Color.BLACK));
    placeNewPiece('b', 8, new Knight(board, Color.BLACK));
    placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
    placeNewPiece('d', 8, new Queen(board, Color.BLACK));
    placeNewPiece('e', 8, new King(board, Color.BLACK,this));
    placeNewPiece('g', 8, new Knight(board, Color.BLACK));
    placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
    placeNewPiece('h', 8, new Rook(board, Color.BLACK));
    placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
    placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
    placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
    placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
    placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
    placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
    placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
    placeNewPiece('h', 7, new Pawn(board, Color.BLACK));

  }
}