import java.util.List;
import java.util.Scanner;

/**
 * This class represents the game engine for a text-based RPG.
 * It handles user input, game progression, and interactions with respect to a singleton design pattern.
 *
 * @author Hsuan-Chu Shih
 * @author Jerry Zhao
 */
public class GameEngine {
    private static GameEngine instance;
    private Scanner scanner;
    private Map map;
    private Inventory inventory;

//    xPosition and yPosition can be changed when implementing the proper map and movement.
    private int xPosition;
    private int yPosition;

    /**
     * Private constructor to initialize the GameEngine instance.
     * It creates a new map, inventory, and scanner for user input.
     */
    private GameEngine() {
        map = new Map();
        inventory = new Inventory();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Get the singleton instance of the GameEngine class.
     *
     * @return The GameEngine instance.
     */
    public static GameEngine getInstance() {
        if (instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }
//Temporary main class for testing, can be removed.
    public static void main(String[] args) {
        GameEngine gameEngine = getInstance();
        gameEngine.startGame();
    }

    /**
     * startGame() starts the user's playthrough of the game.
     *
     * @author Jerry Zhao
     */
    public void startGame() {
        System.out.println("Type 'help' for a list of commands.");
        System.out.println("Some plotline and story for our game");

        boolean isGameOver = false;
        while (!isGameOver) {
            System.out.print("> ");
            String input = scanner.nextLine();
            input = input.toLowerCase(); // Convert input to lowercase for case-insensitivity.

            switch (input) {
                case "help" -> displayCommands();
                case "quit" -> {
                    isGameOver = true;
                    System.out.println("Thanks for playing!");
                }
                case "inventory" -> displayInventory();
                case "take" -> takeItem();
                case "use" -> useItem();
                case "attack" -> {
                    // More logic for keyword followed after attack, i.e., "attack goblin" will attack goblin,
                    // but if no goblin exists then return 'That is not a valid action!'
                    System.out.println("Attack");
                }
                case "move" -> {
                    System.out.println("Which direction do you want to move? : ");
                    System.out.print("> ");
                    String nextLine = scanner.nextLine();
                    nextLine = nextLine.toLowerCase();
                    boolean flag = false;
                    switch (nextLine) {
                        case "forward" -> {flag = move(Direction.Forward);}
                        case "backward" -> {flag = move(Direction.Backward);}
                        case "left" -> {flag = move(Direction.Left);}
                        case "right" -> {flag = move(Direction.Right);}
                    }
                    // More logic for keyword followed after move, i.e., "move forward" will move forward,
                    // but if the player is at the edge of our map, then return invalid'
                    if (flag) {System.out.println("Move " + nextLine);}
                    else {System.out.println("Move command is invalid");}
                }
                case "talk" -> {
                    // Similar logic with attack, but instead with talking to NPCs
                        System.out.print("Who do you want to talk to? ");
                        String npcName = scanner.nextLine().toLowerCase(); //
                        NPC npc = getNPCAtPosition(xPosition, yPosition, npcName);
                        if (npc != null) {
                            talkToNPC(npc);
                        } else {
                            System.out.println("There is no " + npcName + " here to talk to.");
                        }

                }
//                case "take" -> {
//                    Item item = getItemAtPosition(xPosition,yPosition);
//                    if (item != null) {
//                        interactWithItem(item);
//                        System.out.println("Took a " + item.getName());
//                    }
//                    else {System.out.println("Cannot take item here");}
//
//                }
//                case "use" -> {
//                    System.out.println("Which item do you want to use: ");
//                    List<Item> items = inventory.getItems();
//                    for (Item item : items) {
//                        System.out.println(item.getName());
//                    }
//                    System.out.print("> ");
//                    String itemName = scanner.nextLine();
//                    Item selectedItem = inventory.getItem(itemName);
//                    if (selectedItem != null) {
//                        switch (selectedItem.getName()) {
//                            case "potion" -> {
//                                System.out.println("You used a potion");
//                                inventory.removeItem(selectedItem);
//                            }
//                            case "gold" -> System.out.println("You look at the gold in your inventory " +
//                                    "and wonder what purpose it might have.");
//                            }
//                        }
//                    else {System.out.println("Invalid item name");}
//                    }

                // Add more commands such as save and load later
                default -> System.out.println("Please enter a valid command or type help to see the commands.");
            }
        }
        scanner.close();
    }

    /**
     * Commands printed to the user if desired
     */
    private void displayCommands() {
        // Implement a method to display a list of available commands.
        System.out.println("Available commands:");
        System.out.println("help - Display this help message");
        System.out.println("quit - Quit the game");
        System.out.println("move - Move your character to a new position");
        if (inventory.getItems().size() > 0) {System.out.println("use - Use an item in your inventory");}
        if (getItemAtPosition(xPosition,yPosition) != null) {System.out.println("take - take item at current position");}
        // Add more commands as needed.
    }

    /**
     * moves the user in the input direction
     *
     * @returns True if the movement was successful, false if the movement was unsuccessful.
     * @author Sam Powell
     */
    public boolean move(Direction direction) {
        if (direction == Direction.Left) {
            if (isValidPosition(xPosition -1,yPosition)) {xPosition -=1; return true;}
            else {return false;}
        }
        if (direction == Direction.Right) {
            if (isValidPosition(xPosition+1,yPosition)) {xPosition +=1; return true;}
            else {return false;}
        }
        if (direction == Direction.Forward) {
            if (isValidPosition(xPosition,yPosition+1)) {yPosition +=1; return true;}
            else {return false;}
        }
        if (direction == Direction.Backward) {
            if (isValidPosition(xPosition,yPosition-1)) {yPosition -=1; return true;}
            else {return false;}
        }
        return false;
    }

    /**
     * asserts whether the coordinates given are a valid position of the game map,
     * including the position of NPCs and enemies.
     *
     * @returns True if the given coordinates are a valid position for the player to move to, false otherwise.
     * @author Sam Powell
     */
    public boolean isValidPosition(int x, int y) {
//        Position in bounds of map
//        Can change variables to official map bounds later.
        int mapLeftEdge = 0;
        int mapRightEdge = 1;
        int mapTopEdge = 1;
        int mapBottomEdge = 0;
        if (x < mapLeftEdge || y < mapBottomEdge || x > mapRightEdge || y > mapTopEdge) {return false;}
        return true;
    }

    public void fightEnemy(Enemy enemy) {
        enemy.talk();
    }

    public void talkToNPC(NPC npc) {
        npc.talk();
    }

    /**
     *
     * @param item
     * Adds item to players inventory
     * @author Sam Powell
     */
    public void interactWithItem(Item item) {
        inventory.addItem(item);
    }

    /**
     * @param x
     * @param y
     *
     * @returns The item at the players current position, or null if there is no item at the players position
     */
    public Item getItemAtPosition(int x, int y) {
//        placeholder until map intergration
        return new Item("potion",-1,-1);
    }


    private void displayInventory() {
        List<Item> items = inventory.getItems();
        if (items.isEmpty()) {
            System.out.println("Your inventory is empty.");
            return;
        }

        System.out.println("Your inventory contains:");
        for (Item item : items) {
            System.out.println("- " + item.getName());
        }
    }

    private void takeItem() {
        Item item = getItemAtPosition(xPosition, yPosition);
        if (item != null) {
            inventory.addItem(item);
            System.out.println("You have taken the " + item.getName() + ".");
        } else {
            System.out.println("There's no item to take here.");
        }
    }

    private void useItem() {
        System.out.println("Which item do you want to use?");
        List<Item> items = inventory.getItems();
        for (Item item : items) {
            System.out.println("- " + item.getName());
        }
        System.out.print("> ");
        String itemName = scanner.nextLine();
        Item selectedItem = inventory.getItem(itemName);

        if (selectedItem != null) {
            // Logic for using the item
            switch (selectedItem.getName()) {
                case "potion":
                    System.out.println("You used a potion.");
                    inventory.removeItem(selectedItem);
                    break;
                // More logic for other items can be added
                default:
                    System.out.println("You looked at the " + selectedItem.getName() + " in your inventory.");
            }
        } else {
            System.out.println("You don't have an item named " + itemName + ".");
        }
    }


    /**
     * Get an NPC instance at the specified position.
     *
     * @param x       The x-coordinate of the NPC.
     * @param y       The y-coordinate of the NPC.
     * @param npcName The name of the NPC to look for.
     * @return If a matching NPC is found, return the corresponding NPC instance; otherwise, return null.
     */
    public NPC getNPCAtPosition(int x, int y, String npcName) {
        if (npcName.equals("blacksmith") && x == 1 && y == 1) {
            return new NPC("Blacksmith", x, y);
        }

        return null;
    }




}

