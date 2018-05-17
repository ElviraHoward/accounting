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
    private void handleExpense() {
        mainApp.showExpenseOverview();
    }

    @FXML
    private void handleSettings() {
//        Operation tempOperation = new Operation();
//        boolean okClicked = mainApp.showOperationEditDialogWizard(tempOperation);
//        if (okClicked) {
//            mainApp.getRevenueOperationData().add(tempOperation);
//        }
//        mainApp.getPrimaryStage().getScene().getStylesheets().add(MainApp.class.getResource("controller/darkTheme.css").toExternalForm());
    }

}
