package Pieces;

import Chess.chess;
import Chess.position;

public class pawn extends piece{
	
	public pawn(String value) {
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
		
		//cannot move backwards
		if(isWhite && newRowNum-rowNum<0)
			return false;
		else if(!isWhite && rowNum-newRowNum<0)
			return false;
		
		//if Diagonal
		if(Math.abs(rowNum-newRowNum)==1 && Math.abs(colNum-newColNum)==1) {
			//for kill?
			if(isWhite && chess.board.get(newPos).getColor()=='b')
				return true;
			else if(!isWhite && chess.board.get(newPos).getColor()=='w')
				return true;
			//for EnPassant?
			else if(!isWhite && (chess.board.get(newPos) instanceof emptySquare || chess.board.get(newPos).getColor() == 'w') && chess.isEnPassant(currPos.toStringPos(), newPos.toStringPos(),isWhite, numTurn))
				return true;
			else if(isWhite && ((chess.board.get(newPos) instanceof emptySquare) || chess.board.get(newPos).getColor() == 'b') && chess.isEnPassant(currPos.toStringPos(), newPos.toStringPos(),isWhite, numTurn))
				return true;
		}
		//if 2 steps ahead, MUST BE FIRST MOVE
		else if(Math.abs(rowNum-newRowNum)==2 && Math.abs(colNum-newColNum)==0 && chess.board.get(newPos) instanceof emptySquare) {
			if(chess.board.get(currPos).getMovement()==0)
				return true;
		}
		//otherwise only one step ahead
		else if(Math.abs(rowNum-newRowNum)==1 && Math.abs(colNum-newColNum)==0 && chess.board.get(newPos) instanceof emptySquare)
			return true;
		
		return false;
	}
}
