package accounting.controller;

import accounting.model.Operation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 * Created by Elvira on 17.05.2018.
 */
public class OperationEditController {

    @FXML
    private TextField inputBillColumn;
    @FXML
    private TextField outputBillColumn;
    @FXML
    private TextField descriptionColumn;
    @FXML
    private DatePicker dateColumn;
    @FXML
    private ComboBox typeColumn;
    @FXML
    private ComboBox categoryColumn;
    @FXML
    private TextField valueColumn;


    private Stage dialogStage;
    private Operation operation;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        ObservableList<String> categories =
                FXCollections.observableArrayList(
                        "Доход",
                        "Расход"
                );
        categoryColumn.setItems(categories);
        categoryColumn.setValue("Доходы");
        ObservableList<String> types =
                FXCollections.observableArrayList(
                        "Приход",
                        "Расход",
                        "Перевод"
                );
        typeColumn.setItems(types);
        typeColumn.setValue("Приход");
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;

        inputBillColumn.setText(operation.getInputBill());
        outputBillColumn.setText(operation.getOutputBill());
        descriptionColumn.setText(operation.getDescription());
        dateColumn.setValue(operation.getDateOperation());
        typeColumn.setValue(operation.getType());
        categoryColumn.setValue(operation.getCategory());
        valueColumn.setText(String.valueOf(operation.getValue()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            operation.setInputBill(inputBillColumn.getText());
            operation.setOutputBill(outputBillColumn.getText());
            operation.setDescription(descriptionColumn.getText());
            operation.setDateOperation(dateColumn.getValue());
            operation.setType(typeColumn.getSelectionModel().getSelectedItem().toString());
            operation.setCategory(categoryColumn.getSelectionModel().getSelectedItem().toString());
            operation.setValue(Integer.parseInt(valueColumn.getText()));
            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (inputBillColumn.getText() == null || inputBillColumn.getText().length() == 0) {
            errorMessage += "Ошибка ввода счёта!\n";
        }
        if (outputBillColumn.getText() == null || outputBillColumn.getText().length() == 0) {
            errorMessage += "Ошибка ввода счёта!\n";
        }
        if (descriptionColumn.getText() == null || descriptionColumn.getText().length() == 0) {
            errorMessage += "Ошибка ввода описания!\n";
        }
        if (dateColumn.getValue() == null || dateColumn.getValue().getDayOfMonth() == 0) {
            errorMessage += "Неверная дата!\n";
        } else {
            try {
                dateColumn.getValue();
            } catch (NumberFormatException e) {
                errorMessage += "Неверная дата(введите число)!\n";
            }
        }
        if (valueColumn.getText() == null || valueColumn.getText().length() == 0) {
            errorMessage += "Неверная сумма!\n";
        } else {
            try {
                Integer.parseInt(valueColumn.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Неверная сумма(введите число)!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Неверные данные");
            alert.setHeaderText("Пожалуйста, введите корректные данные");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
