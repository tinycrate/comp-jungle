package hk.edu.polyu.comp.comp2021.jungle.models;

import hk.edu.polyu.comp.comp2021.jungle.models.tiles.Tile;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for Board
 */
public class BoardTest {

    private Player playerA, playerB;
    private BoardConfiguration config;
    private Board board;

    /**
     * Create player before testing
     */
	@Before
    public void createPlayer() {
        playerA = new Player("A");
        playerB = new Player("B");
    }

    /**
     * Test for creating a board
     */
	@Test
	public void testCreateBoardWithDefaultConfig() {
		config = BoardConfiguration.getDefault(playerA, playerB);
		board = new Board(config);
	}
}
