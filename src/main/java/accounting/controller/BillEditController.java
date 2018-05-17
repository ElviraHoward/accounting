package accounting.controller;

import accounting.model.Bill;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javafx.scene.control.TextField;

public class BillEditController {

    @FXML
    private TextField nameColumn;
    @FXML
    private TextField descriptionColumn;
    @FXML
    private TextField balanceColumn;


    private Stage dialogStage;
    private Bill bill;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setBill(Bill bill) {
        this.bill = bill;

        nameColumn.setText(bill.getName());
        descriptionColumn.setText(bill.getDescription());
        balanceColumn.setText(Integer.toString(bill.getBalance()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            bill.setName(nameColumn.getText());
            bill.setDescription(descriptionColumn.getText());
            bill.setBalance(Integer.parseInt(balanceColumn.getText()));

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

        if (nameColumn.getText() == null || nameColumn.getText().length() == 0) {
            errorMessage += "Ошибка ввода названия!\n";
        }
        if (descriptionColumn.getText() == null || descriptionColumn.getText().length() == 0) {
            errorMessage += "Ошибка ввода описания!\n";
        }
        if (balanceColumn.getText() == null || balanceColumn.getText().length() == 0) {
            errorMessage += "Неверный баланс!\n";
        } else {
            try {
                Integer.parseInt(balanceColumn.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Неверный баланс(введите число)!\n";
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
