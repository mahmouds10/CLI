package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

// Command to move or rename a file
public class MvCommand implements Command {

    @Override
    public void execute(String input) {
        String[] tokens = input.split(" ");

        // Check number of arguments (either 3 or 4 tokens)
        if (tokens.length != 3 && tokens.length != 4) {
            System.out.println(CLI.ANSI_RED + "Invalid usage. Correct usage: mv <source> <destination> [new_name]" + CLI.ANSI_RESET);
            return;
        }

        // Create Path object for the source file
        Path sourcePath = new File(CLI.currentPath, tokens[1]).toPath();

        // Check if the source file exists
        if (!Files.exists(sourcePath)) {
            System.out.println(CLI.ANSI_RED + "Source file does not exist." + CLI.ANSI_RESET);
            return;
        }

        Path destinationPath;

        if (tokens.length == 3) {
            // Move to the destination directory, keeping the same name
            destinationPath = new File(tokens[2], sourcePath.getFileName().toString()).toPath();
        } else {
            // Move to the destination directory with the new name
            destinationPath = new File(tokens[2], tokens[3]).toPath();
        }

        try {
            // Ensure the destination directory exists
            Files.createDirectories(destinationPath.getParent());

            // Move or rename the file
            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

            // Print success message
            if (tokens.length == 3) {
                System.out.println("Moved " + sourcePath.getFileName() + " to " + destinationPath.toAbsolutePath());
            } else {
                System.out.println("Renamed " + sourcePath.getFileName() + " to " + destinationPath.getFileName() + " and moved to " + destinationPath.getParent().toAbsolutePath());
            }

        } catch (IOException e) {
            System.out.println(CLI.ANSI_RED + "Failed to move or rename file: " + e.getMessage() + CLI.ANSI_RESET);
        }
    }
}
