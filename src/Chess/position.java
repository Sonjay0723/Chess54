package Chess;

/**
 * @author Dhrishti Hazari
 * @author Jayson Pitta
 */

import java.util.Objects;

/**
 * position is a class that defines the position of every one of the objects, with an int row and int column
 */
public class position {
	int row;
	int col;
	
	/**
	 * Constructor for position class
	 * 
	 * @param row is integer of row
	 * @param col is integer of column
	 */
	public position(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/**
	 * getRow gives the row of this position
	 * 
	 * @return row integer
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * getColumn gives the column of this position
	 * 
	 * @return column integer
	 */
	public int getColumn() {
		return col;
	}
	
	/**
	 * setRow sets this position's row to a new value
	 * 
	 * @param row is the integer to set the row value to
	 * 
	 * Has no return value
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * setCol sets this position's col to a new value
	 * 
	 * @param col is the integer to set the column value to
	 * 
	 * Has no return value
	 */
	public void setCol(int col) {
		this.col = col;
	}
	
	/**
	 * toStringPos converts a Object position to a String position
	 * There is no input parameter
	 * 
	 * @return a string position corresponding to the object position
	 */
	public String toStringPos() {
		return (String.valueOf(getColLetter(this.getColumn())).concat(Integer.toString(this.getRow())));
	}
	
	/**
	 * getColLetter is to find the corresponding letter to the integer column field
	 * 
	 * @param colNum is the character of the column
	 * 
	 * @return a char that corresponds with the given integer of the column
	 */
	public static char getColLetter(int colNum) {
		if(colNum == 8)
			return 'a';
		else if(colNum == 7)
			return 'b';
		else if(colNum == 6)
			return 'c';
		else if(colNum == 5)
			return 'd';
		else if(colNum == 4)
			return 'e';
		else if(colNum == 3)
			return 'f';
		else if(colNum == 2)
			return 'g';
		return 'h';
	}
	
	/**
	 * Overrides Object equal method so that it searches based on value rather than memory address
	 */
	 @Override
	    public boolean equals(Object obj) {
	       if (!(obj instanceof position))
	            return false;
	        if (obj == this)
	            return true;
	        position pos = (position)obj;
	        return(pos.col == this.col && pos.row == this.row);
	    }
	 
	 /**
	  * Overrides hashCode so that new memory location isn't created if it has same row+column as another already existing position in a memory location
	  */
	 @Override
	 public int hashCode() {
		 return Objects.hash(row,col);
	 }
}
