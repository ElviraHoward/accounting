package accounting.controller;

import accounting.model.Expense;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CategoryExpenseEditController {

    @FXML
    private TextField expenseColumn;
    @FXML
    private TextField sumColumn;

    private Stage dialogStage;
    private Expense expense;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;

        expenseColumn.setText(expense.getCategoryExpense());
        sumColumn.setText(String.valueOf(expense.getSum()));
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            expense.setCategoryExpense(expenseColumn.getText());
            expense.setSum(Integer.parseInt(sumColumn.getText()));

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

        if (expenseColumn.getText() == null || expenseColumn.getText().length() == 0) {
            errorMessage += "Ошибка ввода источника расхода!\n";
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
