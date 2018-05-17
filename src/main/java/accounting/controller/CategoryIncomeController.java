package accounting.controller;

import accounting.MainApp;
import accounting.model.Income;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CategoryIncomeController {

    @FXML
    private TableView<Income> incomeTable;
    @FXML
    private TableColumn<Income, String> categoryIncomeColumn;
    @FXML
    private TableColumn<Income, Integer> sumColumn;

    private MainApp mainApp;

    public CategoryIncomeController() {
    }

    @FXML
    private void initialize() {
        categoryIncomeColumn.setCellValueFactory(cellData -> cellData.getValue().categoryIncomeProperty());
        sumColumn.setCellValueFactory(cellData -> cellData.getValue().sumProperty().asObject());

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        incomeTable.setItems(mainApp.getIncomeData());
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


}
