package Domination;

/* Remember: YOU WRITE CODE NOT ONLY FOR MACHINE (in this case, JVM)
             TO COMPILE, BUT ALSO FOR HUMAN TO READ.
             PLEASE MAKE SURE THAT OTHER HUMAN READER CAN UNDERSTAND
             WHAT YOU'RE TRYING TO COMMUNICATE!
 */

/* For this code file, you made the same mistakes I've mentioned in `Player.java`
 */

public class Territory {
	// All of these following fields are *encapsulated*
	private int column;
	private int row;
	private Player owner;
	private int armies;

	// Constructor
	public Territory(int col, int row) {
		this.column = col;
		this.row = row;
		this.armies = 0;
		this.owner = null;
	}

	// Getters and Setters
	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public int getArmies() {
		return armies;
	}

	public void setArmies(int armies) {
		this.armies = armies;
	}

	// Define String presentation for `Territory` object instance
	public String toString() {
		Player thisOwner = this.owner;
		return String.format(
				"[%d,%d]%s(%d) ",
				this.column,
				this.row,
				(thisOwner == null) ? "null" : thisOwner.getName(), // Confused? Please google "ternary operator"!
				this.armies
		);
	}

	/* To tell the truth, using the same method name with `World` in this specific case
       is seriously a bad practice. It tells nothing about what the coder intends to do
       with this method. However, we're just trying to follow the instruction.
     */
	public void placeArmies(Player owner, int armies) {
        /* Notes: This will implement the logic without any visible input/output console actions.
           • If this territory is already occupied by other player, then do nothing;
           • If the number of armies is negative, then do nothing;
           • Otherwise, mobilise the requested number of armies to the target territory
               and confirm the player's ownership.
         */
		if (this.getOwner().equals(owner) || this.getOwner().equals(null)) {
			if (armies > 0) {
				if (this.armies == 0) owner.gain1Territory();
				this.setOwner(owner);
				this.setArmies(armies);
				owner.setUnplacedArmies(owner.getUnplacedArmies() - armies);
			}
		} // in all other `else` cases { do nothing; }
	}

	public void getAttacked () {
		if (this.armies <= 1) {
			this.getOwner().lose1Territory();
			this.setOwner(null);
		}
		this.armies -= 1;
	}
}

