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
		return value.charAt(0);
	}
	
	public char getColor() {
		return value.charAt(1);
	}
	
	public int getMovement() {
		return this.priorMove;
	}
	
	public void setMovement(int moved) {
		this.priorMove = moved;
	}
	
	public boolean possibleEmptyBox(int row, int column) {
		if(chess.board.get(new position(row,column)).getValue().equals("  ") || chess.board.get(new position(row,column)).getValue().equals("##")){
			return true;
		}
		return false;
	}
	
	public boolean validMove(position oldPos, position newPos) {
		return false;
	}
}
