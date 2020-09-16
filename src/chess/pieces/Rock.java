package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rock extends ChessPiece{

// Constructor
  public Rock(Board board, Color color) {
	super(board, color);
  }

// methods 
  @Override 
  public String toString() {
	  return "R";
  }
}
