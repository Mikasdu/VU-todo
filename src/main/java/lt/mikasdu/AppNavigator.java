package lt.mikasdu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lt.mikasdu.ui.controller.MainController;
import lt.mikasdu.ui.controller.TaskAddController;

import java.io.IOException;


public class AppNavigator {
    static final String MAIN = "main.fxml";
    public static final String LOGIN = "login.fxml";
    public static final String TASKS = "tasks.fxml";
    public static final String USERS = "users.fxml";
    public static final String TASK_ADD = "taskAdd.fxml";

    private static MainController mainController;

    static void setMainController(MainController mainController) {
        AppNavigator.mainController = mainController;
    }

    public static void loadApp(String fxml) {
        try {
            mainController.setApp(
                    FXMLLoader.load(AppNavigator.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadViewAsUser(User user, String fxml) {
        try {
            mainController.setUser(user);
            mainController.setApp(
                    FXMLLoader.load(AppNavigator.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addTask(Task task, String windowName) {
        Stage window = new Stage();
        window.setTitle(windowName);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AppNavigator.class.getResource(AppNavigator.TASK_ADD));
        StackPane layout = new StackPane();
        try {
            layout = loader.load();
        } catch (IOException e) {
            System.out.println(e);
        }
        Scene scene = new Scene(layout);
        TaskAddController taskAddController = loader.getController();
        taskAddController.initData(task);
        scene.getStylesheets().setAll(AppNavigator.class.getResource("main.css").toExternalForm());
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.setScene(scene);
        window.showAndWait();
    }
}