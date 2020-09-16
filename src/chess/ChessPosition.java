package chess;

import chess.exception.ChessException;
import boardgame.Position;

public class ChessPosition {
// attributes
  private char column;
  private int row;

//constructors  
  public ChessPosition(char column, int row) {
	if((column < 'a' ||  column > 'h') || (row < 1 || row > 8))
		throw new ChessException("Error in chess position: "
				+ "Valid values are from h1 to h8");
	this.column = column;
	this.row = row;
  }

// getters and setters
  public char getColumn() {
	return column;
  }
  
  public int getRow() {
	return row;
  }

// methods
  protected Position toPosition() {
	  return new Position(8-this.row,this.column-'a');
  }
  
  protected static ChessPosition fromPosition(Position position) {
	  return new ChessPosition((char)('a'-position.getColumn()),8-position.getRow());
  }
  
  @Override
  public String toString() {
	  return ""+this.column+ this.row;
  }
  
}
