package hk.edu.polyu.comp.comp2021.jungle.models.tiles;

import hk.edu.polyu.comp.comp2021.jungle.models.Player;
import hk.edu.polyu.comp.comp2021.jungle.models.pieces.Piece;

/**
 * This class represent a normal game tile on a board
 */
public class GameTile implements Tile {
    private final TileType tileType;
    private Piece occupiedPiece = null;

    /**
     * Creates a GameTile
     *
     * @param tileType The type of the tile, event tiles are not accepted
     */
    public GameTile(TileType tileType) {
        if (tileType.isEventTile()) {
            throw new IllegalArgumentException("Event tiles could not be used as tileType for GameTile");
        }
        this.tileType = tileType;
    }

    /**
     * Copy constructer for type GameTile
     *
     * @param tile GameTile to copy from
     */
    public GameTile(GameTile tile) {
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
    public boolean isOccupied() {
        return getOccupiedPiece() != null;
    }

    @Override
    public Piece getOccupiedPiece() {
        return occupiedPiece;
    }

    @Override
    public void setOccupiedPiece(Piece occupiedPiece) {
        this.occupiedPiece = occupiedPiece;
    }
}
