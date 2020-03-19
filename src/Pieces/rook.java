package Pieces;

import Chess.chess;
import Chess.position;

public class rook extends piece{
	
	public rook(String value) {
		super(value);
	}
	
	@Override
	public boolean validMove(position currPos, position newPos, boolean isWhite, int numTurn) {
		
		//calculate rowNumbers and column numbers
		int rowNum = currPos.getRow();
		int newRowNum = newPos.getRow();
		int colNum = currPos.getColumn();
		int newColNum = newPos.getColumn();
		
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
					
		return false;
	}
}
