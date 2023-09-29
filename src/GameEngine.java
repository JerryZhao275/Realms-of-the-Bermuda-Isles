import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileWriter;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

/**
 * This class represents the game engine for a text-based RPG.
 * It handles user input, game progression, and interactions with respect to a singleton design pattern.
 *
 * @author Hsuan-Chu Shih
 * @author Jerry Zhao
 * @author Sam Powell
 * @author Thomas Green
 * @author Kwong Yu Zhou
 */
public class GameEngine {
    private static GameEngine instance;
    protected transient Scanner scanner;
    protected Map map;
    protected Inventory inventory;
    protected int difficulty;

    protected boolean playerHasWeapon = false;  // At the class level, after other fields (testing boolean)
    @JsonProperty("hp")
    protected int HP;
    protected int hp_limit;
    protected int attack_time = 3; // the remained times for the boss to be defeated
    protected int xPosition;
    protected int yPosition;


    /**
     * Private constructor to initialize the GameEngine instance.
     * It creates a new map, inventory, and scanner for user input.
     *
     * @author Jerry Zhao
     */
    protected GameEngine() {
        map = new Map();
        inventory = new Inventory();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Get the singleton instance of the GameEngine class.
     *
     * @author Jerry Zhao
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
     * @author Hsuan-Chu Shih
     * @author Jerry Zhao
     * @author Sam Powell
     * @author Thomas Green
     * @author Kwong Yu Zhou
     *
     * @param difficulty integer corresponding to the difficulty of the game; 0 = easy, 1 = normal, 2 = hard
     */
    public void startGame(int difficulty, String[] testInput) {
        switch (difficulty) {
            case 0 -> {
                HP = 5;
                hp_limit = HP;
                inventory.addItem(new Item("sword", -1, -1, ItemType.Sword));
                inventory.addItem(new Item("potion", -1, -1, ItemType.Potion));
                inventory.addItem(new Item("gold", -1, -1, ItemType.Gold));
                inventory.addItem(new Item("gold", -1, -1, ItemType.Gold));
                inventory.addItem(new Item("gold", -1, -1, ItemType.Gold));
                inventory.addItem(new Item("gold", -1, -1, ItemType.Gold));
                System.out.println("You are playing easy mode!");
            }
            case 1 -> {
                HP = 4;
                hp_limit = HP;
                inventory.addItem(new Item("sword", -1, -1,ItemType.Sword));
                System.out.println("You are playing normal mode!");
            }
            case 2 -> {
                HP = 3;
                hp_limit = HP;
                System.out.println("You are playing hard mode!");
            }
        }

        if (difficulty != 4) {
            System.out.println("===========================================");
            System.out.println("In the enigmatic expanse of the Bermuda Triangle, an area where time and space mysteriously intertwine, lies a realm unknown to most.\n" +
                    "This place, known as the heart of the Bermuda Triangle, is the Bermuda Isles.\n" +
                    "Its inhabitants, mostly sailors and aviators who vanished over the years, have formed a unique community in this lost world.");
            System.out.println("You stand on the desolate beach, with dense forests to the west and south.\n" +
                    "Currently, you have only two paths to choose from: one leading north and one leading east.");
            System.out.println("===========================================");
        }
        System.out.println("Type 'help' for a list of commands.");

        boolean isGameOver = false;
        if (difficulty != 4) {
            xPosition = 0;
            yPosition = 0;
            this.difficulty = difficulty;
        }
        int prevXPosition = xPosition;
        int prevYPosition = yPosition;

        int currInput = 0;
        int display = 0;

        while ((!isGameOver && currInput < testInput.length)) {
            if (display == 0 && difficulty == 4) display = 1;
            // Check if the player's position has changed and print new dialogue when entering a new area
            if (xPosition != prevXPosition || yPosition != prevYPosition || display == 1) {
                display++;
                if (xPosition == 0 && yPosition == 0) {
                    System.out.println("You find yourself back at where you started, with dense forests to the west and south.\n" +
                            "There are only two paths to choose from: one leading north and one leading east.");
                }
                else if (xPosition == 0 && yPosition == 3) {
                    System.out.println("You find yourself cornered by the dense forest. There seems to be only a path leading south and east.");
                }
                else if (xPosition == 3 && yPosition == 0) {
                    System.out.println("You find yourself cornered by the dense forest. There seems to be only a path leading north and west.");
                }
                else if (xPosition == 3 && yPosition == 3) {
                    System.out.println("You've reached an open highland. From here, you can overlook the entire Bermuda Isles and " +
                            "see the distant coastline.\nYou can choose to descend the highlands and move towards west or south.");
                }
                else if (xPosition == 0 && yPosition == 1 || xPosition == 0 && yPosition == 2 ) {
                    System.out.println("You continue traversing alongside the dense forest on your west, leaving all other directions open.");
                }
                else if (xPosition == 1 && yPosition == 0 || xPosition == 2 && yPosition == 0) {
                    System.out.println("You continue traversing alongside the lonesome beach on your south, leaving all other directions open.");
                }
                else if (xPosition == 3 && yPosition == 1 || xPosition == 3 && yPosition == 2 ) {
                    System.out.println("You find yourself following the cliff of the highlands on your east, leaving all other directions open.");
                }
                else if (xPosition == 1 && yPosition == 3 || xPosition == 2 && yPosition == 3) {
                    System.out.println("You find yourself following the cliff of the highlands on your north, leaving all other directions open.");
                }
                else if (xPosition == 1 && yPosition == 1 || xPosition == 2 && yPosition == 1 || xPosition == 1 && yPosition == 2 || xPosition == 2 && yPosition == 2) {
                    System.out.println("You stand amidst an expansive open plain with all directions open to walk in.");
                }

                switch (map.getEntityTypeAt(xPosition, yPosition)) {
                    case "Enemy" -> {
                        Enemy enemy = (Enemy) map.getEntityAt(xPosition, yPosition);
                        System.out.println("You spot a " + enemy.getName() + " wandering about in the distance.");
                    }
                    case "NPC" -> {
                        NPC npc = (NPC) map.getEntityAt(xPosition, yPosition);
                        if (npc instanceof NPC.Thief) {
                            System.out.println("There's a stranger wandering about in the distance.");
                        } else {
                            System.out.println("There's " + npc.getName() + " wandering about in the distance.");
                        }

                    }
                    case "Item" -> {
                        Item item = (Item) map.getEntityAt(xPosition, yPosition);
                        System.out.println("You spot a " + item.getName() + " glistening in the grass.");
                    }
                    default ->  System.out.println("You spot nothing in the distance close by.");
                }
                prevXPosition = xPosition;
                prevYPosition = yPosition;
            }

            String input;
            System.out.print("> ");
            if (Objects.equals(testInput[0], "playthrough")) {
                input = scanner.nextLine();
                input = input.toLowerCase();
            } else {
                input = testInput[currInput];
                currInput++;
            }

            switch (input) {
                /*
                  Displays a list of available commands to the player.
                 */
                case "help" -> displayCommands();

                /*
                 * Quits the game, ending the current session.
                 */
                case "quit" -> {
                    isGameOver = true;
                    System.out.println("Thanks for playing!");
                }

                /*
                 * Display the contents of the inventory
                 */
                case "inventory" -> displayInventory();

                /*
                 * Display the player's current HP
                 */
                case "hp" -> System.out.println("You current HP is: "+ HP);

                /*
                 * Initiates an attack on enemy entity.
                 */
                case "attack" -> {
                    Entity entity = map.getEntityAt(xPosition, yPosition);
                    if (entity instanceof Enemy) {
                        System.out.println("Please specify an enemy you would like to fight, i.e. 'attack ogre'.");
                    } else {
                        System.out.println("There's no enemy here to fight!");
                    }
                }

                /*
                 * Handles the attack on a specific enemy type. If the enemy exists in the player's current
                 * location, a fight is initiated.
                 * There are four enemies for player to attack(include BOSS)
                 */
                case "attack goblin", "attack ogre", "attack spider" -> {
                    Entity entity = map.getEntityAt(xPosition, yPosition);
                    if (entity instanceof Enemy enemy) {
                        enemy.fight(this, map, xPosition, yPosition, HP);// 'this' refers to the current GameEngine instance
                        if (map.getEntityAt(xPosition, yPosition) == null){openChest(inventory);}
                    } else {
                        System.out.println("There's no enemy here to fight!");
                    }
                }

                /*
                 * Attempts to attack the main boss of the game.
                 * The player can only challenge the boss if they possess a weapon.
                 */
                case "attack boss"-> {
                    if(playerHasWeapon()){
                        Entity entity = map.getEntityAt(xPosition, yPosition);
                        if (entity instanceof Enemy enemy && Objects.equals(entity.getName(), "boss")) {
                            enemy.fight(this, map, xPosition, yPosition, HP);
                            this.attack_time--;
                            this.HP -= 2;
                            if (this.attack_time == 0 && HP > 0) {
                                map.removeEntity(xPosition,yPosition);
                                // Some extra content can be added here
                                System.out.println("Congratulations! You've won the game!");
                                int newListSize = testInput.length - currInput;
                                String[] newList = new String[newListSize];
                                System.arraycopy(testInput, currInput, newList, 0, testInput.length - currInput);
                                map = new Map();
                                inventory = new Inventory();
                                this.gameOver(newList);
                            }
                            else if (HP <= 0) {
                                System.out.println("The boss hits back at you, crushing you into the ground!");
                                isGameOver = true;
                                System.out.println("# You have been defeated by the boss!");
                                System.out.println("## Game Over ##");
                                int newListSize = testInput.length - currInput;
                                String[] newList = new String[newListSize];
                                System.arraycopy(testInput, currInput, newList, 0, testInput.length - currInput);
                                map = new Map();
                                inventory = new Inventory();
                                this.gameOver(newList);
                            }
                            else if (attack_time == 1) {
                                System.out.println("The boss hits back at you! You lost 2 HP.");
                                System.out.println("Your current HP is "+ HP +". Attack the boss one more time to defeat it!");
                            } else {
                                System.out.println("The boss hits back at you! You lost 2 HP.");
                                System.out.println("Your current HP is "+ HP +". Attack " + attack_time + " more times to defeat the boss.");
                            }
                        } else {
                            System.out.println("There's no enemy here to fight!");
                        }
                    } else {System.out.println("You are unable to challenge the boss without a weapon!");}
                }

                /*
                 * Allow the player to move at a specified position
                 */
                case "move" -> System.out.println("Please specify the direction you would like to move in, i.e. 'move right'");

                /*
                 * Handles command 'move' and moves the player to the specified direction.
                 * There are four positions for player to move
                 */
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
                            if (!moveFlag) {
                                System.out.println("You cannot move in that direction!");
                            }
                        } else {
                            System.out.println("[" + "Move " + directionStr + "]");
                        }
                    } else {
                        System.out.println("Invalid move command. Please specify a direction.");
                    }
                }

                /*
                 * Prompts the player to specify the NPC they'd like to talk with.
                 */
                case "talk" -> System.out.println("Specify who you would like to talk to, i.e. 'talk [Entity]'");

                /*
                 * Handles conversations with various NPCs within the game.
                 * There are eight NPCs for player to talk in total.
                 */
                case "talk dwarf", "talk merchant", "talk stranger", "talk blacksmith",
                        "talk goblin", "talk spider", "talk boss", "talk ogre" -> {
                    String[] parts = input.split(" ");
                    Entity entity = map.getEntityAt(xPosition, yPosition);
                    if (entity instanceof NPC.Merchant) {
                        NPC.Merchant merchant = (NPC.Merchant) entity;
                        int newListSize = testInput.length - currInput;
                        String[] newList = new String[newListSize];
                        System.arraycopy(testInput, currInput, newList, 0, testInput.length - currInput);
                        String message = "";
                        if (newList != null && !Objects.equals(newList[0], "playthrough")) {
                            testInput = merchant.talk(map, xPosition, yPosition, this.inventory, newList);
                            currInput = 0;
                        } else {
                            message = merchant.talk(map, xPosition, yPosition, this.inventory);
                        }
                        System.out.println(message);
                    }
                    else if (entity instanceof NPC && entity.getName().equalsIgnoreCase(parts[1])) {
                        NPC npc = (NPC) entity;
                        String message = npc.talk(this.map,xPosition,yPosition,this.inventory);  // Assuming GameEngine has a field called 'inventory'
                        System.out.println(message);

                    } else if (entity instanceof Enemy && entity.getName().equalsIgnoreCase(parts[1])) {
                        Enemy enemy = (Enemy) entity;
                        String message = enemy.talk();  // Assuming GameEngine has a field called 'inventory'
                        System.out.println(message);

                    } else {
                        System.out.println("There is no " + parts[1] + " here to talk to.");
                    }
                }

                /*
                 * Allows the player to specify items they'd like to take.
                 */
                case "take" -> System.out.println("What would you like to take?");

                /*
                 * Handles the action of the player taking items.
                 * There are four items for player to take in total.
                 */
                case "take potion", "take armor", "take gold", "take bow" -> {
                    String[] parts = input.split(" ");
                    Entity entity = map.getEntityAt(xPosition, yPosition);
                    if (entity instanceof Item item && entity.getName().equalsIgnoreCase(parts[1])) {
                        inventory.addItem(item);
                        map.removeEntity(xPosition,yPosition);
                        System.out.println("# You took a " + entity.getName());
                    }
                    else {System.out.println("There is no item here");}
                }

                /*
                 * Allows the player to specify an item they'd like to use from their inventory.
                 */
                case "use" -> {
                    System.out.println("Which item do you want to use: ");
                    displayInventory();
                    System.out.println("Type 'use [item]'");
                }

                /*
                 * Handles the action of the player using items from their inventory.
                 * There are five items for player to use in total.
                 */
                case "use potion", "use armor", "use gold", "use sword", "use bow" -> {
                    String[] parts = input.split(" ");
                    Item selectedItem = inventory.getItem(parts[1]);
                    if (selectedItem != null) {
                        switch (selectedItem.getName()) {
                            case "potion" -> {
                                if (HP + 1 <= hp_limit){
                                    HP++;
                                    selectedItem.use(inventory);
                                    System.out.println("# You used a potion (HP:+1). Your HP has been increased to "+ HP +".");
                                }else {
                                    System.out.println("# You are unable to use a potion with full HP.");
                                }
                            }
                            case "armor" -> { //Armor is significantly more useful than potions, and there are fewer ways to get it
                                HP++;
                                inventory.removeItem(selectedItem);
                                System.out.println("# You equipped yourself with an armor (HP:+1). Your HP has been increased to "+ HP +".");
                            }
                            case "gold" -> System.out.println("You look at the piece of gold, " +
                                    "maybe someone would like to trade with you.");
                            default -> System.out.println("You are unsure how to use this item, " +
                                    "you place it back into your inventory.");
                        }

                    }
                    else {System.out.println("Invalid item name");}
                }

                /*
                 * Saves the current state of the game.
                 */
                case "save" -> {
                    System.out.println("You have saved the game!");
                    saveGame();
                }


                // Add more commands such as save and load later
                default -> System.out.println("Please enter a valid command or type help to see the commands.");
            }
        }
        scanner.close();
    }

    /**
     * Commands printed to the user if desired
     *
     * @author Jerry Zhao
     */
    private void displayCommands() {
        System.out.println("Available commands:");
        System.out.println("  help - Display this help message");
        System.out.println("  move [direction] - Move your character towards a direction, i.e. 'move right'");
        System.out.println("  take - Take a specified item in the current area, i.e. 'take armor'");
        System.out.println("  inventory - Displays your inventory");
        System.out.println("  hp - Displays your current HP");
        System.out.println("  attack [enemy] - Attack the specified enemy, i.e. 'attack goblin'");
        System.out.println("  talk [entity] - Talk to the specified enemy or NPC");
        System.out.println("  use [item] - Uses a specific item, i.e. 'use potion'");
        System.out.println("  save - Saves the game.");
        System.out.println("  quit - Quit the game");
    }

    /**
     * moves the user in the input direction
     *
     * @author Sam Powell
     *
     * @param direction the direction to move the character
     *
     * @return True if the movement was successful, false if the movement was unsuccessful.
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

    /**
     * Prints the players' current inventory
     *
     * @author Thomas Green
     */
    private void displayInventory() {
        int goldCount = 0;
        boolean hasGold = false;
        List<Item> items = inventory.getItems();
        if (items.isEmpty()) {
            System.out.println("Your inventory is empty.");
            return;
        }
        System.out.println("Your inventory contains:");
        for (Item item : items) {
            if (item.getItemType() == ItemType.Gold) {
                hasGold = true;
                goldCount++;
            }
            else if (item.getItemType() == ItemType.Armor) System.out.println("- " + item.getName());
            else System.out.println("- " + item.getName() + " (" + item.getDurability() + " use(s))");
        }
        if (hasGold) System.out.println("- gold (" + goldCount + " piece(s))");
    }


    /**
     * Check if the player has weapon or not.
     *
     * @author Kwong Yu Zhou
     * @author Thomas Green
     *
     * @return whether the player has one weapon, false otherwise.
     */
    public boolean playerHasWeapon() {
        for (Item item : inventory.getItems()) {
            if (item.getItemType() == ItemType.Sword || item.getItemType() == ItemType.Bow) {
                return true;
            }
        }
        return playerHasWeapon; // Return false if no weapon was found in the loop
    }

    /**
     * Retrieves the current weapon of the player from the inventory.
     *
     * @author Kwong Yu Zhou
     * @author Thomas Green
     *
     * @return whether the player has one weapon, null otherwise.
     */
    public Item returnWeapon() {
        for (Item item : inventory.getItems()) {
            if (item.getItemType() == ItemType.Bow || item.getItemType() == ItemType.Sword) {
                return item;
            }
        }
        return null;
    }

    /**
     * Getter methods to allow Reading and Writing to JSON with Jackson library
     * Unless alternative method implemented, do not remove.
     *
     * @author Sam Powell
     */

    public Inventory getInventory() {
        return inventory;
    }
    public Map getMap() {return map;}


    public int getxPosition() {return xPosition;}
    public int getyPosition() {return yPosition;}
    public int getHP() {return HP;}

    public int getAttack_time() {return attack_time;}

    public int getDifficulty() {return difficulty;}

    public int getHp_limit() {return hp_limit;}

    /**
     * Handles the game-over scenario and prompts the player to restart the game.
     * If the player chooses to restart, the game is restarted again with previous settings
     * If the player chooses not to restart, the game exits.
     *
     * @author Sam Powell
     * @author Jerry Zhao
     *
     * @param testInput An array of strings containing the player's input. If the input
     * is null, a prompt will be displayed to the player.
     */
    public void gameOver(String[] testInput) {
        if (Objects.equals(testInput[0], "playthrough")) {
            System.out.println("Would you like to play again? [Y/N]: ");
            System.out.print("> ");
            String playAgain = scanner.nextLine();
            switch (playAgain) {
                case "Y" -> startGame(difficulty, new String[]{"playthrough"});
                case "N" -> {
                    System.out.println("See you next time!");
                    System.exit(0);
                }
            }
        }
        else {
            switch (testInput[0]) {
                case "Y" -> {
                    int newListSize = testInput.length - 1;
                    String[] newList = new String[newListSize];
                    System.arraycopy(testInput, 1, newList, 0, testInput.length - 1);
                    startGame(difficulty, newList);
                }
                case "N" -> {
                    System.out.println("See you next time!");
                    System.exit(0);
                }
            }
        }
    }

    /**
     * Open a chest and add the items to the inventory.
     * A random item and some pieces of gold(0 to 2 ) will be added to player's inventory
     *
     * @author Kwong Yu Zhou
     *
     * @param inventory The inventory where the items from the chest will be added.
     */
    public void openChest(Inventory inventory) {
        Random random = new Random();
        Item sword = new Item("sword", -1, -1,ItemType.Sword);
        Item potion = new Item("potion", -1, -1,ItemType.Potion);
        Item bow = new Item("bow", -1, -1,ItemType.Bow);
        Item armor = new Item("armor", -1, -1,ItemType.Armor);
        Item[] possibleItems = {sword, bow, potion, armor};
        int count_gold = 0;

        // Randomly select a weapon (sword or bow) or a supplement (armor or potion)
        Item chosenItem = possibleItems[random.nextInt(4)];
        inventory.addItem(chosenItem);

        // Generate a random number of golds between 0 and 2
        int numberOfGolds = random.nextInt(3);

        for (int i = 0; i < numberOfGolds; i++) {
            inventory.addItem(new Item("gold", -1, -1,ItemType.Gold));
            count_gold++;
        }
        if (count_gold == 0){
            System.out.println("# You have opened a chest. You've got a " + chosenItem.getName()
                    + ".");
        } else if (count_gold == 1) {
            System.out.println("# You have opened a chest. You've got a " + chosenItem.getName()
                    + " and a piece of gold.");
        } else{
            System.out.println("# You have opened a chest. You've got a " + chosenItem.getName()
                    + " and " + count_gold + " pieces of gold.");
        }
    }

    /**
     * Saves the current state of the game to a file using the Jackson library.
     *
     * @author Sam Powell
     */
    public void saveGame() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            FileWriter writer = new FileWriter("saves/savefile.json");
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer,this);
            writer.close();
        } catch (Exception e) {
            System.out.println("Failed to save game.");
        }
    }



}