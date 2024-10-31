package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MvCommandTest {
    MvCommand mvCommand;

    // Save the original System.out to reuse after the test
    private final PrintStream originalOut = System.out;

    // create a stream to capture the output instead of printing in default stream
    private ByteArrayOutputStream outputStream;

    // Pre test setup
    @BeforeEach
    void setUp() {
        mvCommand = new MvCommand();
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

    // Test invalid usage
    @Test
    void wrongUsageTest (){
        mvCommand.execute("mv source");

        // Get the output from the output stream
        String actualOutput = outputStream.toString();

        // Expected output
        String expectedOutput = CLI.ANSI_RED + "Invalid usage. Correct usage: mv <source> <destination> [new_name]" + CLI.ANSI_RESET;

        assertEquals(
                // Convert newline from windows "\r\n" to unix "\n"
                expectedOutput.replace("\r\n", "\n").trim(),
                actualOutput.replace("\r\n", "\n").trim());

    }

    // Test Source file doesn't exist
    @Test
    void fileNotExist () {
        mvCommand.execute("mv NotExist.txt Recursive_Folder");

        // Get the output from the output stream
        String actualOutput = outputStream.toString();

        // Expected output
        String expectedOutput = CLI.ANSI_RED + "Source file does not exist." + CLI.ANSI_RESET;

        assertEquals(
                // Convert newline from windows "\r\n" to unix "\n"
                expectedOutput.replace("\r\n", "\n").trim(),
                actualOutput.replace("\r\n", "\n").trim());
    }

    // Test move only
    @Nested
    class MoveTest {
        @Test
        void moveOnlyTest () {
            CdCommand cdCommand = new CdCommand();
            cdCommand.execute("cd Testing");
            mvCommand.execute("mv Test.txt F:\\Projects\\Java\\CLI_JUnit\\testing\\Recursive_Folder");

            // Get the output from output stream
            String actualOutput = outputStream.toString();

            // Expected Output
            String expectedOutput = "Moved Test.txt to F:\\Projects\\Java\\CLI_JUnit\\testing\\Recursive_Folder\\Test.txt";

            assertEquals(
                    // Convert newline from windows "\r\n" to unix "\n"
                    expectedOutput.replace("\r\n", "\n").trim(),
                    actualOutput.replace("\r\n", "\n").trim());
        }

        @AfterEach
        void tearDown() {
            CdCommand cdCommand = new CdCommand();
            cdCommand.execute("cd Recursive_Folder");
            mvCommand.execute("mv Test.txt F:\\Projects\\Java\\CLI_JUnit\\Testing");
        }
    }

    // Test move and rename
    @Nested
    class MoveAndRenameTest {
        @Test
        void moveOnlyTest () {
            CdCommand cdCommand = new CdCommand();
            cdCommand.execute("cd Testing");
            mvCommand.execute("mv Test.txt F:\\Projects\\Java\\CLI_JUnit\\testing\\Recursive_Folder new.txt");

            // Get the output from output stream
            String actualOutput = outputStream.toString();

            // Expected Output
            String expectedOutput = "Renamed Test.txt to new.txt and moved to F:\\Projects\\Java\\CLI_JUnit\\testing\\Recursive_Folder";

            assertEquals(
                    // Convert newline from windows "\r\n" to unix "\n"
                    expectedOutput.replace("\r\n", "\n").trim(),
                    actualOutput.replace("\r\n", "\n").trim());
        }

        @AfterEach
        void tearDown() {
            CdCommand cdCommand = new CdCommand();
            cdCommand.execute("cd Recursive_Folder");
            mvCommand.execute("mv new.txt F:\\Projects\\Java\\CLI_JUnit\\Testing Test.txt");
        }
    }
}