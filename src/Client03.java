import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author : Hasitha Lakshan
 * Project :Group Chat Application
 * Date :8/15/2022
 * Time :3:56 PM
 */

public class Client03 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/ColourChatForm.fxml"))));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("assets/messenger.gif"));
        primaryStage.setTitle("Messenger");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
