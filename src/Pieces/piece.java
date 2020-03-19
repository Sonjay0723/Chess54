package Pieces;

import Chess.chess;
import Chess.position;

/**
 * piece is an abstract class for all of the subclasses(knight, emptySquare, etc) to take from
 * @author Dhrishti Hazari
 * @author Jayson Pitta
 */
public abstract class piece {
	
	String value;
	int priorMove;
	int canEnPassant;
	
	/**
	 * Constructor of class
	 * @param value takes in a String value
	 * priorMove is an int that keeps track of how many movements the piece has previously made
	 * canEnPassant is an int to keep track of number of rounds and is meant for pawns to be able to tell if it is the very next round and they can EnPassant
	 * 
	 */
	public piece(String value) {
		this.value = value;
		priorMove = 0;
		canEnPassant = -1;
	}
	
	/**
	 * getValue gets the value of the piece
	 * has no input parameter
	 * 
	 * @return value of piece
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * getType gets the type of piece
	 * has no input parameter
	 * 
	 * @return char type of piece('p','R','Q',etc)
	 */
	public char getType() {
		return value.charAt(1);
	}
	
	/**
	 * getColor gets the color of piece
	 * has no input parameter
	 * 
	 * @return char color of piece('w' OR 'b')
	 */
	public char getColor() {
		return value.charAt(0);
	}
	
	/**
	 * getMovement gets the number of moves of the piece
	 * has no input parameter
	 * 
	 * @return an int for how many moves the piece has done
	 */
	public int getMovement() {
		return this.priorMove;
	}
	
	/**
	 *setMovement sets the number of moves of the piece to a specific int value
	 *
	 *@param moved is the int to set the movement of the piece to
	 * 
	 * has no return value
	 */
	public void setMovement(int moved) {
		this.priorMove = moved;
	}
	
	/**
	 * getCanEnPassant gets the getCanEnPassant value of the piece
	 * has no input parameter
	 * 
	 * @return an int for the getCanEnPassant value of the piece
	 */
	public int getCanEnPassant() {
		return this.canEnPassant;
	}
	
	/**
	 *setEnPassant sets the getCanEnPassant value of the piece to a specific int value
	 *
	 *@param value is the int to set the getCanEnPassant value of the piece to
	 * 
	 * has no return value
	 */
	public void setEnPassant(int value) {
		this.canEnPassant = value;
	}
	
	/**
	 * validMove is used by the subclasses to tell if the move of the specific piece is valid or not
	 * 
	 * @param currPos is the current position of the piece
	 * @param newPos is the position the piece wants to move to
	 * @param isWhite tells us whose turn it is(if true, white; if false, black)
	 * @param numTurn tells us how many rounds it has been
	 * 
	 * @return returns a boolean, true if the move is valid, false otherwise
	 */
	public boolean validMove(position currPos, position newPos, boolean isWhite, int numTurn) {
		return true;
	}
}
