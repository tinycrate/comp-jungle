package hk.edu.polyu.comp.comp2021.jungle.models.tiles;

/**
 * This enum represent different types of Tiles available
 */
public enum TileType {
    /**
     * Grass tiles are "blank" tiles that could be moved to without constraints
     */
    GRASS("Grass", "　", true),

    /**
     * River tiles could be walked onto by Rat and jumped over by Lion and Tiger
     */
    RIVER("River", "～", true),

    /**
     * Traps weakens any opponent animal which makes them eatable by animals of any ranks
     * This tile is not neutral and will be owned by either of the players
     */
    TRAP("Trap", "井", false),

    /**
     * Den is not moveable to by friendly animals. A player wins when they reaches the opponent's den
     * This tile is not neutral and will be owned by either of the players
     * This tile is an event tile and will signal a gameover when a piece is successfully being moved to
     */
    DEN("Den", "穴", false);

    private final String name;
    private final String placeHolder;
    private final boolean neutralTile;

    TileType(String name, String placeHolder, boolean neutralTile) {
        this.name = name;
        this.placeHolder = placeHolder;
        this.neutralTile = neutralTile;
    }

    /**
     * Returns the name of the tile
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the placeholder of the tile which would be displayed if the tile is empty
     *
     * @return The placeholder as String
     */
    public String getPlaceHolder() {
        return placeHolder;
    }

    /**
     * Returns whether a tile is neutral
     * A neutral tiles do not have a permanent owner
     *
     * @return True if the tile is neutral
     */
    public boolean isNeutralTile() {
        return neutralTile;
    }
}
