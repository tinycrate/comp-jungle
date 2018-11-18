package hk.edu.polyu.comp.comp2021.jungle.models.pieces;

import hk.edu.polyu.comp.comp2021.jungle.models.Coordinates;
import hk.edu.polyu.comp.comp2021.jungle.models.Player;

/**
 * Implementation of the BasicPiece Leopard
 */
public class Leopard extends BasicPiece {

    /**
     * Creates a Piece object with reference to the board and its coordinates
     * Most likely called by board
     *
     * @param coordinates The coordinares of the piece on the board
     * @param owner       The owner of the piece
     */
    public Leopard(Coordinates coordinates, Player owner) {
        super(coordinates, owner);
    }

    @Override
    public int getRank() {
        return 5;
    }

    @Override
    public String getSymbol() {
        return "豹";
    }
}
