package accounting.controller;

import accounting.MainApp;
import accounting.model.Expense;
import accounting.model.Income;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CategoryExpenseController {

    @FXML
    private TableView<Expense> expenseTable;
    @FXML
    private TableColumn<Expense, String> categoryExpenseColumn;
    @FXML
    private TableColumn<Expense, Integer> sumColumn;

    private MainApp mainApp;

    public CategoryExpenseController() {
    }

    @FXML
    private void initialize() {
        categoryExpenseColumn.setCellValueFactory(cellData -> cellData.getValue().categoryExpenseProperty());
        sumColumn.setCellValueFactory(cellData -> cellData.getValue().sumProperty().asObject());

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        expenseTable.setItems(mainApp.getExpenseData());
    }

    @FXML
    private void handleDeleteExpense() {
        int selectedIndex = expenseTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            expenseTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Не выбрано");
            alert.setHeaderText("Не выбран расход");
            alert.setContentText("Пожалуйста, выберите расход из таблицы");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewExpense() {
        Expense tempExpense = new Expense();
        boolean okClicked = mainApp.showExpenseEditDialog(tempExpense);
        if (okClicked) {
            mainApp.getExpenseData().add(tempExpense);
        }
    }

    @FXML
    private void handleEditExpense() {
        Expense selectedExpense = expenseTable.getSelectionModel().getSelectedItem();
        if (selectedExpense != null) {
            boolean okClicked = mainApp.showExpenseEditDialog(selectedExpense);
            if (okClicked) {

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Не выбрано");
            alert.setHeaderText("Расход не выбран");
            alert.setContentText("Пожалуйста, выберите расход из таблицы");

            alert.showAndWait();
        }
    }

}
