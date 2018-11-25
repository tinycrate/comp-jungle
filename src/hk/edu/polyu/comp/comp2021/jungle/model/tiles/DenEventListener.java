package hk.edu.polyu.comp.comp2021.jungle.model.tiles;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;

/**
 * Triggers when a player moves to the opponent's den
 */
@FunctionalInterface
public interface DenEventListener {
    /**
     * Called when the event is being triggered
     *
     * @param triggeredPlayer Player who originally triggers the event
     */
    void OnTrigger(Player triggeredPlayer);
}
