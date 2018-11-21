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
        while (true) {
            System.out.print("> ");
            Scanner scanner = new Scanner(System.in);
            String commandStr = scanner.nextLine();
            CommandType type = Command.getCommandType(commandStr);
            if (type != null) {
                Command command = Command.getCommand(commandStr);
                if (command != null) return command;
                System.out.format("Invalid command. Usage: \"%s\"%n", type.getCommandUsage());
            } else {
                System.out.println("Unknown command.");
            }
        }
    }

    @Override
    public String promptUser(String message) {
        System.out.print(message);
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
        String fullWidthNumbers = "９８７６５４３２１";
        System.out.println("　    Ａ     Ｂ     Ｃ     Ｄ     Ｅ     Ｆ     Ｇ   ");
        for (int y = 0; y < Board.BOARD_HEIGHT; y++) {
            System.out.println("   +  －  +  －  +  －  +  －  +  －  +  －  +  －  +");
            System.out.format("%c ", fullWidthNumbers.charAt(y));
            for (int x = 0; x < Board.BOARD_WIDTH; x++) {
                System.out.print("|");
                Tile tile = board.getTile(new Coordinates(x, y));
                String symbol;
                if (tile.isOccupied()) {
                    symbol = tile.getOccupiedPiece().getSymbol();
                    symbol = String.format((tile.getOwner() == board.getCurrentPlayer()) ? "(%s)" : "%s", symbol);
                    switch (tile.getTileType()) {
                        case RIVER:
                            symbol = String.format("~%s~", symbol);
                            break;
                        case TRAP:
                            if (tile.getOccupiedPiece().isWeakenByTrap(board)) symbol = String.format("{%s}", symbol);
                            break;
                    }
                    while (symbol.length() < 5) symbol = String.format(" %s ", symbol);
                } else {
                    symbol = String.format("  %s  ", tile.getTileType().getPlaceHolder());
                }
                System.out.print(symbol);
            }
            System.out.println("|");
        }
        System.out.println("   +  －  +  －  +  －  +  －  +  －  +  －  +  －  +");
    }

    private void printWelcomeMessage() {
        // TODO: Beautify it
        System.out.format("Welcome!! \nUse %s to start a new game \nOr %s to load a saved game.%n", CommandType.NEW.getCommandUsage(), CommandType.OPEN.getCommandUsage());
    }

    private void clearScreen() {
        String newline = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
        newline += newline;
        System.out.println(newline);
    }

}
