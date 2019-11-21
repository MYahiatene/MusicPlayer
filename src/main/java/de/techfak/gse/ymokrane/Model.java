package de.techfak.gse.ymokrane;

import java.io.File;
import java.util.List;

import de.techfak.gse.ymokrane.exceptions.InvalidPathException;
import de.techfak.gse.ymokrane.exceptions.NoMp3FilesException;
import de.techfak.gse.ymokrane.model.ConsoleReader;
import de.techfak.gse.ymokrane.model.MusicPlayer;
import de.techfak.gse.ymokrane.model.PathParser;


public class Model {
    private static final int STATUS_0 = 0;

    private List<String> args;

    public List<String> getArgs() {
        return args;
    }

    public Model(List<String> args) {
        this.args = args;
    }

    public void noGui() {

        final int errorcode100 = 100;
        MusicPlayer player = null;
        String argument;
        if (args.size() == 1) {
            argument = args.get(0);
        } else if (args.size() >= 2) {
            argument = args.get(1);
        } else {
            argument = "";
        }

        try {
            //Erzeugen meines parsers //
            final PathParser parser = new PathParser(argument);
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
        } catch (InvalidPathException | NoMp3FilesException exception) {
            exception.printStackTrace();
            System.exit(errorcode100);

        } finally {
            if (player != null) {
                player.mediaPlayer.release();
                System.exit(STATUS_0);
            }

        }

    }

}

