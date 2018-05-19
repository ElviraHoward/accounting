package accounting.controller.wizard;

import accounting.model.Operation;
import com.google.inject.Inject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Elvira on 17.05.2018.
 */
public class Step2Controller {

    private Logger log = LoggerFactory.getLogger(Step2Controller.class);

    @FXML
    ComboBox typeField;
    @FXML
    TextField valueField;

    @Inject
    Operation operation;

    @FXML
    public void initialize() {
        ObservableList<String> types =
                FXCollections.observableArrayList(
                        "Приход",
                        "Расход",
                        "Перевод"
                );
        typeField.setItems(types);
        typeField.setValue("Приход");
        typeField.valueProperty().bindBidirectional(operation.typeProperty());
        valueField.textProperty().bindBidirectional(operation.valueProperty(), new NumberStringConverter());
    }

    @Validate
    public boolean validate() throws Exception {
        if (valueField.getText() == null || valueField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Шаг 2");
            alert.setHeaderText("Пропущено поле");
            alert.setContentText("Поле суобязательно для заполнения");
            alert.showAndWait();
            return false;
        }
        return true;
    }
}
