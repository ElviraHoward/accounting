package accounting.controller.wizard;

import accounting.model.Operation;
import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Elvira on 17.05.2018.
 */
public class Step1Controller {

    private Logger log = LoggerFactory.getLogger(Step1Controller.class);

    @FXML
    private TextField inputField;
    @FXML
    private TextField outputField;
    @FXML
    private TextField descField;

    @Inject
    Operation operation;

    @FXML
    public void initialize() {
        inputField.textProperty().bindBidirectional(operation.inputBillProperty() );
        outputField.textProperty().bindBidirectional(operation.outputBillProperty());
        descField.textProperty().bindBidirectional(operation.descriptionProperty());

    }

    @Validate
    public boolean validate() throws Exception {

        if( inputField.getText() == null || inputField.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Шаг 1");
            alert.setHeaderText( "Пропущено поле" );
            alert.setContentText( "Поле входного счета обязательно для заполнения" );
            alert.showAndWait();
            return false;
        }

        if( outputField.getText() == null || outputField.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Шаг 1");
            alert.setHeaderText( "Пропущено поле" );
            alert.setContentText( "Поле выходного счета обязательно для заполнения" );
            alert.showAndWait();
            return false;
        }

        if( descField.getText() == null || descField.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Шаг 1");
            alert.setHeaderText( "Пропущено поле" );
            alert.setContentText( "Поле описания обязательно для заполнения" );
            alert.showAndWait();
            return false;
        }

        return true;
    }

    @Submit
    public void submit() throws Exception {

    }
}
