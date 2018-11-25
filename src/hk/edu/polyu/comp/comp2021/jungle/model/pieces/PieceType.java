package hk.edu.polyu.comp.comp2021.jungle.model.pieces;

/**
 * Total type for piece.
 */
public enum PieceType {
    /**
     * Elephant piece
     */
    ELEPHANT(8, "象"),

    /**
     * Lion piece
     */
    LION(7, "獅"),

    /**
     * Tiger piece
     */
    TIGER(6, "虎"),

    /**
     * Lepoard piece
     */
    LEOPARD(5, "豹"),

    /**
     * Wolf piece
     */
    WOLF(4, "狼"),

    /**
     * Dog piece
     */
    DOG(3, "狗"),

    /**
     * Cat piece
     */
    CAT(2, "貓"),

    /**
     * Rat piece
     */
    RAT(1, "鼠");


    private int rank;
    private String symbol;

    PieceType(int rank, String symbol) {
        this.rank = rank;
        this.symbol = symbol;
    }

    /**
     * @return The rank of piece
     */
    public int getRank() {
        return rank;
    }

    /**
     * @return The symbol of piece
     */
    public String getSymbol() {
        return symbol;
    }
}
