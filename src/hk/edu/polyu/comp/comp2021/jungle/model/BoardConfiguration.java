package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.model.pieces.*;
import hk.edu.polyu.comp.comp2021.jungle.model.tiles.*;

import java.io.*;

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
        configuration.setCurrentPlayer(playerOne);
        return configuration;
    }

    /**
     * Saves the current board configurations to disk
     *
     * @param board The current board
     * @param path  Path to be saved at
     * @return True if the operation is successful
     */
    public static boolean save(Board board, String path) {
        try {
            FileOutputStream out = new FileOutputStream(path);
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            Tile[][] tiles = new Tile[Board.BOARD_WIDTH][Board.BOARD_HEIGHT];
            for (int i = 0; i < Board.BOARD_WIDTH; i++) {
                for (int j = 0; j < Board.BOARD_HEIGHT; j++) {
                    tiles[i][j] = board.getTile(new Coordinates(i, j));
                }
            }
            BoardConfiguration configuration = new BoardConfiguration();
            configuration.setPlayerOne(board.getPlayerOne());
            configuration.setPlayerTwo(board.getPlayerTwo());
            configuration.setTiles(tiles);
            configuration.setCurrentPlayer(board.getCurrentPlayer());
            objOut.writeObject(configuration);
            objOut.close();
            out.close();
            return true;
        } catch (NotSerializableException e) {
            throw new RuntimeException("This class is not serializable, please fix your code!");
        } catch (IOException | SecurityException e) {
            return false;
        }
    }

    /**
     * Loads an exisitng board configuration from disk
     *
     * @param path The path to the file
     * @return The obtained BoardConfiguration, if the operation is not successful, return null
     */
    public static BoardConfiguration load(String path) {
        try {
            FileInputStream in = new FileInputStream(path);
            ObjectInputStream objIn = new ObjectInputStream(in);
            BoardConfiguration configuration = (BoardConfiguration) objIn.readObject();
            objIn.close();
            in.close();
            return configuration;
        } catch (IOException | SecurityException | ClassNotFoundException e) {
            return null;
        }
    }

    private static Tile[][] generateDefaultTiles(Player playerOne, Player playerTwo) {
        Tile[][] tiles = new Tile[Board.BOARD_WIDTH][Board.BOARD_HEIGHT];
        // Fill Grass
        for (int y = 0; y < Board.BOARD_HEIGHT; y++) {
            for (int x = 0; x < Board.BOARD_WIDTH; x++) {
                tiles[x][y] = new NeutralTile(TileType.GRASS);
            }
        }
        // Fill River
        for (int x = 1; x <= 5; x++) {
            if (x == 3) continue;
            for (int y = 3; y <= 5; y++) {
                tiles[x][y] = new NeutralTile(TileType.RIVER);
            }
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
            tiles[3 - 3 * mirror][4 + 4 * mirror].setOccupiedPiece(new Tiger(player));
            tiles[3 + 3 * mirror][4 + 4 * mirror].setOccupiedPiece(new Lion(player));
            // Middle
            tiles[3 - 2 * mirror][4 + 3 * mirror].setOccupiedPiece(new Cat(player));
            tiles[3 + 2 * mirror][4 + 3 * mirror].setOccupiedPiece(new Dog(player));
            // Frontline
            tiles[3 - 3 * mirror][4 + 2 * mirror].setOccupiedPiece(new Elephant(player));
            tiles[3 - mirror][4 + 2 * mirror].setOccupiedPiece(new Wolf(player));
            tiles[3 + mirror][4 + 2 * mirror].setOccupiedPiece(new Leopard(player));
            tiles[3 + 3 * mirror][4 + 2 * mirror].setOccupiedPiece(new Rat(player));
            mirror = -1;
        }
        return tiles;
    }

    private Tile[][] tiles;
    private Player playerOne, playerTwo;
    private Player currentPlayer;

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

    /**
     * @return The current player in turn
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
