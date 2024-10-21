package org.example;
import java.util.HashMap;

// The command parser class that parses user input and returns the corresponding command
public class CommandParser {
    // A map to store the commands
    private final HashMap<String, CommandExecutor> commands;

    // Constructor to initialize the command map
    public CommandParser() {
        commands = new HashMap<>();
        // pwd command to print the current directory
        commands.put("pwd", new PwdCommand());
        // cd command to change the current directory
        commands.put("cd", new CdCommand());
        // ls command to list the files in the current directory
        commands.put("ls", new LsCommand());
        // mkdir command to create a new directory
        commands.put("mkdir", new MkdirCommand());
        // rmdir command to remove a directory
        commands.put("rmdir", new RmdirCommand());
        // touch command to create a new file
        commands.put("touch", new TouchCommand());
        // mv command to move a file or directory
        commands.put("mv", new MvCommand());
        // cp command to copy a file or directory
        commands.put("rm", new RmCommand());
        // cat command to display the contents of a file
        commands.put("cat", new CatCommand());
        // exit command to exit the CLI
        commands.put("exit", new ExitCommand());
        // help command to display the list of available commands
        commands.put("help", new HelpCommand());
        // overwriting command
        commands.put(">", new RedirectOverwriteCommand());
        // appending command
        commands.put(">>", new RedirectAppendCommand());
    }

    // Method to parse the user input and return the corresponding command
    public CommandExecutor parse(String input) {

        // Handle appending operator
        if (input.contains(">>")) {
            return new RedirectAppendCommand();
        }

        // Handle overwriting operator
        if (input.contains(">")) {
            return new RedirectOverwriteCommand();
        }

        // Split the input into tokens
        String[] tokens = input.split(" ");

        // Get the command from the first token
        CommandExecutor command = commands.get(tokens[0]);

        // Return the command if it is recognized
        if (command != null) {
            return command;
        } else {
            // Print error message if the command is not recognized
            System.out.println("Unknown command: " + tokens[0]);
            return null;
        }
    }
}
