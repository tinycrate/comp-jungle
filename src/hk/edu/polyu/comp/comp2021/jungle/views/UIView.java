package hk.edu.polyu.comp.comp2021.jungle.views;

import hk.edu.polyu.comp.comp2021.jungle.models.Board;

/**
 * This interface defines the required methods to communicate with the Controllers
 */
public interface UIView {
    /**
     * Initialize the View
     */
    void init();

    /**
     * Updates the display of a game board on the View
     *
     * @param board The board to be displayed
     */
    void updateBoard(Board board);

    /**
     * Gets a command from user
     *
     * @return A command
     */
    Command getCommand();

    /**
     * Prompts the user for input
     *
     * @param message The message
     * @return The user input
     */
    String promptUser(String message);

    /**
     * Notifies the user a message
     *
     * @param message The message
     */
    void notifyUser(String message);
}
