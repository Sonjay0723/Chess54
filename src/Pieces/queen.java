package Pieces;

import Chess.chess;
import Chess.position;

/**
 * Queen is a piece and extends the superclass piece
 * @author Dhrishti Hazari
 * @author Jayson Pitta
 */
public class queen extends piece{

	public queen(String value) {
		super(value);
	}
	
	/**
	 * validMove overrides super class's validMove to check if move is valid for the piece
	 * it can move in any direction(vertical,horizontal, or diagonal)
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
		
		//cannot be in same position
		if(currPos.equals(newPos))
			return false;
		
		//check to make sure not taking over same color
		if(chess.board.get(currPos).getColor() == chess.board.get(newPos).getColor())
			return false;
		
		//if vertical movement
		if(Math.abs(rowNum-newRowNum)!=0 && Math.abs(colNum-newColNum)==0)
			return true;
		//if horizontal movement
		else if(Math.abs(rowNum-newRowNum)==0 && Math.abs(colNum-newColNum)!=0)
			return true;
		//if diagonal movement
		else if(Math.abs(rowNum-newRowNum) == Math.abs(colNum-newColNum))
			return true;
		
		return false;
	}
}
