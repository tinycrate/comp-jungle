package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.model.tiles.DenEventListener;

/**
 * Test class for DenEventListener
 */
public class TestDenEventListener implements DenEventListener {

    private boolean triggered = false;

    @Override
    public void OnTrigger(Player triggeredPlayer) {
        triggered = true;
    }

    /**
     * @return Check if it is triggered.
     */
    public boolean isTriggered() {
        return triggered;
    }
}
