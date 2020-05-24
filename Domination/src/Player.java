// `import java.util.Scanner;` is unneccesary
import World;

public class Player {
    /* `static` field means that this field belong to the class itself
        not belong exclusively to any of its object instances
     */
    private static int count = 0;
    /* `private Scanner s = new Scanner(System.in);` is unnecesary
        just use `Scanner` instance defined as
       `keyboard` static field in `World` class
     */

    /* If the `name` field is not `private`,
           what the point of defining setter and getter for `name`?
           When `name` field is public:
              to get the name, just simply do `playerObject.name`
              to changet the name, just do `playerObject.name = newName;`
         */
    private String name;

    /* `static int numArmies` is terribly wrong because each of the
       `Player` object will have a `numArmies` field independent to that of each other
     */
    private int numArmies = 0;

    /* `private Territory land;`
       is logically wrong, because a player can own an `array` of territories
     */
    public Player()
    {
        /* ```++count;
              System.out.print("Enter player" + count + "'s name: ");
           ```
           is not cool.
           Lemme show you what's good!
        */
        System.out.println(String.format("Enter player%s\'s name: ", ++count));
        /* Why in this case omitting a step is acceptable?
           Because reader of your code can still understand your intention
         */

        /* Remember: YOU WRITE CODE NOT ONLY FOR MACHINE (in this case, JVM)
                     TO COMPILE, BUT ALSO FOR HUMAN TO READ.
                     PLEASE MAKE SURE THAT OTHER HUMAN READER CAN UNDERSTAND
                     WHAT YOU'RE TRYING TO COMMUNICATE!
         */
        name = World.keyboard.nextLine();
    }

    /* Tell me dude, why the fuck do you need a getter for a publicly static field? :)
       `public int count() { return count; }`
     */

    /* SETTER DOESN'T DO THE JOB OF `placeArmies` (that how normally people think about it!!!);
       even if it does, what you do here is really confusing!!!
     `public void placeArmies(int armies, Territory territory) { Player.numArmies = armies;}`
     */

    /* PLEASE USE MEANINGFUL NAMES FOR YOUR VARIABLES/METHOD SIGNATURES!!!
       BE CLEAR WITH YOUR INTENTION!!!
       `public void unplacedArmies(int armies) { this.numArmies = armies; }`
     */

    /* HERE COMES ALL THE MOTHERFUCKER GETTERS AND SETTERS */

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Player.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumArmies() {
        return numArmies;
    }

    public void setNumArmies(int numArmies) {
        this.numArmies = numArmies;
    }

    /*  Why do you all of these motherfuckers when they seems useless?
        The only keywords: ENCAPSULATION.
        For example, if you have to write another classes for `Monkey` and `Zoo`,
        these things have nothing to do with `Player` and `World`.
        For that reason, getters and setters will make you remember that do not accidentally
        evoke any `playerObject.getName()` or `playerObject.setName()` when you're building the zoo.

        P/S: But to be honest, getters and setters just exist to give you existential crisis.
             We will discuss this later when you get to the level of a human coder.
             At this moment, you're just a monkey coder, bro!
     */
}