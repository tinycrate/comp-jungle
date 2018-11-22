package hk.edu.polyu.comp.comp2021.jungle.models.pieces;

import hk.edu.polyu.comp.comp2021.jungle.models.Board;
import hk.edu.polyu.comp.comp2021.jungle.models.Coordinates;
import hk.edu.polyu.comp.comp2021.jungle.models.Player;
import hk.edu.polyu.comp.comp2021.jungle.models.tiles.Tile;
import hk.edu.polyu.comp.comp2021.jungle.models.tiles.TileType;

/**
 * This class contains default implementations for animals that can jump over rivers
 */
public abstract class RiverJumperPiece extends Piece {

    /**
     * Creates a Piece object with reference to the board and its coordinates
     * Most likely called by board
     *
     * @param owner The owner of the piece
     */
    RiverJumperPiece(Player owner, PieceType type) {
        super(owner, type);
    }

    @Override
    public boolean isMoveableTo(Coordinates coords, Board board) {

        int moveDis = Math.abs(coords.getX() - board.getCoordinates(this).getX())
                + Math.abs(coords.getY() - board.getCoordinates(this).getY());

        // The animals are only allowed to move 1 tile in either direction, unless they are placed near the river
        if (moveDis < 1) return false;

        if (moveDis > 1) {
            // Allow horizontal jump over river
            if (coords.getX() == board.getCoordinates(this).getX()
                    && (coords.getX() == 3 || coords.getX() == 4 || coords.getX() == 5)
                    && Math.abs(coords.getY() - board.getCoordinates(this).getY()) == 3) {

                // Check whether river tile has a rat, which is the only Piece that can go into river
                int min = Math.min(board.getCoordinates(this).getY(), coords.getY());
                Coordinates riverL = new Coordinates(coords.getX(), min + 1);
                Coordinates riverR = new Coordinates(coords.getX(), min + 2);
                return !board.getTile(riverL).isOccupied() && !board.getTile(riverR).isOccupied();

                // Allow vertical jump over river
            } else if (coords.getY() == board.getCoordinates(this).getY()
                    && (coords.getY() == 1 || coords.getY() == 2 || coords.getY() == 4 || coords.getY() == 5)
                    && Math.abs(coords.getX() - board.getCoordinates(this).getX()) == 4) {

                // Check whether river tile has a rat, which is the only Piece that can go into river
                int min = Math.min(board.getCoordinates(this).getX(), coords.getX());
                Coordinates riverL = new Coordinates(coords.getY(), min + 1);
                Coordinates riverM = new Coordinates(coords.getY(), min + 2);
                Coordinates riverR = new Coordinates(coords.getY(), min + 3);
                return !board.getTile(riverL).isOccupied() && !board.getTile(riverM).isOccupied() && !board.getTile(riverR).isOccupied();
            }
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
        if (tile.getOccupiedPiece().getOwner() == getOwner()) return false;

        // Running into an animal with higher rank is not allowed unless it's in a trap
        Piece opponent = tile.getOccupiedPiece();
        return opponent.isWeakenByTrap(board) || opponent.getRank() <= getRank();
    }
}
