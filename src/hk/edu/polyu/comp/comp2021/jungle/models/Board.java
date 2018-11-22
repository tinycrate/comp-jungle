package hk.edu.polyu.comp.comp2021.jungle.models;

import hk.edu.polyu.comp.comp2021.jungle.models.pieces.Piece;
import hk.edu.polyu.comp.comp2021.jungle.models.pieces.PieceType;
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

    // Additional effort is made to track the coordinates of the piece instead of finding them each time
    private final HashMap<Piece, Coordinates> coordinates = new HashMap<>();
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
        setOrReplacePiece(getTile(from).getOccupiedPiece(), to);
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

    /**
     * Gets the coordinates of a piece
     *
     * @param piece The piece in the board
     * @return The coordinates, null if the piece is not in the board
     */
    public Coordinates getCoordinates(Piece piece) {
        Coordinates coords = coordinates.get(piece);
        if (coords != null && getTile(coords).getOccupiedPiece() != piece) {
            throw new IllegalStateException("Coordinates out of sync.");
        }
        return coords;
    }

    /**
     * Gets the coordinates of a piece with specific player
     * @param player The player
     * @param type   Type of piece
     * @return The coordinates, null if the piece is not in the board
     */
    public Coordinates getCoordinates(Player player, PieceType type) {
        for(Piece p: coordinates.keySet()) {
            if(p.getPieceType() == type && p.getOwner() == player)
                return coordinates.get(p);
        }
        return null;
    }

    private void initializeBoard() {
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                Coordinates coords = new Coordinates(x, y);
                Tile tile = getTile(coords);
                if (tile.isOccupied()) coordinates.put(tile.getOccupiedPiece(), coords);
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

    private void removePiece(Piece piece) {
        Coordinates coords = getCoordinates(piece);
        Tile tile = tiles[coords.getX()][coords.getY()];
        tile.setOccupiedPiece(null);
        coordinates.replace(piece, null);
    }

    private void setOrReplacePiece(Piece piece, Coordinates coords) {
        Coordinates originCoords = getCoordinates(piece);
        Tile origin = tiles[originCoords.getX()][originCoords.getY()];
        Tile destination = tiles[coords.getX()][coords.getY()];
        if (destination.isOccupied()) {
            removePiece(destination.getOccupiedPiece());
        }
        origin.setOccupiedPiece(null);
        destination.setOccupiedPiece(piece);
        coordinates.replace(piece, coords);
    }

}
