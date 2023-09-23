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
    public void fight(GameEngine gameEngine) {
        if (gameEngine.playerHasWeapon()) {
            System.out.println("With your weapon, you slay the " + getName() + "!");
        } else {
            System.out.println(getName() + " overpowers you! Game Over!");
            System.exit(0); // Ends the game
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
            default:
                System.out.println("There is an enemy!");
                break;
        }
    }
}
