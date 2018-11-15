package hk.edu.polyu.comp.comp2021.jungle.models.tiles;

import hk.edu.polyu.comp.comp2021.jungle.models.pieces.Piece;

/**
 * This class represent a tile on a board
 */
public class Tile {
    private final TileType tileType;
    private Piece occupiedPiece = null;

    /**
     * Creates a Tile
     *
     * @param tileType The type of the tile
     */
    public Tile(TileType tileType) {
        this.tileType = tileType;
    }

    /**
     * Copy constructer for type Tile
     *
     * @param tile Tile to copy from
     */
    public Tile(Tile tile) {
        this.tileType = tile.getTileType();
        this.occupiedPiece = tile.getOccupiedPiece();
    }

    /**
     * Returns the tile type
     *
     * @return Tile type
     */
    public TileType getTileType() {
        return tileType;
    }

    /**
     * Returns the piece which is currently occupying the tile, returns null if none are occupying
     *
     * @return The occupying piece
     */
    public Piece getOccupiedPiece() {
        return occupiedPiece;
    }

    /**
     * Set the current occupying piece for a tile
     *
     * @param occupiedPiece The piece to replace
     */
    public void setOccupiedPiece(Piece occupiedPiece) {
        this.occupiedPiece = occupiedPiece;
    }
}
