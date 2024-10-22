package org.example;
import java.io.File;

public class CdCommand implements Command {

    @Override
    public void execute(String input) {
        String[] tokens = input.split(" ");

        // Check the number of arguments
        if (tokens.length != 2) {
            System.out.println(CLI.ANSI_RED + "Invalid usage" + CLI.ANSI_RESET);
            System.out.println(CLI.ANSI_YELLOW + "Hint: Correct Usage: cd <directory>" + CLI.ANSI_RESET);
            return;
        }

        // Determine the new path to move to
        String newPath = tokens[1];

        // Handle the special case of moving one step back
        if (newPath.equals("..")) {
            // Get the parent directory
            File parentDir = new File(CLI.currentPath).getParentFile();
            if (parentDir != null) {
                // Change the current path to the parent directory
                CLI.currentPath = parentDir.getAbsolutePath();
                System.out.println("Changed to " + CLI.currentPath);
            } else {
                System.out.println(CLI.ANSI_RED + "Already at the root directory." +CLI.ANSI_RESET);
            }
            return;
        }

        // Create a file object with the new path
        File dir = new File(CLI.currentPath, newPath);
        // Check if the directory exists
        if (dir.exists() && dir.isDirectory()) {
            // Change the current path to the new directory
            CLI.currentPath = dir.getAbsolutePath();
        } else {
            // Print error message if the directory does not exist
            System.out.println(CLI.ANSI_RED + "Directory not found." + CLI.ANSI_RESET);
        }
    }
}
