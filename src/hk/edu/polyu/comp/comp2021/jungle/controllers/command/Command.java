package hk.edu.polyu.comp.comp2021.jungle.controllers.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String[] args = splitCommand(command);
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
        String[] args = splitCommand(command);
        if (args.length <= 0) return null;
        String commandName = args[0].toUpperCase();
        for (CommandType type : CommandType.values()) {
            if (type.name().equals(commandName)) return type;
        }
        return null;
    }

    private static String[] splitCommand(String command) {
        List<String> matches = new ArrayList<>();
        Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
        Matcher matcher = regex.matcher(command);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                matches.add(matcher.group(1));
            } else if (matcher.group(2) != null) {
                matches.add(matcher.group(2));
            } else {
                matches.add(matcher.group());
            }
        }
        return matches.toArray(new String[]{});
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
