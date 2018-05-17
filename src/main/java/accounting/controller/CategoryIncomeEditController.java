package accounting.controller;

import accounting.model.Income;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CategoryIncomeEditController {

    @FXML
    private TextField incomeColumn;
    @FXML
    private TextField sumColumn;

    private Stage dialogStage;
    private Income income;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setIncome(Income income) {
        this.income = income;

        incomeColumn.setText(income.getCategoryIncome());
        sumColumn.setText(String.valueOf(income.getSum()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            income.setCategoryIncome(incomeColumn.getText());
            income.setSum(Integer.parseInt(sumColumn.getText()));

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

        if (incomeColumn.getText() == null || incomeColumn.getText().length() == 0) {
            errorMessage += "Ошибка ввода источника дохода!\n";
        }
        if (sumColumn.getText() == null || sumColumn.getText().length() == 0) {
            errorMessage += "Неверная сумма!\n";
        } else {
            try {
                Integer.parseInt(sumColumn.getText());
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
