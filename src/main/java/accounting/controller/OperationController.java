package accounting.controller;

import accounting.MainApp;
import accounting.model.Operation;
import accounting.utils.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class OperationController {
    @FXML
    private TableView<Operation> operationTable;
    @FXML
    private TableColumn<Operation, String> inputColumn;
    @FXML
    private TableColumn<Operation, String> outputColumn;

    @FXML
    private Label inputLabel;
    @FXML
    private Label outputLabel;
    @FXML
    private Label descLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label valueLabel;
    @FXML
    private Label categoryLabel;

    private MainApp mainApp;

    public OperationController() {
    }

    @FXML
    private void initialize() {
        valueLabel.setTooltip(new Tooltip("в рублях"));
        inputColumn.setCellValueFactory(cellData -> cellData.getValue().inputBillProperty());
        outputColumn.setCellValueFactory(cellData -> cellData.getValue().outputBillProperty());

        operationTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showOperationDetails(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        operationTable.setItems(mainApp.getOperationData());
    }

    private void showOperationDetails(Operation operation) {
        if (operation != null) {
            inputLabel.setText(operation.getInputBill());
            outputLabel.setText(operation.getOutputBill());
            categoryLabel.setText(operation.getCategory());
            valueLabel.setText(String.valueOf(operation.getValue()));
            typeLabel.setText(operation.getType());
            descLabel.setText(operation.getDescription());
            dateLabel.setText(operation.getDateOperation().toString());
        } else {
            inputLabel.setText("");
            outputLabel.setText("");
            categoryLabel.setText("");
            valueLabel.setText("");
            typeLabel.setText("");
            descLabel.setText("");
            dateLabel.setText("");
        }
    }

    @FXML
    private void handleDeleteOperation() {
        int selectedIndex = operationTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            operationTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Не выбрано");
            alert.setHeaderText("Не выбрана операция");
            alert.setContentText("Пожалуйста, выберите операцию из таблицы");
            alert.showAndWait();
        }

    }

    @FXML
    private void handleNewOperation() {
        Operation tempOperation = new Operation();
        mainApp.showOperationAddDialogWizard(tempOperation);
    }

    @FXML
    private void handleEditOperation() {
        Operation selectedOperation = operationTable.getSelectionModel().getSelectedItem();
        if (selectedOperation != null) {
            boolean okClicked = mainApp.showOperationEditDialog(selectedOperation);
            if (okClicked) {
                showOperationDetails(selectedOperation);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Не выбрано");
            alert.setHeaderText("Операция не выбрана");
            alert.setContentText("Пожалуйста, выберите операцию из таблицы");

            alert.showAndWait();
        }
    }

}
