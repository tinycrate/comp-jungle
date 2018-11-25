package hk.edu.polyu.comp.comp2021.jungle.controller;

import hk.edu.polyu.comp.comp2021.jungle.view.ConsoleUIView;
import hk.edu.polyu.comp.comp2021.jungle.view.GraphicsUIVIew;
import hk.edu.polyu.comp.comp2021.jungle.view.UIView;

/**
 * This class handles the launching of the game
 */
public class ApplicationController {
    /**
     * Launches the game application
     *
     * @param args Command line arguments
     */
    public void run(String[] args) {
        // starts the game
        UIView view = (args.length > 0 && args[0].equals("-cli")) ? new ConsoleUIView() : new GraphicsUIVIew();
        GameLogicController game = new GameLogicController(view);
        game.start();
    }
}
