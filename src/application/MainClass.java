package application;

import java.util.Locale;

import chess.ChessMatch;
import gui.UserInterface;

public class MainClass {
  public static void main(String[] args) {
	Locale.setDefault(Locale.US);
	
	ChessMatch chessMatch = new ChessMatch(); 
	UserInterface.printBoard(chessMatch.getPieces()); 
	
  }
}
