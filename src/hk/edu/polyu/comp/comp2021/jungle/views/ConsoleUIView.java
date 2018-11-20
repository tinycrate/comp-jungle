package hk.edu.polyu.comp.comp2021.jungle.views;

import hk.edu.polyu.comp.comp2021.jungle.models.Board;
import hk.edu.polyu.comp.comp2021.jungle.models.Coordinates;
import hk.edu.polyu.comp.comp2021.jungle.models.tiles.Tile;

import java.util.Scanner;

/**
 * This class handles various operations related to communicating with the user via command line interface
 * Standard Java Runtime Library doesn't have a reliable cross-platform console support for features like moving cursors, therefore fancy stuff
 * like double buffering is difficult and time consuming to implement without 3rd party library. We'll have to go with what we have right now.
 */
public class ConsoleUIView implements UIView {

    private Board board;

    @Override
    public void init() {
        clearScreen();
        printWelcomeMessage();
    }

    @Override
    public void updateBoard(Board board) {
        this.board = board;
        updateScreen();
    }

    @Override
    public Command getCommand() {
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        String commandStr = scanner.nextLine();
        CommandType type = Command.getCommandType(commandStr);
        if (type != null) {
            Command command = Command.getCommand(commandStr);
            if (command != null) return command;
            System.out.format("Invalid command. Usage: \"%s\" [Enter]", type.getCommandUsage());
        } else {
            System.out.print("Unknown command. [Enter]");
        }
        scanner.nextLine();
        updateScreen();
        return null;
    }

    @Override
    public String promptUser(String message) {
        System.out.format("%s: ", message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public void notifyUser(String message) {
        System.out.println(message);
    }

    private void updateScreen() {
        clearScreen();
        printBoard();
    }

    private void printBoard() {
        if (board == null) return;
        for (int y = 0; y < Board.BOARD_HEIGHT; y++) {
            System.out.println("**********************");
            for (int x = 0; x < Board.BOARD_WIDTH; x++) {
                System.out.print("*");
                Tile tile = board.getTile(new Coordinates(x, y));
                String symbol = (tile.isOccupied()) ? tile.getOccupiedPiece().getSymbol() : "  ";
                System.out.print(symbol);
            }
            System.out.println("*");
        }
        System.out.println("**********************");
    }

    private void printWelcomeMessage() {
        // TODO: Beautify it
        System.out.format("Welcome!! \nUse %s to start a new game \n Or %s to load a saved game.%n", CommandType.NEW.getCommandUsage(), CommandType.OPEN.getCommandUsage());
    }

    private void clearScreen() {
        String newline = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
        newline += newline;
        System.out.println(newline);
    }

}
