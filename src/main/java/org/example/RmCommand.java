package org.example;
import java.io.File;

public class RmCommand implements CommandExecutor {

    @Override
    public void execute(String input) {
        String[] tokens = input.split(" ");
        if (tokens.length != 2) {
            System.out.println(CLI.ANSI_RED + "Invalid usage. Correct usage: rm <file_name>" + CLI.ANSI_RESET);
            return;
        }

        File file = new File(CLI.currentPath, tokens[1]);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted: " + file.getAbsolutePath());
            } else {
                System.out.println(CLI.ANSI_RED + "Failed to delete file." + CLI.ANSI_RESET);
            }
        } else {
            System.out.println(CLI.ANSI_RED + "File does not exist." + CLI.ANSI_RESET);
        }
    }
}
