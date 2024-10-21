package org.example;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

// Command to append output to a file
public class RedirectAppendCommand implements CommandExecutor {

    @Override
    public void execute(String input) {
        String[] tokens = input.split(">>");

        // Check the number of arguments
        if (tokens.length != 2) {
            System.out.println(CLI.ANSI_RED+"Invalid usage. Correct usage: echo >> file"+CLI.ANSI_RESET);
            return;
        }
        // Get the file name
        String fileName = tokens[1].trim();

        // Split the output tokens and remove the first token which is the command
        String[] outputTokens = Arrays.copyOfRange(tokens[0].split(" "), 1, tokens[0].split(" ").length);
        
        // Join the output tokens to form the output string
        String output = String.join(" ", outputTokens);

        // Create a file object with the given file name
        File file = new File(CLI.currentPath, fileName);
        try (FileWriter writer = new FileWriter(file, true)) {
            // Append the output to the file
            writer.write(output);
            System.out.println("Output appended to file: " + file.getAbsolutePath());
        } catch (IOException e) {
            // Print error message if an exception occurs
            System.out.println(CLI.ANSI_RED+"Error writing to file: " + e.getMessage()+ CLI.ANSI_RESET);
        }
    }
}
