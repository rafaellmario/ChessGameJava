package chess;

import boardgame.Board;
//import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rock;
// import chess.ChessPiece;

public class ChessMatch {
// attributes
  private Board board;

// Constructors
  public ChessMatch() {
	  board = new Board(8,8);
	  initialSetup();
  }
// getters and setters
  
// methods
  public ChessPiece[][] getPieces(){
	  ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
	  
	  for(int i=0;i<this.board.getRows();i++) 
		for(int j=0;j<this.board.getColumns();j++)
			mat[i][j] = (ChessPiece)board.piece(i,j);// downcasting
	  
	  return mat;
  }
  
  private void placeNewPiece(char column, int row, ChessPiece piece) {
	  board.placePiece(piece, new ChessPosition(column, row).toPosition());
  }
  
  private void initialSetup() {
	placeNewPiece('b',6, new Rock(this.board, Color.WHITE));
	placeNewPiece('e',8, new King(this.board, Color.BLACK));
	placeNewPiece('e',1, new King(this.board, Color.WHITE));
  }
  
}
