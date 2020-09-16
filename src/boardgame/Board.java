package boardgame;

public class Board {
// attributes
  private Integer rows;
  private Integer columns;
  private Piece[][] pieces;

// constructors
  public Board(int rows, int columns) {
	  this.rows = rows;
	  this.columns = columns;
	  pieces = new Piece[rows][columns];
  }
// getters and setters
// Columns
  public Integer getColumns() {
	return columns;
  }
  public void setColumns(Integer columns) {
	this.columns = columns;
	return;
  }
// rows
  public Integer getRows() {
	return rows;
  }
  public void setRows(int rows) {
	this.rows = rows;
	return;
  }
  
// methods
}
