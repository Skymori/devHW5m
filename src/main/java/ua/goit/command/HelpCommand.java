package ua.goit.command;

import ua.goit.view.View;

public record HelpCommand(View view) implements Command {

    @Override
    public String commandName() {
        return "help";
    }

    @Override
    public void process() {
        view.write("""
                create - to create user/pet/order
                read - to get existing info
                update - to update existing info
                delete - to delete info
                exit - exit from an application""");
    }
}
