package luzkan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Paint extends Application {

    @Override
    public void start(Stage stage)
            throws IOException {
        Parent root =
                FXMLLoader
                        .load(getClass()
                                .getResource("paint.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Prawie Photoshop, ale póki co - Paint");


        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}




    /*@Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("paint.fxml"))));
        stage.setTitle("Paint - piękny on :3");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }*/