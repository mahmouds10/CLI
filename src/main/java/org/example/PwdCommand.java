package org.example;

// Command to print the current working directory
public class PwdCommand implements Command {

    @Override
    public void execute(String input) {
        System.out.println(CLI.currentPath);
    }
}