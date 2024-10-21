package org.example;
import java.io.File;
import java.io.IOException;

public class TouchCommand implements CommandExecutor {
    @Override
    public void execute(String input) {
        String[] tokens = input.split(" ");
        if (tokens.length != 2) {
            System.out.println(CLI.ANSI_RED + "Invalid usage. Correct usage: touch <file_name>" + CLI.ANSI_RESET);
            return;
        }

        File file = new File(CLI.currentPath, tokens[1]);
        try {
            if (file.exists()) {
                System.out.println("File already exists.");
                file.setLastModified(System.currentTimeMillis());
            } else if (file.createNewFile()) {
                System.out.println("File created: " + file.getAbsolutePath());
            } else {
                System.out.println(CLI.ANSI_RED + "Failed to create file." + CLI.ANSI_RESET);
            }
        } catch (IOException e) {
            System.out.println(CLI.ANSI_RED + "Error creating file: " + e.getMessage() + CLI.ANSI_RESET);
        }
    }
}
