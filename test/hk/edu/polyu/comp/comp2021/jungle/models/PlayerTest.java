package hk.edu.polyu.comp.comp2021.jungle.models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test player.
 */
public class PlayerTest {

    private static final String PLAYER_NAME = "Name";
    private static final String PLAYER_NEW_NAME = "New Name";
    /**
     * Test for creating player by name
     */
    @Test
    public void createPlayer() {
        Player player = new Player("Name");

        assertEquals(PLAYER_NAME, player.getName());
        assertEquals(PLAYER_NAME, player.toString());
    }


    /**
     * Test for renaming player
     */
    @Test
    public void renamePlayer() {
        Player player = new Player(PLAYER_NAME);
        player.setName(PLAYER_NEW_NAME);

        assertNotEquals(PLAYER_NAME, player.getName());
        assertEquals(PLAYER_NEW_NAME, player.getName());
    }
}
