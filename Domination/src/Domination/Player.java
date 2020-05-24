package Domination;
import java.util.Scanner;
public class Player{

    private static int count = 0;   
    Scanner s = new Scanner(System.in);
    String name;
    static int numArmies;
    private Territory land;
    
    public Player()
    {
        ++count;
        System.out.print("Enter player" + count + "'s name: ");
        name = s.nextLine();
    }

    /* Count the numbers of players playing */
    public int count() {
    	return count;
    }
    
    public String getName(){
        return name;
    }
    
    public void placeArmies(int armies,Territory territory) {
    	Player.numArmies = armies;
    }
    
    public int getArmies(Territory territory) {
    	return this.numArmies;
    }
    
    public void unplacedArmies(int armies) {
    	this.numArmies = armies;
    }
}