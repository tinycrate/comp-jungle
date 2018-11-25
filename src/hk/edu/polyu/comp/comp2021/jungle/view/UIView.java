package hk.edu.polyu.comp.comp2021.jungle.view;

import hk.edu.polyu.comp.comp2021.jungle.model.Board;
import hk.edu.polyu.comp.comp2021.jungle.controller.command.CommandListener;

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
     * Sets a command listener for this view
     * When new command is received, the command listener will be triggered
     *
     * @param listener The listener
     */
    void setUserCommandListener(CommandListener listener);

    /**
     * Prompts the user for input
     *
     * @param message The message
     * @return The user input, null if the user cancels the input
     */
    String promptUser(String message);

    /**
     * Prompts the user for a yes/no answer
     *
     * @param message The message
     * @return The user input
     */
    boolean promptUserQuestion(String message);

    /**
     * Notifies the user a message
     *
     * @param message The message
     */
    void notifyUser(String message);
}
