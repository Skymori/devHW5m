package ua.goit.command;

import ua.goit.entity.ApiResponse;
import ua.goit.entity.Order;
import ua.goit.entity.Pet;
import ua.goit.entity.User;
import ua.goit.util.OrderClientUtil;
import ua.goit.util.PetClientUtil;
import ua.goit.util.UserClientUtil;
import ua.goit.view.View;

import java.util.ArrayList;
import java.util.List;

public class CreateCommand extends AbstractCommand implements Command{
    private static final String MENU = " Please, enter the number according to list below" +
            "1 - create user " +
            "2 - create pet" +
            "3 - create order" +
            " return - go back to main menu ";
    private static final String USER_MENU = " Please, enter the number according to list below " +
            "1 - create single user " +
            "2 - create users with list " +
            "return - go back to main menu ";


    private final View view;

    public CreateCommand(View view) {
        super(view);
        this.view = view;
    }

    @Override
    public String commandName() {
        return "create";
    }

    @Override
    public void process() {
        boolean running = true;
        while (running) {
            view.write(MENU);
            String section = view.read();
            switch (section) {
                case "1" -> userMenuProcess();
                case "2" -> createPet();
                case "3" -> createOrder();
                case "return" -> running = false;
                default -> view.write("Please, enter the correct ua.goit.command\n");
            }
        }
    }

    private void userMenuProcess() {
        boolean running = true;
        while (running) {
            view.write(USER_MENU);
            String section = view.read();
            switch (section) {
                case "1" -> createSingleUser();
                case "2" -> createUsersWithList();
                case "return" -> running = false;
                default -> view.write("Please, enter the correct ua.goit.command\n");
            }
        }
    }


    private void createSingleUser() {
        User user = readUserFromConsole();
        ApiResponse apiResponse = UserClientUtil.createUser(user);
        resultOutput(apiResponse);

    }

    private void createUsersWithList() {
        List<User> users = new ArrayList<>();
        User user;
        boolean running = true;
        view.write(" Please, enter the user info according to requests below Enter 'ok' when finish ");
        while (running) {
            user = readUserFromConsole();
            users.add(user);
            view.write(user + "was successfully added to list " +
                    "Press 'enter' to continue" +
                    "Enter 'ok' when finish ");

            if (view.read().equalsIgnoreCase("ok")) {
                running = false;
            }
        }
        ApiResponse apiResponse = UserClientUtil.createUserArray(users);
        if (apiResponse.getCode() == 200) {
            view.write("Users :");
            users.forEach(u -> view.write(u.toString()));
        } else {
            view.write("Failed to create a user" +
                    "Response -" + apiResponse);
        }
        view.write("Were successfully created");
    }

    private void createPet() {
        Pet pet = readPetFromConsole();
        Pet created = PetClientUtil.createPet(pet);
        view.write(created + "was successfully created");
    }

    private void createOrder() {
        Order order = readOrderFromConsole();
        Order created = OrderClientUtil.createOrder(order);
        view.write(created + "was successfully created");
    }

}