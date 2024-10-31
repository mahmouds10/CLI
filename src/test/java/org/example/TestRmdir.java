package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestRmdir {
    Command rmdirCommand;

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

    // Test normal usage
    @Nested
    class normalUsage {
        @BeforeEach
        void setUp() {
            System.setOut(originalOut);
            MkdirCommand mkdirCommand = new MkdirCommand();
            mkdirCommand.execute("mkdir empty");
        }
        @Test
        public void testRmdir(){
            System.setOut(new PrintStream(outputStream));
            rmdirCommand = new RmdirCommand();
            rmdirCommand.execute("rmdir empty");

            String actualOutput = outputStream.toString();

            String expectedOutput = "Directory deleted: F:\\Projects\\Java\\CLI_JUnit\\empty\n";

            assertEquals(
                    // Convert newline from windows "\r\n" to unix "\n"
                    expectedOutput.replace("\r\n", "\n").trim(),
                    actualOutput.replace("\r\n", "\n").trim());
        }
    }

    // Test wrong usage
    @Test
    public void testWrongUsage(){
        rmdirCommand = new RmdirCommand();
        rmdirCommand.execute("rmdir");
        String actual = outputStream.toString();
        String expected = CLI.ANSI_RED+"Invalid usage. Correct usage: rmdir <directory_name>"+CLI.ANSI_RESET;
        assertEquals(expected.trim(), actual.trim());
    }

    // Test if file not exist
    @Test
    public void testNotExisting(){
        rmdirCommand = new RmdirCommand();
        rmdirCommand.execute("rmdir doesn't_exist");
        String actual = outputStream.toString();
        String expected = CLI.ANSI_RED + "Directory does not exist." + CLI.ANSI_RESET;
        assertEquals(expected.trim(), actual.trim());
    }

    // Test if folder not empty
    @Test
    public void TestNotEmpty(){
        CdCommand cdCommand = new CdCommand();
        cdCommand.execute("cd Testing");
        rmdirCommand = new RmdirCommand();
        rmdirCommand.execute("rmdir Recursive_folder");
        String actual = outputStream.toString();
        String expected = CLI.ANSI_RED+"Directory is not empty."+CLI.ANSI_RESET;
        assertEquals(expected.trim(), actual.trim());
    }
}
