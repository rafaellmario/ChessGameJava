package boardgame;

public class Piece {
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
}
