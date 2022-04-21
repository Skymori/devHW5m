package ua.goit.command;

import java.io.IOException;

public interface Command {
    String commandName();
    void process() throws IOException, InterruptedException;

    default boolean canProcess(String command){
        return commandName().equals(command);
    }

}
