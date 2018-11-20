package hk.edu.polyu.comp.comp2021.jungle.views;

import java.util.StringJoiner;

/**
 * This enum represent different types of Commands available
 */
public enum CommandType {
    /**
     * Creats a new game
     * Usage: NEW
     */
    NEW(0),

    /**
     * Loads a game from disk
     * Usage: LOAD `Filename`
     */
    OPEN(1, "Filename"),

    /**
     * Saves a game to disk
     * Usage: SAVE `Filename`
     */
    SAVE(1, "Filename"),

    /**
     * Moves a tile
     * Usage: MOVE `From` `To`
     */
    MOVE(2, "From[A1..G9]", "To[A1..G9]");

    private final int argsCount;
    private final String[] args;

    CommandType(int argsCount, String... args) {
        this.argsCount = argsCount;
        this.args = args;
    }

    /**
     * @return Number of arguments needed for a command
     */
    public int getArgsCount() {
        return argsCount;
    }

    /**
     * @return The correct usage of the command
     */
    public String getCommandUsage() {
        StringJoiner sj = new StringJoiner(" ");
        sj.add(name());
        for (String arg : args) {
            sj.add(String.format("<%s>", arg));
        }
        return sj.toString();
    }
}
