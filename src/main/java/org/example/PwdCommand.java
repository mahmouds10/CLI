package org.example;
import java.io.File;

public class PwdCommand implements CommandExecutor {

    @Override
    public void execute(String input) {
        System.out.println(CLI.currentPath);
    }
}