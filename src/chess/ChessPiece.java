package chess;

import boardgame.Board;
import boardgame.Piece;
public class ChessPiece extends Piece {
// attributes
  private Color color;
  
//constructors
public ChessPiece(Board board, Color color) {
	super(board);
	this.color = color;
}

// getters and setters
// Color
  public Color getColor() {
    return color;
  }
  
// Methods
  
}
