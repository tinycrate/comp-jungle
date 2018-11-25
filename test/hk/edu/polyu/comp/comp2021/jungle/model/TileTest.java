package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.model.pieces.Cat;
import hk.edu.polyu.comp.comp2021.jungle.model.pieces.Piece;
import hk.edu.polyu.comp.comp2021.jungle.model.tiles.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for the tile
 */
public class TileTest {

    private Player player, enemy;
    private Piece piece;

    /**
     * Prepare player and piece before testing
     */
    @Before
    public void prepare() {
        player = new Player("Self");
        enemy = new Player("Enemy");
        piece = new Cat(enemy);
    }

    /**
     * test all method in TrapTile
     */
    @Test
    public void testTrapTile() {
        TrapTile trapTile = new TrapTile(player);
        assertEquals("Trap", trapTile.getTileType().getName());
        assertEquals("井", trapTile.getTileType().getPlaceHolder());
        assertFalse(trapTile.isOccupied());

        trapTile.setOccupiedPiece(piece);
        assertEquals(player, trapTile.getOwner());
        assertEquals(piece, trapTile.getOccupiedPiece());
        assertTrue(trapTile.isOccupied());

        assertEquals(TileType.TRAP, trapTile.getTileType());
        assertFalse(trapTile.getTileType().isNeutralTile());

        TrapTile trapTileClone = (TrapTile) trapTile.getClone();
        assertSame(trapTileClone.getOwner(), trapTile.getOwner());
        assertSame(trapTileClone.getOccupiedPiece(), trapTile.getOccupiedPiece());
        assertSame(trapTileClone.getTileType(), trapTile.getTileType());
        assertTrue(trapTileClone.isOccupied());
    }

    /**
     *test case for vaild TileType
     */
    @Test
    public void testDenTile() {
        TestDenEventListener listener = new TestDenEventListener();

        DenTile denTile = new DenTile(player);
        denTile.SubscribeEvent(listener);
        assertEquals("Den", denTile.getTileType().getName());
        assertEquals("穴", denTile.getTileType().getPlaceHolder());
        assertFalse(denTile.isOccupied());
        assertFalse(listener.isTriggered());

        denTile.setOccupiedPiece(piece);
        assertEquals(player, denTile.getOwner());
        assertEquals(piece, denTile.getOccupiedPiece());
        assertTrue(denTile.isOccupied());
        assertTrue(listener.isTriggered());

        denTile.UnsubscribeEvent(listener);

        assertEquals(TileType.DEN, denTile.getTileType());
        assertFalse(denTile.getTileType().isNeutralTile());

        DenTile denTileClone = (DenTile) denTile.getClone();
        assertSame(denTileClone.getOwner(), denTile.getOwner());
        assertSame(denTileClone.getOccupiedPiece(), denTile.getOccupiedPiece());
        assertSame(denTileClone.getTileType(), denTile.getTileType());
        assertTrue(denTileClone.isOccupied());
    }

    /**
     * Test for neutral tile.
     */
    @Test
    public void testNeutralTile() {
        NeutralTile grassTile = new NeutralTile(TileType.GRASS);
        assertEquals("Grass", grassTile.getTileType().getName());
        assertEquals("　", grassTile.getTileType().getPlaceHolder());
        assertFalse(grassTile.isOccupied());

        grassTile.setOccupiedPiece(piece);
        assertEquals(enemy, grassTile.getOwner());
        assertEquals(piece, grassTile.getOccupiedPiece());
        assertTrue(grassTile.isOccupied());

        assertEquals(TileType.GRASS, grassTile.getTileType());
        assertTrue(grassTile.getTileType().isNeutralTile());

        NeutralTile neutralTileClone = (NeutralTile) grassTile.getClone();
        assertSame(neutralTileClone.getOwner(), grassTile.getOwner());
        assertSame(neutralTileClone.getOccupiedPiece(), grassTile.getOccupiedPiece());
        assertSame(neutralTileClone.getTileType(), grassTile.getTileType());
        assertTrue(neutralTileClone.isOccupied());

        NeutralTile riverTile = new NeutralTile(TileType.RIVER);
        assertEquals(TileType.RIVER, riverTile.getTileType());
        assertEquals("River", riverTile.getTileType().getName());
        assertEquals("～", riverTile.getTileType().getPlaceHolder());

    }

    /**
     * Test for creating neutral tile with trap tile type
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTrapWithNeutralTileCreation() {
        NeutralTile tile = new NeutralTile(TileType.TRAP);
    }

    /**
     * Test for creating neutral tile with den tile type
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDenWithNeutralTileCreation() {
        NeutralTile tile = new NeutralTile(TileType.DEN);
    }
}
