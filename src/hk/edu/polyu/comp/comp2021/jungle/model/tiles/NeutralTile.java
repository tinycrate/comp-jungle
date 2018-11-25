package hk.edu.polyu.comp.comp2021.jungle.model.tiles;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;
import hk.edu.polyu.comp.comp2021.jungle.model.pieces.Piece;

/**
 * This class represent a normal game tile on a board
 */
public class NeutralTile extends Tile {
    private final TileType tileType;
    private Piece occupiedPiece = null;

    /**
     * Creates a NeutralTile
     *
     * @param tileType The type of the tile, tiles not neutral are not accepted
     */
    public NeutralTile(TileType tileType) {
        if (!tileType.isNeutralTile()) {
            throw new IllegalArgumentException("Non-neutral tiles could not be used as tileType for NeutralTile");
        }
        this.tileType = tileType;
    }

    /**
     * Copy constructer for type NeutralTile
     *
     * @param tile NeutralTile to copy from
     */
    public NeutralTile(NeutralTile tile) {
        this.tileType = tile.getTileType();
        this.occupiedPiece = tile.getOccupiedPiece();
    }

    @Override
    public TileType getTileType() {
        return tileType;
    }

    @Override
    public Player getOwner() {
        return (occupiedPiece != null) ? occupiedPiece.getOwner() : null;
    }

    @Override
    public Piece getOccupiedPiece() {
        return occupiedPiece;
    }

    @Override
    public void setOccupiedPiece(Piece occupiedPiece) {
        this.occupiedPiece = occupiedPiece;
    }

    @Override
    public Tile getClone() {
        return new NeutralTile(this);
    }
}
