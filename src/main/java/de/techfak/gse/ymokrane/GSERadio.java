package de.techfak.gse.ymokrane;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import de.techfak.gse.ymokrane.exceptions.InvalidPathException;
import de.techfak.gse.ymokrane.exceptions.NoMp3FilesException;
import de.techfak.gse.ymokrane.fxml.GSERadioController;
import de.techfak.gse.ymokrane.model.ConsoleReader;
import de.techfak.gse.ymokrane.model.MusicPlayer;
import de.techfak.gse.ymokrane.model.PathParser;

public final class GSERadio extends Application {


   // public static final int STATUS_0 = 0;
    private static Scene scene;

   /* private GSERadio() {


    }*/


    @Override
    public void start(Stage stage) throws IOException {
       // final int errorcode100 = 100;
        //MusicPlayer player = null;


       // GSERadioController helloWorldController = loadFXML().getController();
        scene = new Scene(loadFXML("/GSERadioView"), 640, 480);
        stage.setScene(scene);
        stage.show();

        /*try {
            //Erzeugen meines parsers //
            final PathParser parser = new PathParser(getParameters().getRaw().get(0));

            //Einlesen der mp3s aus dem Ornder und speichern in einer Playlist(geshuffled) //

            final List<File> playlist = parser.getPlaylist();
            parser.showPlaylist();


            //erstellt Music Player //
            player = new MusicPlayer(playlist);

            //Abspielen der mp3s //

            player.playSongs();
            //User Input Verarbeitung //

            final ConsoleReader consoleReader = new ConsoleReader(player.getPlaylist());
            while (consoleReader.read(player.getPlaylist())) {
            }
        } catch (InvalidPathException exceptionPath) {
            exceptionPath.printStackTrace();
            System.exit(errorcode100);
        } catch (NoMp3FilesException exceptionMp3) {
            exceptionMp3.printStackTrace();
            System.exit(errorcode100);
        } finally {
            if (player != null) {
                player.mediaPlayer.release();
                System.exit(STATUS_0);
            }

        }
*/
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GSERadio.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String... args) {


      launch(args);
    }

}
