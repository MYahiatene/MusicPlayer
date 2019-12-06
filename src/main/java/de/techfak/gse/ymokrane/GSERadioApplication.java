package de.techfak.gse.ymokrane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import de.techfak.gse.ymokrane.controller.GSERadioController;
import de.techfak.gse.ymokrane.exceptions.InvalidPathException;
import de.techfak.gse.ymokrane.exceptions.NoMp3FilesException;
import de.techfak.gse.ymokrane.model.Model;

public final class GSERadioApplication extends Application {


    public static final int STATUS_0 = 0;


    /**
     * @param args Kommandozeilenargumente
     */
    public static void main(final String[] args) {
        launch(args);

    }


    @Override
    public void start(Stage stage) throws IOException, InvalidPathException, NoMp3FilesException {
        final int width = 800;
        final int height = 500;
        List<String> test = new ArrayList<>();
        test.add("");
        final Parent root = FXMLLoader.load(getClass().getResource("/GSERadioView.fxml"));
        stage.setTitle("GUI");
        stage.setScene(new Scene(root, width, height));
        stage.show();
        System.out.println(List.of(getParameters().getRaw()));
        Model model = new Model(getParameters().getRaw());
        GSERadioController gseRadioController = new GSERadioController();
    }
}
