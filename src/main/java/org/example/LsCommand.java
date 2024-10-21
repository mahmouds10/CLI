package org.example;
import java.io.File;

public class LsCommand implements CommandExecutor {

    @Override
    public void execute(String input) {
        String[] tokens = input.split(" ");
        boolean showHidden = false;  // For 'ls -a'
        boolean recursive = false;   // For 'ls -r'

        if (tokens.length > 1) {
            if (tokens[1].equals("-a")) {
                showHidden = true;
            } else if (tokens[1].equals("-r")) {
                recursive = true;
            } else {
                System.out.println(CLI.ANSI_YELLOW + "Invalid option. Usage: ls, ls -a, ls -r" + CLI.ANSI_RESET);
                return;
            }
        }

        File currentDir = new File(CLI.currentPath);
        listFiles(currentDir, showHidden, recursive, "");
    }

    private void listFiles(File dir, boolean showHidden, boolean recursive, String indent) {
        File[] files = dir.listFiles();

        if (files == null || files.length == 0) {
            System.out.println(CLI.ANSI_YELLOW + "No files in current directory" + CLI.ANSI_RESET);
            return;
        }

        for (File file : files) {

            if (!showHidden && file.isHidden()) {
                continue;
            }

            System.out.println(indent + file.getName());

            if (recursive && file.isDirectory()) {
                listFiles(file, showHidden, true, indent + "  ");
            }
        }
    }
}
