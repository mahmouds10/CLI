package org.example;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class RedirectOverwriteCommand implements Command {
    // Create a scanner object to read user input
    Scanner scanner = new Scanner(System.in);

    @Override
    public void execute(String input) {
        String[] tokens = input.split(">");

        // Check the number of arguments
        if (tokens.length != 2) {
            System.out.println(CLI.ANSI_RED + "Invalid usage. Correct usage: echo content > file" + CLI.ANSI_RESET);
            return;
        }

        // Get the file name
        String fileName = tokens[1].trim();

        // Create a file object with the given file name in the specified path
        File file = new File(CLI.currentPath, fileName);

        // Check if the file exists
        if (!file.exists()) {
            // Prompt user to create a new file or not
            System.out.println(CLI.ANSI_YELLOW + "File does not exist. Do you want to create a new one and write? " + CLI.ANSI_RESET);
            System.out.println(CLI.ANSI_YELLOW + "Y --> Accept" + CLI.ANSI_RESET);
            System.out.println(CLI.ANSI_YELLOW + "N --> Reject" + CLI.ANSI_RESET);
            char option = scanner.next().charAt(0);
            if (option == 'N' || option == 'n') {
                return;
            }
            if (option != 'Y' && option != 'y') {
                System.out.println(CLI.ANSI_RED + "Invalid Option" + CLI.ANSI_RESET);
                return;
            }
        }

        // Split the output tokens and remove the first token which is the command
        String[] outputTokens = Arrays.copyOfRange(tokens[0].split(" "), 1, tokens[0].split(" ").length);

        // Join the output tokens to form the output string
        String output = String.join(" ", outputTokens);

        try (FileWriter writer = new FileWriter(file)) {
            // Write the output to the file
            writer.write(output);
            System.out.println("Output written to file: " + file.getAbsolutePath());
        } catch (IOException e) {
            // Print error message if an exception occurs
            System.out.println(CLI.ANSI_RED + "Error writing to file: " + e.getMessage() + CLI.ANSI_RESET);
        }
    }
}