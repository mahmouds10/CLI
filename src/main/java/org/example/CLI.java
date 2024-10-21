package org.example;
import java.util.Scanner;
public class CLI {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static String currentPath = System.getProperty("user.dir");
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandParser parser = new CommandParser();

        System.out.println("Welcome to the CLI. Type 'help' for available commands.");

        while (true) {
            System.out.print(currentPath+"> ");
            String input = scanner.nextLine();

            if(input.trim().isEmpty()) continue;

            CommandExecutor command = parser.parse(input);

            if (command != null) {
                command.execute(input);
            } else {
                System.out.println(ANSI_RED + "Invalid command. Type 'help' for a list of commands." + ANSI_RESET);
            }

        }
    }

}
