package Domination;
/* Remember: YOU WRITE CODE NOT ONLY FOR MACHINE (in this case, JVM)
             TO COMPILE, BUT ALSO FOR HUMAN TO READ.
             PLEASE MAKE SURE THAT OTHER HUMAN READER CAN UNDERSTAND
             WHAT YOU'RE TRYING TO COMMUNICATE!
*/

// `import java.util.Scanner;` is unneccesary
import Domination.World;

public class Player {
    /* `static` field means that this field belong to the class itself
        not belong exclusively to any of its object instances
     */
    private static int count = 0;
    /* `private Scanner s = new Scanner(System.in);` is unnecesary
        just use `Scanner` instance defined as
       `keyboard` static field in `Domination.World` class
     */

    /* If the `name` field is not `private`,
           what the point of defining setter and getter for `name`?
           When `name` field is public:
              to get the name, just simply do `playerObject.name`
              to changet the name, just do `playerObject.name = newName;`
         */
    private String name;

    /* `static int numArmies` is terribly wrong because each of the
       `Domination.Player` object will have a `numArmies` field independent to that of each other
     */
    private int unplacedArmies;

    /* `private Domination.Territory land;`
       is logically wrong, because a player can own an `array` of territories
     */

    /* Additional field for task 4 of part B
     */
    private int turn = 0;
    private int territories = 0;

    //  CONSTRUCTOR
    public Player()
    {
        /* ```++count;
              System.out.print("Enter player" + count + "'s name: ");
           ```
           is not cool.
           Lemme show you what's good!
        */
        System.out.print(String.format("Enter player%s\'s name: ", ++count));
        /* Why in this case omitting a step is acceptable?
           Because reader of your code can still understand your intention
         */

        /* Because `keyboard` is a static member of class `World`, you don't need to:
           • Encapsulate `keyboard`, which means that you don't need to define getters & setters for `keyboard`
           • Define an instance of `World` to evoke `keyboard`. To be detailed, if `keyboard` is public but not static:
                ``` <In World.java> ```
                public class World {
                    (...)
                    public Scanner keyboard = new Scanner(System.in);
                    (...)
                }

                ```<In Player.java>```
                public class Player {
                    (...)
                    public Player() {
                        (...)
                        World wolrdSingleton = new World();
                        name = worldSingleton.keyboard.nextLine();
                    }
                    (...)
                }
         */
        this.name = World.keyboard.next();
    }

    /* Tell me dude, why the fuck do you need a getter for a publicly static field? :)
       `public int count() { return count; }`
     */

    /* SETTER DOESN'T DO THE JOB OF `placeArmies` (that how normally people think about it!!!);
       even if it does, what you do here is really confusing!!!
     `public void placeArmies(int armies, Domination.Territory territory) { Domination.Player.numArmies = armies;}`
     */

    /* PLEASE USE MEANINGFUL NAMES FOR YOUR VARIABLES/METHOD SIGNATURES!!!
       BE CLEAR WITH YOUR INTENTION!!!
       `public void unplacedArmies(int armies) { this.numArmies = armies; }`
     */

    /* HERE COMES ALL THE MOTHERFUCKER GETTERS AND SETTERS */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnplacedArmies() {
        return unplacedArmies;
    }

    public void setUnplacedArmies(int numArmies) {
        this.unplacedArmies = numArmies;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getTerritories() {
        return territories;
    }

    public void setTerritories(int territories) {
        this.territories = territories;
    }

    /*  Why do you all of these motherfuckers when they seems useless?
        The only keywords: ENCAPSULATION.
        For example, if you have to write another classes for `Monkey` and `Zoo`,
        these things have nothing to do with `Domination.Player` and `Domination.World`.
        For that reason, getters and setters will make you remember that do not accidentally
        evoke any `playerObject.getName()` or `playerObject.setName()` when you're building the zoo.

        P/S: But to be honest, getters and setters just exist to give you existential crisis.
             We will discuss this later when you get to the level of a human coder.
             At this moment, you're just a monkey coder, bro!
     */

    /* After having all getters and setters defined,
       Let's implement `placeArmies` and `toString`
     */
    public String toString() {
        return this.name; // or `return this.getName();`
    }

    // To compare instances of the same class, we need a definition of `equals` method
    public boolean equals (Player that) {
        return that.getName().equals(this.name);
    }

    /* To tell the truth, using the same method name with `World` in this specific case
       is seriously a bad practice. It tells nothing about what the coder intends to do
       with this method. However, we're just trying to follow the instruction.
     */
    public void placeArmies (int armies, Territory territory) {
        /* Notes: This will implement the logic without any visible input/output console actions.
           • If this territory is already occupied by other player, then do nothing;
           • If the number of armies is negative, then do nothing;
           • Otherwise, mobilise the requested number of armies to the target territory
               and confirm the player's ownership.
         */
        if (territory.getOwner().equals(this) || territory.getOwner().equals(null)) {
            if (armies > 0) {
                if (territory.getArmies() == 0) this.gain1Territory();
                territory.setOwner(this);
                territory.setArmies(armies);
                this.unplacedArmies -= armies;
            }
        } // in all other `else` cases { do nothing; }
    }

    public void lose1Territory () {
        this.territories -= 1;
    }
    public void gain1Territory () {
        this.territories += 1;
    }
}