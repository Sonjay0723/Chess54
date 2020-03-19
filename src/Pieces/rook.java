package Pieces;

import Chess.position;

public class rook extends piece{
	
	public rook(String value) {
		super(value);
	}
	
	@Override
	public boolean validMove(position currPos, position newPos, boolean isWhite) {
		
		//calculate rowNumbers and column numbers
		int rowNum = currPos.getRow();
		int newRowNum = newPos.getRow();
		int colNum = currPos.getColumn();
		int newColNum = newPos.getColumn();
		
		//If moving straight along row, not in same position
		if(Math.abs(rowNum - newRowNum)!=0 && Math.abs(colNum - newColNum)==0 && !currPos.equals(newPos))
			return true;
		//If moving straight along column, not in same position
		else if(Math.abs(rowNum - newRowNum)==0 && Math.abs(colNum - newColNum)!=0 && !currPos.equals(newPos))
			return true;
					
		return false;
	}
}
