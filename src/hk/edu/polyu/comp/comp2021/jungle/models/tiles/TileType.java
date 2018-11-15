package hk.edu.polyu.comp.comp2021.jungle.models.tiles;

/**
 * This enum represent different types of Tiles available
 */
public enum TileType {
    /**
     * Grass tiles are "blank" tiles that could be moved to without constraints
     */
    GRASS("Grass", false),

    /**
     * River tiles could be walked onto by Rat and jumped over by Lion and Tiger
     */
    RIVER("River", false),

    /**
     * Traps makes any animal eatable when stepped on regardless of their rank
     */
    TRAP("Trap", false),

    /**
     * Den is not moveable to by friendly animals. A player wins when they reaches the opponent's den
     * This tile is an event tile and will signal a gameover when a piece is successfully being moved to
     */
    DEN("Den", true);

    private final String name;
    private final boolean eventTile;

    TileType(String name, boolean eventTile) {
        this.name = name;
        this.eventTile = eventTile;
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
     * Returns whether a tile is an event tile
     * A event tile behaves differently than normal game tile as it might trigger major game events
     *
     * @return True if the tile is special
     */
    public boolean isEventTile(){
        return eventTile;
    }

}
