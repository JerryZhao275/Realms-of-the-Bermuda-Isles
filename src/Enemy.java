import java.util.Objects;

/**
 * Represents Enemy.
 * This class represents interaction between the user and enemy by fighting or talking with them.
 * There are different kinds of enemies each with their unique dialogue.
 * </p>
 *
 * @author Sam Powell
 * @author Kwong Yu Zhou
 * @author Thomas Green
 * @author Jerry Zhao
 * @author Hsuan-Chu Shih
 */


public class Enemy extends Entity{
    /**
     * Public constructor to initialize the Enemy instance.
     * It creates a new name, x-axis coordinate, and y-axis coordinate for the Enemy.
     *
     * @author Kwong Yu Zhou
     * @author Hsuan Chu Shih
     *
     * @param name The name of the enemy (e.g., goblin, spider, ogre, boss).
     * @param x    The x-coordinate of the enemy on the map.
     * @param y    The y-coordinate of the enemy on the map.
     */
    public Enemy(String name, int x, int y) {
        super(name, x, y);
    }

    /**
     * Empty Constructor to allow JSON mapping
     *
     * @author Sam Powell
     */
    public Enemy() {}

    /**
     * Initiates a battle between the enemy and the player.
     * The outcome of the battle is determined by the player's current weapon and its usability.
     * If the player is equipped with a usable weapon, the enemy (except for the boss) can be defeated.
     * Otherwise, the player loses HP.
     *
     * @author Sam Powell
     * @author Kwong Yu Zhou
     * @author Thomas Green
     * @author Jerry Zhao
     * @author Hsuan-Chu Shih
     *
     * @param gameEngine The game engine.
     * @param map        The game map where enemies are located.
     * @param row        The row coordinate of the enemy on the map.
     * @param column     The column coordinate of the enemy on the map.
     * @param HP         The current HP of the player.
     */
    public void fight(GameEngine gameEngine, Map map, int row, int column, int HP) {
        Item weapon = gameEngine.returnWeapon();
        if (weapon != null && weapon.canUse()) {
            weapon.use(gameEngine.getInventory());
            if (!Objects.equals(getName(), "boss")){
                map.removeEntity(row,column);
                System.out.println("# You have slayed the " + getName() + "!");
            }
        } else {
            System.out.println("# The " + getName() + " gains the upper hand in the fight!");
            HP--;
            System.out.println("# You lost 1 HP (you may need a weapon to defeat this enemy)");
            if (HP <= 0){
                System.out.println("# You have been defeated by the " + getName() + " !");
                System.out.println("## Game Over ##");
                gameEngine.gameOver(null);
            }
            System.out.println("You HP has been decrease to "+ HP +".");
        }
    }


    /**
     * This method is to talk with different kinds of enemy based on type of enemy the player encounters.
     * Different kinds of enemy(e.g., goblin, spider, ogre, boss) will return different dialogue results.
     *
     * @author Kwong Yu Zhou
     * @author Hsuan-Chu Shih
     *
     * @return A string representing the dialogue between the user and the enemy
     */
    public String talk() {
        String response;
        switch (getName().toLowerCase()) {
            case "goblin":
                response = "A puny traveler dares enter my territory? Face my wrath!";
                break;
            case "spider":
                response = "Hssss... Another prey entangled in my web!";
                break;
            case "ogre":
                response = "You smell... tasty! Prepare to be my meal!";
                break;
            case "boss":
                response = "Impressive that you've come this far, but do you really think you can defeat me?"; // feel free to change this line
                break;
            default:
                response = "There is an enemy!";
                break;
        }
        return response;
    }
}
