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
        Elephant piece = new Elephant(playerA);
        assertFalse(piece.isWeakenByTrap(board));

        assertEquals(playerA, piece.getOwner());
        assertEquals("象", piece.getSymbol());
        assertEquals(RANK_ELEPHANT, piece.getRank());
    }

    /**
     * Test for lion piece
     */
    @Test
    public void testLion() {
        Lion piece = new Lion(playerA);

        assertFalse(piece.isWeakenByTrap(board));

        assertEquals(playerA, piece.getOwner());
        assertEquals("獅", piece.getSymbol());
        assertEquals(RANK_LION, piece.getRank());
    }

    /**
     * Test for Tiger piece
     */
    @Test
    public void testTiger() {
        Tiger piece = new Tiger(playerA);

        assertFalse(piece.isWeakenByTrap(board));

        assertEquals(playerA, piece.getOwner());
        assertEquals("虎", piece.getSymbol());
        assertEquals(RANK_TIGER, piece.getRank());
    }

    /**
     * Test for Leopard piece
     */
    @Test
    public void testLeopard() {
        Leopard piece = new Leopard(playerA);

        assertFalse(piece.isWeakenByTrap(board));

        assertEquals(playerA, piece.getOwner());
        assertEquals("豹", piece.getSymbol());
        assertEquals(RANK_LEOPARD, piece.getRank());
    }

    /**
     * Test for Wolf piece
     */
    @Test
    public void testWolf() {
        Wolf piece = new Wolf(playerA);

        assertFalse(piece.isWeakenByTrap(board));

        assertEquals(playerA, piece.getOwner());
        assertEquals("狼", piece.getSymbol());
        assertEquals(RANK_WOLF, piece.getRank());
    }

    /**
     * Test for Dog piece
     */
    @Test
    public void testDog() {
        Dog piece = new Dog(playerA);

        assertFalse(piece.isWeakenByTrap(board));

        assertEquals(playerA, piece.getOwner());
        assertEquals("狗", piece.getSymbol());
        assertEquals(RANK_DOG, piece.getRank());
    }

    /**
     * Test for cat piece
     */
    @Test
    public void testCat() {
        Cat piece = new Cat(playerA);

        assertFalse(piece.isWeakenByTrap(board));

        assertEquals(playerA, piece.getOwner());
        assertEquals("貓", piece.getSymbol());
        assertEquals(RANK_CAT, piece.getRank());
    }

    /**
     * Test for Rat piece
     */
    @Test
    public void testRat() {
        Rat piece = new Rat(playerA);

        assertFalse(piece.isWeakenByTrap(board));

        assertEquals(playerA, piece.getOwner());
        assertEquals("鼠", piece.getSymbol());
        assertEquals(RANK_RAT, piece.getRank());
    }
}
