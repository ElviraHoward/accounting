package accounting.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Operation {

    private final StringProperty inputBill;
    private final StringProperty outputBill;
    private final StringProperty description;
    private final ObjectProperty<LocalDate> dateOperation;
    private final StringProperty type;
    private final StringProperty category;
    private final IntegerProperty value;

    public Operation() {
        this(null, null);
    }

    public Operation(String inputBill, String outputBill) {
        this.inputBill = new SimpleStringProperty(inputBill);
        this.outputBill = new SimpleStringProperty(outputBill);
        this.description = new SimpleStringProperty("Описание");
        this.dateOperation = new SimpleObjectProperty<LocalDate>(LocalDate.of(2018, 5, 26));
        this.category = new SimpleStringProperty("Доход");
        this.value = new SimpleIntegerProperty(0);
        this.type = new SimpleStringProperty("Приход");}

    public String getOutputBill() {
        return outputBill.get();
    }

    public StringProperty outputBillProperty() {
        return outputBill;
    }

    public void setOutputBill(String outputBill) {
        this.outputBill.set(outputBill);
    }

    public String getInputBill() {
        return inputBill.get();
    }

    public StringProperty inputBillProperty() {
        return inputBill;
    }

    public void setInputBill(String inputBill) {
        this.inputBill.set(inputBill);
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public int getValue() {
        return value.get();
    }

    public IntegerProperty valueProperty() {
        return value;
    }

    public void setValue(int value) {
        this.value.set(value);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public LocalDate getDateOperation() {
        return dateOperation.get();
    }

    public ObjectProperty<LocalDate> dateOperationProperty() {
        return dateOperation;
    }

    public void setDateOperation(LocalDate dateOperation) {
        this.dateOperation.set(dateOperation);
    }
}
