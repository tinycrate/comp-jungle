package hk.edu.polyu.comp.comp2021.jungle;

import hk.edu.polyu.comp.comp2021.jungle.controllers.ApplicationController;

/**
 * The main class for the Jungle Game
 */
public class Application {

    /**
     * The entry point of the application
     * @param args Command line parameters
     */
    public static void main(String[] args) {
        ApplicationController app = new ApplicationController();
        app.Run();
    }
}
