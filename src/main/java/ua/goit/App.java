package ua.goit;

import ua.goit.controller.ApplicationController;
import ua.goit.view.Console;
import ua.goit.view.View;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        View view = new Console(System.in, System.out);
        ApplicationController applicationController = new ApplicationController(view);
        applicationController.run();

    }
}