package accounting.controller;

import accounting.MainApp;
import accounting.model.Expense;
import accounting.model.Income;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CategoryController {

    @FXML
    private TableView<Income> incomeTable;
    @FXML
    private TableColumn<Income, String> categoryIncomeColumn;
    @FXML
    private TableColumn<Income, Integer> sumIncomeColumn;

    @FXML
    private TableView<Expense> expenseTable;
    @FXML
    private TableColumn<Expense, String> categoryExpenseColumn;
    @FXML
    private TableColumn<Expense, Integer> sumColumn;

    private MainApp mainApp;

    public CategoryController() {
    }

    @FXML
    private void initialize() {
        categoryIncomeColumn.setCellValueFactory(cellData -> cellData.getValue().categoryIncomeProperty());
        sumIncomeColumn.setCellValueFactory(cellData -> cellData.getValue().sumProperty().asObject());
        categoryExpenseColumn.setCellValueFactory(cellData -> cellData.getValue().categoryExpenseProperty());
        sumColumn.setCellValueFactory(cellData -> cellData.getValue().sumProperty().asObject());

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        incomeTable.setItems(mainApp.getIncomeData());
        expenseTable.setItems(mainApp.getExpenseData());
    }

    @FXML
    private void handleDeleteIncome() {
        int selectedIndex = incomeTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            incomeTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Не выбрано");
            alert.setHeaderText("Не выбран доход");
            alert.setContentText("Пожалуйста, выберите доход из таблицы");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewIncome() {
        Income tempIncome = new Income();
        tempIncome.setSum(0);
        boolean okClicked = mainApp.showIncomeEditDialog(tempIncome);
        if (okClicked) {
            mainApp.getIncomeData().add(tempIncome);
        }
    }

    @FXML
    private void handleEditIncome() {
        Income selectedIncome = incomeTable.getSelectionModel().getSelectedItem();
        if (selectedIncome != null) {
            boolean okClicked = mainApp.showIncomeEditDialog(selectedIncome);
            if (okClicked) {

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Не выбрано");
            alert.setHeaderText("Доход не выбран");
            alert.setContentText("Пожалуйста, выберите доход из таблицы");

            alert.showAndWait();
        }
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
