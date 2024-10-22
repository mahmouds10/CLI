package org.example;

// Command to display available commands with usage and options
public class HelpCommand implements Command {

    @Override
    public void execute(String input) {
        System.out.println("Available Commands and Usage:");
        System.out.println("--------------------------------------------");

        System.out.println("1. pwd");
        System.out.println("   Usage  : pwd");
        System.out.println("   Desc   : Prints the current working directory.");
        System.out.println("--------------------------------------------");

        System.out.println("2. cd");
        System.out.println("   Usage  : cd <directory>");
        System.out.println("   Desc   : Changes the current directory to the specified <directory>.");
        System.out.println("   Options: If no directory is provided, changes to the home directory.");
        System.out.println("--------------------------------------------");

        System.out.println("3. ls");
        System.out.println("   Usage  : ls [options]");
        System.out.println("   Desc   : Lists the contents of the current directory.");
        System.out.println("   Options:");
        System.out.println("      -a : Lists all files, including hidden files.");
        System.out.println("      -l : Lists files with detailed information (permissions, size, etc.).");
        System.out.println("--------------------------------------------");

        System.out.println("4. mkdir");
        System.out.println("   Usage  : mkdir <directory_name>");
        System.out.println("   Desc   : Creates a new directory with the specified <directory_name>.");
        System.out.println("--------------------------------------------");

        System.out.println("5. rmdir");
        System.out.println("   Usage  : rmdir <directory_name>");
        System.out.println("   Desc   : Removes the specified empty directory.");
        System.out.println("--------------------------------------------");

        System.out.println("6. touch");
        System.out.println("   Usage  : touch <file_name>");
        System.out.println("   Desc   : Creates a new empty file with the specified <file_name>.");
        System.out.println("--------------------------------------------");

        System.out.println("7. mv");
        System.out.println("   Usage  : mv <source> <destination>");
        System.out.println("            mv <source> <destination> <new_name>");
        System.out.println("   Desc   : Moves or renames a file or directory.");
        System.out.println("   Options:");
        System.out.println("      - If 3 arguments are provided, the source file is moved to the destination.");
        System.out.println("      - If 4 arguments are provided, the source file is moved and renamed.");
        System.out.println("--------------------------------------------");

        System.out.println("8. rm");
        System.out.println("   Usage  : rm <file_name>");
        System.out.println("   Desc   : Removes the specified file.");
        System.out.println("--------------------------------------------");

        System.out.println("9. cat");
        System.out.println("   Usage  : cat <file_name>");
        System.out.println("   Desc   : Displays the content of the specified <file_name>.");
        System.out.println("--------------------------------------------");

        System.out.println("10. exit");
        System.out.println("    Usage  : exit");
        System.out.println("    Desc   : Exits the application.");
        System.out.println("--------------------------------------------");

        System.out.println("11. help");
        System.out.println("    Usage  : help");
        System.out.println("    Desc   : Provides detailed information about available commands.");
        System.out.println("--------------------------------------------");

        System.out.println("12. echo");
        System.out.println("    Usage  : echo <text> [operator] <file_name>");
        System.out.println("    Operators:");
        System.out.println("       >   : Overwrite the content of the specified file with <text>.");
        System.out.println("       >>  : Append <text> to the end of the specified file.");
        System.out.println("    Desc   : Outputs <text> to the console or to a file, overwriting or appending based on the operator.");
        System.out.println("--------------------------------------------");
    }
}
