package hk.edu.polyu.comp.comp2021.jungle.models.pieces;

import hk.edu.polyu.comp.comp2021.jungle.models.Player;

/**
 * Implementation of the BasicPiece Wolf
 */
public class Wolf extends BasicPiece {

    /**
     * Creates a Piece object with reference to the board and its coordinates
     * Most likely called by board
     *
     * @param owner The owner of the piece
     */
    public Wolf(Player owner) {
        super(owner, PieceType.WOLF);
    }
}
