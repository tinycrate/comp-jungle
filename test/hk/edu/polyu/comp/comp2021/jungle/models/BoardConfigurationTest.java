package hk.edu.polyu.comp.comp2021.jungle.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for board configuration.
 */
public class BoardConfigurationTest {

    private static final String PLAYER_NAME_A = "A";
    private static final String PLAYER_NAME_B = "B";

    private Player playerA, playerB;
    /**
     * Prepre two players before test.
     */
    @Before
    public void preparePlayers() {
        playerA = new Player(PLAYER_NAME_A);
        playerB = new Player(PLAYER_NAME_B);
    }

    /**
     * Test for the default board configuration.
     */
    @Test
    public void testDefaultConfiguration() {
        BoardConfiguration config = BoardConfiguration.getDefault(playerA, playerB);
        assertEquals(playerA, config.getPlayerOne());
        assertEquals(playerB, config.getPlayerTwo());
        assertEquals(playerA, config.getCurrentPlayer());
    }

    /**
     * Test for board configuration save feature.
     */
    @Test
    public void testConfigurationSaveAndLoad() {
        String path = System.getProperty("java.io.tmpdir") + "/test.save";

        BoardConfiguration config = BoardConfiguration.getDefault(playerA, playerB);
        Board board = new Board(config);
        BoardConfiguration.save(board, path);

        BoardConfiguration loadConfig = BoardConfiguration.load(path);

        assertNotNull(loadConfig);
        assertEquals(PLAYER_NAME_A, loadConfig.getPlayerOne().getName());
        assertEquals(PLAYER_NAME_B, loadConfig.getPlayerTwo().getName());
        assertEquals(PLAYER_NAME_A, loadConfig.getCurrentPlayer().getName());
    }


    /**
     * Test for board configuration load non-exist file.
     */
    @Test
    public void testConfigurationLoadNonexistFile() {
        String path = System.getProperty("java.io.tmpdir") + "/NonExistFile";
        BoardConfiguration config = BoardConfiguration.load(path);

        assertNull(config);
    }

}
