package Pieces;

import Chess.chess;
import Chess.position;

/**
 * knight is a piece and extends the superclass piece
 * @author Dhrishti Hazari
 * @author Jayson Pitta
 */
public class knight extends piece{
	
	public knight(String value) {
		super(value);
	}

	/**
	 * validMove overrides super class's validMove to check if move is valid for the piece
	 * it can only move 2 steps vertical and 1 step horizontal OR
	 * 1 step vertical and 2 steps horizontal
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
		
		//cannot be in same position
		if(currPos.equals(newPos))
			return false;
		
		//all possible moves
		if(Math.abs(rowNum-newRowNum)==1 && Math.abs(colNum-newColNum)==2)
			return true;
		else if(Math.abs(rowNum-newRowNum)==2 && Math.abs(colNum-newColNum)==1)
			return true;
		
		return false;
	}
}
