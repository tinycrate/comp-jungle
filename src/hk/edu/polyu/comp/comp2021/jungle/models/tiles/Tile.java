package hk.edu.polyu.comp.comp2021.jungle.models.tiles;

import hk.edu.polyu.comp.comp2021.jungle.models.Player;
import hk.edu.polyu.comp.comp2021.jungle.models.pieces.Piece;

import java.io.Serializable;

/**
 * This interface defines the required methods of a tile on a board
 */
public interface Tile extends Serializable {
    /**
     * Returns the tile type
     *
     * @return Tile type
     */
    TileType getTileType();

    /**
     * Returns the owner of the tile, null if the tile is not owned
     *
     * @return Player
     */
    Player getOwner();

    /**
     * Checks if the tile is occupied by someone
     * @return True if the tile is occupied
     */
    boolean isOccupied();

    /**
     * Returns the piece which is currently occupying the tile, returns null if none are occupying
     *
     * @return The occupying piece
     */
    Piece getOccupiedPiece();

    /**
     * Set the current occupying piece for a tile
     *
     * @param occupiedPiece The piece to replace
     */
    void setOccupiedPiece(Piece occupiedPiece);
}
