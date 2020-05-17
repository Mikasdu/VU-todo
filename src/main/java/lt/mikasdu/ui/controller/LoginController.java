package lt.mikasdu.ui.controller;


import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lt.mikasdu.AppNavigator;
import lt.mikasdu.User;
import lt.mikasdu.ui.alerts.AlertBox;
import lt.mikasdu.ui.database.SqlConnection;

public class LoginController {
    @FXML public TextField inputName;
    @FXML public PasswordField inputPassword;

    public void buttonLogin() {
        User user = new User(inputName.getText());
        SqlConnection.getUserByName(user.getName(), user);
        if (user.getPassword() != null && user.getPassword().equals(inputPassword.getText())) {
            AppNavigator.loadViewAsUser(user, AppNavigator.TASKS);
        } else {
            AlertBox.alertSimple("No such user or bad password.");
        }
    }

}
