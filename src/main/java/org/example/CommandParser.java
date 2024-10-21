package org.example;
import java.util.HashMap;

public class CommandParser {
    private final HashMap<String, CommandExecutor> commands;

    public CommandParser() {
        commands = new HashMap<>();
        commands.put("pwd", new PwdCommand());
        commands.put("cd", new CdCommand());
        commands.put("ls", new LsCommand());
        commands.put("mkdir", new MkdirCommand());
        commands.put("rmdir", new RmdirCommand());
        commands.put("touch", new TouchCommand());
        commands.put("mv", new MvCommand());
        commands.put("rm", new RmCommand());
        commands.put("cat", new CatCommand());
        commands.put("exit", new ExitCommand());
        commands.put("help", new HelpCommand());
        commands.put(">", new RedirectOverwriteCommand());
        commands.put(">>", new RedirectAppendCommand());
    }

    public CommandExecutor parse(String input) {

        if (input.contains(">>")) {
            return new RedirectAppendCommand();
        }

        if (input.contains(">")) {
            return new RedirectOverwriteCommand();
        }

        String[] tokens = input.split(" ");
        CommandExecutor command = commands.get(tokens[0]);

        if (command != null) {
            return command;
        } else {
            System.out.println("Unknown command: " + tokens[0]);
            return null;
        }
    }
}
