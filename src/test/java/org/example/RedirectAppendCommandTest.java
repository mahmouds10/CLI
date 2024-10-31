package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
class RedirectAppendCommandTest {

    Command append = new RedirectAppendCommand();

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

    // test invalid usage
    @Test
    void invalidUsage () {
        append.execute("echo");

        String actualOutput = outputStream.toString();

        String expectedOutput = CLI.ANSI_RED + "Invalid usage. Correct usage: echo content >> file" + CLI.ANSI_RESET;

        assertEquals(
                // Convert newline from windows "\r\n" to unix "\n"
                expectedOutput.replace("\r\n", "\n").trim(),
                actualOutput.replace("\r\n", "\n").trim());
    }

    // Test valid usage
    @Test
    void appendTest() {
        CdCommand cdCommand = new CdCommand();
        cdCommand.execute("cd Testing");
        append.execute("echo content >> Test.txt");
        String actualOutput = outputStream.toString();
        String expectedOutput ="Output appended to file: F:\\Projects\\Java\\CLI_JUnit\\Testing\\Test.txt";

        assertEquals(
                // Convert newline from windows "\r\n" to unix "\n"
                expectedOutput.replace("\r\n", "\n").trim(),
                actualOutput.replace("\r\n", "\n").trim());
    }

}