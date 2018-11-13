package hk.edu.polyu.comp.comp2021.jungle.models.tiles;

/**
 * This enum represent different types of Tiles available
 */
public enum TileType {
    /**
     * Grass tiles are "blank" tiles that could be moved to without constraints
     */
    GRASS("Grass"),

    /**
     * River tiles could be walked onto by Rat and jumped over by Lion and Tiger
     */
    RIVER("River"),

    /**
     * Traps makes any animal's rank 0 when being stepped on
     */
    TRAP("Trap"),

    /**
     * Den is not moveable to by friendly animals. A player wins when they reaches the opponent's den
     */
    DEN("Den");

    private final String name;

    TileType(String name) {
        this.name = name;
    }

    /**
     * @return Returns the name of the tile
     */
    public String getName() {
        return name;
    }
}
