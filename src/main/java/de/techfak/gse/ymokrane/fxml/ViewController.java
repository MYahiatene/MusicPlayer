package de.techfak.gse.ymokrane.fxml;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import de.techfak.gse.ymokrane.GSERadio;
import de.techfak.gse.ymokrane.Model;

public class ViewController {
    public static Scene scene;

    List<String> arguments;

    private Stage stage;

    public ViewController(Stage stage, List<String> arguments) {
        this.stage = stage;
        GSERadio gseRadio = new GSERadio();
        this.arguments = arguments;
    }

    public static Scene getScene() {
        return scene;
    }

    public Stage getStage() {
        return stage;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public void viewStart() throws IOException {
        if (arguments.contains("--gui") || arguments.contains("-g")) {
            FXMLLoader fxmlLoader = new FXMLLoader(GSERadio.class.getResource("GSERadioView.fxml"));
            ViewController.scene = new Scene(loadFXML("/GSERadioView"), 640, 480);
            stage.setScene(scene);
            stage.show();
        } else {
            Model model = new Model(arguments);
            model.noGui();

        }
    }

    private Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GSERadio.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }


}
