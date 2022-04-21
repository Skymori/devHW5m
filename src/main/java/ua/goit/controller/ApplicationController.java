package ua.goit.controller;

import ua.goit.command.*;
import ua.goit.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApplicationController{
    private final View view;
    private final List<Command> commands;

    public ApplicationController(View view) {
        this.view = view;
        this.commands = new ArrayList<>(Arrays.asList(
                new HelpCommand(view),
                new CreateCommand(view),
                new ReadCommand(view),
                new UpdateCommand(view),
                new DeleteCommand(view)));
    }

    public void run() throws IOException, InterruptedException {
        view.write("Welcome to the pet store");
        doCommand();
    }

    private void doCommand() throws IOException, InterruptedException {
        boolean running = true;
        while (running) {
            view.write("Please enter a command or 'help' to retrieve command list\nEnter 'exit' to leave");
            String inputCommand = view.read();
            for (Command command : commands) {
                if (command.canProcess(inputCommand)) {
                    command.process();
                    break;
                } else if (inputCommand.equalsIgnoreCase("exit")) {
                    view.write("Good Bye!");
                    running = false;
                    break;
                }
            }
        }
    }
}
