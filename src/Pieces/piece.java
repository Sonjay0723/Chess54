package Pieces;

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
	
	public boolean validMove(position oldPos, position newPos, boolean isWhite) {
		return true;
	}
	
	public String toStringPos(position changePos) {
		return (Integer.toString(changePos.getColumn()) + Integer.toString(changePos.getRow()));
	}
}
