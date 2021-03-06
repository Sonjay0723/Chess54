package Pieces;

import Chess.chess;
import Chess.position;

/**
 * rook is a piece and extends the superclass piece
 * @author Dhrishti Hazari
 * @author Jayson Pitta
 */
public class rook extends piece{
	
	public rook(String value) {
		super(value);
	}
	
	/**
	 * validMove overrides super class's validMove to check if move is valid for the piece
	 * it can move ONLY vertical or ONLY horizontal
	 * makes sure it does not jump over other pieces in the path
	 * 
	 * @param currPos is the current position of the piece
	 * @param newPos is the position the piece wants to move to
	 * @param isWhite tells us whose turn it is(if true, white; if false, black)
	 * @param numTurn tells us how many rounds it has been
	 * 
	 * @return returns a boolean, true if the move is valid, false otherwise
	 */
	@Override
	public boolean validMove(position currPos, position newPos, boolean isWhite, int numTurn) {
		
		//calculate rowNumbers and column numbers
		int rowNum = currPos.getRow();
		int newRowNum = newPos.getRow();
		int colNum = currPos.getColumn();
		int newColNum = newPos.getColumn();
		
		//check to make sure not taking over same color
		if(chess.board.get(currPos).getColor() == chess.board.get(newPos).getColor())
			return false;
		
		//If moving straight along row, not in same position
		if(Math.abs(rowNum - newRowNum)!=0 && Math.abs(colNum - newColNum)==0 && !currPos.equals(newPos)) {
			boolean up = (rowNum - newRowNum) < 0 ? true : false;
			
			if (up) {
				for (int i = rowNum + 1; i < newRowNum; i++) {
					if (!(chess.board.get(new position(i, colNum)) instanceof emptySquare))
						return false;
				}
			}
			else {
				for (int i = rowNum - 1; i > newRowNum; i--) {
					if (!(chess.board.get(new position(i, colNum)) instanceof emptySquare))
						return false;
				}
			}
			
			return true;
		}
		//If moving straight along column, not in same position
		else if(Math.abs(rowNum - newRowNum)==0 && Math.abs(colNum - newColNum)!=0 && !currPos.equals(newPos)) {
			boolean right = (colNum - newColNum) < 0 ? false : true;
			
			if (right) {
				for (int i = colNum - 1; i > newColNum; i--) {
					if (!(chess.board.get(new position(rowNum, i)) instanceof emptySquare))
						return false;
				}
			}
			else {
				for (int i = colNum + 1; i < newColNum; i++) {
					if (!(chess.board.get(new position(rowNum, i)) instanceof emptySquare))
						return false;
				}
			}
			
			return true;
		}
					
		return false;
	}
}
