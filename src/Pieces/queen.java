package Pieces;

import Chess.position;

public class queen extends piece{

	public queen(String value) {
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
