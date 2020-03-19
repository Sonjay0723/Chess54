package Pieces;

import Chess.chess;
import Chess.position;

/**
 * pawn is a piece and extends the superclass piece
 * @author Dhrishti Hazari
 * @author Jayson Pitta
 */
public class pawn extends piece{
	
	public pawn(String value) {
		super(value);
	}
	
	/**
	 * validMove overrides super class's validMove to check if move is valid for the piece
	 * it can only move one step ahead UNLESS:
	 * 	It is the first move, so can do 2 steps ahead
	 * 	It is going for a kill, so MUST BE DIAGONAL
	 * 	It is going for an EnPassant
	 * makes sure it does not jump over other pieces in the path
	 * 
	 * @param currPos is the current position of the piece
	 * @param newPos is the position the piece wants to move to
	 * @param isWhite tells us whose turn it is(if true, white; if false, black)
	 * @param numTurn tells us how many rounds it has been(necessary to tell for EnPassant, must be same as pawn's canEnPassant value)
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
		
		//cannot be in same position
		if(currPos.equals(newPos))
			return false;
		
		//cannot move backwards
		if(isWhite && newRowNum-rowNum<0)
			return false;
		else if(!isWhite && rowNum-newRowNum<0)
			return false;
		
		//check to make sure not taking over same color
		if(chess.board.get(currPos).getColor() == chess.board.get(newPos).getColor())
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
