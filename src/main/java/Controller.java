import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    Canvas canvas;

    @FXML
    TextField text;

    @FXML
    Button button;

    AnalogClock analogClock;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        analogClock = new AnalogClock(canvas.getGraphicsContext2D());
    }

    public void arrows(ActionEvent actionEvent) {
        analogClock.drawClockHands();
    }

}
