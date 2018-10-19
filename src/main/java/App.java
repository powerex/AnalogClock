import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = new FXMLLoader().load(getClass().getResourceAsStream("clock.fxml"));
        primaryStage.setTitle("Clock");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() {
    }
}
