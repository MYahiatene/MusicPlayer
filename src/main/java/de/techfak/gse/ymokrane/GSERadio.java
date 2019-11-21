package de.techfak.gse.ymokrane;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

import de.techfak.gse.ymokrane.controller.GSERadioController;


public final class GSERadio extends Application {


    public GSERadio() {


    }

    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Model model = new Model(getParameters().getRaw());
        GSERadioController GSERadioController = new GSERadioController(stage, model);
        GSERadioController.viewStart();


    }

}
