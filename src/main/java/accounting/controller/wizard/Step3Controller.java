package accounting.controller.wizard;

import accounting.model.Operation;
import com.google.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

/**
 * Created by Elvira on 17.05.2018.
 */
public class Step3Controller {

    private Logger log = LoggerFactory.getLogger(Step3Controller.class);

    @FXML
    private ComboBox categoryField;
    @FXML
    private DatePicker dateField;

    @Inject
    Operation operation;

    @FXML
    public void initialize() {
        ObservableList<String> categories =
                FXCollections.observableArrayList(
                        "Доход",
                        "Расход"
                );
        categoryField.setItems(categories);
        categoryField.setValue("Доходы");
        categoryField.valueProperty().bindBidirectional(operation.categoryProperty());
        dateField.valueProperty().bindBidirectional(operation.dateOperationProperty());
        dateField.setValue(LocalDate.now());
    }

    @Validate
    public boolean validate() throws Exception {
        return true;
    }

    @Submit
    public void submit() throws Exception {

    }
}
