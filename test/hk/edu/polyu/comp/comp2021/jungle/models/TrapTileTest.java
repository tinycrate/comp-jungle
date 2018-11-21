package hk.edu.polyu.comp.comp2021.jungle.models;
import hk.edu.polyu.comp.comp2021.jungle.models.tiles.TrapTile;
import hk.edu.polyu.comp.comp2021.jungle.models.pieces.Cat;
import hk.edu.polyu.comp.comp2021.jungle.models.Player;
import hk.edu.polyu.comp.comp2021.jungle.models.Coordinates;
import hk.edu.polyu.comp.comp2021.jungle.models.tiles.TileType;
import org.junit.Test;

import static org.junit.Assert.*;
import static sun.audio.AudioPlayer.player;

/**
 * unit test for TrapTile
 */
public class TrapTileTest {
    /**
     * test all method in TrapTile
     */
    @Test
    public void TrapTileTest() {
        Player ken = new Player("ken");
        Coordinates testC=new Coordinates("A1");
        Cat cat= new Cat(testC,ken);
        TrapTile TestTile=new TrapTile(ken);
        TileType testType= TileType.TRAP;
        assertSame(ken,TestTile.getOwner());
        TestTile.setOccupiedPiece(cat);
        TrapTile catTrap=new TrapTile(TestTile);
        assertSame(cat,catTrap.getOccupiedPiece());
        TrapTile newTrap= (TrapTile) TestTile.getClone();
        assertSame(newTrap.getTileType(),TestTile.getTileType());
        assertSame(newTrap.getOwner(),TestTile.getOwner());
        assertSame(TestTile.getTileType(),testType);

    }
}

