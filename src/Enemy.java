import java.util.Objects;

public class Enemy extends Entity{
    /**
     * Public constructor to initialize the Enemy instance.
     * It creates a new name, x-axis coordinate, and y-axis coordinate for the Enemy.
     *
     * @author Kwong Yu Zhou
     * @author Hsuan Chu Shih
     */
    public Enemy(String name, int x, int y) {
        super(name, x, y);
    }

    /**
     * Initiates a battle with the player.
     */
    public void fight(GameEngine gameEngine, Map map, int row, int column, int HP) {
        Item weapon = gameEngine.getWeapon();
        if (weapon != null && weapon.canUse()) {
            weapon.use(gameEngine.getInventory());
            if (!Objects.equals(getName(), "boss")){
                map.removeEntity(row,column);
                System.out.println("# You have slayed the " + getName() + "!");
            }
        } else {
            System.out.println("# The " + getName() + "gains the upper hand in the fight!");
            HP--;
            System.out.println("# You lost 1 HP (you may need a weapon to defeat this enemy)");
            if (HP <= 0){
                System.out.println("# You have been defeated by the " + getName() + " !");
                System.out.println("## Game Over ##");
                gameEngine.gameOver();
            }
            System.out.println("You HP has been decrease to "+ HP +".");
        }
    }


    /**
     * Handles interaction and dialogue with the player.
     */
    public void talk() {
        switch (getName().toLowerCase()) {
            case "goblin":
                System.out.println("A puny traveler dares enter my territory? Face my wrath!");
                break;
            case "spider":
                System.out.println("Hssss... Another prey entangled in my web!");
                break;
            case "ogre":
                System.out.println("You smell... tasty! Prepare to be my meal!");
                break;
            case "boss":
                System.out.println("Here comes the boss!"); // feel free to change this line
                break;
            default:
                System.out.println("There is an enemy!");
                break;
        }
    }
}
