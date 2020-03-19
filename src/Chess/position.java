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
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public String toStringPos() {
		return (String.valueOf(getColLetter(this.getColumn())).concat(Integer.toString(this.getRow())));
	}
	
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
