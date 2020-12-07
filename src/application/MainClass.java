package application;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
	
	List<ChessPiece> captured = new ArrayList<>();
	
	Scanner sc = new Scanner(System.in);
	
	while(!chessMatch.getCheckMate()) {
		
		try {
			UserInterface.clearScreen();
			
			UserInterface.printMatch(chessMatch, captured);
			
			System.out.println();
			System.out.print("Source: ");
			ChessPosition source = UserInterface.readChessPosition(sc);
			
			boolean[][] possibleMoves = chessMatch.possibleMoves(source);
			UserInterface.clearScreen();
			UserInterface.printBoard(chessMatch.getPieces(), possibleMoves);
			
			System.out.println();
			
			System.out.print("Target: ");
			ChessPosition target = UserInterface.readChessPosition(sc);
			
			ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
			
			if(capturedPiece != null)
				captured.add(capturedPiece);
			
			if(chessMatch.getPromoted()!=null) {
				System.out.print("Enter piece for promotion(B/N/R/Q):");
				String type = sc.nextLine().toUpperCase();
				
				while(!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q")) {
					System.out.print("Invalid argument! Enter piece for promotion(B/N/R/Q):");
					type = sc.nextLine().toUpperCase();
				}
				
				chessMatch.replacePromotedPiece(type);
					
			}
		 }
		catch(InputMismatchException e){
			System.out.print(e.getMessage());
			sc.nextLine();
		}
		catch(ChessException e) {
			System.out.print(e.getMessage());
			sc.nextLine();
		}
		catch(InvalidParameterException e) {
			System.out.print(e.getMessage());
			sc.nextLine();
		}
		
	}
	UserInterface.clearScreen();
	UserInterface.printMatch(chessMatch, captured);
  }
}
