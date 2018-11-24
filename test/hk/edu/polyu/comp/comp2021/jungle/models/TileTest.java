package hk.edu.polyu.comp.comp2021.jungle.models;

import hk.edu.polyu.comp.comp2021.jungle.models.pieces.Cat;
import hk.edu.polyu.comp.comp2021.jungle.models.tiles.TileType;
import hk.edu.polyu.comp.comp2021.jungle.models.tiles.TrapTile;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for the tile
 */
public class TileTest {

    /**
     * test all method in TrapTile
     */
    @Test
    public void testtrapTile() {
        Player ken = new Player("ken");
        Cat cat = new Cat(ken);
        TrapTile TestTile = new TrapTile(ken);
        TileType testType = TileType.TRAP;
        assertSame(ken, TestTile.getOwner());
        TestTile.setOccupiedPiece(cat);
        TrapTile catTrap = new TrapTile(TestTile);
        assertSame(cat, catTrap.getOccupiedPiece());
        TrapTile newTrap = (TrapTile) TestTile.getClone();
        assertSame(newTrap.getTileType(), TestTile.getTileType());
        assertSame(newTrap.getOwner(), TestTile.getOwner());
        assertSame(TestTile.getTileType(), testType);
    }

    /**
     *test case for vaild TileType
     */
    @Test
    public void testDenTile() {
        TileType test = TileType.DEN;
        assertEquals("Den", test.getName());
        assertEquals("穴",test.getPlaceHolder());
        assertFalse(test.isNeutralTile());
    }
}
