package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.rmi.server.RMIClassLoader;

import static org.junit.jupiter.api.Assertions.*;

class TouchCommandTest {
    TouchCommand touchCommand = new TouchCommand();

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

    // Wrong usage test
    @Test
    void wrongUsageTest () {
        touchCommand.execute("touch");

        // Get the output from output stream
        String actualOutput = outputStream.toString();

        // Expected output
        String expectedOutput = CLI.ANSI_RED + "Invalid usage. Correct usage: touch <file_name>" + CLI.ANSI_RESET;

        assertEquals(
                // Convert newline from windows "\r\n" to unix "\n"
                expectedOutput.replace("\r\n", "\n").trim(),
                actualOutput.replace("\r\n", "\n").trim());
    }

    // File already exist test
    @Test
    void fileExist () {
        // Change to testing directory
        CdCommand cdCommand = new CdCommand();
        cdCommand.execute("cd testing");

        touchCommand.execute("touch Test.txt");

        // Get the output from output stream
        String actualOutput = outputStream.toString();

        // Expected output
        String expectedOutput = CLI.ANSI_RED+"File already exists."+CLI.ANSI_RESET;

        assertEquals(
                // Convert newline from windows "\r\n" to unix "\n"
                expectedOutput.replace("\r\n", "\n").trim(),
                actualOutput.replace("\r\n", "\n").trim());
    }

    // Normal usage test
    @Nested
    class CreateFile{
        @Test
        void normalUsageTest () {
            // Change to testing directory
            CdCommand cdCommand = new CdCommand();
            cdCommand.execute("cd testing");

            touchCommand.execute("touch new.txt");

            // Get the output from output stream
            String actualOutput = outputStream.toString();

            // Expected output
            String expectedOutput = "File created: F:\\Projects\\Java\\CLI_JUnit\\testing" ;
        }
        @AfterEach
        void tearDown() {
            RmCommand rmCommand = new RmCommand();
            rmCommand.execute("rm new.txt");
        }
    }


}