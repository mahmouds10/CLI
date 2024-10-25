package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LsCommandTest {
    LsCommand lsCommand;

    // Save the original System.out to reuse after the test
    private final PrintStream originalOut = System.out;

    // create a stream to capture the output instead of printing in default stream
    private ByteArrayOutputStream outputStream;

    // Pre test setup
    @BeforeEach
    void setUp() {
        lsCommand = new LsCommand();
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

    // Test the hidden files option
    @Test
    @Order(1)
    void TestHiddenFiles() {
        // Execute the 'ls' command
        lsCommand.execute("ls -a");

        // Get the output from the output stream
        String actualOutput = outputStream.toString();

        // Define the expected output
        String expectedOutput = ".git\n"
                + ".gitignore\n"
                + ".idea\n"
                + "Class_Diagram.png\n"
                + "pom.xml\n"
                + "src\n"
                + "target\n"
                + "Testing";
        assertEquals(
                // Convert newline from windows "\r\n" to unix "\n"
                expectedOutput.replace("\r\n", "\n").trim(),
                actualOutput.replace("\r\n", "\n").trim());
    }

    // Test the wrong usage case
    @Test
    @Order(3)
    void TestWrongUsage() {
        // Execute the 'ls' command
        lsCommand.execute("ls -s");

        // Get the output from the output stream
        String actualOutput = outputStream.toString();

        // Define the expected output
        String expectedOutput = CLI.ANSI_YELLOW + "Invalid option. Usage: ls, ls -a, ls -r" + CLI.ANSI_RESET;

        // Trim both outputs to remove trailing whitespace for comparison
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }

    // Test the normal usage case
    @Test
    @Order(2)
    void TestNormalUsage() {
        // Execute the 'ls' command
        lsCommand.execute("ls");

        // Get the output from the output stream
        String actualOutput = outputStream.toString();

        // Define the expected output
        String expectedOutput = ".gitignore\n"
                + ".idea\n"
                + "Class_Diagram.png\n"
                + "pom.xml\n"
                + "src\n"
                + "target\n"
                + "Testing";

        assertEquals(
                // Convert newline from windows "\r\n" to unix "\n"
                expectedOutput.replace("\r\n", "\n").trim(),
                actualOutput.replace("\r\n", "\n").trim());

    }

    // Test the recursive files option
    @Test
    @Order(4)
    void TestRecursiveLsCommand() {
        // Change to the testing directory
        CdCommand cdCommand = new CdCommand();
        cdCommand.execute("cd testing");

        // Execute the 'ls' command recursively
        lsCommand.execute("ls -r");

        // Get the output from the output stream
        String actualOutput = outputStream.toString();

        // Define the expected output with proper indentation and structure
        String expectedOutput =
                """
                        Recursive_Folder
                          Inner1.txt
                          inner2.txt
                        Test.txt""";

        // Normalize line endings for cross-platform compatibility
        assertEquals(
                expectedOutput.replace("\r\n", "\n").trim(),
                actualOutput.replace("\r\n", "\n").trim());
    }

}
