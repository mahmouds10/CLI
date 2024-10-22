package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    // Test the wrong usage case
    @Test
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
                + "Text_Files";

        assertEquals(
                // Convert newline from windows "\r\n" to unix "\n"
                expectedOutput.replace("\r\n", "\n").trim(),
                actualOutput.replace("\r\n", "\n").trim());

    }

    // Test the hidden files option
    @Test
    void TestHiddenFiles() {
        // Execute the 'ls' command
        lsCommand.execute("ls");

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
                + "Text_Files";
        assertEquals(
                // Convert newline from windows "\r\n" to unix "\n"
                expectedOutput.replace("\r\n", "\n").trim(),
                actualOutput.replace("\r\n", "\n").trim());
    }

    // Test the recursive files option
    @Test
    void TestRecursiveLsCommand() {
        // Execute the 'ls' command recursively
        lsCommand.execute("ls -r");

        // Get the output from the output stream
        String actualOutput = outputStream.toString();

        // Define the expected output with proper indentation and structure
        String expectedOutput = ".gitignore\n"
                + ".idea\n"
                + "  .gitignore\n"
                + "  compiler.xml\n"
                + "  encodings.xml\n"
                + "  inspectionProfiles\n"
                + "    Project_Default.xml\n"
                + "  jarRepositories.xml\n"
                + "  libraries\n"
                + "    junit.xml\n"
                + "  misc.xml\n"
                + "  uiDesigner.xml\n"
                + "  vcs.xml\n"
                + "  workspace.xml\n"
                + "Class_Diagram.png\n"
                + "pom.xml\n"
                + "src\n"
                + "  main\n"
                + "    java\n"
                + "      org\n"
                + "        example\n"
                + "          CatCommand.java\n"
                + "          CdCommand.java\n"
                + "          CLI.java\n"
                + "          Command.java\n"
                + "          CommandParser.java\n"
                + "          ExitCommand.java\n"
                + "          HelpCommand.java\n"
                + "          LsCommand.java\n"
                + "          MkdirCommand.java\n"
                + "          MvCommand.java\n"
                + "          PwdCommand.java\n"
                + "          RedirectAppendCommand.java\n"
                + "          RedirectOverwriteCommand.java\n"
                + "          RmCommand.java\n"
                + "          RmdirCommand.java\n"
                + "          TouchCommand.java\n"
                + "    resources\n"
                + CLI.ANSI_YELLOW + "No files in current directory" + CLI.ANSI_RESET + "\n"
                + "  test\n"
                + "    java\n"
                + "      org\n"
                + "        example\n"
                + "          LsCommandTest.java\n"
                + "target\n"
                + "  classes\n"
                + "    org\n"
                + "      example\n"
                + "        CatCommand.class\n"
                + "        CdCommand.class\n"
                + "        CLI.class\n"
                + "        Command.class\n"
                + "        CommandParser.class\n"
                + "        ExitCommand.class\n"
                + "        HelpCommand.class\n"
                + "        LsCommand.class\n"
                + "        MkdirCommand.class\n"
                + "        MvCommand.class\n"
                + "        PwdCommand.class\n"
                + "        RedirectAppendCommand.class\n"
                + "        RedirectOverwriteCommand.class\n"
                + "        RmCommand.class\n"
                + "        RmdirCommand.class\n"
                + "        TouchCommand.class\n"
                + "  generated-sources\n"
                + "    annotations\n"
                + CLI.ANSI_YELLOW + "No files in current directory" + CLI.ANSI_RESET + "\n"
                + "  generated-test-sources\n"
                + "    test-annotations\n"
                + CLI.ANSI_YELLOW + "No files in current directory" + CLI.ANSI_RESET + "\n"
                + "  test-classes\n"
                + "    org\n"
                + "      example\n"
                + "        LsCommandTest.class\n"
                + "Text_Files\n"
                + CLI.ANSI_YELLOW + "No files in current directory" + CLI.ANSI_RESET;

        // Normalize line endings for cross-platform compatibility
        assertEquals(
                expectedOutput.replace("\r\n", "\n").trim(),
                actualOutput.replace("\r\n", "\n").trim());
    }

}
