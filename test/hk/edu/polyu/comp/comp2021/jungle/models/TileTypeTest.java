package hk.edu.polyu.comp.comp2021.jungle.models;
import hk.edu.polyu.comp.comp2021.jungle.models.tiles.TileType;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class TileTypeTest {
    /**
     *test case for vaild TileType
     */
    @Test
    public void testDen() {
        TileType test = TileType.DEN;
        assertTrue("Den".equals(test.getName()));
        assertEquals("穴",test.getPlaceHolder());
        assertFalse(test.isNeutralTile());

    }
}
