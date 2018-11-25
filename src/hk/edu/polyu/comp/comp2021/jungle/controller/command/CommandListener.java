package hk.edu.polyu.comp.comp2021.jungle.controller.command;

/**
 * Triggers when a player makes a command
 */
@FunctionalInterface
public interface CommandListener {
    /**
     * Triggers when a command has been received
     *
     * @param command The command
     */
    void OnCommand(Command command);
}
