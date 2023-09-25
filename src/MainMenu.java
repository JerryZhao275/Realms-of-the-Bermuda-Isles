import java.util.Scanner;

/**
 * The MainMenu class represents the main menu and user interface of the Text-based RPG game.
 * It allows the user to start the game or quit the application.
 */
public class MainMenu {
    private Scanner scanner;
    private GameEngine gameEngine;

    public MainMenu() {
        this.scanner = new Scanner(System.in);
        this.gameEngine = null;
    }

    /**
     * Initialize the main menu and allow the user to start the game or quit.
     * Once the user chooses to start the game, control is handed over to the GameEngine.
     *
     * @author Jerry Zhao
     */
    public void initialise() {
        System.out.println("===========================================");
        System.out.println("Welcome to the Realms of the Bermuda Isles!");
        System.out.println("===========================================");
        System.out.println("Enter 'play easy' to play on easy mode!");
        System.out.println("Enter 'play normal' to play on normal mode!");
        System.out.println("Enter 'play hard' to play on hard mode!");
        System.out.println("Enter 'quit' to quit the game.");
        System.out.println("===========================================");


        boolean isInMenu = true;
        while (isInMenu) {
            System.out.print("> ");
            String input = scanner.nextLine();
            input = input.toLowerCase();


            switch (input) {
                case "quit":
                    isInMenu = false;
                    System.out.println("Thanks for playing!");
                    break;
                case "play":
                    System.out.println("Please select the difficult you would like to play on");
                    break;
                case "play easy":
                    if (gameEngine == null) {
                        isInMenu = false;
                        gameEngine = GameEngine.getInstance();
                        gameEngine.startGame(0);
                    }
                    break;
                case "play normal":
                    if (gameEngine == null) {
                        isInMenu = false;
                        gameEngine = GameEngine.getInstance();
                        gameEngine.startGame(1);
                    }
                    break;
                case "play hard":
                    if (gameEngine == null) {
                        isInMenu = false;
                        gameEngine = GameEngine.getInstance();
                        gameEngine.startGame(2);
                    }
                    break;
                default:
                    System.out.println("Please enter a valid command or type help to see the commands.");
                    break;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        // Initialize the program for the user
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise();
    }
}
