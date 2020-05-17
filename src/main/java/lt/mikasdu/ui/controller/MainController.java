package lt.mikasdu.ui.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Window;
import lt.mikasdu.AppNavigator;
import lt.mikasdu.User;

public class MainController {
    @FXML
    public StackPane contentHolder;
    @FXML
    public MenuItem logoutMenu;
    @FXML
    public Menu menuMenu;
    @FXML
    public MenuItem usersMenu;
    static User user = new User("Please Login");


    public void setUser(User user) {
        MainController.user = user;
        loggedInUser();
    }

    private void windowSize(int width, int height) {
        Window window = contentHolder.getScene().getWindow();
        window.setWidth(width);
        window.setHeight(height);
    }

    private void loggedInUser() {
        logoutMenu.setVisible(true);
        logoutMenu.setText("Logout: " + user.getName());
        menuMenu.setVisible(true);
            if (user.getLevel() <= 1) {
                usersMenu.setVisible(true);
            } else {
                usersMenu.setVisible(false);
            }
        windowSize(800, 600);
    }

    public void setApp(Node node) {
        contentHolder.getChildren().setAll(node);

    }

    public void closeProgram() {
        Platform.exit();
    }

    public void logOut() {
        MainController.user = new User("");
        logoutMenu.setVisible(false);
        menuMenu.setVisible(false);
        windowSize(300, 270);
        AppNavigator.loadApp(AppNavigator.LOGIN);
    }

    public void showUsers() {
        AppNavigator.loadViewAsUser(user, AppNavigator.USERS);
    }

    public void showTasks() {
        AppNavigator.loadViewAsUser(user, AppNavigator.TASKS);
    }
}
