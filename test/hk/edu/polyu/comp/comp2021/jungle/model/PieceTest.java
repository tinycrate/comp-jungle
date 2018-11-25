package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.model.pieces.*;
import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

/**
 * Test for the piece
 */
public class PieceTest {

    private Player playerA, playerB;
    private Board board;
    private BoardConfiguration config;

    /**
     * Setup game before doing test
     * @throws UnsupportedEncodingException File path decode may not success
     */
    @Before
    public void prepare() throws UnsupportedEncodingException {
        config = BoardConfigurationSave.getGeneralSave(getClass().getClassLoader());
        assertNotNull(config);

        board = new Board(config);
        playerA = config.getPlayerOne();
        playerB = config.getPlayerTwo();
    }

    /**
     * Test for the Elephant piece
     */
    @Test
    public void testElephant() {
        Elephant piece = new Elephant(playerA);
        assertEquals(8, piece.getRank());
        assertEquals("象", piece.getSymbol());
    }
    
    /**
     * Test for the Lion piece
     */
    @Test
    public void testLion() {
        Lion piece = new Lion(playerA);
        assertEquals(7, piece.getRank());
        assertEquals("獅", piece.getSymbol());
    }

    /**
     * Test for the Tiger piece
     */
    @Test
    public void testTiger() {
        Tiger piece = new Tiger(playerA);
        assertEquals(6, piece.getRank());
        assertEquals("虎", piece.getSymbol());
    }

    /**
     * Test for the Leopard piece
     */
    @Test
    public void testLeopard() {
        Leopard piece = new Leopard(playerA);
        assertEquals(5, piece.getRank());
        assertEquals("豹", piece.getSymbol());
    }

    /**
     * Test for the Wolf piece
     */
    @Test
    public void testWolf() {
        Wolf piece = new Wolf(playerA);
        assertEquals(4, piece.getRank());
        assertEquals("狼", piece.getSymbol());
    }

    /**
     * Test for the Dog piece
     */
    @Test
    public void testDog() {
        Dog piece = new Dog(playerA);
        assertEquals(3, piece.getRank());
        assertEquals("狗", piece.getSymbol());
    }

    /**
     * Test for the Cat piece
     */
    @Test
    public void testCat() {
        Cat piece = new Cat(playerA);
        assertEquals(2, piece.getRank());
        assertEquals("貓", piece.getSymbol());
    }
}
