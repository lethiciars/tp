package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

public class ViewCommandNameTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validName_success() {
        Person personToView = model.getFilteredPersonList().get(0);
        Name validName = personToView.getName();

        ViewCommandName viewCommand = new ViewCommandName(validName);

        String expectedMessage = String.format(ViewCommandName.MESSAGE_VIEW_PERSON_SUCCESS, personToView);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, personToView);

        assertCommandSuccess(viewCommand, model, expectedCommandResult, model);
    }

    @Test
    public void execute_invalidName_throwsCommandException() {
        Name invalidName = new Name("Invalid name");
        ViewCommandName viewCommand = new ViewCommandName(invalidName);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
    }

    @Test
    public void equals() {
        Person firstNameInList = getTypicalPersons().get(0);
        Person secondNameInList = getTypicalPersons().get(1);

        ViewCommandName viewFirstCommand = new ViewCommandName(firstNameInList.getName());
        ViewCommandName viewSecondCommand = new ViewCommandName(secondNameInList.getName());

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommandName viewFirstCommandCopy = new ViewCommandName(firstNameInList.getName());
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }
}
