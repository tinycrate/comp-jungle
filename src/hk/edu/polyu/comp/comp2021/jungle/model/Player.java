package hk.edu.polyu.comp.comp2021.jungle.model;

import java.io.Serializable;

/**
 * This class holds the basic info of a player
 */
public class Player implements Serializable {
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
     * Returns the name of the player
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
