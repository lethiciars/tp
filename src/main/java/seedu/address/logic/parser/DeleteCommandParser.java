package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteCommandIndex;
import seedu.address.logic.commands.DeleteCommandName;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        ParseException pe;
        // by index
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteCommandIndex(index);
        } catch (ParseException peIndex) {
            pe = peIndex;
        }

        try {
            Name name = ParserUtil.parseName(args);
            return new DeleteCommandName(name);
        } catch (ParseException peName) {
            pe = peName;
        }

        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);

    }

}
