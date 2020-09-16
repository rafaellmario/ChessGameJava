package boardgame;

public class Position {
// attributes
  private Integer row;
  private Integer column;
  
// constructors
  public Position(int row, int column) {
	  this.row = row;
	  this.column = column;
  }

// getters and setters
// Row 
  public Integer getRow() {
	return row;
  }
  public void setRow(Integer row) {
	this.row = row;
	return;
  }
// Column 
  public Integer getColumn() {
	return column;
  }
  public void setColumn(Integer column) {
	this.column = column;
  } 
// Methods
  @Override
  public String toString() {
	  return this.row+", "+this.column;
  }
  
}
