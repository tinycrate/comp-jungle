package hk.edu.polyu.comp.comp2021.jungle.models;

/**
 * This class holds the basic info of a player
 */
public class Player {
    private String name;

    /**
     * Creates a Player object
     *
     * @param name The player's name
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * @return Returns the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Sets the name of the player
     */
    public void setName(String name) {
        this.name = name;
    }
}
