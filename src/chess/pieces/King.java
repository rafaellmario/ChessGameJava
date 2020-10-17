package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece{

  // Constructors
  public King(Board board, Color color) {
	super(board, color);
  }
	
// methods 
  
  
  private boolean canMove(Position position) {
	  ChessPiece p = (ChessPiece)this.getBoard().piece(position);
	  return (p == null || p.getColor() != this.getColor());
  }
  
 
  @Override 
  public String toString() {
	  return "K";
  }

  @Override
  public boolean[][] possibleMoves() {	  
	boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
	  
	Position p = new Position(0,0);
	
	// above
	p.setValues(this.position.getRow() - 1, this.position.getColumn());
	if(this.getBoard().positionExists(p) && this.canMove(p))
		mat[p.getRow()][p.getColumn()] = true;
	
	// below 
	p.setValues(this.position.getRow()+1, this.position.getColumn());
	if(this.getBoard().positionExists(p) && this.canMove(p))
		mat[p.getRow()][p.getColumn()] = true;
	
	// Left
	p.setValues(this.position.getRow(), this.position.getColumn()-1);
	if(this.getBoard().positionExists(p) && this.canMove(p))
		mat[p.getRow()][p.getColumn()] = true;
	
	// Right
	p.setValues(this.position.getRow(), this.position.getColumn()+1);
	if(this.getBoard().positionExists(p) && this.canMove(p))
		mat[p.getRow()][p.getColumn()] = true;
	
	// NorthWeast
	p.setValues(this.position.getRow()-1, this.position.getColumn()-1);
	if(this.getBoard().positionExists(p) && this.canMove(p))
		mat[p.getRow()][p.getColumn()] = true;
	
	// NorthEast
	p.setValues(this.position.getRow()-1, this.position.getColumn()+1);
	if(this.getBoard().positionExists(p) && this.canMove(p))
		mat[p.getRow()][p.getColumn()] = true;
	
	// SouthWeast
	p.setValues(this.position.getRow()+1, this.position.getColumn()-1);
	if(this.getBoard().positionExists(p) && this.canMove(p))
		mat[p.getRow()][p.getColumn()] = true;
	
	// SouthEast
	p.setValues(this.position.getRow()+1, this.position.getColumn()+1);
	if(this.getBoard().positionExists(p) && this.canMove(p))
		mat[p.getRow()][p.getColumn()] = true;
	
	
	return mat;
  }


}
