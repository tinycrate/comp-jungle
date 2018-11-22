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
     * @param owner The owner of the piece
     */
    public Elephant(Player owner) {
        super(owner, PieceType.ELEPHANT);
    }

    @Override
    public boolean isMoveableTo(Coordinates coords, Board board) {
        // The animals are only allowed to move 1 tile in either direction
        if (Math.abs(coords.getX() - board.getCoordinates(this).getX()) + Math.abs(coords.getY() - board.getCoordinates(this).getY()) != 1) {
            return false;
        }

        Tile tile = board.getTile(coords);

        // Going into river is not allowed
        if (tile.getTileType() == TileType.RIVER) return false;

        // Going into its own den is not allowed
        if (tile.getTileType() == TileType.DEN && tile.getOwner() == getOwner()) return false;

        // If the destination is not occupied, they can visit freely
        if (!tile.isOccupied()) return true;

        // Running into a friendly piece is not allowed
        if (tile.getOwner() == getOwner()) return false;

        // Running into a rat unless it's in a trap, otherwise anything can be eaten
        Piece opponent = tile.getOccupiedPiece();
        return opponent.isWeakenByTrap(board) || !(opponent instanceof Rat);
    }
}
