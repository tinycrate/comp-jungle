package hk.edu.polyu.comp.comp2021.jungle.models.pieces;

import hk.edu.polyu.comp.comp2021.jungle.models.Board;
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
     * @param board       The board the piece is currently on
     * @param coordinates The coordinares of the piece on the board
     * @param owner       The owner of the piece
     */
    public Dog(Board board, Coordinates coordinates, Player owner) {
        super(board, coordinates, owner);
    }

    @Override
    public int getRank() {
        return 3;
    }
}