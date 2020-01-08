package de.techfak.gse.ymokrane;

import de.techfak.gse.ymokrane.controller.GSERadioController;
import de.techfak.gse.ymokrane.exceptions.InvalidPathException;
import de.techfak.gse.ymokrane.exceptions.NoMp3FilesException;
import de.techfak.gse.ymokrane.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public final class GSERadioApplication extends Application {


    public static final int STATUS_0 = 0;

    /*default*/ static final String VIEWFXML = "/GSERadioView.fxml";


    /**
     * @param args Kommandozeilenargumente
     */
    public static void main(final String[] args) {
        launch(args);

    }


    @Override
    public void start(final Stage stage) throws IOException, InvalidPathException, NoMp3FilesException {
        final int width = 1024;
        final int height = 500;
        final Model model = new Model(getParameters().getRaw().toArray(new String[getParameters().getRaw().size()]));
        final GSERadioController gseRadioController = new GSERadioController(model);
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(VIEWFXML));
        loader.setController(gseRadioController);
        final Parent root = loader.load();
        stage.setTitle("GUI");
        stage.setScene(new Scene(root, width, height));
        stage.show();
        stage.setOnCloseRequest(e -> model.getPlayer().getMediaPlayer().release());

    }
}
