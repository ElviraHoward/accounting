package accounting.controller;

import accounting.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import accounting.model.Bill;

public class BillController {

    @FXML
    private TableView<Bill> billTable;
    @FXML
    private TableColumn<Bill, String> nameColumn;
    @FXML
    private TableColumn<Bill, String> descriptionColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label balanceLabel;

    private MainApp mainApp;

    public BillController() {
    }

    @FXML
    private void initialize() {
        balanceLabel.setTooltip(new Tooltip("в рублях"));
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        billTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBillDetails(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        billTable.setItems(mainApp.getBillData());
    }


    private void showBillDetails(Bill bill) {
        if (bill != null) {
            nameLabel.setText(bill.getName());
            descriptionLabel.setText(bill.getDescription());
            balanceLabel.setText(Integer.toString(bill.getBalance()));
        } else {
            nameLabel.setText("");
            descriptionLabel.setText("");
            balanceLabel.setText("");
        }
    }

    @FXML
    private void handleDeleteBill() {
        int selectedIndex = billTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            billTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Не выбрано");
            alert.setHeaderText("Не выбран счёт");
            alert.setContentText("Пожалуйста, выберите счёт из таблицы");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewBill() {
        Bill tempBill = new Bill();
        boolean okClicked = mainApp.showBillEditDialog(tempBill);
        if (okClicked) {
            mainApp.getBillData().add(tempBill);
        }
    }

    @FXML
    private void handleEditBill() {
        Bill selectedBill = billTable.getSelectionModel().getSelectedItem();
        if (selectedBill != null) {
            boolean okClicked = mainApp.showBillEditDialog(selectedBill);
            if (okClicked) {
                showBillDetails(selectedBill);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Не выбрано");
            alert.setHeaderText("Счёт не выбран");
            alert.setContentText("Пожалуйста, выберите счёт из таблицы");

            alert.showAndWait();
        }
    }
}
