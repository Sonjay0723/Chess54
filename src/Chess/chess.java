package Chess;

import java.util.*;
import Pieces.*;

public class chess {
	
	public static HashMap<position,piece> board= new HashMap<position,piece>(70);
	
	public static void setUpBoard() {
		
		for(int i = 1; i<= 8; i++) {
			for(int j = 1; j<=8; j++) {
				
				position currPos = new position(i,j);
				
				//set up white pieces
				if((currPos.getRow() ==1 && currPos.getColumn() ==1) || (currPos.getRow() ==1 && currPos.getColumn() ==8))
					board.put(currPos, new rook("wR"));
				else if((currPos.getRow() ==1 && currPos.getColumn() ==2) || (currPos.getRow() ==1 && currPos.getColumn() ==7))
					board.put(currPos, new knight("wN"));
				else if((currPos.getRow() ==1 && currPos.getColumn() ==3) || (currPos.getRow() ==1 && currPos.getColumn() ==6))
					board.put(currPos, new bishop("wB"));
				else if(currPos.getRow() ==1 && currPos.getColumn() ==5)
					board.put(currPos, new queen("wQ"));
				else if(currPos.getRow() ==1 && currPos.getColumn() ==4)
					board.put(currPos, new king("wK"));
				else if(currPos.getRow() == 2)
					board.put(currPos, new pawn("wp"));
				
				//set up black pieces
				else if((currPos.getRow() ==8 && currPos.getColumn() ==1) || (currPos.getRow() ==8 && currPos.getColumn() ==8))
					board.put(currPos, new rook("bR"));
				else if((currPos.getRow() ==8 && currPos.getColumn() ==2) || (currPos.getRow() ==8 && currPos.getColumn() ==7))
					board.put(currPos, new knight("bN"));
				else if((currPos.getRow() ==8 && currPos.getColumn() ==3) || (currPos.getRow() ==8 && currPos.getColumn() ==6))
					board.put(currPos, new bishop("bB"));
				else if(currPos.getRow() ==8 && currPos.getColumn() ==5)
					board.put(currPos, new queen("bQ"));
				else if(currPos.getRow() ==8 && currPos.getColumn() ==4) 
					board.put(currPos, new king("bK"));
				else if(currPos.getRow() == 7) 
					board.put(currPos, new pawn("bp"));
				
				//set up empty boxes
				else if(isBlackBox(i,j)) 
					board.put(currPos, new emptySquare("##"));
				else 
					board.put(currPos, new emptySquare("  "));
			}
		}
	}
	
	public static boolean isBlackBox(int row, int col) {
		if(((row%2 == 1) && (col%2 == 1)) || ((row%2 == 0) && (col%2 == 0)))
			return false;
		return true;
	}
	
	public static void printBoard() {
		for(int i = 8; i>=1; i--) {
			for(int j=8; j>=1; j--) {
				String pieceValue = board.get(new position(i,j)).getValue();
				System.out.print(pieceValue+" ");
			}
			System.out.println(i);
		}
		System.out.println(" a" + "  b" + "  c" + "  d" + "  e" + "  f" + "  g" + "  h");
		System.out.println();
	}
	
	public static void main(String[] args) {
		setUpBoard();
		printBoard();
		
		String input;
		
		boolean whiteMove = true;
		boolean validMove = false;
		boolean askDraw = false;
		
		Scanner gamePlay = new Scanner(System.in);
		
		boolean continueGame = true;
		
		while(continueGame) {
			
			if(whiteMove) 
				System.out.print("White's Move: ");
			else
				System.out.print("Black's Move: ");
			
			input = gamePlay.nextLine();
			
			if((!input.toLowerCase().equals("resign") && !input.toLowerCase().equals("draw")) && (input.length() != 5 || input.charAt(2)!=' '|| !Character.isLetter(input.charAt(0)) || !Character.isLetter(input.charAt(3)) || Character.isLetter(input.charAt(1)) || Character.isLetter(input.charAt(4)))) {
				System.out.println("Illegal move, try again\n");
				continue;
			}
			
			if(input.toLowerCase().equals("resign")) {
				if(whiteMove) 
					System.out.println("Black wins");
				else
					System.out.println("White wins");
				
				gamePlay.close();
				continueGame = false;
				return;
			}
			
			if(askDraw) {
				if(input.toLowerCase().equals("draw")) {
					System.out.println("draw");
					gamePlay.close();
					continueGame = false;
					return;
				}
				else
					askDraw = false;
			}
		}
	}

}
