package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class RmCommandTest {
    RmCommand rmCommand = new RmCommand();

    // Save the original System.out to reuse after the test
    private final PrintStream originalOut = System.out;

    // create a stream to capture the output instead of printing in default stream
    private ByteArrayOutputStream outputStream;

    // Pre test setup
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
    @Nested
    class normalUsage {
        @BeforeEach
        void setUp() {
            System.setOut(originalOut);

            // Change to testing directory
            CdCommand cdCommand = new CdCommand();
            cdCommand.execute("cd testing");

            // Create file to remove
            TouchCommand command = new TouchCommand();
            command.execute("touch new.txt");
        }
        @Test
        void normalUsageTest() {
            System.setOut(new PrintStream(outputStream));

            rmCommand.execute("rm new.txt");

            // Get the output from output stream
            String actualOutput = outputStream.toString();

            // Expected output
            String expectedOutput = "File deleted: F:\\Projects\\Java\\CLI_JUnit\\testing\\new.txt";

            assertEquals(
                    // Convert newline from windows "\r\n" to unix "\n"
                    expectedOutput.replace("\r\n", "\n").trim(),
                    actualOutput.replace("\r\n", "\n").trim());
        }

    }

    // Wrong usage
    @Test
    void wrongUsage() {
        rmCommand.execute("rm");

        // get the output from output stream
        String actualOutput = outputStream.toString();

        //Expected output
        String expectedOutput = CLI.ANSI_RED + "Invalid usage. Correct usage: rm <file_name>" + CLI.ANSI_RESET;

        assertEquals(
                // Convert newline from windows "\r\n" to unix "\n"
                expectedOutput.replace("\r\n", "\n").trim(),
                actualOutput.replace("\r\n", "\n").trim());
    }

    // Test file not exist
    @Test
    void fileNotExist() {
        rmCommand.execute("rm notExist.txt");

        // get the output from output stream
        String actualOutput = outputStream.toString();

        //Expected output
        String expectedOutput = CLI.ANSI_RED + "File does not exist." + CLI.ANSI_RESET;

        assertEquals(
                // Convert newline from windows "\r\n" to unix "\n"
                expectedOutput.replace("\r\n", "\n").trim(),
                actualOutput.replace("\r\n", "\n").trim());
    }

}