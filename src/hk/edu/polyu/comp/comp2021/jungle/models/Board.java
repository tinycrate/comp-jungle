package hk.edu.polyu.comp.comp2021.jungle.models;

import hk.edu.polyu.comp.comp2021.jungle.models.tiles.Tile;

/**
 * This class holds the states of the game board
 */
public class Board {
    /**
     * The width of the board
     */
    public static final int BOARD_WIDTH = 7;

    /**
     * The height of the board
     */
    public static final int BOARD_HEIGHT = 9;

    private final Tile[][] tiles;
    private final Player playerOne;
    private final Player playerTwo;

    /**
     * Initialize a board with a specific BoardConfiguration
     *
     * @param configuration The configuration of the board
     */
    public Board(BoardConfiguration configuration) {
        tiles = configuration.getTiles();
        playerOne = configuration.getPlayerOne();
        playerTwo = configuration.getPlayerTwo();
    }

    /**
     * Gets a tile by coordinates
     *
     * @param coords The coordinates
     * @return The Tile in that coordinates
     */
    public Tile getTile(Coordinates coords) {
        return tiles[coords.getX()][coords.getY()].getClone();
    }

    /**
     * @return The player object for player one
     */
    public Player getPlayerOne() {
        return playerOne;
    }

    /**
     * @return The player object for player two
     */
    public Player getPlayerTwo() {
        return playerTwo;
    }
}
