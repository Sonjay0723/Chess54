package Chess;

import java.util.Objects;

public class position {
	int row;
	int col;
	
	public position(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return col;
	}
	
	public char getColLetter() {
		if(col == 1)
			return 'a';
		else if(col == 2)
			return 'b';
		else if(col == 3)
			return 'c';
		else if(col == 4)
			return 'd';
		else if(col == 5)
			return 'e';
		else if(col == 6)
			return 'f';
		else if(col == 7)
			return 'g';
		return 'h';
	}
	
	public String getStrPos() {
		return (Character.toString(getColLetter())+Integer.toString(row));
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	 @Override
	    public boolean equals(Object obj) {
	       if (!(obj instanceof position))
	            return false;
	        if (obj == this)
	            return true;
	        position pos = (position)obj;
	        return(pos.col == this.col && pos.row == this.row);
	    }
	 
	 @Override
	 public int hashCode() {
		 return Objects.hash(row,col);
	 }
}
