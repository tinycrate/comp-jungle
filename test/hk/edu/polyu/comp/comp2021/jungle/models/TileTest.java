package hk.edu.polyu.comp.comp2021.jungle.models;

import hk.edu.polyu.comp.comp2021.jungle.models.pieces.Cat;
import hk.edu.polyu.comp.comp2021.jungle.models.pieces.Piece;
import hk.edu.polyu.comp.comp2021.jungle.models.tiles.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for the tile
 */
public class TileTest {

    private boolean triggered = false;
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
        DenEventListener listener = new DenEventListener() {
            @Override
            public void OnTrigger(Player triggeredPlayer) {
                triggered = true;
            }
        };

        DenTile denTile = new DenTile(player);
        denTile.SubscribeEvent(listener);
        assertFalse(denTile.isOccupied());
        assertFalse(triggered);

        denTile.setOccupiedPiece(piece);
        assertEquals(player, denTile.getOwner());
        assertEquals(piece, denTile.getOccupiedPiece());
        assertTrue(denTile.isOccupied());
        assertTrue(triggered);

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
