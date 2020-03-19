package Pieces;

import Chess.position;

public class knight extends piece{
	
	public knight(String value) {
		super(value);
	}

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
		
		//all possible moves
		if(Math.abs(rowNum-newRowNum)==1 && Math.abs(colNum-newColNum)==2)
			return true;
		else if(Math.abs(rowNum-newRowNum)==2 && Math.abs(colNum-newColNum)==1)
			return true;
		
		return false;
	}
}
