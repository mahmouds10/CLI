package org.example;
import java.io.File;

public class CdCommand implements CommandExecutor {

    @Override
    public void execute(String input) {
        String[] tokens = input.split(" ");

        if (tokens.length != 2) {
            System.out.println(CLI.ANSI_RED + "Invalid usage" + CLI.ANSI_RESET);
            System.out.println(CLI.ANSI_YELLOW + "Hint: Correct Usage: cd <directory>" + CLI.ANSI_RESET);
            return;
        }

        String newPath = tokens[1];

        if (newPath.equals("..")) {
            File parentDir = new File(CLI.currentPath).getParentFile();
            if (parentDir != null) {
                CLI.currentPath = parentDir.getAbsolutePath();
                System.out.println("Changed to " + CLI.currentPath);
            } else {
                System.out.println(CLI.ANSI_RED + "Already at the root directory." +CLI.ANSI_RESET);
            }
            return;
        }

        File dir = new File(CLI.currentPath, newPath);
        if (dir.exists() && dir.isDirectory()) {
            CLI.currentPath = dir.getAbsolutePath();
        } else {
            System.out.println(CLI.ANSI_RED + "Directory not found." + CLI.ANSI_RESET);
        }
    }
}
