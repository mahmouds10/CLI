package org.example;
import java.util.Scanner;
// The main class that runs the CLI
public class CLI {
    // ANSI color codes for colored output
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    // The current path of the CLI
    public static String currentPath = System.getProperty("user.dir");
    
    // Main function to run the CLI
    public static void main(String[] args) {
        // Create a scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        // Create a command parser object to parse user input
        CommandParser parser = new CommandParser();

        // Display welcome message
        System.out.println("Welcome to the CLI. Type 'help' for available commands.");

        // Main loop to read user input
        while (true) {
            // Display the current path and prompt
            System.out.print(currentPath+"> ");
            String input = scanner.nextLine();

            // Check if the input is empty
            if(input.trim().isEmpty()) continue;

            // Parse the input and get the corresponding command
            CommandExecutor command = parser.parse(input);

            // Execute the command if it is recognized by the parser    
            if (command != null) {
                command.execute(input);
            } else {
                // Print error message if the command is not recognized
                System.out.println(ANSI_RED + "Invalid command. Type 'help' for a list of commands." + ANSI_RESET);
            }

        }
    }

}
