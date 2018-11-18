package hk.edu.polyu.comp.comp2021.jungle.models.pieces;

import hk.edu.polyu.comp.comp2021.jungle.models.Coordinates;
import hk.edu.polyu.comp.comp2021.jungle.models.Player;

/**
 * Implementation of the BasicPiece Dog
 */
public class Dog extends BasicPiece {

    /**
     * Creates a Piece object with reference to the board and its coordinates
     * Most likely called by board
     *
     * @param coordinates The coordinares of the piece on the board
     * @param owner       The owner of the piece
     */
    public Dog(Coordinates coordinates, Player owner) {
        super(coordinates, owner);
    }

    @Override
    public int getRank() {
        return 3;
    }

    @Override
    public String getSymbol() {
        return "狗";
    }
}
