import org.junit.*;
import java.io.*;

/**
 * This is a test class responsible for simulating a playthrough within the game, through
 * providing simulated user inputs and covering code within GameEngine and MainMenu classes.
 *
 * @author Jerry Zhao
 * @author Hsuan-Chu
 */
public class AutomaticGameTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    /**
     * Tests a playthrough with the "easy" difficulty setting.
     * Expected outcome: The game should terminate with a "Thanks for playing!" message.
     *
     * @author Hsuan-Chu
     * @author Jerry Zhao
     */
    @Test
    public void testPlayEasyTrivial() {
        String[] input = {"play", "play easy","help", "quit"};
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise(input);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Thanks for playing!"));
    }

    /**
     * Tests a playthrough with the "normal" difficulty setting.
     * Expected outcome: The game should terminate with a "Thanks for playing!" message.
     *
     * @author Hsuan-Chu
     * @author Jerry Zhao
     */
    @Test
    public void testPlayNormalTrivial() {
        String[] input = {"play easyyy", "play normal", "help", "quit"};
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise(input);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Thanks for playing!"));
    }

    /**
     * Tests a playthrough with the "hard" difficulty setting.
     * Expected outcome: The game should terminate with a "Thanks for playing!" message.
     *
     * @author Hsuan-Chu
     * @author Jerry Zhao
     */
    @Test
    public void testPlayHardTrivial() {
        String[] input = {"play hard","help", "quit"};
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise(input);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Thanks for playing!"));
    }

    /**
     * Tests a longer playthrough with 'use', 'move' 'attack' 'interaction' and a series of actions
     * to interact with NPC, items, and enemies iin the game.
     * Expected outcome: The game should terminate with a "Thanks for playing!" message.
     *
     * @author Jerry Zhao
     */
    @Test
    public void testPlay() {
        String[] input = {"play easy", "use", "use potion", "use Sword", "use saword", "attack", "hp", "help", "inventory",
                "move forward", "move backward", "move right", "attack spider", "inventory", "use gold", "move right",
                "take potion", "move right", "take armor", "move forward", "take gold", "fight ogre", "move left",
                "talk dwarf", "take bow", "inventory", "move forward", "attack goblin", "move right", "move right",
                "move right", "move forward", "move left", "move left", "move left", "talk blacksmith", "move right",
                "move right", "move right", "attack", "talk boss", "attack boss", "attack boss", "use potion",
                "use armor", "attack boss", "Y", "quit"};
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise(input);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Thanks for playing!"));
    }

    /**
     * Tests a longer playthrough with the scenario where the player dies after attacking boss in the game.
     * Expected outcome: The game should terminate with a "Thanks for playing!" message.
     *
     * @author Jerry Zhao
     */
    @Test
    public void testPlayDeath() {
        String[] input = {"play hard", "move forward", "move forward", "move forward", "talk blacksmith" ,
                "move right", "move right", "move right", "attack boss", "attack boss", "Y", "quit"};
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise(input);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Thanks for playing!"));
    }

    /**
     * Tests a longer playthrough with the scenario where the player trade with merchant and quit.
     * Expected outcome: The game should terminate with a "Farewell, traveler!" message.
     *
     * @author Jerry Zhao
     */
    @Test
    public void testMerchant() {
        String[] input = {"play easy", "move right", "move forward", "talk dwarf", "move right", "move forward",
                "talk merchant", "trade potion", "trade armor", "trade sword", "exit", "quit"};
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise(input);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Farewell, traveler!"));
    }

    /**
     * Tests a longer playthrough with the scenario where the player trade with merchant but don't have enough gold to buy.
     * Expected outcome: The game should terminate with a "Farewell, traveler!" message.
     *
     * @author Jerry Zhao
     */
    @Test
    public void testMerchantNoGold() {
        String[] input = {"play normal", "move right", "move forward", "talk dwarf", "move right", "move forward",
                "talk", "talk merchant", "trade potion", "trade armor", "trade sword", "exit", "quit"};
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise(input);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Farewell, traveler!"));
    }

    /**
     * Tests a playthrough with the scenario where the player quit the game directly.
     * Expected outcome: The game should terminate with a "Thanks for playing!" message.
     *
     * @author Jerry Zhao
     */
    @Test
    public void testQuit() {
        String[] input = {"quit"};
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise(input);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Thanks for playing!"));
    }

    /**
     * Tests a playthrough with the scenario where the player interacts with 'Thief' NPC.
     * Expected outcome: The game should terminate with a "Thanks for playing!" message.
     *
     * @author Jerry Zhao
     */
    @Test
    public void testThiefPlay() {
        String[] input = {"play easy", "move forward", "talk stranger", "quit"};
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise(input);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Thanks for playing!"));
    }


    /**
     * Tests save and load function in the game.
     * Expected outcome: The game should terminate with a "Thanks for playing!" message.
     *
     * @author Jerry Zhao
     */
    @Test
    public void testSaveAndLoad() {
        String[] input = {"load", "play easy", "move forward", "save", "quit"};
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise(input);

        String[] inputAfterSave = {"load", "quit"};
        MainMenu mainMenu2 = new MainMenu();
        mainMenu2.initialise(inputAfterSave);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Thanks for playing!"));
    }
}