package hk.edu.polyu.comp.comp2021.jungle.controllers;

import hk.edu.polyu.comp.comp2021.jungle.views.ConsoleUIView;
import hk.edu.polyu.comp.comp2021.jungle.views.GraphicsUIVIew;
import hk.edu.polyu.comp.comp2021.jungle.views.UIView;

/**
 * This class handles the launching of the game
 */
public class ApplicationController {
    /**
     * Launches the game application
     *
     * @param args Command line arguments
     */
    public void Run(String[] args) {
        // starts the game
        UIView view = (args.length > 0 && args[0].equals("-cli")) ? new ConsoleUIView() : new GraphicsUIVIew();
        GameLogicController game = new GameLogicController(view);
        game.Start();
    }
}
