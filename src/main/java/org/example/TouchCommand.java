package org.example;
import java.io.File;
import java.io.IOException;

// Command to create a new file
public class TouchCommand implements Command {
    @Override
    public void execute(String input) {
        String[] tokens = input.split(" ");

        // Check the number of arguments
        if (tokens.length != 2) {
            System.out.println(CLI.ANSI_RED + "Invalid usage. Correct usage: touch <file_name>" + CLI.ANSI_RESET);
            return;
        }

        // Create a file object with the new file name
        File file = new File(CLI.currentPath, tokens[1]);
        try {
            // Check if the file already exists
            if (file.exists()) {
                System.out.println(CLI.ANSI_RED+"File already exists."+CLI.ANSI_RESET);
                file.setLastModified(System.currentTimeMillis());
            } else if (file.createNewFile()) {
                // Create the file
                System.out.println("File created: " + file.getAbsolutePath());
            } else {
                System.out.println(CLI.ANSI_RED + "Failed to create file." + CLI.ANSI_RESET);
            }
        } catch (IOException e) {
            // Print error message if the file creation fails
            System.out.println(CLI.ANSI_RED + "Error creating file: " + e.getMessage() + CLI.ANSI_RESET);
        }
    }
}
