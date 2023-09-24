import java.util.List;
import java.util.Scanner;

/**
 * This class represents the game engine for a text-based RPG.
 * It handles user input, game progression, and interactions with respect to a singleton design pattern.
 *
 * @author Hsuan-Chu Shih
 * @author Jerry Zhao
 * @author Sam Powell
 */
public class GameEngine {
    private static GameEngine instance;
    private Scanner scanner;
    private Map map;
    private Inventory inventory;

    private boolean playerHasWeapon = false;  // At the class level, after other fields (testing boolean)


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
        inventory.addItem(new Item("bow",-1,-1)); // Initial weapon
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


    /**
     * startGame() starts the user's playthrough of the game.
     *
     * @author Jerry Zhao
     * @author Sam Powell
     */
    public void startGame() {
        System.out.println("Type 'help' for a list of commands.");
        System.out.println("Some plotline and story for our game");

        boolean isGameOver = false;
        int prevXPosition = xPosition; // Store the previous positions
        int prevYPosition = yPosition;

        while (!isGameOver) {
            // Check if the player's position has changed and print new dialogue when entering a new area
            if (xPosition != prevXPosition || yPosition != prevYPosition) {
                switch (map.getEntityTypeAt(xPosition, yPosition)) {
                    case "Enemy" -> System.out.println("Insert dialogue where the player sees an enemy");
                    case "NPC" -> System.out.println("Insert dialogue where the player sees an NPC");
                    case "Item" -> System.out.println("Insert dialogue where the player sees an item");
                }
                prevXPosition = xPosition;
                prevYPosition = yPosition;
            }

            System.out.print("> ");
            String input = scanner.nextLine();
            input = input.toLowerCase().trim(); // Convert input to lowercase for case-insensitivity.
            switch (input) {
                case "help" -> displayCommands();
                case "quit" -> {
                    isGameOver = true;
                    System.out.println("Thanks for playing!");
                }
                case "inventory" -> displayInventory();
                case "attack" -> {
                    // More logic for keyword followed after attack, i.e., "attack goblin" will attack goblin,
                    // but if no goblin exists then return 'That is not a valid action!'
                    Entity entity = map.getEntityAt(xPosition, yPosition);
                    if (entity instanceof Enemy enemy) {
                        enemy.fight(this, map, xPosition, yPosition, inventory);  // 'this' refers to the current GameEngine instance
                    } else {
                        System.out.println("There's no enemy here to fight!");
                    }
                }
                case "move" -> System.out.println("Please specify the direction you would like to move in, i.e. 'move right'");
                case "move forward", "move backward", "move left", "move right" -> {
                    String[] parts = input.split(" ");
                    if (parts.length == 2) {
                        String directionStr = parts[1];
                        Direction direction = null;
                        if ("forward".equalsIgnoreCase(directionStr)) {
                            direction = Direction.Forward;
                        } else if ("backward".equalsIgnoreCase(directionStr)) {
                            direction = Direction.Backward;
                        } else if ("left".equalsIgnoreCase(directionStr)) {
                            direction = Direction.Left;
                        } else if ("right".equalsIgnoreCase(directionStr)) {
                            direction = Direction.Right;
                        }
                        if (direction != null) {
                            boolean moveFlag = move(direction);
                            if (moveFlag) {
                                System.out.println("Move " + directionStr);
                            } else {
                                System.out.println("Move command is invalid");
                            }
                        } else {
                            System.out.println("Invalid direction: " + directionStr);
                        }
                    } else {
                        System.out.println("Invalid move command. Please specify a direction.");
                    }
                }

                case "talk" -> {
                    System.out.print("Who do you want to talk to? ");
                    String npcName = scanner.nextLine().toLowerCase().trim();
                    System.out.println("Trying to talk to: " + npcName);
                    System.out.println("At position: x=" + xPosition + ", y=" + yPosition);

                    Entity entity = map.getEntityAt(xPosition, yPosition);
                    if (entity instanceof NPC && entity.getName().equalsIgnoreCase(npcName)) {
                        NPC npc = (NPC) entity;
                        String message = npc.talk(this.map,xPosition,yPosition,this.inventory);  // Assuming GameEngine has a field called 'inventory'
                        System.out.println(message);
                    } else {
                        System.out.println("There is no " + npcName + " here to talk to.");
                    }
                }
                case "take" -> {
                    Entity entity = map.getEntityAt(xPosition, yPosition);
                    if (entity instanceof Item item) {
                        inventory.addItem(item);
                        map.removeEntity(xPosition,yPosition);
                        System.out.println("# You took a " + entity.getName());
                    }
                    else {System.out.println("There is no item here");}

                }

                case "use" -> {
                    displayInventory();
                    System.out.println("Which item do you want to use: ");
                    System.out.print("> ");
                    String itemName = scanner.nextLine().trim();
                    Item selectedItem = inventory.getItem(itemName);
                    if (selectedItem != null) {
                        switch (selectedItem.getName()) {
                            case "potion" -> {
                                System.out.println("You used a potion");
                                inventory.removeItem(selectedItem);
                            }
                            case "gold" -> System.out.println("You look at the gold in your inventory " +
                                    "and wonder what purpose it might have.");
                            default -> System.out.println("You are unsure how to use this item, " +
                                    "you place it back into your inventory.");
                            }

                        }
                    else {System.out.println("Invalid item name");}
                    }

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
        if (map.getEntityTypeAt(xPosition,yPosition).equalsIgnoreCase("item")) {System.out.println("take - take item at current position");}
        // Add more commands as needed.
    }

    /**
     * moves the user in the input direction
     * @param direction the direction to move the character
     *
     * @return True if the movement was successful, false if the movement was unsuccessful.
     * @author Sam Powell
     */
    public boolean move(Direction direction) {
        if (direction == Direction.Left) {
            if (map.isValidPosition(xPosition - 1, yPosition)) {xPosition -=1; return true;}
            else {return false;}
        }
        if (direction == Direction.Right) {
            if (map.isValidPosition(xPosition+1,yPosition)) {xPosition +=1; return true;}
            else {return false;}
        }
        if (direction == Direction.Forward) {
            if (map.isValidPosition(xPosition,yPosition+1)) {yPosition +=1; return true;}
            else {return false;}
        }
        if (direction == Direction.Backward) {
            if (map.isValidPosition(xPosition,yPosition-1)) {yPosition -=1; return true;}
            else {return false;}
        }
        return false;
    }


    public void fightEnemy(Enemy enemy) {
        enemy.talk();
    }

//    public void talkToNPC(NPC npc) {
//        npc.talk();
//    }


    /**
     * Prints the players current inventory
     */
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

    /**
     * Get an NPC instance at the specified position.
     *
     * @param x       The x-coordinate of the NPC.
     * @param y       The y-coordinate of the NPC.
     * @param npcName The name of the NPC to look for.
     * @return If a matching NPC is found, return the corresponding NPC instance; otherwise, return null.
     */
    public NPC getNPCAtPosition(int x, int y, String npcName) {
        Entity entity = map.getEntityAt(x, y);
        System.out.println("Entity at position: " + entity);
        if (entity instanceof NPC && entity.getName().equalsIgnoreCase(npcName)) {
            System.out.println("Found matching NPC: " + entity.getName());
            return (NPC) entity;
        }
        return null;
    }

    /**
     * Check if the player has weapon or not.
     *
     * @return does the player has a weapon.
     * @author Kwong Yu Zhou
     * @author Thomas Green
     */
    public boolean playerHasWeapon() {
        for (Item item : inventory.getItems()) {
            switch (item.getName()) {
                case "sword", "bow" -> {return true;}
            }
        }
        return playerHasWeapon; // Return false if no weapon was found in the loop
    }


    public void equipPlayerWeapon() {
        this.playerHasWeapon = true;
    }

    public void playerLoseWeapon() {
        this.playerHasWeapon = false;
    }








}

