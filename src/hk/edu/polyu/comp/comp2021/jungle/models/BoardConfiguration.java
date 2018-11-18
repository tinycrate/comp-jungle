package hk.edu.polyu.comp.comp2021.jungle.models;

import hk.edu.polyu.comp.comp2021.jungle.models.tiles.Tile;

import java.io.Serializable;

/**
 * This class provides methods for generating, saving and loading board configurations
 */
public final class BoardConfiguration implements Serializable {
    /**
     * Generates a new board with default configurations
     *
     * @param playerOne Player object for player one
     * @param playerTwo Player object for player two
     * @return The BoardConfiguration
     */
    public static BoardConfiguration getDefault(Player playerOne, Player playerTwo) {
        BoardConfiguration configuration = new BoardConfiguration();
        configuration.setTiles(generateDefaultTiles());
        configuration.setPlayerOne(playerOne);
        configuration.setPlayerTwo(playerTwo);
        return configuration;
    }

    private static Tile[][] generateDefaultTiles() {
        return new Tile[Board.BOARD_WIDTH][Board.BOARD_HEIGHT];
    }

    private Tile[][] tiles = null;
    private Player playerOne, playerTwo;

    private BoardConfiguration() {
    }

    /**
     * @return The tiles used by Board
     */
    public Tile[][] getTiles() {
        return tiles;
    }

    private void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    /**
     * @return The player object for player one
     */
    public Player getPlayerOne() {
        return playerOne;
    }

    private void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    /**
     * @return The player object for player two
     */
    public Player getPlayerTwo() {
        return playerTwo;
    }

    private void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

}
