package accounting.controller.wizard;

import accounting.model.Operation;
import accounting.utils.DateUtil;
import com.google.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Elvira on 17.05.2018.
 */
public class ConfirmController {

    private Logger log = LoggerFactory.getLogger(ConfirmController.class);

    @FXML
    TextField inputField, outputField, descField, typeField, categoryField, valueField, dateField;

    @Inject
    Operation operation;

    @FXML
    public void initialize() {
        inputField.textProperty().bind(operation.inputBillProperty());
        outputField.textProperty().bind(operation.outputBillProperty());
        descField.textProperty().bind(operation.descriptionProperty());
        typeField.textProperty().bind(operation.typeProperty());
        categoryField.textProperty().bind(operation.categoryProperty());
        valueField.textProperty().bind(operation.valueProperty().asString());
        dateField.textProperty().bind(operation.dateOperationProperty().asString());
    }

    @Submit
    public void submit() throws Exception {
        operation.setOutputBill(inputField.getText());
        operation.setInputBill(outputField.getText());
        operation.setDateOperation(DateUtil.parse(dateField.getText()));
        operation.setType(typeField.getText());
        operation.setCategory(categoryField.getText());
        operation.setValue(Integer.parseInt(valueField.getText()));
        operation.setDescription(descField.getText());
        if( log.isDebugEnabled() ) {
            log.debug("[SUBMIT] saving fields 1-7 to the database via a web service call (not really)");
        }
    }


    @Finish
    public Operation getOperation() {
        return operation;
    }
}
