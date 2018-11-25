package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.model.pieces.Piece;
import hk.edu.polyu.comp.comp2021.jungle.model.pieces.PieceType;
import hk.edu.polyu.comp.comp2021.jungle.model.tiles.DenTile;
import hk.edu.polyu.comp.comp2021.jungle.model.tiles.Tile;
import hk.edu.polyu.comp.comp2021.jungle.model.tiles.TileType;

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
    private GameOverListener gameOverListener;

    // Additional effort is made to track the coordinatesMap of the piece instead of finding them each time
    private final HashMap<Piece, Coordinates> coordinatesMap = new HashMap<>();
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
     * Gets a tile by coordinatesMap
     *
     * @param coords The coordinatesMap
     * @return The Tile in that coordinatesMap
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
     * Gets the coordinatesMap of a piece
     *
     * @param piece The piece in the board
     * @return The coordinatesMap, null if the piece is not in the board
     */
    public Coordinates getCoordinates(Piece piece) {
        Coordinates coords = coordinatesMap.get(piece);
        if (coords != null && getTile(coords).getOccupiedPiece() != piece) {
            throw new IllegalStateException("Coordinates out of sync.");
        }
        return coords;
    }

    /**
     * Gets the coordinatesMap of a piece with specific player
     *
     * @param player The player
     * @param type   Type of piece
     * @return The coordinatesMap, null if the piece is not in the board
     */
    public Coordinates getCoordinates(Player player, PieceType type) {
        for (Piece p : coordinatesMap.keySet()) {
            if (p.getPieceType() == type && p.getOwner() == player)
                return coordinatesMap.get(p);
        }
        return null;
    }

    /**
     * Gets all available moves originated from a Coordinates `from`
     *
     * @param from The origin
     * @return All available moves
     */
    public List<Coordinates> getAvailableMoves(Coordinates from) {
        return new ArrayList<>(availableMoves.get(from));
    }

    /**
     * Subscribes to a DenEvent, which will be triggered when a piece is move into a Den
     *
     * @param listener The event listener
     */
    public void subscribeGameOverEvent(GameOverListener listener) {
        gameOverListener = listener;
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                Tile tile = tiles[x][y];
                if (tile.getTileType() == TileType.DEN) {
                    ((DenTile) tile).subscribeEvent(listener);
                }
            }
        }
    }

    private void initializeBoard() {
        for (int x = 0; x < BOARD_WIDTH; x++) {
            for (int y = 0; y < BOARD_HEIGHT; y++) {
                Coordinates coords = new Coordinates(x, y);
                Tile tile = getTile(coords);
                if (tile.isOccupied()) coordinatesMap.put(tile.getOccupiedPiece(), coords);
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
            if (!source.isOccupied() || source.getOccupiedPiece().getOwner() != currentPlayer) continue;
            Piece piece = source.getOccupiedPiece();
            for (Coordinates to : availableMoves.keySet()) {
                if (piece.isMoveableTo(to, this)) {
                    moves.add(to);
                    noMoves = false;
                }
            }
        }
        if (noMoves) {
            boolean noPiece = true;
            for (Piece piece : coordinatesMap.keySet()) {
                if (piece.getOwner() == currentPlayer && coordinatesMap.get(piece) != null) noPiece = false;
            }
            currentPlayer = (currentPlayer == playerOne) ? playerTwo : playerOne;
            if (noPiece && gameOverListener != null) gameOverListener.OnTrigger(currentPlayer);
            updateMoves();
        }
    }

    private void removePiece(Piece piece) {
        Coordinates coords = getCoordinates(piece);
        Tile tile = tiles[coords.getX()][coords.getY()];
        tile.setOccupiedPiece(null);
        coordinatesMap.replace(piece, null);
    }

    private void setOrReplacePiece(Piece piece, Coordinates coords) {
        Coordinates originCoords = getCoordinates(piece);
        Tile origin = tiles[originCoords.getX()][originCoords.getY()];
        Tile destination = tiles[coords.getX()][coords.getY()];
        if (destination.isOccupied()) {
            removePiece(destination.getOccupiedPiece());
        }
        coordinatesMap.replace(piece, coords);
        origin.setOccupiedPiece(null);
        destination.setOccupiedPiece(piece);
    }

}
