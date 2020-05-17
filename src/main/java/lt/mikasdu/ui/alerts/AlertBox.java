package lt.mikasdu.ui.alerts;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class AlertBox {

    private static Alert alert(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UTILITY);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");
        alert.setContentText(text);
        return alert;
    }

    public static void alertSimple(String text) {
        Alert alert = alert(text);
        alert.showAndWait();
    }


}
