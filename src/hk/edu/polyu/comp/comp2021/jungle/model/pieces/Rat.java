package hk.edu.polyu.comp.comp2021.jungle.model.pieces;

import hk.edu.polyu.comp.comp2021.jungle.model.Board;
import hk.edu.polyu.comp.comp2021.jungle.model.Coordinates;
import hk.edu.polyu.comp.comp2021.jungle.model.Player;
import hk.edu.polyu.comp.comp2021.jungle.model.tiles.Tile;
import hk.edu.polyu.comp.comp2021.jungle.model.tiles.TileType;

/**
 * Implementation of the Piece Rat
 */
public class Rat extends Piece {

    /**
     * Creates a Piece object with reference to the board and its coordinates
     * Most likely called by board
     *
     * @param owner The owner of the piece
     */
    public Rat(Player owner) {
        super(owner, PieceType.RAT);
    }

    @Override
    public boolean isMoveableTo(Coordinates coords, Board board) {
        // The animals are only allowed to move 1 tile in either direction
        if (Math.abs(coords.getX() - board.getCoordinates(this).getX()) + Math.abs(coords.getY() - board.getCoordinates(this).getY()) != 1) {
            return false;
        }

        Tile source = board.getTile(board.getCoordinates(this));
        Tile destination = board.getTile(coords);

        // Going into its own den is not allowed
        if (destination.getTileType() == TileType.DEN && destination.getOwner() == getOwner()) return false;

        // If the destination is not occupied, the rat can visit freely
        if (!destination.isOccupied()) return true;

        // Runnning into friendly pieces is not allowed
        if (destination.getOccupiedPiece().getOwner() == getOwner()) return false;

        // Going from land to river but someone's in the way is not allowed
        if (source.getTileType() == TileType.GRASS && destination.getTileType() == TileType.RIVER) {
            return false;
        }

        // Going from river to land but someone's in the way is not allowed
        if (source.getTileType() == TileType.RIVER && destination.getTileType() == TileType.GRASS) {
            return false;
        }

        // Rats can only eat animals in the trap, Elephants or Rats
        if (destination.isOccupied()) {
            Piece opponent = destination.getOccupiedPiece();
            return opponent.isWeakenByTrap(board) || opponent instanceof Elephant || opponent instanceof Rat;
        }

        return true;
    }
}
