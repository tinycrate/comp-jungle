package hk.edu.polyu.comp.comp2021.jungle;

import hk.edu.polyu.comp.comp2021.jungle.controller.ApplicationController;

/**
 * The main class for the Jungle Game
 */
public class Application {

    /**
     * The entry point of the application
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        ApplicationController app = new ApplicationController();
        app.run(args);
    }
}
