package hk.edu.polyu.comp.comp2021.jungle.model.tiles;

import hk.edu.polyu.comp.comp2021.jungle.model.GameOverListener;
import hk.edu.polyu.comp.comp2021.jungle.model.Player;
import hk.edu.polyu.comp.comp2021.jungle.model.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a den event tile, which triggers GameOverListener when a piece is successfully moved to
 * A DenTile is assigned to each Player
 */
public class DenTile extends Tile {

    private final Player owner;
    private transient List<GameOverListener> listeners;
    private Piece occupiedPiece = null;

    /**
     * Creates a DenTile
     *
     * @param owner The owner of the tile
     */
    public DenTile(Player owner) {
        this.owner = owner;
    }

    /**
     * Copy constructer for type DenTile
     *
     * @param tile DenTile to copy from
     */
    public DenTile(DenTile tile) {
        this.owner = tile.getOwner();
        this.listeners = tile.getListeners();
        this.occupiedPiece = tile.getOccupiedPiece();
    }

    /**
     * Subscribe to the event via GameOverListener
     *
     * @param listener The listener
     * @return True if successful
     */
    public boolean subscribeEvent(GameOverListener listener) {
        return getListeners().add(listener);
    }

    /**
     * Unsubscribe from the event by the GameOverListener
     *
     * @param listener Listener wish to be removed
     * @return True if successful
     */
    public boolean unsubscribeEvent(GameOverListener listener) {
        return getListeners().remove(listener);
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public void setOccupiedPiece(Piece occupiedPiece) {
        this.occupiedPiece = occupiedPiece;
        TriggerEvents(occupiedPiece.getOwner());
    }

    @Override
    public TileType getTileType() {
        return TileType.DEN;
    }

    @Override
    public Piece getOccupiedPiece() {
        return occupiedPiece;
    }

    @Override
    public Tile getClone() {
        return new DenTile(this);
    }

    private void TriggerEvents(Player triggeredPlayer) {
        for (GameOverListener listener : getListeners()) {
            listener.OnTrigger(triggeredPlayer);
        }
    }

    /**
     * Locks the access of listeners behind a getter to lazy load the listeners field if needed
     * This is because the reference of listeners could be set to null after deserialization as constructors will not be called
     */
    private List<GameOverListener> getListeners() {
        if (listeners == null) listeners = new ArrayList<>();
        return listeners;
    }
}
