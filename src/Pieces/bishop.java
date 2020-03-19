package Pieces;

import Chess.chess;
import Chess.position;

public class bishop extends piece{

	public bishop(String value) {
		super(value);
	}
	
	@Override
	public boolean validMove(position currPos, position newPos, boolean isWhite, int numTurn) {
		//calculate rowNumbers and column numbers
		int rowNum = currPos.getRow();
		int newRowNum = newPos.getRow();
		int colNum = currPos.getColumn();
		int newColNum = newPos.getColumn();
		
		//check if move valid(diagonal only, cannot move from old position->same old position)
		if(Math.abs(rowNum - newRowNum) == Math.abs (colNum - newColNum) && !currPos.equals(newPos))
				return true;
		
		return false;
	}
}
