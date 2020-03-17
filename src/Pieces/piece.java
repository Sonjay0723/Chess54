package Pieces;

import Chess.chess;
import Chess.position;

public abstract class piece {
	
	String value;
	int priorMove;
	
	public piece(String value) {
		this.value = value;
		priorMove = 0;
	}

	public String getValue() {
		return this.value;
	}
	
	public char getType() {
		return value.charAt(1);
	}
	
	public char getColor() {
		return value.charAt(0);
	}
	
	public int getMovement() {
		return this.priorMove;
	}
	
	public void setMovement(int moved) {
		this.priorMove = moved;
	}
	
	public boolean validMove(position oldPos, position newPos) {
		//if out of bounds movement
		if(newPos.getColumn() > 8 || newPos.getColumn() < 1 || newPos.getRow() > 1 || newPos.getRow() < 8)
			return false;
		//if trying to take over same color
		if(!(chess.board.get(newPos) instanceof emptySquare)) {
			if(chess.board.get(oldPos).getValue().charAt(0) == chess.board.get(newPos).getValue().charAt(0))
				return false;
		}
		return true;
	}
}
