package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.model.pieces.PieceType;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

/**
 * Unit test for Board
 */
public class BoardTest {

    private Player playerA, playerB;
    private BoardConfiguration config;
    private Board board;

    /**
     * Setup game before doing test
     * @throws UnsupportedEncodingException File path decode may not success
     */
	@Before
    public void prepare() throws UnsupportedEncodingException {
        config = BoardConfigurationSave.getGeneralSave(getClass().getClassLoader());
        assertNotNull(config);

        playerA = config.getPlayerOne();
        playerB = config.getPlayerTwo();

        board = new Board(config);
    }

    /**
     * Test for creating a board with default configuration
     */
	@Test
	public void testCreateBoardWithDefaultConfig() {
		BoardConfiguration defaultConfig = BoardConfiguration.getDefault(playerA, playerB);
		Board board = new Board(defaultConfig);

		assertEquals(playerA, board.getCurrentPlayer());
		assertEquals(playerA, board.getPlayerOne());
		assertEquals(playerB, board.getPlayerTwo());
	}

    /**
     * Test for creating a board with game save
     */
    @Test
    public void testCreateBoardWithSave() {
        assertEquals(playerA, board.getCurrentPlayer());
        assertEquals(playerA, board.getPlayerOne());
        assertEquals(playerB, board.getPlayerTwo());
    }

    /**
     * Test the result of coordinates of non-exist piece
     */
    @Test
    public void testNonExistPiece() {
        assertNull(board.getCoordinates(playerB, PieceType.WOLF));
    }

    /**
     * Test for player change when enemy player does not have possible move
     * @throws UnsupportedEncodingException Exception if system does not support UTF-8
     */
    @Test
    public void testNoNove() throws UnsupportedEncodingException {
        config = BoardConfigurationSave.getNoMoveSave(getClass().getClassLoader());
        board = new Board(config);

        assertEquals(config.getPlayerOne(), board.getCurrentPlayer());
        assertTrue(board.getAvailableMoves(new Coordinates("D5")).isEmpty());
        assertFalse(board.getAvailableMoves(new Coordinates("D4")).isEmpty());

        assertTrue(board.movePiece(new Coordinates("A1"), new Coordinates("A2")));
        assertEquals(config.getPlayerOne(), board.getCurrentPlayer());
    }

    /**
     * Test if a DenEventListner will be called
     */
    @Test
    public void testDenEvent() {
        TestGameOverListener listener = new TestGameOverListener();
        board.subscribeGameOverEvent(listener);

        assertFalse(listener.isTriggered());
        assertTrue(board.movePiece(new Coordinates("D8"), new Coordinates("D9")));
        assertTrue(listener.isTriggered());
    }

    /**
     * Test for Elephant cannot capture Rat and cannot go into river
     */
    @Test
    public void testElephantMoves() {
        Coordinates current = board.getCoordinates(playerA, PieceType.ELEPHANT);
        assertFalse(board.movePiece(current, new Coordinates("A5")));
        assertFalse(board.movePiece(current, new Coordinates("A4")));
        assertFalse(board.movePiece(current, new Coordinates("B3")));
        assertFalse(board.movePiece(current, new Coordinates("A2")));
    }

    /**
     * Test for a random piece in trap can be captured
     */
    @Test
    public void testTrapCapture() {
        Coordinates current = board.getCoordinates(playerA, PieceType.LEOPARD);
        assertTrue(board.movePiece(current, new Coordinates("D2")));
    }

    /**
     * Test for a river-jumper piece can jump over river
     */
    @Test
    public void testRiverJumperPiece() {
        Coordinates current = board.getCoordinates(playerA, PieceType.TIGER);
        assertTrue(board.movePiece(current, new Coordinates("A6")));
    }

    /**
     * Test for a river-jumper piece blocked by rat in river
     */
    @Test
    public void testRiverJumperPieceBlockedByRat() {
        Coordinates current = board.getCoordinates(playerA, PieceType.LION);
        assertFalse(board.movePiece(current, new Coordinates("F7")));
    }

    /**
     * Test for a rat in river cannot capture elephant on land, but can move in
     * river.
     */
    @Test
    public void testRiverRat() {
        Coordinates current = board.getCoordinates(playerA, PieceType.RAT);
        assertFalse(board.movePiece(current, new Coordinates("G5")));
        assertTrue(board.movePiece(current, new Coordinates("E5")));
    }
}
