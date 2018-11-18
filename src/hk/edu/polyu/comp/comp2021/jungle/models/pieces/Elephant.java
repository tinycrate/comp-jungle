package hk.edu.polyu.comp.comp2021.jungle.models.pieces;

import hk.edu.polyu.comp.comp2021.jungle.models.Board;
import hk.edu.polyu.comp.comp2021.jungle.models.Coordinates;
import hk.edu.polyu.comp.comp2021.jungle.models.Player;
import hk.edu.polyu.comp.comp2021.jungle.models.tiles.Tile;
import hk.edu.polyu.comp.comp2021.jungle.models.tiles.TileType;

/**
 * Implementation of the Piece Elephant
 */
public class Elephant extends Piece {

    /**
     * Creates a Piece object with reference to the board and its coordinates
     * Most likely called by board
     *
     * @param board       The board the piece is currently on
     * @param coordinates The coordinares of the piece on the board
     * @param owner       The owner of the piece
     */
    public Elephant(Board board, Coordinates coordinates, Player owner) {
        super(board, coordinates, owner);
    }

    @Override
    public int getRank() {
        return 8;
    }

    @Override
    public boolean isMoveableTo(Coordinates coords) {
        // The animals are only allowed to move 1 tile in either direction
        if (Math.abs(coords.getX() - getCoordinates().getX()) + Math.abs(coords.getY() - getCoordinates().getY()) != 1) {
            return false;
        }

        Tile tile = getBoard().getTile(coords);

        // Going into river is not allowed
        if (tile.getTileType() == TileType.RIVER) return false;

        // Runnning into friendly pieces or its own den is not allowed
        if (tile.getOwner() == getOwner()) return false;

        // Running into a rat is not allowed unless it's in a trap, otherwise anything can be eaten
        if (tile.isOccupied()) {
            Piece opponent = tile.getOccupiedPiece();
            return opponent.isWeakenByTrap() || !(opponent instanceof Rat);
        }

        return true;
    }
}
