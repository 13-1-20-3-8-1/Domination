package Domination;

public class Territory {

	private int row,column;
	private int numArmies = 0;
	private Player owner ;
	private String name;
	
	public Territory( int y, int x) {
		this.row 	= x;
		this.column = y;
	}
	
	public void placeArmies(Player owner,int numArmies) {
		this.numArmies  = numArmies;
		this.owner 		= owner;
	}
	public void setName(Player owner) {
		this.name = owner.getName();
	}
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return column;
	}
	public String toString() {
		return "[" + this.row + "," + this.column + "]" + name + "(" + this.numArmies +") "; 
	}
}
