package org.example;
import java.io.File;

// Command to delete a file
public class RmCommand implements CommandExecutor {

    @Override
    public void execute(String input) {
        String[] tokens = input.split(" ");

        // Check the number of arguments
        if (tokens.length != 2) {
            System.out.println(CLI.ANSI_RED + "Invalid usage. Correct usage: rm <file_name>" + CLI.ANSI_RESET);
            return;
        }

        // Create file object with the given file name
        File file = new File(CLI.currentPath, tokens[1]);

        // Check if the file exists
        if (file.exists()) {
            // Delete the file
            if (file.delete()) {
                System.out.println("File deleted: " + file.getAbsolutePath());
            } else {
                System.out.println(CLI.ANSI_RED + "Failed to delete file." + CLI.ANSI_RESET);
            }
        } else {
            // Print error message if the file does not exist
            System.out.println(CLI.ANSI_RED + "File does not exist." + CLI.ANSI_RESET);
        }
    }
}
