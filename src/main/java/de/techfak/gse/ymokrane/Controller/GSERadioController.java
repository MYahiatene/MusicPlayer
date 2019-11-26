package de.techfak.gse.ymokrane.Controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GSERadioController extends Application {

    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/GSERadioView.fxml"));
        stage.setTitle("GUI");
        stage.setScene(new Scene(root, 800, 500));
        stage.show();
    }
}

