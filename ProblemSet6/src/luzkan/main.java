//Marcel Jerzyk napierdalał

package luzkan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {

    @Override
    public void start(Stage stage)
            throws IOException {
        Parent root =
                FXMLLoader
                        .load(getClass()
                                .getResource("kolorki.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Random Colors Grid Creator");

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}