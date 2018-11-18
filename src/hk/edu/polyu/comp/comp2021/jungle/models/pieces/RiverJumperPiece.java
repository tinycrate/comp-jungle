package hk.edu.polyu.comp.comp2021.jungle.models.pieces;

import hk.edu.polyu.comp.comp2021.jungle.models.Board;
import hk.edu.polyu.comp.comp2021.jungle.models.Coordinates;
import hk.edu.polyu.comp.comp2021.jungle.models.Player;
import hk.edu.polyu.comp.comp2021.jungle.models.tiles.Tile;
import hk.edu.polyu.comp.comp2021.jungle.models.tiles.TileType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * This class contains default implementations for animals that can jump over rivers
 */
public abstract class RiverJumperPiece extends Piece {

    /**
     * Creates a Piece object with reference to the board and its coordinates
     * Most likely called by board
     *
     * @param coordinates The coordinares of the piece on the board
     * @param owner       The owner of the piece
     */
    RiverJumperPiece(Coordinates coordinates, Player owner) {
        super(coordinates, owner);
    }

    @Override
    public boolean isMoveableTo(Coordinates coords, Board board) {
        // The animals are only allowed to move 1 tile in either direction, unless they are placed near the river
        if (Math.abs(coords.getX() - getCoordinates().getX()) + Math.abs(coords.getY() - getCoordinates().getY()) != 1) {
            // TODO: 1) Check if the animal is currently near the river and if the destination is right at the other end of the river, if not return false
            // TODO: 2) Check if there's a rat intervening, if yes return false
            // TODO: 3) Remove the NotImplementedException and the todo comments when finished
            throw new NotImplementedException();
        }

        Tile tile = board.getTile(coords);

        // Going into river is not allowed
        if (tile.getTileType() == TileType.RIVER) return false;

        // Runnning into friendly pieces or its own den is not allowed
        if (tile.getOwner() == getOwner()) return false;

        // Running into an animal with higher rank is not allowed unless it's in a trap
        if (tile.isOccupied()) {
            Piece opponent = tile.getOccupiedPiece();
            return opponent.isWeakenByTrap(board) || opponent.getRank() <= getRank();
        }

        return true;
    }
}
