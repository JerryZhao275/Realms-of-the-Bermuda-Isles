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
        System.out.println("Welcome to Text-based RPG!");
        System.out.println("Type 'play' to dive into the game!");
        System.out.println("Type 'quit' to quit the game.");

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
                    if (gameEngine == null) {
                        isInMenu = false;
                        gameEngine = GameEngine.getInstance(); // Retrieve the existing instance
                        System.out.println("Game Start!");
                        gameEngine.startGame();
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
