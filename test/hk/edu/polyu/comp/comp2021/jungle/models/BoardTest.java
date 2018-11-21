package hk.edu.polyu.comp.comp2021.jungle.models;

import hk.edu.polyu.comp.comp2021.jungle.models.tiles.Tile;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for Board
 */
public class BoardTest {
	/**
	 *
	 */
	@Test
	public void prepare() {
		Tile[][] test = new Tile[Board.BOARD_WIDTH][Board.BOARD_HEIGHT];
	}

	/**
	 *
	 */
	@Test
	public void test(){
		assert Board.BOARD_WIDTH==7;
		assert Board.BOARD_HEIGHT==9;
	}
	
	
}
