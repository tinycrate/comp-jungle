package hk.edu.polyu.comp.comp2021.jungle.models.pieces;

import hk.edu.polyu.comp.comp2021.jungle.models.Board;
import hk.edu.polyu.comp.comp2021.jungle.models.Coordinates;
import hk.edu.polyu.comp.comp2021.jungle.models.Player;
import hk.edu.polyu.comp.comp2021.jungle.models.tiles.Tile;
import hk.edu.polyu.comp.comp2021.jungle.models.tiles.TileType;

import java.io.Serializable;

/**
 * An immutable base class for the game pieces (animals)
 */
public abstract class Piece implements Serializable {
    private final Coordinates coordinates;
    private final Player owner;

    /**
     * Creates a Piece object with reference to the board and its coordinates
     * Most likely called by board
     *
     * @param coordinates The coordinares of the piece on the board
     * @param owner       The owner of the piece
     */
    Piece(Coordinates coordinates, Player owner) {
        this.coordinates = coordinates;
        this.owner = owner;
    }

    /**
     * @return The rank of the piece.
     */
    public abstract int getRank();

    /**
     * Checks if the piece is being weaken by an enemy trap
     *
     * @param board The board where this piece is located at
     * @return True if the piece is being weaken
     */
    public boolean isWeakenByTrap(Board board) {
        Tile tile = board.getTile(coordinates);
        return tile.getTileType() == TileType.TRAP && tile.getOwner() != owner;
    }

    /**
     * Checks whether the piece could be moved to a tile of a certain coordinates
     * If the destination tile is occupied by an animal and the animal could be eaten, the move is also considered possible
     * Inherited class should override this method if special rules have to be considered
     *
     * @param coords The coordinates to be checked
     * @param board  The board where this piece is located at
     * @return True if the move is possible
     */
    public abstract boolean isMoveableTo(Coordinates coords, Board board);

    /**
     * @return The owner of the piece
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * @return The coordinates of the piece
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Gets the sybmol representing the piece
     *
     * @return The symbol for the piece
     */
    public abstract String getSymbol();
}
