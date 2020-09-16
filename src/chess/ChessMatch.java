package chess;

import boardgame.Board;
// import chess.ChessPiece;

public class ChessMatch {
// attributes
  private Board board;

// Constructors
  public ChessMatch() {
	  board = new Board(8,8);
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
  
}
