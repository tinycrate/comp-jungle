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

    private Tile[][] tiles = new Tile[BOARD_WIDTH][BOARD_HEIGHT];

    /**
     * Gets a tile by coordinates
     *
     * @param coords The coordinates
     * @return The Tile in that coordinates
     */
    public Tile getTile(Coordinates coords) {
        return new Tile(tiles[coords.getX()][coords.getY()]);
    }
}
