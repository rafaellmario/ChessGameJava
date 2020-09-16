package chess;

import boardgame.Board;
import boardgame.Position;
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
  
  private void initialSetup() {
	board.placePiece(new Rock(this.board, Color.WHITE), new Position(2,1));
	board.placePiece(new King(this.board, Color.BLACK), new Position(0,4));
	board.placePiece(new King(this.board, Color.WHITE), new Position(7,4));
  }
  
}
