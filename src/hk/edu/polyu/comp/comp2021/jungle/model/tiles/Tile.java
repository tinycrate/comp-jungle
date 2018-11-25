package hk.edu.polyu.comp.comp2021.jungle.model.tiles;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;
import hk.edu.polyu.comp.comp2021.jungle.model.pieces.Piece;

import java.io.Serializable;

/**
 * This class defines the required methods of a tile on a board
 */
public abstract class Tile implements Serializable {
    /**
     * Returns the tile type
     *
     * @return Tile type
     */
    public abstract TileType getTileType();

    /**
     * Returns the owner of the tile, null if the tile is not owned
     *
     * @return Player
     */
    public abstract Player getOwner();

    /**
     * Checks if the tile is occupied by someone
     *
     * @return True if the tile is occupied
     */
    public boolean isOccupied() {
        return getOccupiedPiece() != null;
    }

    /**
     * Returns the piece which is currently occupying the tile, returns null if none are occupying
     *
     * @return The occupying piece
     */
    public abstract Piece getOccupiedPiece();

    /**
     * Set the current occupying piece for a tile
     *
     * @param occupiedPiece The piece to replace
     */
    public abstract void setOccupiedPiece(Piece occupiedPiece);

    /**
     * Clones the tile object
     *
     * @return A new copy of the tile
     */
    public abstract Tile getClone();
}
