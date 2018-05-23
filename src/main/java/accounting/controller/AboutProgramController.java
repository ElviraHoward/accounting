package accounting.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


public class AboutProgramController {

    @FXML
    private ListView listHelp;

    @FXML
    private void initialize() {
        ObservableList<String> items = FXCollections.observableArrayList (
                "Добавление сущностей", "Изменение сущностей", "Удаление сущностей", "Добавление операции с помощью мастера");
        listHelp.setItems(items);
    }
}
