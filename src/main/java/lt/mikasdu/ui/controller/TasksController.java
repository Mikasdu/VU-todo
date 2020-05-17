package lt.mikasdu.ui.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lt.mikasdu.AppNavigator;
import lt.mikasdu.Task;
import lt.mikasdu.User;
import lt.mikasdu.ui.alerts.AlertBox;
import lt.mikasdu.ui.database.SqlConnection;


import java.net.URL;
import java.util.ResourceBundle;


public class TasksController implements Initializable {
    @FXML public Button editTaskButton;
    @FXML public Button deleteTaskButton;
    @FXML public Button newTaskButton;

    @FXML public TableView<Task> tbData;
    @FXML public TableColumn<Task, Integer> taskId;
    @FXML public TableColumn<Task, String> taskDescription;
    @FXML public TableColumn<Task, String> taskStatus;
    @FXML public TableColumn<Task, Integer> userId;

    User user = MainController.user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      if (user.getLevel() <= 1) {
          editTaskButton.setVisible(true);
          deleteTaskButton.setVisible(true);
          newTaskButton.setVisible(true);
      } else {
          editTaskButton.setVisible(false);
          deleteTaskButton.setVisible(false);
          newTaskButton.setVisible(false);
      }
      updateTableData();
    }

    private void updateTableData() {
        ObservableList<Task> taskObservableList = SqlConnection.getTasksByUserId(user.getId(), user.getLevel());
        taskId.setCellValueFactory(new PropertyValueFactory<>("id"));
        taskDescription.setCellValueFactory(new PropertyValueFactory<>("taskDescription"));
        taskStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        userId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        tbData.setItems(taskObservableList);
    }

    private void taskWindow(Task task) {
        AppNavigator.addTask(task, "Edit");
        updateTableData();
    }

    public void newTask() {
        taskWindow(new Task());
    }

    public void editTask() {
        if (tbData.getSelectionModel().isEmpty()) {
            AlertBox.alertSimple("Select task first");
        } else {
            taskWindow(tbData.getSelectionModel().getSelectedItem());
        }
    }

    public void markAsDone() {
        if (tbData.getSelectionModel().isEmpty()) {
            AlertBox.alertSimple("Select task first");
        } else {
            Task selectedTask = tbData.getSelectionModel().getSelectedItem();
            if (selectedTask.getStatus().equals("Active")) {
                selectedTask.setStatus("Completed");
                selectedTask.updateDatabase();
                updateTableData();
            } else {
                AlertBox.alertSimple("You can mark as Completed only Active tasks");
            }
        }
    }

    public void deleteTask() {
        if (tbData.getSelectionModel().isEmpty()) {
            AlertBox.alertSimple("Select task first");
        } else {
            Task selectedTask = tbData.getSelectionModel().getSelectedItem();
            if (!selectedTask.getStatus().equals("Deleted")) {
                selectedTask.setStatus("Deleted");
                selectedTask.updateDatabase();
                updateTableData();
            } else {
                AlertBox.alertSimple("You cant delete Deleted task.");
            }
        }
    }
}
