package org.example;

import java.io.File;

// Command to move or rename a file
public class MvCommand implements CommandExecutor {

    @Override
    public void execute(String input) {
        String[] tokens = input.split(" ");

        // Check number of arguments (either 3 or 4 tokens)
        if (tokens.length != 3 && tokens.length != 4) {
            System.out.println(CLI.ANSI_RED + "Invalid usage. Correct usage: mv <source> <destination> [new_name]" + CLI.ANSI_RESET);
            return;
        }

        // Create file object for the source file
        File source = new File(CLI.currentPath, tokens[1]);

        // Check if the source file exists
        if (!source.exists()) {
            System.out.println(CLI.ANSI_RED + "Source file does not exist." + CLI.ANSI_RESET);
            return;
        }

        File destination;

        if (tokens.length == 3) {
              // Move to the destination directory, keeping the same name
            destination = new File(tokens[2], source.getName());
        } else {
            // Move to the destination directory with the new name
            destination = new File(tokens[2], tokens[3]);  
        }

        // Move the file to the destination
        if (tokens.length == 3){
            if (source.renameTo(destination)) {
            System.out.println("Moved " + source.getName() + " to " + destination.getAbsolutePath());
            } else {
                System.out.println(CLI.ANSI_RED + "Failed to move file." + CLI.ANSI_RESET);
            }
        }
        // Rename the file and move to the destination
        else {
            if (source.renameTo(destination)) {
            System.out.println("Renamed " + source.getName() + " to " + destination.getName()+ " and moved to " + destination.getAbsolutePath());
            } else {
                System.out.println(CLI.ANSI_RED + "Failed to rename or move file." + CLI.ANSI_RESET);
            }
        }
        
    }
}
