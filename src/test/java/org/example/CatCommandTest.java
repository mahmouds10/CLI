package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CatCommandTest {

    CatCommand catCommand ;

    // Save the original System.out to reuse after the test
    private final PrintStream originalOut = System.out;

    // create a stream to capture the output instead of printing in default stream
    private ByteArrayOutputStream outputStream;

    // Pre test setup
    @BeforeEach
    void setUp() {
        catCommand = new CatCommand();
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

    // Test wrong usage
    @Test
    void wrongUsageTest (){
        catCommand.execute("cat");

        // Get the output from the output stream
        String actualOutput = outputStream.toString();

        // Expected output
        String expectedOutput = CLI.ANSI_RED + "Invalid usage. Correct usage: cat <file_name>" + CLI.ANSI_RESET;

        assertEquals(
                // Convert newline from windows "\r\n" to unix "\n"
                expectedOutput.replace("\r\n", "\n").trim(),
                actualOutput.replace("\r\n", "\n").trim());
    }

    // Test file doesn't exist
    @Test
    void fileNotExistTest(){
        catCommand.execute("cat not-exist.txt");

        String actualOutput = outputStream.toString();

        String expectedOutput = CLI.ANSI_RED + "File not found." +CLI. ANSI_RESET;

        assertEquals(
                // Convert newline from windows "\r\n" to unix "\n"
                expectedOutput.replace("\r\n", "\n").trim(),
                actualOutput.replace("\r\n", "\n").trim());
    }

    // Test reading file
    @Test
    void normalUsageTest(){
        // Change to testing directory
        CdCommand cdCommand = new CdCommand();
        cdCommand.execute("cd testing");

        catCommand.execute("cat Cat.txt");

        // Get the output from the output stream
        String actualOutput = outputStream.toString();

        // Expected output
        String expectedOutput = "File content";
        assertEquals(
                // Convert newline from windows "\r\n" to unix "\n"
                expectedOutput.replace("\r\n", "\n").trim(),
                actualOutput.replace("\r\n", "\n").trim());
    }
}