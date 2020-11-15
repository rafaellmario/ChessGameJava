package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
// attributes 
	private Color color;
	private int moveCount;
// constructors
  public ChessPiece(Board board, Color color) {
	super(board);
	this.color = color;
	this.moveCount = 0;
  }

// getters and setters
  public Color getColor() {
	return this.color;
  }
  
  public ChessPosition getChessPosition() {
	  return ChessPosition.fromPosition(this.position); 
  }
  
  public int getMoveCount() {
	  return moveCount;
  }

// methods
  protected boolean isThereOpponentPiece(Position position) {
	ChessPiece p = (ChessPiece)this.getBoard().piece(position);
	return (p != null && p.getColor() != this.color);
  }
  
  
  public void increaseMoveCount() {
	  this.moveCount++;
  }
  public void decreaseMoveCount() {
	  this.moveCount--;
  }
  
}