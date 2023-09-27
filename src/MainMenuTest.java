import org.junit.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class MainMenuTest {

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
    public void testStartEasyCommand() {
        String[] input = {"play easy"};
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise(input);

        String output = outContent.toString();
        Assert.assertTrue(output.contains("Thanks for playing!"));
    }

    @Test
    public void testPlay() {
        String[] input = {"play easy", "quit"};
        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise(input);

        String output = outContent.toString();
        Assert.assertTrue(output.contains("Thanks for playing!"));
    }

    @Test
    public void testQuitCommand() {
        String input = "quit\n";
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        MainMenu mainMenu = new MainMenu();
        mainMenu.initialise(null);

        String output = outContent.toString();
        System.setOut(originalOut);
        System.out.println(output);
        System.setOut(new PrintStream(outContent));
        Assert.assertTrue(output.contains("Welcome to the Realms of the Bermuda Isles!"));
    }


}