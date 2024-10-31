package org.example;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestMkdir {
    Command mkdirCommand;

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
    class normalUsage{
        @Test
        void testMkdir(){
            mkdirCommand = new MkdirCommand();
            mkdirCommand.execute("mkdir new_directory");

            String expectedOutput = "Directory created: F:\\Projects\\Java\\CLI_JUnit\\new_directory\n";

            String actualOutput = outputStream.toString();

            assertEquals(
                    // Convert newline from windows "\r\n" to unix "\n"
                    expectedOutput.replace("\r\n", "\n").trim(),
                    actualOutput.replace("\r\n", "\n").trim());

        }

        @AfterEach
        void tearDown() {
            RmdirCommand rmdirCommand = new RmdirCommand();
            rmdirCommand.execute("rm new_directory");
        }

    }

    // Test if dir is already exists
    @Test
    public void TestExistDir(){
        mkdirCommand = new MkdirCommand();
        mkdirCommand.execute("mkdir src");
        String actual = outputStream.toString();
        String expected = CLI.ANSI_RED + "Directory already exists." + CLI.ANSI_RESET;
        assertEquals(expected.trim(), actual.trim());
    }

    // Test invalid usage
    @Test
    public void TestInvalidUsage(){
        mkdirCommand = new MkdirCommand();
        mkdirCommand.execute("mkdir");
        String actual = outputStream.toString();
        String expected = CLI.ANSI_RED+"Invalid usage. Correct usage: mkdir <directory_name>"+CLI.ANSI_RESET;
        assertEquals(expected.trim(), actual.trim());
    }
}
