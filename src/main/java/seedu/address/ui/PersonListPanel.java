package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    // Ui parts in this Ui container
    private PersonPreview personPreview = new PersonPreview();

    @FXML
    private ListView<Person> personListView;

    @FXML
    private StackPane personPreviewPlaceHolder;


    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList) {
        super(FXML);
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
        personListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Person selected = personListView.getSelectionModel().getSelectedItem();
                logger.info("clicked on " + selected);
                updatePreview(selected);
            }
        });
        //personPreviewPlaceHolder.getChildren().add(personPreview.getRoot());

    }

    void updatePreview(Person selected) {
        //personPreview = new PersonPreview(selected);
        personPreview.setSelectedPerson(selected);
        //personPreviewPlaceHolder.getChildren().add(personPreview.getRoot());
    }

    /*
    @FXML
    public void handleMouseClick(MouseEvent mouseEvent) {
        Person selectedPerson = personListView.getSelectionModel().getSelectedItem();
        //personPreviewPanel.setPerson(selectedPerson);
        logger.info("clicked on " + selectedPerson);
    }
     */

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
