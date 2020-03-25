package Chess;

/**
 * @author Dhrishti Hazari
 * @author Jayson Pitta
 */


import java.util.*;
import Pieces.*;

public class chess {
	
	//overall HashMap
	public static HashMap<position,piece> board= new HashMap<position,piece>(70);
	
	/**
	 * setUpBoard is a void Method that sets up original board
	 * no inputs or return
	 */
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
	
	/**
	 * printBoard is a void method to print out board
	 * no inputs or return
	 */
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
	
	/**
	 * The main function does all of the calls to other methods to test for resigns, draws, and moves of the specific pieces
	 * 
	 * @param args is where all the inputs come through
	 */
	public static void main(String[] args) {
		setUpBoard();
		printBoard();
		
		//input String names
		String input;
		String[] inputArr = new String[3];
		
		//boolean values + int number of turns that is needed for EnPassant
		boolean whiteMove = true;
		boolean askDraw = false;
		boolean continueGame = true;
		int numTurns = 0;
		
		Scanner gamePlay = new Scanner(System.in);
		
		//White the game is still on
		while(continueGame) {
			
			//print out appropriate move
			if(whiteMove) 
				System.out.print("White's Move: ");
			else
				System.out.print("Black's Move: ");
			
			input = gamePlay.nextLine();
			System.out.println("");
			
			//check to see if input is valid
			if((!input.toLowerCase().equals("resign") && !input.toLowerCase().equals("draw")) && (input.length() > 11 || input.charAt(2)!=' '|| !Character.isLetter(input.charAt(0)) || !Character.isLetter(input.charAt(3)) || Character.isLetter(input.charAt(1)) || Character.isLetter(input.charAt(4)))) {
				System.out.println("Illegal move, try again\n");
				continue;
			}
			
			//check to see if resigning
			if(input.toLowerCase().equals("resign")) {
				if(whiteMove) 
					System.out.println("Black wins");
				else
					System.out.println("White wins");
				
				gamePlay.close();
				continueGame = false;
				return;
			}
			
			//check to see if asking to draw game
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
			
			//Check all other movements in the game
			inputArr = input.split(" ");
			
			//check if input is valid
			if(inputArr.length<2) {
				System.out.println("Illegal move, try again\n");
				continue;
			}
			
			//check if attempting to move empty square
			if(chess.board.get(toIntPosition(inputArr[0])) instanceof emptySquare) {
				System.out.println("Illegal move, try again\n");
				continue;
			}
			//check if out of bounds movement
			if(toIntPosition(inputArr[1]).getColumn() > 8 || toIntPosition(inputArr[1]).getColumn() < 1 || toIntPosition(inputArr[1]).getRow() < 1 || toIntPosition(inputArr[1]).getRow() > 8 || toIntPosition(inputArr[0]).getColumn() > 8 || toIntPosition(inputArr[0]).getColumn() < 1 || toIntPosition(inputArr[0]).getRow() < 1 || toIntPosition(inputArr[0]).getRow() > 8 ) {
				System.out.println("Illegal move, try again\n");
				continue;
			}
			//check if player is attempting to move other player's piece
			if(whiteMove && (board.get(toIntPosition(inputArr[0])).getColor()=='b')){
				System.out.println("Illegal move, try again\n");
				continue;
			}
			else if(!whiteMove && (board.get(toIntPosition(inputArr[0])).getColor()=='w')){
				System.out.println("Illegal move, try again\n");
				continue;
			}
			
			//check if third input is valid
			if(inputArr.length>2) {
				if(!inputArr[2].toLowerCase().equals("draw?") && inputArr[2].length()>1) {
					System.out.println("Illegal move, try again\n");
					continue;
				}
			}
			
			//check if move it valid, then do all other steps!
			piece currPiece = board.get(toIntPosition(inputArr[0]));
			if(currPiece.validMove(toIntPosition(inputArr[0]), toIntPosition(inputArr[1]), whiteMove, numTurns)) {
				
				//check if draw is being asked for
				if(inputArr.length>2) {
					if(inputArr[2]!=null && inputArr[2].toLowerCase().equals("draw?"))
						askDraw = true;
				}
				
				//check to see if check would result after movement
				piece movingPiece = board.get(toIntPosition(inputArr[0]));
				piece takeOverPiece = board.get(toIntPosition(inputArr[1]));
				
				//Move piece, increment number of steps, replace old position with emptySquare!
				movingPiece.setMovement(movingPiece.getMovement()+1);
				board.put(toIntPosition(inputArr[1]), movingPiece);
				if(isBlackBox(toIntPosition(inputArr[0]).getRow(),toIntPosition(inputArr[0]).getColumn()))
					board.put(toIntPosition(inputArr[0]), new emptySquare("##"));
				else
					board.put(toIntPosition(inputArr[0]), new emptySquare("  "));
				
				//If movement results in getting into check
				String positionK = kingPosition(whiteMove);
				if(isCheck(positionK, whiteMove, numTurns)) {
					System.out.println("Illegal move, try again\n");
					//go back to OG position
					movingPiece.setMovement(movingPiece.getMovement()-1);
					board.put(toIntPosition(inputArr[0]), movingPiece);
					board.put(toIntPosition(inputArr[1]), takeOverPiece);
					//might have been pawn so reset twoSteps in case
					chess.board.get(toIntPosition(inputArr[0])).setTwoStep(-1);
					continue;
				}
				
				//reverse
				movingPiece.setMovement(movingPiece.getMovement()-1);
				board.put(toIntPosition(inputArr[0]), movingPiece);
				board.put(toIntPosition(inputArr[1]), takeOverPiece);
				
				//check EnPassant, make sure it is attempting EnPassant in the very next round!
				if(numTurns - board.get(toIntPosition(inputArr[0])).getCanEnPassant() == 0 && isEnPassant(inputArr[0], inputArr[1], whiteMove, numTurns)) {
					int rowNum = Integer.parseInt(String.valueOf(inputArr[0].charAt(1)));
					int colNum = getColInt(inputArr[0].charAt(0));
					int newColNum = getColInt(inputArr[1].charAt(0));
					//Check if removing to left or to right
					if(colNum+1 == newColNum) {
						if(isBlackBox(rowNum,colNum+1))
							board.put(new position(rowNum,colNum+1), new emptySquare("##"));
						else
							board.put(new position(rowNum,colNum+1), new emptySquare("  "));
						//check if there was other pawn for which EnPassant was possible, set to -1 again
						if(colNum+2 <= 8 && board.get(new position(rowNum, colNum+2)).getValue().equals(movingPiece.getValue()))
							board.get(new position(rowNum, colNum+2)).setEnPassant(-1);
					}
					else if(colNum-1 == newColNum) {
						if(isBlackBox(rowNum,colNum-1))
							board.put(new position(rowNum,colNum-1), new emptySquare("##"));
						else
							board.put(new position(rowNum,colNum-1), new emptySquare("  "));
						//check if there was other pawn for which EnPassant was possible, set to -1 again
						if(colNum-2 >= 1 && board.get(new position(rowNum, colNum-2)).getValue().equals(movingPiece.getValue()))
							board.get(new position(rowNum, colNum-2)).setEnPassant(-1);
					}
				}
				
				//Check Castling
				if(isCastling(inputArr[0], inputArr[1], whiteMove, numTurns)) {
					int newColNum = getColInt(inputArr[1].charAt(0));
					int rowNum = Integer.parseInt(String.valueOf(inputArr[0].charAt(1)));
					//move rook(on right or on left)
					if(newColNum - 1 == 1) {
						board.put(new position(rowNum, 3), board.get(new position(rowNum,1)));
						board.get(new position(rowNum, 3)).setMovement((board.get(new position(rowNum, 3)).getMovement())+1);
						if(isBlackBox(rowNum,1))
							board.put(new position(rowNum,1), new emptySquare("##"));
						else
							board.put(new position(rowNum,1), new emptySquare("  "));
					}
					else {
						board.put(new position(rowNum, 5), board.get(new position(rowNum,8)));
						board.get(new position(rowNum, 5)).setMovement((board.get(new position(rowNum, 5)).getMovement())+1);
						if(isBlackBox(rowNum,8))
							board.put(new position(rowNum,8), new emptySquare("##"));
						else
							board.put(new position(rowNum,8), new emptySquare("  "));
					}
				}
				
				//Move Position after all items have been checked(EXCEPT promotions)
				movingPiece.setMovement(movingPiece.getMovement()+1);
				board.put(toIntPosition(inputArr[1]), movingPiece);
				if(isBlackBox(toIntPosition(inputArr[0]).getRow(),toIntPosition(inputArr[0]).getColumn()))
					board.put(toIntPosition(inputArr[0]), new emptySquare("##"));
				else
					board.put(toIntPosition(inputArr[0]), new emptySquare("  "));
				
				//check promotion
				if(isPromotion(inputArr[0], inputArr[1],whiteMove)) {
					if(whiteMove) {
						if(inputArr.length>2) {
							if(inputArr[2].charAt(0) == 'R') 
								board.put(toIntPosition(inputArr[1]), new rook("wR"));
							else if(inputArr[2].charAt(0) == 'N') 
								board.put(toIntPosition(inputArr[1]), new knight("wN"));
							else if(inputArr[2].charAt(0) == 'B') 
								board.put(toIntPosition(inputArr[1]), new bishop("wB"));
							else
								board.put(toIntPosition(inputArr[1]), new queen("wQ"));
						}
						else
							board.put(toIntPosition(inputArr[1]), new queen("wQ"));
					}
					else {
						if(inputArr.length>2) {
							if(inputArr[2].charAt(0) == 'R') 
								board.put(toIntPosition(inputArr[1]), new rook("bR"));
							else if(inputArr[2].charAt(0) == 'N') 
								board.put(toIntPosition(inputArr[1]), new knight("bN"));
							else if(inputArr[2].charAt(0) == 'B') 
								board.put(toIntPosition(inputArr[1]), new bishop("bB"));
							else
								board.put(toIntPosition(inputArr[1]), new queen("bQ"));
						}
						else
							board.put(toIntPosition(inputArr[1]), new queen("bQ"));
					}
					//give same movement as pawn
					board.get(toIntPosition(inputArr[1])).setMovement(movingPiece.getMovement());
				}
					
				//it is now the other person's turn!
				whiteMove = !whiteMove;
				printBoard();
				numTurns++;
				
				//check if EnPassant is possible in the current state for any pawn, until piece is moved again it will not be set to -1 again
				checkEnPassant(whiteMove, numTurns);
				
				//check if there is a checkmate with current player now that old player has made a move
				if(isCheck(kingPosition(whiteMove),whiteMove, numTurns)) {
					if(isCheckMate(kingPosition(whiteMove), whiteMove, numTurns)) {
						System.out.println("Checkmate\n");
						if(whiteMove) 
							System.out.println("Black wins");
						else
							System.out.println("White wins");
						
						gamePlay.close();
						continueGame = false;
						return;
					}
				}
			}
			//if move is not valid it is illegal
			else{
				System.out.println("Illegal move, try again\n");
				continue;
			}		
		}
		
		//close scanner
		gamePlay.close();
	}
	
	/**
	 * isPromotion check to see if a pawn has reached the other side of the board and is able to get a promotion
	 * 
	 * @param currPos is the current position of the pawn
	 * @param newPos is the position it wants to move to
	 * @param isWhiteMove is a boolean that tells if its white's turn or black's turn
	 * 
	 * @return true if pawn can get a promotion, false otherwise
	 */
	public static boolean isPromotion(String currPos, String newPos, boolean isWhiteMove) {
		
		//calculate rowNumbers and column numbers
		int newRowNum = Integer.parseInt(String.valueOf(newPos.charAt(1)));
		int newColNum = getColInt(newPos.charAt(0));
		
		//must be on opposite end of board
		if(isWhiteMove) {
			if(board.get(new position(newRowNum,newColNum)).getType() == 'p' && newRowNum == 8)
				return true;
		}
		else {
			if(board.get(new position(newRowNum,newColNum)).getType() == 'p' && newRowNum == 1)
				return true;
		}
		
		return false;
	}
	
	/**
	 * checkEnPassant checks to see if any pawn is eligible to do an enPassant. 
	 * If so, it's canEnPassant field is assigned the current round number, so the program knows when the pawn is allowed to enPassant(Only in the current round!)
	 * 
	 * @param isWhiteMove is a boolean that tells if its white's turn or black's turn
	 * @param numTurn tells us how many rounds it has been
	 * 
	 * It has no return type
	 */
	public static void checkEnPassant(boolean isWhiteMove, int numTurn) {
		piece pawnPieces;
		
		for (position pawnPiecePos: board.keySet()) {
			pawnPieces = board.get(pawnPiecePos);
			//find all pawn pieces
			if(pawnPieces.getType() == 'p') {
				//set all possible EnPassants to numberTurn that can currently do it, ONLY WORKS if set at -1(meaning it has not had the opportunity to do so!)
				//if white turn
				if(pawnPieces.getColor() == 'w' && isWhiteMove && (pawnPiecePos.getRow()+1)<=8) {
					if((pawnPiecePos.getColumn()+1)<=8 && (numTurn - board.get(new position(pawnPiecePos.getRow(),(pawnPiecePos.getColumn()+1))).madeTwoStep() == 1) && pawnPieces.getCanEnPassant() == -1 && isEnPassant(pawnPiecePos.toStringPos(), (new position((pawnPiecePos.getRow()+1),(pawnPiecePos.getColumn()+1))).toStringPos(), isWhiteMove, numTurn))
						pawnPieces.setEnPassant(numTurn);
					if((pawnPiecePos.getColumn()-1)>=1 && (numTurn - board.get(new position(pawnPiecePos.getRow(),(pawnPiecePos.getColumn()-1))).madeTwoStep() == 1) && pawnPieces.getCanEnPassant() == -1 && isEnPassant(pawnPiecePos.toStringPos(), (new position((pawnPiecePos.getRow()+1),(pawnPiecePos.getColumn()-1))).toStringPos(), isWhiteMove, numTurn))
						pawnPieces.setEnPassant(numTurn);
				}
				//if black turn
				else if(pawnPieces.getColor() == 'b' && !isWhiteMove && (pawnPiecePos.getRow()-1)>=1) {
					if((pawnPiecePos.getColumn()+1)<=8 && (numTurn - board.get(new position(pawnPiecePos.getRow(),(pawnPiecePos.getColumn()+1))).madeTwoStep() == 1) && pawnPieces.getCanEnPassant() == -1 && isEnPassant(pawnPiecePos.toStringPos(), (new position((pawnPiecePos.getRow()-1),(pawnPiecePos.getColumn()+1))).toStringPos(), isWhiteMove, numTurn))
						pawnPieces.setEnPassant(numTurn);
					if((pawnPiecePos.getColumn()-1)>=1 && (numTurn - board.get(new position(pawnPiecePos.getRow(),(pawnPiecePos.getColumn()-1))).madeTwoStep() == 1) && pawnPieces.getCanEnPassant() == -1 && isEnPassant(pawnPiecePos.toStringPos(), (new position((pawnPiecePos.getRow()-1),(pawnPiecePos.getColumn()-1))).toStringPos(), isWhiteMove, numTurn))
						pawnPieces.setEnPassant(numTurn);
				}
			}
			
		}
	}
	
	/**
	 * isEnPassant tells if a pawn is eligible to perform an enPassant or not
	 * 
	 * @param currPos is the current position of the piece
	 * @param newPos is the position the piece wants to move to
	 * @param isWhiteMove tells us whose turn it is(if true, white; if false, black)
	 * @param numTurn tells us how many rounds it has been
	 * 
	 * @return a boolean, true if EnPassant can be done, false otherwise
	 */
	public static boolean isEnPassant(String currPos, String newPos, boolean isWhiteMove, int numTurn) {
		
		//calculate rowNumbers and column numbers
		int rowNum = Integer.parseInt(String.valueOf(currPos.charAt(1)));
		int newRowNum = Integer.parseInt(String.valueOf(newPos.charAt(1)));
		int colNum = getColInt(currPos.charAt(0));
		int newColNum = getColInt(newPos.charAt(0));
		
		//must be pawn!
		if(board.get(toIntPosition(currPos)).getType() != 'p')
			return false;
		
		//must be allowed to EnPassant(Can only do so in very next turn!)
		//if(numTurn - board.get(toIntPosition(currPos)).getCanEnPassant() != 0)
		//	return false;
		
		//EnPassant for white
		if(isWhiteMove) {
			//must be in fifth row skipping over to 6th!
			if(rowNum != 5 && newRowNum != 6)
				return false;
			
			//check if black pawn to the right or to the left, make sure piece being killed Just took 2 steps the last round!
			if(colNum + 1 == newColNum) {
				if(numTurn - board.get(new position(rowNum,colNum+1)).madeTwoStep()!=1)
					return false;
				String val = board.get(new position(rowNum,colNum+1)).getValue();
				int moves = board.get(new position(rowNum,colNum+1)).getMovement();
				if(val.equals("bp") && moves == 1) 
					return true;
			}
			else if(colNum - 1 == newColNum) {
				if(numTurn - board.get(new position(rowNum,colNum-1)).madeTwoStep()!=1)
					return false;
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
			
			//check if white pawn to the right or to the left, make sure piece being killed Just took 2 steps the last round!
			if(colNum + 1 == newColNum) {
				if(numTurn - board.get(new position(rowNum,colNum+1)).madeTwoStep()!=1)
					return false;
				String val = board.get(new position(rowNum,colNum+1)).getValue();
				int moves = board.get(new position(rowNum,colNum+1)).getMovement();
				if(val.equals("wp") && moves == 1) 
					return true;
			}
			else if(colNum - 1 == newColNum) {
				if(numTurn - board.get(new position(rowNum,colNum-1)).madeTwoStep()!=1)
					return false;
				String val = board.get(new position(rowNum,colNum-1)).getValue();
				int moves = board.get(new position(rowNum,colNum-1)).getMovement();
				if(val.equals("wp") && moves == 1) 
					return true;
			}
		}
		return false;
	}
	
	/**
	 * isCastling tells us if king is eligible to do a castling move
	 * 
	 * @param currPos is the current position of the piece
	 * @param newPos is the position the piece wants to move to
	 * @param isWhiteMove tells us whose turn it is(if true, white; if false, black)
	 * @param numMove tells us how many rounds it has been
	 * 
	 * @return a boolean, true if Castling can be done, false otherwise
	 */
	public static boolean isCastling(String currPos, String newPos, boolean isWhiteMove, int numMove) {
		
		//calculate rowNumbers and column numbers
		int rowNum = Integer.parseInt(String.valueOf(currPos.charAt(1)));
		int colNum = getColInt(currPos.charAt(0));
		int newColNum = getColInt(newPos.charAt(0));
		
		//check if it is a king
		if(!(board.get(new position(rowNum,colNum)) instanceof king))
			return false;
		
		//Check if king has moved
		if(board.get(new position(rowNum,colNum)).getMovement()!= 0)
			return false;
		
		//check if movement correct
		if(newColNum!=2 && newColNum!= 6)
			return false;
		
		//check for white movement
		if(isWhiteMove) {
			
			//check if moving to right or left
			if(newColNum == 2) {
				//check if rook has moved and must be rook
				if(board.get(new position(rowNum,1)).getMovement()!= 0 || board.get(new position(rowNum,1)).getType()!= 'R')
					return false;
				//check if any piece in between is still there
				if((!board.get(new position(rowNum,3)).getValue().equals("  ")) || (!board.get(new position(rowNum,2)).getValue().equals("##")))
					return false;
				//check if any movement would be through a check
				if(isCheck(newPos, true, numMove) || isCheck(currPos, true, numMove) || isCheck("f1", true, numMove))
					return false;
			}
			else if(newColNum == 6) {
				//check if rook has moved and must be rook
				if(board.get(new position(rowNum,8)).getMovement()!= 0 || board.get(new position(rowNum,8)).getType()!= 'R')
					return false;
				//check if any piece in between is still there
				if((!board.get(new position(rowNum,5)).getValue().equals("  ")) || (!board.get(new position(rowNum,6)).getValue().equals("##")) || (!board.get(new position(rowNum,7)).getValue().equals("  ")))
					return false;
				//check if any movement would be through a check
				if(isCheck(newPos, true, numMove) || isCheck(currPos, true, numMove) || isCheck("d1", true, numMove))
					return false;
			}
		}
		
		//if black piece
		else {
			
			//check if moving to right or left
			if(newColNum == 2) {
				//check if rook has moved and must be rook
				if(board.get(new position(rowNum,1)).getMovement()!= 0 || board.get(new position(rowNum,1)).getType()!= 'R')
					return false;
				//check if any piece in between is still there
				if((!board.get(new position(rowNum,3)).getValue().equals("##")) || (!board.get(new position(rowNum,2)).getValue().equals("  ")))
					return false;
				//check if any movement would be through a check
				if(isCheck(newPos, false, numMove) || isCheck(currPos, false, numMove) || isCheck("f8", false, numMove))
					return false;
			}
			else if(newColNum == 6) {
				//check if rook has moved and must be rook
				if(board.get(new position(rowNum,8)).getMovement()!= 0  || board.get(new position(rowNum,8)).getType()!= 'R')
					return false;
				//check if any piece in between is still there
				if((!board.get(new position(rowNum,5)).getValue().equals("##")) || (!board.get(new position(rowNum,6)).getValue().equals("  ")) || (!board.get(new position(rowNum,7)).getValue().equals("##")))
					return false;
				//check if any movement would be through a check
				if(isCheck(newPos, false, numMove) || isCheck(currPos, false, numMove) || isCheck("d8", false, numMove))
					return false;
			}
		}
		
		return true;
	}
	
	/**
	 * isCheck is a function that tells us if the king(for white OR black, whoever turn it is) is in check/will be in check if moved to positionK)
	 * 
	 * @param positionK gives us the possible String position of the king
	 * @param isWhiteMove tells us whose turn it is(if true, white; if false, black)
	 * @param numTurn tells us how many rounds it has been
	 * 
	 * @return a boolean, true if the king in positionk will be in check, false otherwise
	 */
	public static boolean isCheck(String positionK, boolean isWhiteMove, int numTurn) {
		piece oppPieces = null;
		
		//check if black pieces can attack white king
		if(isWhiteMove) {
			//for all other black pieces, test if any of them can move to given position
			for (position opponentPiecePos: board.keySet()) {
				oppPieces = board.get(opponentPiecePos);
				if(oppPieces.getColor()=='b' && oppPieces.validMove(opponentPiecePos, toIntPosition(positionK),isWhiteMove, numTurn))
					return true;
			}
		}
		
		//check if white pieces can attack black king
		else {
			//for all other white pieces, test if any of them can move to given position
			for (position opponentPiecePos: board.keySet()) {
				oppPieces = board.get(opponentPiecePos);
				if(oppPieces.getColor()=='w' && oppPieces.validMove(opponentPiecePos, toIntPosition(positionK),isWhiteMove, numTurn))
					return true;
			}
		}
		
		return false;
	}
	
	/**
	 * isCheckMate tells is if the king(white or black depending on whose turn it is) is in checkmate by testing all possible movements of the king to see if a check results for all possible movements
	 * It also makes sure that the king cannot get out of check by another one of the current player's pieces killing the opposing piece that is currently putting the king in the check position
	 * 
	 * @param positionK gives us the String position of the king
	 * @param isWhiteMove tells us whose turn it is(if true, white; if false, black)
	 * @param numMove tells us how many rounds it has been
	 * 
	 * @return a boolean, true if the king in positionk will be in checkmate, false otherwise
	 */
	public static boolean isCheckMate(String positionK, boolean isWhiteMove, int numMove) {
		
		//remove current king
		position kPos = toIntPosition(positionK);
		piece currKing = board.get(kPos);
		if(isBlackBox(kPos.getRow(),kPos.getColumn()))
			board.put(kPos, new emptySquare("##"));
		else
			board.put(kPos, new emptySquare("  "));
		
		if((kPos.getColumn()+1) <=8) {
			String testPosition = getColLetter(kPos.getColumn()+1)+Integer.toString(kPos.getRow());
			//Is moving to this position allowed for the king
			position testPos = toIntPosition(testPosition);
			boolean canMove = false;
			if(isWhiteMove && board.get(testPos).getColor() != 'w')
				canMove = true;
			else if(!isWhiteMove && board.get(testPos).getColor() != 'b')
				canMove = true;
			
			//place king in new position for testing
			piece tempMovePiece = board.get(testPos);
			if(canMove)
				board.put(testPos, currKing);
			
			//check if now there is still a check
			if(canMove && !isCheck(testPosition, isWhiteMove, numMove)) {
				board.put(kPos, currKing);
				board.put(testPos, tempMovePiece);
				return false;
			}
			//otherwise place king back where it came from
			board.put(testPos, tempMovePiece);
		}
		if((kPos.getColumn()+1) <= 8 && (kPos.getRow() +1) <=8) {
			String testPosition = getColLetter(kPos.getColumn()+1)+Integer.toString(kPos.getRow()+1);
			//Is moving to this position allowed for the king
			position testPos = toIntPosition(testPosition);
			boolean canMove = false;
			if(isWhiteMove && board.get(testPos).getColor() != 'w')
				canMove = true;
			else if(!isWhiteMove && board.get(testPos).getColor() != 'b')
				canMove = true;
			
			//place king in new position for testing
			piece tempMovePiece = board.get(testPos);
			if(canMove)
				board.put(testPos, currKing);
			
			//check if now there is still a check
			if(canMove && !isCheck(testPosition, isWhiteMove, numMove)) {
				board.put(kPos, currKing);
				board.put(testPos, tempMovePiece);
				return false;
			}
			//otherwise place king back where it came from
			board.put(testPos, tempMovePiece);
		}
		if((kPos.getColumn()+1) <= 8 && (kPos.getRow() -1) >=1) {
			String testPosition = getColLetter(kPos.getColumn()+1)+Integer.toString(kPos.getRow()-1);
			//Is moving to this position allowed for the king
			position testPos = toIntPosition(testPosition);
			boolean canMove = false;
			if(isWhiteMove && board.get(testPos).getColor() != 'w')
				canMove = true;
			else if(!isWhiteMove && board.get(testPos).getColor() != 'b')
				canMove = true;
			
			//place king in new position for testing
			piece tempMovePiece = board.get(testPos);
			if(canMove)
				board.put(testPos, currKing);
			
			//check if now there is still a check
			if(canMove && !isCheck(testPosition, isWhiteMove, numMove)) {
				board.put(kPos, currKing);
				board.put(testPos, tempMovePiece);
				return false;
			}
			//otherwise place king back where it came from
			board.put(testPos, tempMovePiece);
		}
		if((kPos.getColumn()-1) >=1) {
			String testPosition = getColLetter(kPos.getColumn()-1)+Integer.toString(kPos.getRow());
			//Is moving to this position allowed for the king
			position testPos = toIntPosition(testPosition);
			boolean canMove = false;
			if(isWhiteMove && board.get(testPos).getColor() != 'w')
				canMove = true;
			else if(!isWhiteMove && board.get(testPos).getColor() != 'b')
				canMove = true;
			
			//place king in new position for testing
			piece tempMovePiece = board.get(testPos);
			if(canMove)
				board.put(testPos, currKing);
			
			//check if now there is still a check
			if(canMove && !isCheck(testPosition, isWhiteMove, numMove)) {
				board.put(kPos, currKing);
				board.put(testPos, tempMovePiece);
				return false;
			}
			//otherwise place king back where it came from
			board.put(testPos, tempMovePiece);
		}
		if((kPos.getColumn()-1) >= 1 && (kPos.getRow() +1) <=8) {
			String testPosition = getColLetter(kPos.getColumn()-1)+Integer.toString(kPos.getRow()+1);
			//Is moving to this position allowed for the king
			position testPos = toIntPosition(testPosition);
			boolean canMove = false;
			if(isWhiteMove && board.get(testPos).getColor() != 'w')
				canMove = true;
			else if(!isWhiteMove && board.get(testPos).getColor() != 'b')
				canMove = true;

			//place king in new position for testing
			piece tempMovePiece = board.get(testPos);
			if(canMove)
				board.put(testPos, currKing);
			
			//check if now there is still a check
			if(canMove && !isCheck(testPosition, isWhiteMove, numMove)) {
				board.put(kPos, currKing);
				board.put(testPos, tempMovePiece);
				return false;
			}
			//otherwise place king back where it came from
			board.put(testPos, tempMovePiece);
		}
		if((kPos.getColumn()-1) >= 1 && (kPos.getRow() -1) >=1) {
			String testPosition = getColLetter(kPos.getColumn()-1)+Integer.toString(kPos.getRow()-1);
			//Is moving to this position allowed for the king
			position testPos = toIntPosition(testPosition);
			boolean canMove = false;
			if(isWhiteMove && board.get(testPos).getColor() != 'w')
				canMove = true;
			else if(!isWhiteMove && board.get(testPos).getColor() != 'b')
				canMove = true;
			
			//place king in new position for testing
			piece tempMovePiece = board.get(testPos);
			if(canMove)
				board.put(testPos, currKing);
			
			//check if now there is still a check
			if(canMove && !isCheck(testPosition, isWhiteMove, numMove)) {
				board.put(kPos, currKing);
				board.put(testPos, tempMovePiece);
				return false;
			}
			//otherwise place king back where it came from
			board.put(testPos, tempMovePiece);
		}
		if((kPos.getRow()+1) <= 8) {
			String testPosition = getColLetter(kPos.getColumn())+Integer.toString(kPos.getRow()+1);
			//Is moving to this position allowed for the king
			position testPos = toIntPosition(testPosition);
			boolean canMove = false;
			if(isWhiteMove && board.get(testPos).getColor() != 'w')
				canMove = true;
			else if(!isWhiteMove && board.get(testPos).getColor() != 'b')
				canMove = true;
			
			//place king in new position for testing
			piece tempMovePiece = board.get(testPos);
			if(canMove)
				board.put(testPos, currKing);
			
			//check if now there is still a check
			if(canMove && !isCheck(testPosition, isWhiteMove, numMove)) {
				board.put(kPos, currKing);
				board.put(testPos, tempMovePiece);
				return false;
			}
			//otherwise place king back where it came from
			board.put(testPos, tempMovePiece);
		}
		if((kPos.getRow()-1) >= 1) {
			String testPosition = getColLetter(kPos.getColumn())+Integer.toString(kPos.getRow()-1);
			
			//Is moving to this position allowed for the king
			position testPos = toIntPosition(testPosition);
			boolean canMove = false;
			if(isWhiteMove && board.get(testPos).getColor() != 'w')
				canMove = true;
			else if(!isWhiteMove && board.get(testPos).getColor() != 'b')
				canMove = true;
			
			//place king in new position for testing
			piece tempMovePiece = board.get(testPos);
			if(canMove)
				board.put(testPos, currKing);
			
			//check if now there is still a check
			if(canMove && !isCheck(testPosition, isWhiteMove, numMove)) {
				board.put(kPos, currKing);
				board.put(testPos, tempMovePiece);
				return false;
			}
			//otherwise place king back where it came from
			board.put(testPos, tempMovePiece);
		}
		
		board.put(kPos, currKing);
		
		//check to see if your own piece can attack the piece putting the king in check
		piece oppPieces = null;
		position enemy = null;
		piece ownPieces = null;
		//check if black pieces can attack white king
		if(isWhiteMove) {
			//for all other black pieces, test if any of them can move to given position
			for (position opponentPiecePos: board.keySet()) {
				oppPieces = board.get(opponentPiecePos);
				if(oppPieces.getColor()=='b' && oppPieces.validMove(opponentPiecePos, kPos, isWhiteMove, numMove)) {
					enemy = opponentPiecePos;
					break;
				}
			}
			
			//test all other pieces to see if they can save the king
			for (position ownPiecePos: board.keySet()) {
				ownPieces = board.get(ownPiecePos);
				if ((ownPieces.getType()!='K' && ownPieces.getColor()=='w') && ownPieces.validMove(ownPiecePos, enemy, isWhiteMove, numMove))
					return false;
			}
		}
		
		//check if white pieces can attack black king
		else {
			//for all other white pieces, test if any of them can move to given position
			for (position opponentPiecePos: board.keySet()) {
				oppPieces = board.get(opponentPiecePos);
				if(oppPieces.getColor()=='w' && oppPieces.validMove(opponentPiecePos, kPos, isWhiteMove, numMove)) {
					enemy = opponentPiecePos;
					break;
				}
			}
			
			//test all other pieces to see if they can save the king
			for (position ownPiecePos: board.keySet()) {
				ownPieces = board.get(ownPiecePos);
				if ((ownPieces.getType()!='K' && ownPieces.getColor()=='b') && ownPieces.validMove(ownPiecePos, enemy, isWhiteMove, numMove))
					return false;
			}
		}
		
		return true;
	}
	
	/**
	 * kingPosition is a method to find the king of the specified color
	 * 
	 * @param isWhite tells us whose king we are searching for(if true, white; if false, black)
	 * 
	 * @return a string with the position of the king of the given color
	 */
	public static String kingPosition(boolean isWhite) {
		piece oppPieces = null;
		String retVal = "";
		
		//Find King
		for (position opponentPiecePos: board.keySet()) {
			oppPieces = board.get(opponentPiecePos);
			//if white
			if(isWhite) {
				if(oppPieces.getValue() == "wK")
					retVal = getColLetter(opponentPiecePos.getColumn()) + Integer.toString(opponentPiecePos.getRow());
			}
			//if black
			else {
				if(oppPieces.getValue() == "bK")
					retVal = getColLetter(opponentPiecePos.getColumn()) + Integer.toString(opponentPiecePos.getRow());
			}
		}
		
		return retVal;
	}
	
	/**
	 * isBlackBox tells us if the current box would be black or white if it were an emptySquare
	 * 
	 * @param row gives is the row number
	 * @param col gives is the column number
	 * 
	 * @return true if it is a black box, false otherwise
	 */
	public static boolean isBlackBox(int row, int col) {
		if(((row%2 == 1) && (col%2 == 1)) || ((row%2 == 0) && (col%2 == 0)))
			return false;
		return true;
	}
	
	/**
	 * getColInt is to find the corresponding integer to letter column field
	 * 
	 * @param col is the character of the column
	 * 
	 * @return an integer that corresponds with the letter of the column
	 */
	public static int getColInt(char col) {

		if(col == 'a')
			return 8;
		else if(col == 'b')
			return 7;
		else if(col == 'c')
			return 6;
		else if(col == 'd')
			return 5;
		else if(col == 'e')
			return 4;
		else if(col == 'f')
			return 3;
		else if(col == 'g')
			return 2;
		return 1;
	}
	
	/**
	 * getColLetter is to find the corresponding letter to the integer column field
	 * 
	 * @param colNum is the character of the column
	 * 
	 * @return a char that corresponds with the given integer of the column
	 */
	public static char getColLetter(int colNum) {
		if(colNum == 8)
			return 'a';
		else if(colNum == 7)
			return 'b';
		else if(colNum == 6)
			return 'c';
		else if(colNum == 5)
			return 'd';
		else if(colNum == 4)
			return 'e';
		else if(colNum == 3)
			return 'f';
		else if(colNum == 2)
			return 'g';
		return 'h';
	}
	
	/**
	 * toIntPosition gives the corresponding position object to the string location
	 * 
	 * @param loc is the string position
	 * 
	 * @return a position object that corresponds to the given string position
	 */
	public static position toIntPosition(String loc) {
		return (new position(Integer.parseInt(String.valueOf(loc.charAt(1))), getColInt(loc.charAt(0))));
	}
}
