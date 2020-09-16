package boardgame.exception;

public class BoardException extends RuntimeException{
// serialization 
  private static final long serialVersionUID = 1L;

//Constructor 
  public BoardException(String msg) {
	super(msg);
  }
  
}
