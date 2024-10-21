package org.example;
import java.io.File;

// Command to list files in the current directory
public class LsCommand implements CommandExecutor {

    @Override
    public void execute(String input) {
        String[] tokens = input.split(" ");
        boolean showHidden = false;  // For 'ls -a'
        boolean recursive = false;   // For 'ls -r'

        // Check the number of arguments and determine the flags
        if (tokens.length > 1) {
            if (tokens[1].equals("-a")) {
                showHidden = true; // Show hidden files using 'ls -a'
            } else if (tokens[1].equals("-r")) {
                recursive = true; // Show files recursively using 'ls -r'
            } else {
                // Print error message if the option is invalid
                System.out.println(CLI.ANSI_YELLOW + "Invalid option. Usage: ls, ls -a, ls -r" + CLI.ANSI_RESET);
                return;
            }
        }

        // Get the current directory
        File currentDir = new File(CLI.currentPath);
        listFiles(currentDir, showHidden, recursive, "");
    }

    // Helper method to list files in a directory
    private void listFiles(File dir, boolean showHidden, boolean recursive, String indent) {
        // Get the list of files in the directory
        File[] files = dir.listFiles();

        // Check if the directory is empty
        if (files == null || files.length == 0) {
            // Print message if the directory is empty
            System.out.println(CLI.ANSI_YELLOW + "No files in current directory" + CLI.ANSI_RESET);
            return;
        }

        // Loop through the files and print their names
        for (File file : files) {

            // Skip hidden files if the option is not set
            if (!showHidden && file.isHidden()) {
                continue;
            }

            System.out.println(indent + file.getName());

            // Recursively list files in subdirectories
            if (recursive && file.isDirectory()) {
                listFiles(file, showHidden, true, indent + "  ");
            }
        }
    }
}
