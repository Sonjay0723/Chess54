package Pieces;

import Chess.position;
import Chess.chess;

public class king extends piece{

	public king(String value) {
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
				
		//If Castling
		if(Math.abs(newColNum-colNum)==2) {
			if(chess.isCastling(currPos.toStringPos(), newPos.toStringPos(), isWhite, numTurn))
				return true;
		}
		//If moved Diagonally
		else if(Math.abs(newColNum-colNum)==1 && Math.abs(newRowNum-rowNum)==1)
			return true;
		//if moved 1 along column
		else if(Math.abs(newColNum-colNum)==1 && Math.abs(newRowNum-rowNum)==0)
			return true;
		//if moved 1 along row
		else if(Math.abs(newColNum-colNum)==0 && Math.abs(newRowNum-rowNum)==1)
			return true;
		
		return false;
	}
	
}
