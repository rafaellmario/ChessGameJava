package boardgame;

import boardgame.exception.BoardException;

public class Board {
// attributes
  private int rows;
  private int columns;
  private Piece[][] pieces;
  
// constructors
  public Board(int rows, int columns) {
	if (rows < 1 || columns < 1) 
	    throw new BoardException("Error creating board: there must be "
					+ "at least 1 row and 1 column");
	
	this.rows = rows;
	this.columns = columns;
	pieces = new Piece[rows][columns];
  }
// getters and setters
  public int getRows() {
	return this.rows;
  }

  public int getColumns() {
	 return this.columns;
  }

// Methods
  public Piece piece(int row, int column) {
	if (!this.positionExists(row, column))
		 throw new BoardException("Position not on the board");
		
	return this.pieces[row][column];
  }
  public Piece piece(Position position) {
	if (!this.positionExists(position)) 
		  throw new BoardException("Position not on the board");
		
    return this.pieces[position.getRow()][position.getColumn()];
  }
	
  public void placePiece(Piece piece, Position position) {
    if (this.thereIsAPiece(position)) 
		  throw new BoardException("There is already a piece on position " + position);
		
     this.pieces[position.getRow()][position.getColumn()] = piece;
	 piece.position = position;
  }
	
  public Piece removePiece(Position position) {
	if(!this.positionExists(position)) 
	   throw new BoardException("Position not on the board");
	
	if(this.piece(position) == null)
		return null;
		
	Piece aux = this.piece(position);
	aux.position = null;
	pieces[position.getRow()][position.getColumn()] = null;
	return aux;
  }
	
  private boolean positionExists(int row, int column) {
	return row >= 0 && row < rows && column >= 0 && column < columns;
  }
	
  public boolean positionExists(Position position) {
	return this.positionExists(position.getRow(), position.getColumn());
  }
	
  public boolean thereIsAPiece(Position position) {
  	 if(!this.positionExists(position))
		 throw new BoardException("Position not on the board");
	 
	 return this.piece(position) != null;
  }
}