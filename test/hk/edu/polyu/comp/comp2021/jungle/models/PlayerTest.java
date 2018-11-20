package hk.edu.polyu.comp.comp2021.jungle.models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test player.
 */
public class PlayerTest {

    /**
     * Test for creating player by name
     */
    @Test
    public void createPlayer() {
        Player player = new Player("Name");

        assertEquals(player.getName(), "Name");
    }


    /**
     * Test for renaming player
     */
    @Test
    public void renamePlayer() {
        Player player = new Player("Name");
        player.setName("NewName");

        assertEquals(player.getName(), "NewName");
    }
}
