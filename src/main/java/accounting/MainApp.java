package accounting;

import accounting.controller.*;
import accounting.model.Expense;
import accounting.model.Income;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import accounting.model.Bill;

import java.io.IOException;


public class MainApp extends Application{
    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<Bill> billData = FXCollections.observableArrayList();
    private ObservableList<Income> incomeData = FXCollections.observableArrayList();
    private ObservableList<Expense> expenseData = FXCollections.observableArrayList();

    public MainApp() {
        billData.add(new Bill("Hans", "Muster"));
        billData.add(new Bill("Ruth", "Mueller"));
        billData.add(new Bill("Heinz", "Kurz"));
        billData.add(new Bill("Cornelia", "Meier"));
        billData.add(new Bill("Werner", "Meyer"));
        billData.add(new Bill("Lydia", "Kunz"));
        billData.add(new Bill("Anna", "Best"));
        billData.add(new Bill("Stefan", "Meier"));
        billData.add(new Bill("Martin", "Mueller"));
        incomeData.add(new Income("Зарплата", 100000));
        incomeData.add(new Income("Подработка", 20000));
        incomeData.add(new Income("Стипендия", 5000));
        expenseData.add(new Expense("Образование", 90000));
        expenseData.add(new Expense("Лекарства", 10000));
        expenseData.add(new Expense("Еда", 7000));
    }

    public ObservableList<Bill> getBillData() {
        return billData;
    }

    public ObservableList<Income> getIncomeData() {
        return incomeData;
    }

    public ObservableList<Expense> getExpenseData() {
        return expenseData;
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Домашняя бухгалтерия");

        initRootLayout();

        showBillOverview();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showBillOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/BillPage.fxml"));
            AnchorPane billOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(billOverview);

            BillController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showIncomeOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/CategoryIncomePage.fxml"));
            AnchorPane billOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(billOverview);

            CategoryIncomeController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showExpenseOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/CategoryExpensePage.fxml"));
            AnchorPane billOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(billOverview);

            CategoryExpenseController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showBillEditDialog(Bill bill) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/BillEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование счета");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            BillEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setBill(bill);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showIncomeEditDialog(Income income) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/CategoryIncomeEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование таблицы дохода");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            CategoryIncomeEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setIncome(income);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showExpenseEditDialog(Expense expense) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/CategoryExpenseEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование таблицы расходов");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            CategoryExpenseEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setExpense(expense);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
