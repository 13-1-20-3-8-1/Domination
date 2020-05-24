package Domination;
/* Remember: YOU WRITE CODE NOT ONLY FOR MACHINE (in this case, JVM)
             TO COMPILE, BUT ALSO FOR HUMAN TO READ.
             PLEASE MAKE SURE THAT OTHER HUMAN READER CAN UNDERSTAND
             WHAT YOU'RE TRYING TO COMMUNICATE!
*/

import Domination.Player;
import Domination.Territory;

import java.util.Scanner;

public class World
{
	public static Scanner keyboard = new Scanner(System.in);
    
	private static Player player1;
	private static Player player2;
	
	private static Territory territory1;   
	private static Territory territory2;	
	private static Territory territory3;	
	private static Territory territory4;	
	
	public World() {
	    this.player1 	= 	new Player();
	    this.player2 	= 	new Player();
	    
	    this.territory1 =	new Territory(0,0);
	    this.territory2 =	new Territory(0,1);
	    this.territory3 =	new Territory(1,0);
	    this.territory4 =	new Territory(1,1);
	}

	public Territory findTerritory (int coordinateCol, int coordinateRow) {
		Territory res = null;
		if (coordinateCol == 0) {
			if (coordinateRow == 0) { res = territory1; }
			if (coordinateRow == 1) { res = territory2; }
		}
		if (coordinateCol == 1) {
			if (coordinateRow == 0) { res = territory3; }
			if (coordinateRow == 1) { res = territory4; }
		}
		return res;
	}

	public String toString() {
		return String.format("%s %s %s %s", territory1, territory2, territory3, territory4);
	}

	public void placeArmies(Player player) {
		// DO NOT OMIT STEPS
		// it's not cool to do so because it will confuse readers of your code

		// The Assessment force us to give player 3 unplaced armies in the beginning
		player.setUnplacedArmies(3);
		System.out.println(
			String.format("You have %d armies to place.", player.getUnplacedArmies())
		);
		System.out.println(this);

		// If this player still have armies not used yet, the loop will not end
		while (player.getUnplacedArmies() > 0) {
			System.out.print("Select a territory: ");
			int inputCoordinateCol = keyboard.nextInt();
			int inputCoordinateRow = keyboard.nextInt();

			/* In the scenario of this assessment, I assume that all inputs are valid,
			   which means that `inputCoordinateCol` & `inputCoordinateRow` will be either 0 or 1
			 */

			// Remember that `placeArmies` we defined in class Player?
			player.placeArmies(
				1,
				this.findTerritory(inputCoordinateCol, inputCoordinateRow)
			);
			System.out.println(this);
		}
	}

	public void transfer (Player player) {
		int colOriginal, colDestination, rowOriginal, rowDestination, originalArmies;
		Territory originalTerritory, destinationTerritory;

		System.out.println("Select source/target territories for a transfer.");
		System.out.println(this);
		System.out.print("Select a territory: ");
		colOriginal = keyboard.nextInt();
		while (colOriginal != -1) {
			rowOriginal = keyboard.nextInt();
			System.out.print("Select a territory: ");
			colDestination = keyboard.nextInt();
			rowDestination = keyboard.nextInt();

			originalTerritory = findTerritory(colOriginal, rowOriginal);
			originalArmies = originalTerritory.getArmies();
			destinationTerritory = findTerritory(colDestination, rowDestination);

			// if this player doesn't own the requested territory, just ignore the request
			if (originalTerritory.getOwner().equals(player)) {
				player.placeArmies(1, destinationTerritory);
				if (originalArmies == 1) {
					originalTerritory.setOwner(null);
				}
				originalTerritory.setArmies(originalArmies - 1);
				System.out.println(this);
			}
		}
	}

	public void attack (Player player) {
		int colOriginal, colDestination, rowOriginal, rowDestination;
		Territory originalTerritory = null, destinationTerritory, tmp;
		boolean isFirstTime = true;

		System.out.println("Select source/target territories for an attack.");
		System.out.println(this);
		System.out.print("Select a territory: ");
		colOriginal = keyboard.nextInt();
		while (colOriginal != -1) {
			rowOriginal = keyboard.nextInt();
			tmp = findTerritory(colOriginal, rowOriginal);

			if ((originalTerritory != null) && (!tmp.getOwner().equals(player))) {
				destinationTerritory = tmp;
				originalTerritory.getAttacked();
				destinationTerritory.getAttacked();
				break;
			}

			originalTerritory = tmp;
			// in the very first time of this loop,
			// if this player owns this territory, this loop is skipped
			while (!originalTerritory.getOwner().equals(player) && isFirstTime) {
				System.out.print("Select a territory: ");
				colOriginal = keyboard.nextInt();
				rowOriginal = keyboard.nextInt();
				originalTerritory = findTerritory(colOriginal, rowOriginal);
			}

			isFirstTime = false;

			System.out.print("Select a territory: ");
			colDestination = keyboard.nextInt();
			rowDestination = keyboard.nextInt();
			destinationTerritory = findTerritory(colDestination, rowDestination);

			if (!destinationTerritory.getOwner().equals(player)) {
				originalTerritory.getAttacked();
				destinationTerritory.getAttacked();
			}

			System.out.println(this);
		}
	}

	public void turn (Player player) {
		System.out.println(String.format("%s\'s turn", player.getName()));

		player.setUnplacedArmies(
			player.getUnplacedArmies() + player.getTerritories()
		);
		player.setTurn(player.getTurn() + 1);

		if (player.getTurn() == 1) {
			placeArmies(player);
		} else {
			placeArmies(player);
			attack(player);
			if (player.getTerritories() > 0) {
				transfer(player);
			}
		}
	}

	public static void main(String[] args) {
		World thisWorld = new World();
		while (
			(thisWorld.player1.getTerritories() < 4)
		 || (thisWorld.player2.getTerritories() < 4)
		) {
			thisWorld.turn(player1);
			thisWorld.turn(player2);
		}

		if (thisWorld.player1.getTerritories() == 4) {
			System.out.println(String.format("%s wins!", player1.getName()));
		} else {
			System.out.println(String.format("%s wins!", player2.getName()));
		}
	}
}