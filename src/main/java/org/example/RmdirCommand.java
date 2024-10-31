package org.example;
import java.io.File;

// Command to delete a directory
public class RmdirCommand implements Command {

    @Override
    public void execute(String input) {
        String[] tokens = input.split(" ");

        // Check the number of arguments
        if (tokens.length != 2) {
            System.out.println(CLI.ANSI_RED + "Invalid usage. Correct usage: rmdir <directory_name>" + CLI.ANSI_RESET);
            return;
        }

        // Create file object with the given directory name
        File dir = new File(CLI.currentPath, tokens[1]);

        if (!dir.exists()) {
            System.out.println(CLI.ANSI_RED + "Directory does not exist." + CLI.ANSI_RESET);
            return;
        }
        
        if (dir.list().length != 0){
            System.out.println(CLI.ANSI_RED + "Directory is not empty." + CLI.ANSI_RESET);
            return;
        }

        

        // Check if the directory exists and is empty
        if (dir.exists() && dir.isDirectory() && dir.list().length == 0) {
            if (dir.delete()) {
                System.out.println("Directory deleted: " + dir.getAbsolutePath());
            } else {
                System.out.println(CLI.ANSI_RED + "Failed to delete directory." + CLI.ANSI_RESET);
            }
        }
    }
}
