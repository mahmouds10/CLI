package org.example;
import java.io.File;

public class RmdirCommand implements CommandExecutor {

    @Override
    public void execute(String input) {
        String[] tokens = input.split(" ");
        if (tokens.length != 2) {
            System.out.println(CLI.ANSI_RED + "Invalid usage. Correct usage: rmdir <directory_name>" + CLI.ANSI_RESET);
            return;
        }

        File dir = new File(CLI.currentPath, tokens[1]);
        if (dir.exists() && dir.isDirectory() && dir.list().length == 0) {
            if (dir.delete()) {
                System.out.println("Directory deleted: " + dir.getAbsolutePath());
            } else {
                System.out.println(CLI.ANSI_RED + "Failed to delete directory." + CLI.ANSI_RESET);
            }
        } else {
            System.out.println(CLI.ANSI_RED + "Directory is either not empty or does not exist." + CLI.ANSI_RESET);
        }
    }
}
