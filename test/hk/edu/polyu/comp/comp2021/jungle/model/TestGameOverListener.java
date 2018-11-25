package hk.edu.polyu.comp.comp2021.jungle.model;

/**
 * Test class for GameOverListener
 */
public class TestGameOverListener implements GameOverListener {

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
