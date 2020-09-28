package application;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.exception.ChessException;
import gui.UserInterface;

public class MainClass {
  public static void main(String[] args) {
	Locale.setDefault(Locale.US);
	
	ChessMatch chessMatch = new ChessMatch(); 
	
	Scanner sc = new Scanner(System.in);
	
	while(true) {
		
		try {
			UserInterface.clearScreen();
			
			UserInterface.printBoard(chessMatch.getPieces());
			
			System.out.println();
			System.out.print("Source: ");
			ChessPosition source = UserInterface.readChessPosition(sc);
			System.out.println();
			
			System.out.print("Target: ");
			ChessPosition target = UserInterface.readChessPosition(sc);
			
			ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
		}
		catch(InputMismatchException e){
			System.out.print(e.getMessage());
			sc.nextLine();
		}
		catch(ChessException e) {
			System.out.print(e.getMessage());
			sc.nextLine();
		}
		
	}
	
	
	
  }
}
