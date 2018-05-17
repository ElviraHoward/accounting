package accounting.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Expense {

    private final StringProperty categoryExpense;
    private final IntegerProperty sum;

    public Expense() {
        this(null, 0);
    }

    public Expense(String categoryExpense, Integer sum) {
        this.categoryExpense = new SimpleStringProperty(categoryExpense);
        this.sum = new SimpleIntegerProperty(sum);
    }

    public String getCategoryExpense() {
        return categoryExpense.get();
    }

    public StringProperty categoryExpenseProperty() {
        return categoryExpense;
    }

    public void setCategoryExpense(String categoryExpense) {
        this.categoryExpense.set(categoryExpense);
    }

    public int getSum() {
        return sum.get();
    }

    public IntegerProperty sumProperty() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum.set(sum);
    }
}
