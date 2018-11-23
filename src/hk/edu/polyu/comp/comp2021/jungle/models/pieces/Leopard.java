package hk.edu.polyu.comp.comp2021.jungle.models.pieces;

import hk.edu.polyu.comp.comp2021.jungle.models.Player;

/**
 * Implementation of the BasicPiece Leopard
 */
public class Leopard extends BasicPiece {

    /**
     * Creates a Piece object with reference to the board and its coordinates
     * Most likely called by board
     *
     * @param owner The owner of the piece
     */
    public Leopard(Player owner) {
        super(owner, PieceType.LEOPARD);
    }
}
