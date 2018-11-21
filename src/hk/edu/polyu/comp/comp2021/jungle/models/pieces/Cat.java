package hk.edu.polyu.comp.comp2021.jungle.models.pieces;

import hk.edu.polyu.comp.comp2021.jungle.models.Player;

/**
 * Implementation of the BasicPiece Cat
 */
public class Cat extends BasicPiece {

    /**
     * Creates a Piece object with reference to the board and its coordinates
     * Most likely called by board
     *
     * @param owner The owner of the piece
     */
    public Cat(Player owner) {
        super(owner);
    }

    @Override
    public int getRank() {
        return 2;
    }

    @Override
    public String getSymbol() {
        return "貓";
    }
}
