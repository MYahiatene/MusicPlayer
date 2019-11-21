package de.techfak.gse.ymokrane.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import de.techfak.gse.ymokrane.GSERadio;
import de.techfak.gse.ymokrane.Model;

public class GSERadioController implements PropertyChangeListener {
    public static Scene scene;

    List<String> arguments;

    private Stage stage;

    private Model model;

    public GSERadioController(Stage stage, Model model) {
        this.stage = stage;
        this.model = model;
        this.arguments = model.getArgs();
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
            FXMLLoader fxmlLoader = new FXMLLoader(GSERadio.class.getResource("GSERadioView"));
            GSERadioController.scene = new Scene(loadFXML("/GSERadioView"), 640, 480);
            stage.setScene(scene);
            stage.show();
            ViewController viewController = new ViewController();
            viewController.addPropertyChangeListener(this);

        } else {
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


    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("play")) {
            model.noGui();
        }
    }
}
