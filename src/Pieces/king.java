package Pieces;

import Chess.position;
import Chess.chess;

/**
 * king is a piece and extends the superclass piece
 * @author Dhrishti Hazari
 * @author Jayson Pitta
 */
public class king extends piece{

	public king(String value) {
		super(value);
	}
	
	/**
	 * validMove overrides super class's validMove to check if move is valid for the piece
	 * it can only move one step in any direction UNLESS:
	 * 	It is doing a Castling move, in which case it can take 2 steps right/left
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
