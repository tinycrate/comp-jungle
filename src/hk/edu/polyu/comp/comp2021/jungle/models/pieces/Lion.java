package hk.edu.polyu.comp.comp2021.jungle.models.pieces;

import hk.edu.polyu.comp.comp2021.jungle.models.Coordinates;
import hk.edu.polyu.comp.comp2021.jungle.models.Player;

/**
 * Implementation of the RiverJumperPiece Lion
 */
public class Lion extends RiverJumperPiece {

    /**
     * Creates a Piece object with reference to the board and its coordinates
     * Most likely called by board
     *
     * @param coordinates The coordinares of the piece on the board
     * @param owner       The owner of the piece
     */
    public Lion(Coordinates coordinates, Player owner) {
        super(coordinates, owner);
    }

    @Override
    public int getRank() {
        return 7;
    }
}
