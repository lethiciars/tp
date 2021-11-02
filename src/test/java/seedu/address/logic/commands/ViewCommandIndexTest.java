package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class ViewCommandIndexTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Person personToView = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ViewCommandIndex viewCommand = new ViewCommandIndex(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ViewCommandIndex.MESSAGE_VIEW_PERSON_SUCCESS, personToView);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, personToView);

        Model expectedModel = model;

        assertCommandSuccess(viewCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index invalidIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ViewCommandIndex viewCommand = new ViewCommandIndex(invalidIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewCommandIndex viewFirstCommand = new ViewCommandIndex(INDEX_FIRST_PERSON);
        ViewCommandIndex viewSecondCommand = new ViewCommandIndex(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommandIndex viewFirstCommandCopy = new ViewCommandIndex(INDEX_FIRST_PERSON);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

}
