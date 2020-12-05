package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece{
	// attributes

	// constructors
	  public Queen(Board board, Color color) {
	    super(board, color);
	  }

	// methods
	  @Override
	  public String toString() {
		return "Q";
	  }

	  @Override
	  public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[this.getBoard().getRows()][this.getBoard().getColumns()];
		Position p = new Position(0, 0);
		
		// Possible moves
		// Above
		p.setValues(this.position.getRow() - 1, this.position.getColumn());
		while(this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)) {
			  mat[p.getRow()][p.getColumn()] = true;
			  p.setRow(p.getRow() - 1);
		}
		if(this.getBoard().positionExists(p) && this.isThereOpponentPiece(p))
		  mat[p.getRow()][p.getColumn()] = true;
			
		// Left
		p.setValues(this.position.getRow(), this.position.getColumn() - 1);
		while(this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)){
		  mat[p.getRow()][p.getColumn()] = true;
		  p.setColumn(p.getColumn() - 1);
		}
		if(this.getBoard().positionExists(p) && this.isThereOpponentPiece(p))
		  mat[p.getRow()][p.getColumn()] = true;
		
		// Right
		p.setValues(this.position.getRow(), this.position.getColumn() + 1);
		while(this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)) {
		  mat[p.getRow()][p.getColumn()] = true;
		  p.setColumn(p.getColumn() + 1);
		}
		if(this.getBoard().positionExists(p) && this.isThereOpponentPiece(p))
		  mat[p.getRow()][p.getColumn()] = true;
		
		// Below
		p.setValues(this.position.getRow() + 1, this.position.getColumn());
		while(this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)) {
		  mat[p.getRow()][p.getColumn()] = true;
		  p.setRow(p.getRow() + 1);
		}
		if(this.getBoard().positionExists(p) && this.isThereOpponentPiece(p)) 
		  mat[p.getRow()][p.getColumn()] = true;
		
		// NW
		p.setValues(this.position.getRow() - 1, this.position.getColumn()-1);
		while(this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)) {
			  mat[p.getRow()][p.getColumn()] = true;
			  p.setValues(p.getRow()-1, p.getColumn()-1);
		}
		if(this.getBoard().positionExists(p) && this.isThereOpponentPiece(p))
		  mat[p.getRow()][p.getColumn()] = true;
			
		// NE
		p.setValues(this.position.getRow()-1, this.position.getColumn()+1);
		while(this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)){
		  mat[p.getRow()][p.getColumn()] = true;
		  p.setValues(p.getRow()-1, p.getColumn()+1);
		}
		if(this.getBoard().positionExists(p) && this.isThereOpponentPiece(p))
		  mat[p.getRow()][p.getColumn()] = true;
		
		// SE
		p.setValues(this.position.getRow()+1, this.position.getColumn()+1);
		while(this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)) {
		  mat[p.getRow()][p.getColumn()] = true;
		  p.setValues(p.getRow()+1, p.getColumn()+1);
		}
		if(this.getBoard().positionExists(p) && this.isThereOpponentPiece(p))
		  mat[p.getRow()][p.getColumn()] = true;
		
		// SW
		p.setValues(this.position.getRow()+1, this.position.getColumn()-1);
		while(this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p)) {
		  mat[p.getRow()][p.getColumn()] = true;
		  p.setValues(p.getRow()+1, p.getColumn()-1);
		}
		if(this.getBoard().positionExists(p) && this.isThereOpponentPiece(p)) 
		  mat[p.getRow()][p.getColumn()] = true;
		
		return mat;
	  }
}
