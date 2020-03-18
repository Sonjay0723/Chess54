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
		return true;
	}
	
	public int getColInt(char col) {
		if(col == 'a')
			return 1;
		else if(col == 'b')
			return 2;
		else if(col == 'c')
			return 3;
		else if(col == 'd')
			return 4;
		else if(col == 'e')
			return 5;
		else if(col == 'f')
			return 6;
		else if(col == 'g')
			return 7;
		return 8;
	}
}
