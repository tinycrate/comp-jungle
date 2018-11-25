package hk.edu.polyu.comp.comp2021.jungle.model.tiles;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;
import hk.edu.polyu.comp.comp2021.jungle.model.pieces.Piece;

/**
 * This class represents a trap tile
 * A TrapTile is assigned to each Player
 */
public class TrapTile extends Tile {
    private Piece occupiedPiece = null;
    private final Player owner;

    /**
     * Creates a TrapTile
     *
     * @param owner The owner of the tile
     */
    public TrapTile(Player owner) {
        this.owner = owner;
    }

    /**
     * Copy constructer for type TrapTile
     *
     * @param tile TrapTile to copy from
     */
    public TrapTile(TrapTile tile) {
        this.owner = tile.getOwner();
        this.occupiedPiece = tile.getOccupiedPiece();
    }

    @Override
    public TileType getTileType() {
        return TileType.TRAP;
    }

    @Override
    public Player getOwner() {
        return owner;
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
        return new TrapTile(this);
    }
}
