import org.junit.*;
import java.io.*;

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

    @Test
    public void testPlayEasyTrivial() {
        String[] input = {"play", "play easy","help", "quit"};
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise(input);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Thanks for playing!"));
    }

    @Test
    public void testPlayNormalTrivial() {
        String[] input = {"play easyyy", "play normal", "help", "quit"};
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise(input);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Thanks for playing!"));
    }

    @Test
    public void testPlayHardTrivial() {
        String[] input = {"play hard","help", "quit"};
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise(input);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Thanks for playing!"));
    }

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

    @Test
    public void testPlayDeath() {
        String[] input = {"play hard", "move forward", "move forward", "move forward", "talk blacksmith" ,
                "move right", "move right", "move right", "attack boss", "attack boss", "Y", "quit"};
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise(input);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Thanks for playing!"));
    }

    @Test
    public void testMerchant() {
        String[] input = {"play easy", "move right", "move forward", "talk dwarf", "move right", "move forward",
                "talk merchant", "potion", "armor", "sword", "exit", "quit"};
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise(input);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Farewell, traveler!"));
    }


    @Test
    public void testMerchantNoGold() {
        String[] input = {"play normal", "move right", "move forward", "talk dwarf", "move right", "move forward",
                "talk", "talk merchant", "potion", "armor", "sword", "exit", "quit"};
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise(input);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Farewell, traveler!"));
    }

    @Test
    public void testQuit() {
        String[] input = {"quit"};
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise(input);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Thanks for playing!"));
    }
}