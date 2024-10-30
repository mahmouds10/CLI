import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class TestMkdir {
    Command c;

    // Save the original System.out to reuse after the test
    private final PrintStream originalOut = System.out;

    // create a stream to capture the output instead of printing in default stream
    private ByteArrayOutputStream outputStream;
    @BeforeEach
    void setUp() {
        Main.currentPath = System.getProperty("user.dir");

        // Set up the output stream to capture System.out
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    // Post test teardown
    @AfterEach
    void tearDown() {
        // Restore System.out to its original state
        System.setOut(originalOut);
    }
    @Test
    public void testMkdir(){
        c = new MkdirCommand();
        c.execute("mkdir files");

        c = new LsCommand();
        c.execute("ls");
        String actualOutput = outputStream.toString();
        String[] directories = actualOutput.split("\r\n");

        assertTrue(Arrays.asList(directories).contains("files"));
    }

    // test if dir is already exists
    @Test
    public void TestExistDir(){
        c = new MkdirCommand();
        c.execute("mkdir src");
        String actual = outputStream.toString().replaceAll("\u001B\\[[;\\d]*m", "");
        String expected = "Directory already exists.";
        assertEquals(expected.trim(), actual.trim());
    }

    // test invalid usage
    @Test
    public void TestInvalidUsage(){
        c = new MkdirCommand();
        c.execute("mkdir");
        String actual = outputStream.toString().replaceAll("\u001B\\[[;\\d]*m", "");
        String expected = "Invalid usage. Correct usage: mkdir <directory_name>";
        assertEquals(expected.trim(), actual.trim());
    }
}
