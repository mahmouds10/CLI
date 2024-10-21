package org.example;
import java.io.File;


public class MkdirCommand implements CommandExecutor {

    @Override
    public void execute(String input) {
        String[] tokens = input.split(" ");
        if (tokens.length != 2) {
            System.out.println(CLI.ANSI_RED + "Invalid usage. Correct usage: mkdir <directory_name>" + CLI.ANSI_RESET);
            return;
        }

        File dir = new File(CLI.currentPath, tokens[1]);
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Directory created: " + dir.getAbsolutePath());
            } else {
                System.out.println(CLI.ANSI_RED + "Failed to create directory." + CLI.ANSI_RESET);
            }
        } else {
            System.out.println(CLI.ANSI_RED + "Directory already exists." + CLI.ANSI_RESET);
        }
    }
}
