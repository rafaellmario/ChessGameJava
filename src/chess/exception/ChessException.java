package chess.exception;

public class ChessException extends RuntimeException {
// serialization
  private static final long serialVersionUID = 1L;

// constructor
  public ChessException(String msg) {
	  super(msg);
  }

}
