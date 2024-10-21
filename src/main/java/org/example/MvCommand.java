package org.example;
import java.io.File;

public class MvCommand implements CommandExecutor {

    @Override
    public void execute(String input) {
        String[] tokens = input.split(" ");
        if (tokens.length != 3) {
            System.out.println(CLI.ANSI_RED + "Invalid usage. Correct usage: mv <source> <destination>" + CLI.ANSI_RESET);
            return;
        }

        File source = new File(CLI.currentPath, tokens[1]);
        File destination = new File(CLI.currentPath, tokens[2]);

        if (source.exists()) {
            if (source.renameTo(destination)) {
                System.out.println("Moved/Renamed " + source.getName() + " to " + destination.getName());
            } else {
                System.out.println(CLI.ANSI_RED + "Failed to move/rename file." + CLI.ANSI_RESET);
            }
        } else {
            System.out.println(CLI.ANSI_RED + "Source file does not exist." + CLI.ANSI_RESET);
        }
    }
}
