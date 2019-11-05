package de.techfak.gse.ymokrane;

import java.io.File;
import java.util.List;

import de.techfak.gse.ymokrane.exceptions.InvalidPathException;
import de.techfak.gse.ymokrane.exceptions.NoMp3FilesException;
import de.techfak.gse.ymokrane.model.ConsoleReader;
import de.techfak.gse.ymokrane.model.MusicPlayer;
import de.techfak.gse.ymokrane.model.PathParser;

public final class GSERadio {


    public static final int STATUS_0 = 0;

    private GSERadio() {


    }


    /**
     * @param args Kommandozeilenargumente
     */
    public static void main(final String... args) {
        int exitStatus = 0;
        final int errorcode100 = 100;
        MusicPlayer player = null;
        try {
            //Erzeugen meines parsers //
            final PathParser parser = new PathParser(args);

            //Einlesen der mp3s aus dem Ornder und speichern in einer Playlist(geshuffled) //

            final List<File> playlist = parser.getPlaylist();
            parser.showPlaylist();


            //erstellt Music Player //
            player = new MusicPlayer(playlist);

            //Abspielen der mp3s //

            player.playSongs();
            //User Input Verarbeitung //

            ConsoleReader consoleReader = new ConsoleReader(playlist);
            while (consoleReader.read(player.getPlaylistIndex())) {
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

    }

}
