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
		String[] inputArr = new String[3];
		
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
			
			if((!input.toLowerCase().equals("resign") && !input.toLowerCase().equals("draw")) && (input.length() > 7 || input.charAt(2)!=' '|| !Character.isLetter(input.charAt(0)) || !Character.isLetter(input.charAt(3)) || Character.isLetter(input.charAt(1)) || Character.isLetter(input.charAt(4)))) {
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
					continue;
			}
			
			inputArr = input.split(" ");
			
			//check if player is attempting to move other player's piece
			if(whiteMove && (board.get(toIntPosition(inputArr[0])).getValue().charAt(0)=='b')){
				System.out.println("Illegal move, try again\n");
				continue;
			}
			if(!whiteMove && (board.get(toIntPosition(inputArr[0])).getValue().charAt(0)=='w')){
				System.out.println("Illegal move, try again\n");
				continue;
			}
		}
	}
	
	private static boolean isEnPassant(String currPos, String newPos, boolean isWhiteMove) {
		
		//calculate rowNumbers and column numbers
		int rowNum = Integer.parseInt(String.valueOf(currPos.charAt(1)));
		int newRowNum = Integer.parseInt(String.valueOf(newPos.charAt(1)));
		int colNum = getColInt(currPos.charAt(0));
		int newColNum = getColInt(newPos.charAt(0));
		
		//EnPassant for white
		if(isWhiteMove) {
			
			//must be in fifth row skipping over to 6th!
			if(rowNum != 5 && newRowNum != 6)
				return false;
			
			//check if black pawn to the right or to the left
			if(colNum + 1 == newColNum) {
				String val = board.get(new position(rowNum,colNum+1)).getValue();
				int moves = board.get(new position(rowNum,colNum+1)).getMovement();
				if(val.equals("bp") && moves == 1) 
					return true;
			}
			else if(colNum - 1 == newColNum) {
				String val = board.get(new position(rowNum,colNum-1)).getValue();
				int moves = board.get(new position(rowNum,colNum-1)).getMovement();
				if(val.equals("bp") && moves == 1) 
					return true;
			}
		}
		
		//EnPassant for Black
		else {
			//must be in fourth row skipping over to 3rd!
			if(rowNum != 4 && newRowNum != 3)
				return false;
			
			//check if white pawn to the right or to the left
			if(colNum + 1 == newColNum) {
				String val = board.get(new position(rowNum,colNum+1)).getValue();
				int moves = board.get(new position(rowNum,colNum+1)).getMovement();
				if(val.equals("wp") && moves == 1) 
					return true;
			}
			else if(colNum - 1 == newColNum) {
				String val = board.get(new position(rowNum,colNum-1)).getValue();
				int moves = board.get(new position(rowNum,colNum-1)).getMovement();
				if(val.equals("wp") && moves == 1) 
					return true;
			}
		}
		return false;
	}
	
	private static boolean isCastling(String currPos, String newPos, boolean isWhiteMove) {
		
		//calculate rowNumbers and column numbers
		int rowNum = Integer.parseInt(String.valueOf(currPos.charAt(1)));
		int newRowNum = Integer.parseInt(String.valueOf(newPos.charAt(1)));
		int colNum = getColInt(currPos.charAt(0));
		int newColNum = getColInt(newPos.charAt(0));
		
		//check if it is a king
		if(!(board.get(new position(rowNum,colNum)) instanceof king))
			return false;
		//Check if king has moved
		if(board.get(new position(rowNum,colNum)).getMovement()!= 0)
			return false;
		//check if movement correct
		if(newColNum!=3 && newColNum!= 7)
			return false;
		
		//check for white movement
		if(isWhiteMove) {
			
			//check if moving to right or left
			if(newColNum == 7) {
				//check if rook has moved
				if(board.get(new position(rowNum,8)).getMovement()!= 0)
					return false;
				//check if any piece in between is still there
				if((!board.get(new position(rowNum,6)).getValue().equals("  ")) || (!board.get(new position(rowNum,7)).getValue().equals("##")))
					return false;
				//check if any movement would be through a check
				if(isCheck(newPos, true) || isCheck(currPos, true) || isCheck("f1", true))
					return false;
			}
			else if(newColNum == 3) {
				//check if rook has moved
				if(board.get(new position(rowNum,1)).getMovement()!= 0)
					return false;
				//check if any piece in between is still there
				if((!board.get(new position(rowNum,4)).getValue().equals("  ")) || (!board.get(new position(rowNum,3)).getValue().equals("##")) || (!board.get(new position(rowNum,2)).getValue().equals("  ")))
					return false;
				//check if any movement would be through a check
				if(isCheck(newPos, true) || isCheck(currPos, true) || isCheck("d1", true))
					return false;
			}
		}
		
		//if black piece
		else {
			
			//check if moving to right or left
			if(newColNum == 7) {
				//check if rook has moved
				if(board.get(new position(rowNum,8)).getMovement()!= 0)
					return false;
				//check if any piece in between is still there
				if((!board.get(new position(rowNum,6)).getValue().equals("##")) || (!board.get(new position(rowNum,7)).getValue().equals("  ")))
					return false;
				//check if any movement would be through a check
				if(isCheck(newPos, false) || isCheck(currPos, false) || isCheck("f8", false))
					return false;
			}
			else if(newColNum == 3) {
				//check if rook has moved
				if(board.get(new position(rowNum,1)).getMovement()!= 0)
					return false;
				//check if any piece in between is still there
				if((!board.get(new position(rowNum,4)).getValue().equals("##")) || (!board.get(new position(rowNum,3)).getValue().equals("  ")) || (!board.get(new position(rowNum,2)).getValue().equals("##")))
					return false;
				//check if any movement would be through a check
				if(isCheck(newPos, false) || isCheck(currPos, false) || isCheck("d8", false))
					return false;
			}
		}
		
		return true;
	}
	
	private static boolean isCheck(String positionK, boolean isWhiteMove) {
		piece oppPieces = null;
		
		//check if black pieces can attack white king
		if(isWhiteMove) {
			//for all other black pieces, test if any of them can move to given position
			for (position opponentPiecePos: board.keySet()) {
				oppPieces = board.get(opponentPiecePos);
				if(oppPieces.getValue().charAt(0)=='b' && oppPieces.validMove(opponentPiecePos, toIntPosition(positionK)))
					return true;
			}
		}
		
		//check if white pieces can attack black king
		else {
			//for all other white pieces, test if any of them can move to given position
			for (position opponentPiecePos: board.keySet()) {
				oppPieces = board.get(opponentPiecePos);
				if(oppPieces.getValue().charAt(0)=='w' && oppPieces.validMove(opponentPiecePos, toIntPosition(positionK)))
					return true;
			}
		}
		
		return false;
	}
	
	private static boolean isCheckMate(String positionK, boolean isWhiteMove) {
		
		position kPos = toIntPosition(positionK);
		
		if(kPos.getColumn()+1 <=8) {
			String testPosition = getColLetter(kPos.getColumn()+1)+Integer.toString(kPos.getRow());
			if(!isCheck(testPosition, isWhiteMove))
				return false;
		}
		if(kPos.getColumn()+1 <= 8 && kPos.getRow() +1 <=8) {
			String testPosition = getColLetter(kPos.getColumn()+1)+Integer.toString(kPos.getRow()+1);
			if(!isCheck(testPosition, isWhiteMove))
				return false;
		}
		if(kPos.getColumn()+1 <= 8 && kPos.getRow() -1 >=1) {
			String testPosition = getColLetter(kPos.getColumn()+1)+Integer.toString(kPos.getRow()-1);
			if(!isCheck(testPosition, isWhiteMove))
				return false;
		}
		if(kPos.getColumn()-1 >=1) {
			String testPosition = getColLetter(kPos.getColumn()-1)+Integer.toString(kPos.getRow());
			if(!isCheck(testPosition, isWhiteMove))
				return false;
		}
		if(kPos.getColumn()-1 >= 1 && kPos.getRow() +1 <=8) {
			String testPosition = getColLetter(kPos.getColumn()-1)+Integer.toString(kPos.getRow()+1);
			if(!isCheck(testPosition, isWhiteMove))
				return false;
		}
		if(kPos.getColumn()-1 >= 1 && kPos.getRow() -1 >=1) {
			String testPosition = getColLetter(kPos.getColumn()-1)+Integer.toString(kPos.getRow()-1);
			if(!isCheck(testPosition, isWhiteMove))
				return false;
		}
		if(kPos.getRow()+1 <= 8) {
			String testPosition = getColLetter(kPos.getColumn())+Integer.toString(kPos.getRow()+1);
			if(!isCheck(testPosition, isWhiteMove))
				return false;
		}
		if(kPos.getRow()-1 >= 1) {
			String testPosition = getColLetter(kPos.getColumn())+Integer.toString(kPos.getRow()-1);
			if(!isCheck(testPosition, isWhiteMove))
				return false;
		}
		
		return true;
	}
	
	private static int getColInt(char col) {

		if(col == 'a')
			return 1;
		else if(col == 'b')
			return 2;
		else if(col == 'c')
			return 3;
		else if(col == 'd')
			return 4;
		else if(col == 'e')
			return 5;
		else if(col == 'f')
			return 6;
		else if(col == 'g')
			return 7;
		return 8;
	}
	
	private static char getColLetter(int colNum) {
		if(colNum == 1)
			return 'a';
		else if(colNum == 2)
			return 'b';
		else if(colNum == 3)
			return 'c';
		else if(colNum == 4)
			return 'd';
		else if(colNum == 5)
			return 'e';
		else if(colNum == 6)
			return 'f';
		else if(colNum == 7)
			return 'g';
		return 'h';
	}
	
	private static position toIntPosition(String loc) {
		return (new position(Integer.parseInt(String.valueOf(loc.charAt(1))), getColInt(loc.charAt(0))));
	}
	
	public static boolean isBlackBox(int row, int col) {
		if(((row%2 == 1) && (col%2 == 1)) || ((row%2 == 0) && (col%2 == 0)))
			return false;
		return true;
	}

}
