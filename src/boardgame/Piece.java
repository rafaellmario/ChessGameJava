package boardgame;

public abstract class Piece {
// attributes 
  protected Position position;
  private Board board;
 
// constructors
  public Piece(Board board) {
	  this.position = null;
	  this.board = board; 
  }

// getters and setters
  protected Board getBoard() {
		return board;
  }
// Methods
  public abstract boolean[][] possibleMoves();
  
  public boolean possibleMove(Position position) {
	  return possibleMoves()[position.getRow()][position.getColumn()];
  }
  
  public boolean isThereAnyPossibleMove() {
	  boolean[][] mat = possibleMoves();
	  
	  for(int i = 0; i<mat.length; i++)
		  for(int j = 0; j<mat.length; j++)
			  if(mat[i][j])
				  return true;
	  
	  return false;
  }
  
}
