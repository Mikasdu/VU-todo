package lt.mikasdu.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lt.mikasdu.Task;

public class TaskAddController {
    @FXML public Button cancelButton;
    @FXML public Label headerLabelCategory;
    @FXML public TextField taskDescription;
    @FXML public TextField userId;
    @FXML public ComboBox<String> taskStatus;

    private Task task;
    private boolean isNew;

    public void initData(Task task) {
        this.task = task;
        setDefaultSettings();
    }

    private void setDefaultSettings() {
        isNew = this.task.getId() == 0;
        if (isNew) {
            headerLabelCategory.setText("Add new task");
        } else {
            headerLabelCategory.setText("Edit task Id: " + task.getId());
            taskDescription.setText(task.getTaskDescription());
            userId.setText(String.valueOf(task.getUserId()));
            taskStatus.getSelectionModel().select(task.getStatus());
        }
    }

    public void buttonSave() {
        task.setTaskDescription(taskDescription.getText());
        task.setStatus(taskStatus.getSelectionModel().getSelectedItem());
        task.setUserId(Integer.parseInt(userId.getText()));
        if (isNew) {
            task.saveToDatabase();
        } else {
            task.updateDatabase();
        }
        closeCurrentWindow();
    }

    public void buttonCancel() {
        closeCurrentWindow();
    }

    private void closeCurrentWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
