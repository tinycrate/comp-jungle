package hk.edu.polyu.comp.comp2021.jungle.models;

import hk.edu.polyu.comp.comp2021.jungle.models.pieces.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test the piece
 */
public class PieceTest {

    private static final int RANK_ELEPHANT = 8;
    private static final int RANK_LION = 7;
    private static final int RANK_TIGER = 6;
    private static final int RANK_LEOPARD = 5;
    private static final int RANK_WOLF = 4;
    private static final int RANK_DOG = 3;
    private static final int RANK_CAT = 2;
    private static final int RANK_RAT = 1;

    private static final Coordinates ORIGINAL = new Coordinates("F2");
    private static final Coordinates TRAP = new Coordinates("E1");

    private Player playerA, playerB;
    private BoardConfiguration config;
    private Board board;

    /**
     * Before all starts, player and board need to be reated
     */
    @Before
    public void createPlayerAndBoard() {
        playerA = new Player("A");
        playerB = new Player("B");

        config = BoardConfiguration.getDefault(playerA, playerB);
        board = new Board(config);
    }

    /**
     * Test for elephant piece
     */
    @Test
    public void testElephant() {
        Elephant piece = new Elephant(ORIGINAL, playerA);
        testNormalTileMoving(piece);

        assertFalse(piece.isWeakenByTrap(board));

        assertEquals(playerA, piece.getOwner());
        assertEquals(ORIGINAL, piece.getCoordinates());
        assertEquals("象", piece.getSymbol());
        assertEquals(RANK_ELEPHANT, piece.getRank());
    }

    /**
     * Test for lion piece
     */
    @Test
    public void testLion() {
        Lion piece = new Lion(ORIGINAL, playerA);
        testNormalTileMoving(piece);

        assertFalse(piece.isWeakenByTrap(board));

        assertEquals(playerA, piece.getOwner());
        assertEquals(ORIGINAL, piece.getCoordinates());
        assertEquals("獅", piece.getSymbol());
        assertEquals(RANK_LION, piece.getRank());
    }

    /**
     * Test for Tiger piece
     */
    @Test
    public void testTiger() {
        Tiger piece = new Tiger(ORIGINAL, playerA);
        testNormalTileMoving(piece);

        assertFalse(piece.isWeakenByTrap(board));

        assertEquals(playerA, piece.getOwner());
        assertEquals(ORIGINAL, piece.getCoordinates());
        assertEquals("虎", piece.getSymbol());
        assertEquals(RANK_TIGER, piece.getRank());
    }

    /**
     * Test for Leopard piece
     */
    @Test
    public void testLeopard() {
        Leopard piece = new Leopard(ORIGINAL, playerA);
        testNormalTileMoving(piece);

        assertFalse(piece.isWeakenByTrap(board));

        assertEquals(playerA, piece.getOwner());
        assertEquals(ORIGINAL, piece.getCoordinates());
        assertEquals("豹", piece.getSymbol());
        assertEquals(RANK_LEOPARD, piece.getRank());
    }

    /**
     * Test for Wolf piece
     */
    @Test
    public void testWolf() {
        Wolf piece = new Wolf(ORIGINAL, playerA);
        testNormalTileMoving(piece);

        assertFalse(piece.isWeakenByTrap(board));

        assertEquals(playerA, piece.getOwner());
        assertEquals(ORIGINAL, piece.getCoordinates());
        assertEquals("狼", piece.getSymbol());
        assertEquals(RANK_WOLF, piece.getRank());
    }

    /**
     * Test for Dog piece
     */
    @Test
    public void testDog() {
        Dog piece = new Dog(ORIGINAL, playerA);
        testNormalTileMoving(piece);

        assertFalse(piece.isWeakenByTrap(board));

        assertEquals(playerA, piece.getOwner());
        assertEquals(ORIGINAL, piece.getCoordinates());
        assertEquals("狗", piece.getSymbol());
        assertEquals(RANK_DOG, piece.getRank());
    }

    /**
     * Test for cat piece
     */
    @Test
    public void testCat() {
        Cat piece = new Cat(ORIGINAL, playerA);
        testNormalTileMoving(piece);

        assertFalse(piece.isWeakenByTrap(board));

        assertEquals(playerA, piece.getOwner());
        assertEquals(ORIGINAL, piece.getCoordinates());
        assertEquals("貓", piece.getSymbol());
        assertEquals(RANK_CAT, piece.getRank());
    }

    /**
     * Test for Rat piece
     */
    @Test
    public void testRat() {
        Rat piece = new Rat(ORIGINAL, playerA);
        testNormalTileMoving(piece);

        assertFalse(piece.isWeakenByTrap(board));

        assertEquals(playerA, piece.getOwner());
        assertEquals(ORIGINAL, piece.getCoordinates());
        assertEquals("鼠", piece.getSymbol());
        assertEquals(RANK_RAT, piece.getRank());
    }


    /**
     * Test the basic move for the piece
     * @param piece Piece to be tested
     */
    public void testNormalTileMoving(Piece piece) {
        Coordinates left = new Coordinates("E2");
        Coordinates right = new Coordinates("G2");
        Coordinates up = new Coordinates("F3");
        Coordinates down = new Coordinates("F1");

        assertFalse(piece.isWeakenByTrap(board));
        assertFalse(piece.isMoveableTo(ORIGINAL, board));
        assertTrue(piece.isMoveableTo(left, board));
        assertTrue(piece.isMoveableTo(right, board));
        assertTrue(piece.isMoveableTo(up, board));
        assertTrue(piece.isMoveableTo(down, board));
    }
}
