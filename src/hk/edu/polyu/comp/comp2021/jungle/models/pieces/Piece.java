package hk.edu.polyu.comp.comp2021.jungle.models.pieces;

import hk.edu.polyu.comp.comp2021.jungle.models.Board;
import hk.edu.polyu.comp.comp2021.jungle.models.Coordinates;

/**
 * An immutable base class for the game pieces (animals)
 */
public abstract class Piece {
    private final Board board;
    private final Coordinates coordinates;

    /**
     * Creates a Piece object with reference to the board and its coordinates
     * Most likely called by board
     *
     * @param board       The board the piece is currently on
     * @param coordinates The coordinares of the piece on the board
     */
    protected Piece(Board board, Coordinates coordinates) {
        this.board = board;
        this.coordinates = coordinates;
    }

    /**
     * @return The rank of the piece.
     */
    public abstract int getRank();

    /**
     * Checks whether the piece could be moved to a tile of a certain coordinates
     * If the destination tile is occupied by an animal and the animal could be eaten, the move is also considered possible
     * Inherited class should override this method if special rules have to be considered
     *
     * @param coords The coordinates to be checked
     * @return True if the move is possible
     */
    public abstract boolean isMoveableTo(Coordinates coords);

    /**
     * @return Returns the coordinates of the piece
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return Returns the board the piece belongs to
     */
    public Board getBoard() {
        return board;
    }
}