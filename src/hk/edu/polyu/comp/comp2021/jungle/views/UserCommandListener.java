package hk.edu.polyu.comp.comp2021.jungle.views;

/**
 * Triggers when a player makes a command
 */
@FunctionalInterface
public interface UserCommandListener {
    /**
     * Triggers when a command has been received
     *
     * @param command The command
     */
    void OnCommand(UserCommand command);
}
