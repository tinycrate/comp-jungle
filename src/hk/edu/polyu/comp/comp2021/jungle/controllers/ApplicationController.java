package hk.edu.polyu.comp.comp2021.jungle.controllers;

import hk.edu.polyu.comp.comp2021.jungle.views.ConsoleUIView;

/**
 * This class handles the launching of the game
 */
public class ApplicationController {
    /**
     * Launches the game application
     */
    public void Run() {
        // starts the game
        GameLogicController game = new GameLogicController(new ConsoleUIView());
        game.Start();
    }
}
