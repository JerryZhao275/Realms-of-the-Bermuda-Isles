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
        String[] input = {"play hard","help", "inventory", "move right"};
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise(input);
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Thanks for playing!"));
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