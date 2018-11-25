package hk.edu.polyu.comp.comp2021.jungle.controller;

import hk.edu.polyu.comp.comp2021.jungle.model.Board;
import hk.edu.polyu.comp.comp2021.jungle.model.BoardConfiguration;
import hk.edu.polyu.comp.comp2021.jungle.model.Coordinates;
import hk.edu.polyu.comp.comp2021.jungle.model.Player;
import hk.edu.polyu.comp.comp2021.jungle.controller.command.Command;
import hk.edu.polyu.comp.comp2021.jungle.view.UIView;

/**
 * This class controls the main game loop and logics
 */
public class GameLogicController {

    private final UIView view;
    private Board board;
    private boolean dirty = false;

    /**
     * Initilize the GameLogicController
     *
     * @param view The UIView which it is supposed to be interacting with
     */
    public GameLogicController(UIView view) {
        this.view = view;
    }

    /**
     * Starts the game loop
     */
    public void Start() {
        view.init();
        view.setUserCommandListener(this::onCommand);
    }

    private void onCommand(Command command) {
        switch (command.getType()) {
            case NEW:
                onNewGame();
                break;
            case OPEN:
                onLoadGame(command);
                break;
            case SAVE:
                onSaveGame(command);
                break;
            case MOVE:
                onMove(command);
                break;
            case EXIT:
                onExit();
                break;
        }
    }

    private void onNewGame() {
        // Check if a game is saved
        if (dirty && !view.promptUserQuestion("You have unsaved game! You may want to save it first. Create game anyways?")) {
            return;
        }
        // Get player names
        Player playerOne, playerTwo;
        while (true) {
            String name = view.promptUser("Player one name?: ");
            if (name == null) return;
            name = name.trim();
            if (name.length() > 0) {
                playerOne = new Player(name);
                break;
            }
        }
        while (true) {
            String name = view.promptUser("Player two name?: ");
            if (name == null) return;
            name = name.trim();
            if (name.length() > 0) {
                playerTwo = new Player(name);
                break;
            }
        }
        // Creates new game
        dirty = true;
        board = new Board(BoardConfiguration.getDefault(playerOne, playerTwo));
        board.subscribeDenEvent(this::onGameOver);
        view.updateBoard(board);
    }

    private void onLoadGame(Command command) {
        // Check if a game is saved
        if (dirty && !view.promptUserQuestion("You have unsaved game! You may want to save it first. Load anyways?")) {
            return;
        }
        BoardConfiguration configuration = BoardConfiguration.load(command.getArgs()[0]);
        if (configuration != null) {
            dirty = false;
            board = new Board(configuration);
            board.subscribeDenEvent(this::onGameOver);
            view.updateBoard(board);
        } else {
            view.notifyUser("Load game failed! Check your file path and try again? ");
        }
    }

    private void onSaveGame(Command command) {
        // Check if a game is playing
        if (board == null) {
            view.notifyUser("You have no game playing right now! Nothing is saved. ");
            return;
        }
        if (BoardConfiguration.save(board, command.getArgs()[0])) {
            view.notifyUser("Game saved!");
            dirty = false;
        } else {
            view.notifyUser("Game save failed! Is the path valid? ");
        }
    }

    private void onMove(Command command) {
        // Check if a game is playing
        if (board == null) {
            if (!view.promptUserQuestion("Game is not started yet! Create one right now?")) return;
            onNewGame();
            return;
        }
        // Check if input valid
        if (!Coordinates.isValid(command.getArgs()[0]) || !Coordinates.isValid(command.getArgs()[1])) {
            view.promptUser(String.format("Invalid coordinates, Usage:%s [Enter]", command.getType().getCommandUsage()));
            view.updateBoard(board);
            return;
        }
        Coordinates from = new Coordinates(command.getArgs()[0]);
        Coordinates to = new Coordinates(command.getArgs()[1]);
        if (!board.movePiece(from, to)) {
            view.promptUser("Your move is invalid! Try again? [Enter]");
        } else {
            dirty = true;
        }
        view.updateBoard(board);
    }

    private void onExit() {
        // Check if a game is saved
        if (dirty && !view.promptUserQuestion("You have unsaved game! You may want to save it first. Exit anyways?")) {
            return;
        }
        System.exit(0);
    }

    private void onGameOver(Player winingPlayer) {
        view.updateBoard(board);
        view.notifyUser(String.format("%1$s's animals ruin their opponent's den abruptly. %1$s wins!\n(The game will now unfortunately exit becuase that's our project requirement.)", winingPlayer.getName()));
        System.exit(0);
    }

}
