package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class TestCd {
    Command cdCommand;
    // Save the original System.out to reuse after the test
    private final PrintStream originalOut = System.out;

    // create a stream to capture the output instead of printing in default stream
    private ByteArrayOutputStream outputStream;
    @BeforeEach
    void setUp() {
        CLI.currentPath = System.getProperty("user.dir");

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

    // Normal usage
    @Test
    public void normalUsage(){
        cdCommand = new CdCommand();
        cdCommand.execute("cd src");

        cdCommand = new PwdCommand();
        cdCommand.execute("pwd");
        String actualPath = outputStream.toString();
        String expectedOutput = "F:\\Projects\\Java\\CLI_JUnit\\src";
        assertEquals(expectedOutput.trim(), actualPath.trim());
    }

    // Wrong usage
    @Test
    public void wrongUsage(){
        cdCommand = new CdCommand();
        cdCommand.execute("cd NotExist");

        String actualPath = outputStream.toString();
        String expectedOutput = CLI.ANSI_RED + "Directory not found." + CLI.ANSI_RESET;
        assertEquals(expectedOutput.trim(), actualPath.trim());
    }

    // Test backCommand
    @Test
    public void backUsage (){
        cdCommand = new CdCommand();
        cdCommand.execute("cd ..");

        Command pdwCommand = new PwdCommand();
        pdwCommand.execute("pwd");

        String actualPath = outputStream.toString();
        String expectedOutput = "F:\\Projects\\Java";

        assertEquals(expectedOutput.trim(), actualPath.trim());
    }
}
