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
                case "inventory" -> {
                    System.out.println("Show inventory");
                }
                case "attack" -> {
                    // More logic for keyword followed after attack, i.e., "attack goblin" will attack goblin,
                    // but if no goblin exists then return 'That is not a valid action!'
                    System.out.println("Attack");
                }
                case "move" -> {
                    System.out.println("Which direction do you want to move? : ");
                    String nextLine = scanner.nextLine();
                    nextLine = nextLine.toLowerCase();
                    switch (nextLine) {
                        case "forward" -> {move(Direction.Forward);}
                        case "backward" -> {move(Direction.Backward);}
                        case "left" -> {move(Direction.Left);}
                        case "right" -> {move(Direction.Right);}
                    }
                    // More logic for keyword followed after move, i.e., "move forward" will move forward,
                    // but if the player is at the edge of our map, then return invalid'
                    System.out.println("Move " + nextLine);
                }
                case "talk" -> {
                    // Similar logic with attack, but instead with talking to NPCs
                    System.out.println("talk");
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
}
