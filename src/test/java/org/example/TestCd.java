import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;



public class TestCd {
    Command c ;
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
    public void testCd(){
        c = new CdCommand();
        c.execute("cd src");

        c = new PwdCommand();
        c.execute("pwd");
        String actualPath = outputStream.toString();
        String expectedOutput = "C:\\Users\\HP\\service\\testCommands\\src\n";
        assertEquals(expectedOutput.trim(), actualPath.trim());

//        originalOut.println("actual : " + actualPath);
    }
}
