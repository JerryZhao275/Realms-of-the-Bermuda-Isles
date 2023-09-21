public class NPC extends Entity{
    /**
     * Public constructor to initialize the NPC instance.
     * It creates a new name, x-axis coordinate, and y-axis coordinate for the NPC.
     */
    public NPC(String name, int x, int y) {
        super(name, x, y);
    }

    // This is the initialisation of a java class in a source folder
    public void talk() {
        System.out.println("Hello, adventurer!");
    }
}
