package de.techfak.gse.ymokrane.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class GSERadioController extends Application {

    public static void main(final String... args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws IOException {
        final int width = 800;
        final int height = 500;

        final Parent root = FXMLLoader.load(getClass().getResource("/GSERadioView.fxml"));
        stage.setTitle("GUI");
        stage.setScene(new Scene(root, width, height));
        stage.show();
    }
}

