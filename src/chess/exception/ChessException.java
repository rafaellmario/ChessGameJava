package chess.exception;

import boardgame.exception.BoardException;

public class ChessException extends BoardException {
// serialization
  private static final long serialVersionUID = 1L;

// constructor
  public ChessException(String msg) {
	  super(msg);
  }

}
