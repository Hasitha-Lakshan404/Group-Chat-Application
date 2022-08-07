import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author : Hasitha Lakshan
 * Project :Group Chat Application
 * Date :8/7/2022
 * Time :4:29 PM
 */

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource(".fxml"))));
        primaryStage.setResizable(false);
        //primaryStage.getIcons().add(new Image("location"));
        primaryStage.setTitle("sample title");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
