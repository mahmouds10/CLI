package org.example;
import java.io.File;

// Command to create a new directory
public class MkdirCommand implements Command {

    @Override
    public void execute(String input) {
        String[] tokens = input.split(" ");

        // Check the number of arguments
        if (tokens.length != 2) {
            System.out.println(CLI.ANSI_RED + "Invalid usage. Correct usage: mkdir <directory_name>" + CLI.ANSI_RESET);
            return;
        }

        // Create a file object with the new directory name
        File dir = new File(CLI.currentPath, tokens[1]);

        // Check if the directory already exists
        if (!dir.exists()) {
            // Create the directory
            if (dir.mkdir()) {
                System.out.println("Directory created: " + dir.getAbsolutePath());
            } else {
                // Print error message if the directory creation fails
                System.out.println(CLI.ANSI_RED + "Failed to create directory." + CLI.ANSI_RESET);
            }
        } else {
            // Print message if the directory already exists
            System.out.println(CLI.ANSI_RED + "Directory already exists." + CLI.ANSI_RESET);
        }
    }
}
