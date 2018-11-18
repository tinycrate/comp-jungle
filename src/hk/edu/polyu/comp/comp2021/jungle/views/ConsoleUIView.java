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
public class ConsoleUIView {

    /**
     * Prints the boards on the console
     *
     * @param board The board to be printed
     */
    public static void printBoard(Board board) {
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

    /**
     * Prints a list of string as a command history
     *
     * @param commands A list of commands
     */
    public static void printCommandHistory(String[] commands) {

    }

    /**
     * Gets a command from user
     *
     * @return A string of input for further parsing
     */
    public static String getCommand() {
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Clears the console by printing newlines
     */
    public static void clearScreen() {
        String newline = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
        newline += newline;
        System.out.println(newline);
    }

}
