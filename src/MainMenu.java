import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * The MainMenu class represents the main menu and user interface of the Text-based RPG game.
 * It allows the user to start the game or quit the application.
 */
public class MainMenu {
    private Scanner scanner;
    private GameEngine gameEngine;
    public MainMenu() {
        this(new Scanner(System.in));
    }
    public MainMenu(Scanner scanner) {
        this.scanner = scanner;
        this.gameEngine = null;
    }

    /**
     * Initialize the main menu and allow the user to start the game or quit.
     * Once the user chooses to start the game, control is handed over to the GameEngine.
     *
     * @author Jerry Zhao
     */
    public void initialise(String[] testInput) {
        System.out.println("===========================================");
        System.out.println("Welcome to the Realms of the Bermuda Isles!");
        System.out.println("===========================================");
        System.out.println("Enter 'play easy' to play on easy mode!");
        System.out.println("Enter 'play normal' to play on normal mode!");
        System.out.println("Enter 'play hard' to play on hard mode!");
        System.out.println("Enter 'quit' to quit the game.");
        System.out.println("Enter 'load' to load a previously saved game");
        System.out.println("===========================================");

        int currInput = 0;
        boolean isInMenu = true;
        while (isInMenu && currInput < testInput.length) {
            String input;
            System.out.print("> ");
            if (Objects.equals(testInput[0], "playthrough")) {
                input = scanner.nextLine();
                input = input.toLowerCase();
            }
            else {
                input = testInput[currInput];
                currInput++;
            }

            switch (input) {
                case "quit":
                    isInMenu = false;
                    System.out.println("Thanks for playing!");
                    break;
                case "play":
                    System.out.println("Please select the difficult you would like to play on");
                    break;
                case "play easy":
                    if (gameEngine == null){
                        int newListSize = testInput.length - currInput;
                        String[] newList = new String[newListSize];
                        for (int i = 0; currInput < testInput.length; i++, currInput++) {
                            newList[i] = testInput[currInput];
                        }

                        isInMenu = false;
                        gameEngine = GameEngine.getInstance();
                        gameEngine.startGame(0, newList);
                    }
                    break;
                case "play normal":
                    if (gameEngine == null) {
                        int newListSize = testInput.length - currInput;
                        String[] newList = new String[newListSize];
                        for (int i = 0; currInput < testInput.length; i++, currInput++) {
                            newList[i] = testInput[currInput];
                        }

                        isInMenu = false;
                        gameEngine = GameEngine.getInstance();
                        gameEngine.startGame(1, newList);
                    }
                    break;
                case "play hard":
                    if (gameEngine == null) {
                        int newListSize = testInput.length - currInput;
                        String[] newList = new String[newListSize];
                        for (int i = 0; currInput < testInput.length; i++, currInput++) {
                            newList[i] = testInput[currInput];
                        }

                        isInMenu = false;
                        gameEngine = GameEngine.getInstance();
                        gameEngine.startGame(2, newList);
                    }
                    break;
                case "load":
                    if (gameEngine == null) {



                        Reader fileReader = null;
                        try {
                            ObjectMapper objectMapper = new ObjectMapper();
                            fileReader = new FileReader("saves/savefile.json");
                            gameEngine = objectMapper.readValue(fileReader,GameEngine.class);
                            fileReader.close();
                            isInMenu = false;

                            int newListSize = testInput.length - currInput;
                            String[] newList = new String[newListSize];
                            for (int i = 0; currInput < testInput.length; i++, currInput++) {
                                newList[i] = testInput[currInput];
                            }

                            gameEngine.startGame(4,newList);
                        } catch (Exception e) {
                            System.out.println("Failed to load game");
                            gameEngine = null;
                            isInMenu = true;
                            if (fileReader != null) {
                                try {
                                    fileReader.close();
                                } catch (Exception f) {
                                    System.out.println("Could not close reader");
                                }
                            }
                        }
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
        mainMenu.initialise(new String[]{"playthrough"});
    }
}
