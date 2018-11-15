package hk.edu.polyu.comp.comp2021.jungle.models.tiles;

import hk.edu.polyu.comp.comp2021.jungle.models.Player;
import hk.edu.polyu.comp.comp2021.jungle.models.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a den event tile, which triggers DenEventListener when a piece is successfully moved to
 * A DenEventTile is assigned to each Player
 */
public class DenEventTile implements Tile {

    private final Player owner;
    private final List<DenEventListener> listeners = new ArrayList<>();

    /**
     * Creates a DenEventTile
     *
     * @param owner The owner of the tile
     */
    public DenEventTile(Player owner) {
        this.owner = owner;
    }

    /**
     * Subscribe to the event via DenEventListener
     *
     * @param listener The listener
     * @return True if successful
     */
    public boolean SubscribeEvent(DenEventListener listener) {
        return listeners.add(listener);
    }

    /**
     * Unsubscribe from the event by the DenEventListener
     *
     * @param listener Listener wish to be removed
     * @return True if successful
     */
    public boolean UnsubscribeEvent(DenEventListener listener) {
        return listeners.remove(listener);
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public void setOccupiedPiece(Piece occupiedPiece) {
        TriggerEvents(occupiedPiece.getOwner());
    }

    @Override
    public TileType getTileType() {
        return TileType.DEN;
    }

    @Override
    public boolean isOccupied() {
        return false;
    }

    @Override
    public Piece getOccupiedPiece() {
        return null;
    }

    private void TriggerEvents(Player triggeredPlayer) {
        for (DenEventListener listener : listeners) {
            listener.OnTrigger(triggeredPlayer);
        }
    }
}
