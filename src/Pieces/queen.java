package Pieces;

import Chess.chess;
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
		if(Math.abs(rowNum-newRowNum)!=0 && Math.abs(colNum-newColNum)==0) {
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
		//if horizontal movement
		else if(Math.abs(rowNum-newRowNum)==0 && Math.abs(colNum-newColNum)!=0) {
			boolean right = (colNum - newColNum) < 0 ? false : true;
			
			if (right) {
				for (int i = colNum + 1; i < newColNum; i++) {
					if (!(chess.board.get(new position(rowNum, i)) instanceof emptySquare))
						return false;
				}
			}
			else {
				for (int i = colNum - 1; i > newColNum; i--) {
					if (!(chess.board.get(new position(rowNum, i)) instanceof emptySquare))
						return false;
				}
			}
			
			return true;
		}
		//if diagonal movement
		else if(Math.abs(rowNum-newRowNum) == Math.abs(colNum-newColNum)) {
			boolean up = (rowNum - newRowNum) < 0 ? true : false;
			boolean right = (colNum - newColNum) < 0 ? false : true;
			
			if (up) {
				for (int i = rowNum + 1; i < newRowNum; i++) {
					int j;
					
					if (!right)
						j = i - rowNum + colNum;
					else
						j = rowNum - i + colNum;
					
					if (!(chess.board.get(new position(i, j)) instanceof emptySquare))
						return false;
				}
			}
			else {
				for (int i = rowNum - 1; i > newRowNum; i--) {
					int j;
					
					if (!right)
						j = i - rowNum + colNum;
					else
						j = rowNum - i + colNum;
					
					if (!(chess.board.get(new position(i, j)) instanceof emptySquare))
						return false;
				}
			}
			
			return true;
		}
		
		return false;
	}
}
