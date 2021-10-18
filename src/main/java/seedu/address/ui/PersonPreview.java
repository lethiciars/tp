package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonPreview extends UiPart<Region> {

    private static final String FXML = "PersonPreviewCard.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonPreview.class);
    private Person selectedPerson;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    //public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label category;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label review;
    @FXML
    private FlowPane tags;
    @FXML
    private Label rating;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonPreview() {
        super(FXML);
        this.selectedPerson = null;
        category.setText("no data");
        name.setText("no data");
        phone.setText("no data");
        address.setText("no data");
        review.setText("no data");
        email.setText("no data");
        rating.setText("no data");
        logger.info("updated with empty person");
        //this.selectedPerson = selectedPerson;
    }

    public PersonPreview(Person person) {
        super(FXML);
        this.selectedPerson = person;
        category.setText(selectedPerson.getCategoryCode().toString());
        name.setText(selectedPerson.getName().fullName);
        phone.setText(selectedPerson.getPhone().value);
        address.setText(selectedPerson.getAddress().value);
        review.setText(selectedPerson.getReview().value);
        email.setText(selectedPerson.getEmail().value);
        selectedPerson.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        rating.setText(selectedPerson.getRating().value + "\u2B50");
        logger.info("updated with new person " + person);
    }



    public void setSelectedPerson(Person person) {
        this.selectedPerson = person;
        category.setText(selectedPerson.getCategoryCode().toString());
        name.setText(selectedPerson.getName().fullName);
        phone.setText(selectedPerson.getPhone().value);
        address.setText(selectedPerson.getAddress().value);
        review.setText(selectedPerson.getReview().value);
        email.setText(selectedPerson.getEmail().value);
        selectedPerson.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        rating.setText(selectedPerson.getRating().value + "\u2B50");
        logger.info("set with new person " + person);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonPreview)) {
            return false;
        }

        // state check
        PersonPreview preview = (PersonPreview) other;
        return selectedPerson.equals(preview.selectedPerson);
    }
}
