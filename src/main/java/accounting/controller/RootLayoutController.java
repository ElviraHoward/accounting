package accounting.controller;

import accounting.MainApp;
import javafx.fxml.FXML;

public class RootLayoutController {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleBill() {
        mainApp.showBillOverview();
    }

    @FXML
    private void handleIncome() {
        mainApp.showIncomeOverview();
    }

    @FXML
    private void handleOperation() {
        mainApp.showOperationOverview();
    }

    @FXML
    private void handleStatistics() {
        mainApp.showStatistics();
    }
}
