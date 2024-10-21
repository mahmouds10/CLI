package org.example;
import java.io.File;

// Command to print the current working directory
public class PwdCommand implements CommandExecutor {

    @Override
    public void execute(String input) {
        System.out.println(CLI.currentPath);
    }
}