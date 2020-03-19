package Pieces;

import Chess.chess;
import Chess.position;

/**
 * bishop is a piece and extends the superclass piece
 * @author Dhrishti Hazari
 * @author Jayson Pitta
 */
public class bishop extends piece{

	public bishop(String value) {
		super(value);
	}
	
	/**
	 * validMove overrides super class's validMove to check if move is valid for bishop
	 * it can only move diagonally
	 * makes sure it does not jump over other pieces in the path
	 * 
	 * @param currPos is the current position of the bishop
	 * @param newPos is the position the bishop wants to move to
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
		
		//check if move valid(diagonal only, cannot move from old position->same old position)
		if(Math.abs(rowNum - newRowNum) == Math.abs (colNum - newColNum) && !currPos.equals(newPos))
				return true;
		
		return false;
	}
}
