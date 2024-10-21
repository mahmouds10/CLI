package org.example;

public class HelpCommand implements CommandExecutor {

    @Override
    public void execute(String input) {
        System.out.println("Available Commands:");
        System.out.println("pwd    ==> Prints the current working directory.");
        System.out.println("cd     ==> Changes the current directory.");
        System.out.println("ls     ==> Lists the contents of the current directory.");
        System.out.println("mkdir  ==> Creates a new directory.");
        System.out.println("rmdir  ==> Removes an empty directory.");
        System.out.println("touch  ==> Creates a new empty file.");
        System.out.println("mv     ==> Moves or renames a file or directory.");
        System.out.println("rm     ==> Removes a file or directory.");
        System.out.println("cat    ==> Displays the content of a file.");
        System.out.println("exit   ==> Exits the application.");
        System.out.println("help   ==> Provides information about available commands.");

    }
}
