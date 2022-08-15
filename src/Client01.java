import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author : Hasitha Lakshan
 * Project :Group Chat Application
 * Date :8/8/2022
 * Time :2:00 PM
 */

public class Client01 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {



        KeyCombination kc = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_ANY);

        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("assets/messenger.gif"));
        primaryStage.setTitle("Messenger");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
