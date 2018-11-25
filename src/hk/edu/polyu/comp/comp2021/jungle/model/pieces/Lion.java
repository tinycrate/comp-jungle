package hk.edu.polyu.comp.comp2021.jungle.model.pieces;

import hk.edu.polyu.comp.comp2021.jungle.model.Player;

/**
 * Implementation of the RiverJumperPiece Lion
 */
public class Lion extends RiverJumperPiece {

    /**
     * Creates a Piece object with reference to the board and its coordinates
     * Most likely called by board
     *
     * @param owner The owner of the piece
     */
    public Lion(Player owner) {
        super(owner, PieceType.LION);
    }
}
