package Domination;
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
	
	public String toString() {
		return territory1.toString() + territory2.toString() + territory3.toString() + territory4.toString();
	}
	
	public void playfirstRound() {
		System.out.println(player1.getName() + ", please place your armies");
		System.out.println(toString());
		System.out.print("How many armies would you like to place on "  + territory1.toString() + "?");
		this.placeArmies(player1, territory1);
		System.out.println(toString());
		System.out.print("How many armies would you like to place on " + territory2.toString()+ "?");
		this.placeArmies(player1, territory2);
		System.out.println(toString());
		System.out.println(player2.getName() + ", please place your armies");
		System.out.print("How many armies would you like to place on "  + territory3.toString() + "?");
		this.placeArmies(player2, territory3);
		System.out.println(toString());
		System.out.print("How many armies would you like to place on " + territory4.toString()+ "?");
		this.placeArmies(player2, territory4);
		System.out.println(toString()); 

	}
	public void placeArmies(Player player, Territory territory) {
			player.unplacedArmies(keyboard.nextInt());
			territory.placeArmies(player,player.getArmies(territory));
			territory.setName(player);
	}
	public void run() {
		
	}
}