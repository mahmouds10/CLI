package org.example;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class RedirectAppendCommand implements CommandExecutor {

    @Override
    public void execute(String input) {
        String[] tokens = input.split(">>");
        if (tokens.length != 2) {
            System.out.println(CLI.ANSI_RED+"Invalid usage. Correct usage: echo >> file"+CLI.ANSI_RESET);
            return;
        }

        String fileName = tokens[1].trim();

        String[] outputTokens = Arrays.copyOfRange(tokens[0].split(" "), 1, tokens[0].split(" ").length);
        String output = String.join(" ", outputTokens);

        File file = new File(CLI.currentPath, fileName);
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(output);
            System.out.println("Output appended to file: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println(CLI.ANSI_RED+"Error writing to file: " + e.getMessage()+ CLI.ANSI_RESET);
        }
    }
}
