package ua.goit.command;

import ua.goit.entity.ApiResponse;
import ua.goit.entity.Pet;
import ua.goit.entity.PetStatus;
import ua.goit.entity.User;
import ua.goit.util.PetClientUtil;
import ua.goit.util.UserClientUtil;
import ua.goit.view.View;

import java.io.File;

public class UpdateCommand extends AbstractCommand implements Command{
    private static final String MENU = """
            Please, enter the number according to list below
            1 - to update user
            2 - to update pet
            3 - add photos for pet
            return - go back to main menu
            """;
    private final View view;

    public UpdateCommand(View view) {
        super(view);
        this.view = view;
    }

    @Override
    public String commandName() {
        return "update";
    }

    @Override
    public void process() {
        boolean running = true;
        while (running) {
            view.write(MENU);
            String section = view.read();
            switch (section) {
                case "1" -> updateUser();
                case "2" -> updatePet();
                case "3" -> addPhotos();
                case "return" -> running = false;
                default -> view.write("Please, enter the correct command\n");
            }
        }
    }

    private void updateUser() {
        view.write("Enter user name you would like to update");
        String userName = view.read();
        UserClientUtil.getUserByUserName(userName);
        User user = readUserFromConsole();
        ApiResponse apiResponse = UserClientUtil.updateUser(userName, user);
        resultOutput(apiResponse);
    }

    private void updatePet() {
        int id = readIntegerFromConsole("Enter pet id you would like to update");
        Pet petToUpdate = PetClientUtil.getPetById(id);
        view.write("Enter pet new name");
        String newName = view.read();
        PetStatus newStatus = readPetStatusFromConsole();
        petToUpdate.setName(newName);
        petToUpdate.setStatus(newStatus);
        ApiResponse apiResponse = PetClientUtil.updatePet(id, petToUpdate);
        resultOutput(apiResponse);
    }

    private void addPhotos() {
        int id = readIntegerFromConsole("Enter pet id you would like to update");
        view.write("Enter description to photo");
        String metaData = view.read();
        File image = readFileFromConsole();
        ApiResponse apiResponse = PetClientUtil.uploadImage(id, metaData, image);
        resultOutput(apiResponse);
    }
}