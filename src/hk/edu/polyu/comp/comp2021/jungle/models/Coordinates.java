package hk.edu.polyu.comp.comp2021.jungle.models;

import java.io.Serializable;

/**
 * A simple immutable class for keeping board coordinates
 */
public class Coordinates implements Serializable {

    /**
     * Checks whether a string represents valid coordinates
     *
     * @param coords The coordinates in String format: "A1" ~ "G9"
     * @return True if the coordinates are valid
     */
    public static boolean isValid(String coords) {
        if (coords.length() != 2) return false;
        if (coords.charAt(0) < 'A' || coords.charAt(0) > 'G') return false;
        return coords.charAt(1) >= '1' && coords.charAt(1) <= '9';
    }

    private final int x;
    private final int y;

    /**
     * Construct the class with integral values
     *
     * @param x The x coordinates from the board
     * @param y The y coordinates from the board
     */
    public Coordinates(int x, int y) {
        if (x < 0 || x >= Board.BOARD_WIDTH) throw new IllegalArgumentException("Invalid x coordinate specified");
        if (y < 0 || y >= Board.BOARD_HEIGHT) throw new IllegalArgumentException("Invalid y coordinate specified");
        this.x = x;
        this.y = y;
    }

    /**
     * Construct the class with a string value
     * Note: The string notation for the y axis is reversed
     *
     * @param coords The coordinates in String format: "A1" ~ "G9"
     */
    public Coordinates(String coords) {
        coords = coords.toUpperCase();
        if (isValid(coords)) {
            this.x = coords.charAt(0) - 'A';
            this.y = 8 - coords.charAt(1) - '1';
        } else {
            throw new IllegalArgumentException("Invalid string coordinates specified");
        }
    }

    /**
     * Returns the x coordinate
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinates
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinates)) return false;
        Coordinates other = (Coordinates) o;
        return x == other.getX() && y == other.getY();
    }
}
