package org.example;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class CatCommand implements CommandExecutor {

    @Override
    public void execute(String input) {
        String[] tokens = input.split(" ");
        if (tokens.length != 2) {
            System.out.println(CLI.ANSI_RED + "Invalid usage. Correct usage: cat <file_name>" + CLI.ANSI_RESET);
            return;
        }

        File file = new File(CLI.currentPath, tokens[1]);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println(CLI.ANSI_RED + "Error reading file: " + e.getMessage() + CLI.ANSI_RESET);
            }
        } else {
            System.out.println(CLI.ANSI_RED + "File not found." +CLI. ANSI_RESET);
        }
    }
}
