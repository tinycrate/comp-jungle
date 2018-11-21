package hk.edu.polyu.comp.comp2021.jungle.models.pieces;

import hk.edu.polyu.comp.comp2021.jungle.models.Board;
import hk.edu.polyu.comp.comp2021.jungle.models.Coordinates;
import hk.edu.polyu.comp.comp2021.jungle.models.Player;
import hk.edu.polyu.comp.comp2021.jungle.models.tiles.Tile;
import hk.edu.polyu.comp.comp2021.jungle.models.tiles.TileType;

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
        super(owner);
    }

    @Override
    public int getRank() {
        return 1;
    }

    @Override
    public boolean isMoveableTo(Coordinates coords, Board board) {
        // The animals are only allowed to move 1 tile in either direction
        if (Math.abs(coords.getX() - board.getCoordinates(this).getX()) + Math.abs(coords.getY() - board.getCoordinates(this).getY()) != 1) {
            return false;
        }

        Tile source = board.getTile(board.getCoordinates(this));
        Tile destination = board.getTile(coords);

        // Runnning into friendly pieces or its own den is not allowed
        if (destination.getOwner() == getOwner()) return false;

        // If the destination is not occupied, the rat can visit freely
        if (!destination.isOccupied()) return true;

        // Going from land to river but someone's in the way is not allowed
        if (source.getTileType() == TileType.GRASS && destination.getTileType() == TileType.RIVER) {
            return false;
        }

        // Going from river to land but someone's in the way is not allowed
        if (source.getTileType() == TileType.RIVER && destination.getTileType() == TileType.GRASS) {
            return false;
        }

        // Rats can only eat animals in the trap or Elephants
        if (destination.isOccupied()) {
            Piece opponent = destination.getOccupiedPiece();
            return opponent.isWeakenByTrap(board) || opponent instanceof Elephant;
        }

        return true;
    }

    @Override
    public String getSymbol() {
        return "鼠";
    }
}
