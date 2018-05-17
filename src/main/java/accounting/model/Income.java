package accounting.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Income {

    private final StringProperty categoryIncome;
    private final IntegerProperty sum;

    public Income() {
        this(null, 0);
    }

    public Income(String categoryIncome, Integer sum) {
        this.categoryIncome = new SimpleStringProperty(categoryIncome);
        this.sum = new SimpleIntegerProperty(sum);
    }

    public String getCategoryIncome() {
        return categoryIncome.get();
    }

    public StringProperty categoryIncomeProperty() {
        return categoryIncome;
    }

    public void setCategoryIncome(String categoryIncome) {
        this.categoryIncome.set(categoryIncome);
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
