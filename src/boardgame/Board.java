package boardgame;

import boardgame.exception.BoardException;

public class Board {
// attributes
  private Integer rows;
  private Integer columns;
  private Piece[][] pieces;

// constructors
  public Board(int rows, int columns) {
	  if(rows < 1 || columns < 1)
		  throw new BoardException("Error creating board: "
		  	+ "there must be necessary at least 1 row and 1 column");
	  
	  this.rows = rows;
	  this.columns = columns;
	  pieces = new Piece[rows][columns];
  }
// getters and setters
// Columns
  public Integer getColumns() {
	return columns;
  }

// rows
  public Integer getRows() {
	return rows;
  }
  
// methods
  public Piece piece(int row, int column) {
	  if(!positionExists(row, column))
		  throw new BoardException("Position not on the board");
	  
	  return this.pieces[row][column];
  }
  public Piece piece(Position position) {
	  if(!this.positionExists(position))
		  throw new BoardException("Position not on the board");
	  
	  return this.pieces[position.getRow()][position.getColumn()];
  }
  
  public void placePiece(Piece piece, Position position) {
	  if(this.thereIsAPiece(position))
		  throw new BoardException("There's already a piece os this position");
	  
	  this.pieces[position.getRow()][position.getColumn()] = piece;
	  piece.position = position;
  }
  
  public Piece removePiece(Position position) {
	  if(!positionExists(position))
		  throw new BoardException("Position not on the board!");
	  
	  if(piece(position) == null)
		  return null;
	  else {
		  Piece aux = piece(position);
		  aux.position = null;
		  this.pieces[position.getRow()][position.getColumn()] = null;
		  return aux;
	  }
  }
  
// 
  private boolean positionExists(int row, int column) {
	  return ((row >= 0 && row < this.rows) && (column >= 0 && this.columns >= this.columns));  
  }
  
  public boolean positionExists(Position position) {
	  return positionExists(position.getRow(), position.getColumn());
  }
// 
  public boolean thereIsAPiece(Position position) {
	  if(!this.positionExists(position))
		  throw new BoardException("Position not on the board");
	  
	  return (piece(position) != null);
  }
  
}
