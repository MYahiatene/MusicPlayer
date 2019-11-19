package de.techfak.gse.ymokrane;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

import de.techfak.gse.ymokrane.fxml.ViewController;


public final class GSERadio extends Application {


    public GSERadio() {


    }

    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        ViewController viewController = new ViewController(stage, getParameters().getRaw());
        viewController.viewStart();


    }

}
