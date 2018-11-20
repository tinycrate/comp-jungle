package hk.edu.polyu.comp.comp2021.jungle.models;

import hk.edu.polyu.comp.comp2021.jungle.models.pieces.Piece;
import hk.edu.polyu.comp.comp2021.jungle.models.tiles.Tile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    private Player currentPlayer;

    private final HashMap<Coordinates, List<Coordinates>> availableMoves = new HashMap<>();

    /**
     * Initialize a board with a specific BoardConfiguration
     *
     * @param configuration The configuration of the board
     */
    public Board(BoardConfiguration configuration) {
        tiles = configuration.getTiles();
        playerOne = configuration.getPlayerOne();
        playerTwo = configuration.getPlayerTwo();
        currentPlayer = configuration.getCurrentPlayer();
        initializeBoard();
    }

    /**
     * Moves a piece
     *
     * @param from The source
     * @param to   The destination
     * @return True if operation is successful
     */
    public boolean movePiece(Coordinates from, Coordinates to) {
        if (!availableMoves.get(from).contains(to)) return false;
        Tile source = tiles[from.getX()][from.getY()];
        Tile destination = tiles[to.getX()][to.getY()];
        Piece piece = source.getOccupiedPiece();
        source.setOccupiedPiece(null);
        destination.setOccupiedPiece(piece);
        currentPlayer = (currentPlayer == playerOne) ? playerTwo : playerOne;
        updateMoves();
        return true;
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

    /**
     * @return The current Player in turn
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void initializeBoard() {
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                availableMoves.put(new Coordinates(x, y), new ArrayList<>());
            }
        }
        updateMoves();
    }

    private void updateMoves() {
        boolean noMoves = true;
        for (Coordinates from : availableMoves.keySet()) {
            List<Coordinates> moves = availableMoves.get(from);
            moves.clear();
            Tile source = getTile(from);
            if (!source.isOccupied() || source.getOwner() != currentPlayer) continue;
            Piece piece = source.getOccupiedPiece();
            for (Coordinates to : availableMoves.keySet()) {
                if (piece.isMoveableTo(to, this)) {
                    moves.add(to);
                    noMoves = false;
                }
            }
        }
        if (noMoves) {
            currentPlayer = (currentPlayer == playerOne) ? playerTwo : playerOne;
            updateMoves();
        }
    }

}
