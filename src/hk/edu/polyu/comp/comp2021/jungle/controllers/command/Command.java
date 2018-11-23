package hk.edu.polyu.comp.comp2021.jungle.controllers.command;

import java.util.Arrays;

/**
 * This class stores a user command obtained from View
 * The command will then be passed to the Controller for further processing
 */
public final class Command {

    /**
     * Gets a command from String
     *
     * @param command The command to be parsed
     * @return The Command object, null if the command is invalid
     */
    public static Command getCommand(String command) {
        CommandType type = getCommandType(command);
        if (type == null) return null;
        String[] args = command.trim().split("\\s+");
        if (args.length <= 0) return null;
        if (args.length != type.getArgsCount() + 1) return null;
        return new Command(type, Arrays.copyOfRange(args, 1, args.length));
    }

    /**
     * Gets the type of the command from String
     * It does not validate the number of arguments given
     *
     * @param command The command
     * @return The CommandType of the string, null if no matching found
     */
    public static CommandType getCommandType(String command) {
        String[] args = command.trim().split("\\s+");
        if (args.length <= 0) return null;
        String commandName = args[0].toUpperCase();
        for (CommandType type : CommandType.values()) {
            if (type.name().equals(commandName)) return type;
        }
        return null;
    }

    private final CommandType type;
    private final String[] args;

    /**
     * Creates a command object
     *
     * @param type The type of the command
     * @param args The arguments
     */
    public Command(CommandType type, String[] args) {
        this.type = type;
        this.args = args;
    }

    /**
     * @return The type of the command
     */
    public CommandType getType() {
        return type;
    }

    /**
     * @return args The arguments of the command
     */
    public String[] getArgs() {
        return args;
    }
}
