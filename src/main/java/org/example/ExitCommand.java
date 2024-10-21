package org.example;

// Command to exit the CLI
public class ExitCommand implements CommandExecutor {

    @Override
    public void execute(String input) {
        System.out.println("Exiting CLI... Bye.");
        System.exit(0);
    }
}
