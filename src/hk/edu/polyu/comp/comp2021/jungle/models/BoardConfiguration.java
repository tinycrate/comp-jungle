package hk.edu.polyu.comp.comp2021.jungle.models;

import hk.edu.polyu.comp.comp2021.jungle.models.pieces.*;
import hk.edu.polyu.comp.comp2021.jungle.models.tiles.*;

import java.io.Serializable;
import java.util.Arrays;

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
        configuration.setTiles(generateDefaultTiles(playerOne, playerTwo));
        configuration.setPlayerOne(playerOne);
        configuration.setPlayerTwo(playerTwo);
        return configuration;
    }

    private static Tile[][] generateDefaultTiles(Player playerOne, Player playerTwo) {
        Tile[][] tiles = new Tile[Board.BOARD_WIDTH][Board.BOARD_HEIGHT];
        // Fill Grass
        for (Tile[] column : tiles) {
            Arrays.fill(column, new NeutralTile(TileType.GRASS));
        }
        // Fill River
        for (int i = 1; i <= 2; i++) {
            Arrays.fill(tiles[i], 3, 6, new NeutralTile(TileType.RIVER));
        }
        for (int i = 4; i <= 5; i++) {
            Arrays.fill(tiles[i], 3, 6, new NeutralTile(TileType.RIVER));
        }
        // Fill Traps for player 1
        tiles[2][8] = new TrapTile(playerOne);
        tiles[3][7] = new TrapTile(playerOne);
        tiles[4][8] = new TrapTile(playerOne);
        // Fill Traps for player 2
        tiles[2][0] = new TrapTile(playerTwo);
        tiles[3][1] = new TrapTile(playerTwo);
        tiles[4][0] = new TrapTile(playerTwo);
        // Fill Dens
        tiles[3][8] = new DenTile(playerOne);
        tiles[3][0] = new DenTile(playerTwo);
        // Fill pieces
        int mirror = 1;
        for (Player player : new Player[]{playerOne, playerTwo}) {
            // Backline
            tiles[3 - 3 * mirror][4 + 4 * mirror].setOccupiedPiece(new Tiger(new Coordinates(3 - 3 * mirror, 4 + 4 * mirror), player));
            tiles[3 + 3 * mirror][4 + 4 * mirror].setOccupiedPiece(new Lion(new Coordinates(3 + 3 * mirror, 4 + 4 * mirror), player));
            // Middle
            tiles[3 - 2 * mirror][4 + 3 * mirror].setOccupiedPiece(new Cat(new Coordinates(3 - 2 * mirror, 4 + 3 * mirror), player));
            tiles[3 + 2 * mirror][4 + 3 * mirror].setOccupiedPiece(new Cat(new Coordinates(3 + 2 * mirror, 4 + 3 * mirror), player));
            // Frontline
            tiles[3 - 3 * mirror][4 + 2 * mirror].setOccupiedPiece(new Elephant(new Coordinates(3 - 3 * mirror, 4 + 2 * mirror), player));
            tiles[3 - mirror][4 + 2 * mirror].setOccupiedPiece(new Wolf(new Coordinates(3 - mirror, 4 + 2 * mirror), player));
            tiles[3 + mirror][4 + 2 * mirror].setOccupiedPiece(new Leopard(new Coordinates(3 + mirror, 4 + 2 * mirror), player));
            tiles[3 + 3 * mirror][4 + 2 * mirror].setOccupiedPiece(new Rat(new Coordinates(3 + 3 * mirror, 4 + 2 * mirror), player));
            mirror = -1;
        }
        return tiles;
    }

    private Tile[][] tiles;
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
