package de.techfak.gse.ymokrane.model;

import de.techfak.gse.ymokrane.exceptions.InvalidPathException;
import de.techfak.gse.ymokrane.exceptions.NoMp3FilesException;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.List;

public class Model {
    /*default */ final ConsoleReader consoleReader;

    /*default */ MusicPlayer player;

    /*default */ List<File> playlist;

    /*default*/ PathParser parser;


    private PropertyChangeSupport support;


    /**
     * @param newArgs args from main class without first element as list.
     * @throws InvalidPathException Own exception thrown when path is invalid.
     * @throws NoMp3FilesException  Own exception thrown when there are no mp3s in directory.
     */
    public Model(final List<String> newArgs) throws InvalidPathException, NoMp3FilesException {

        support = new PropertyChangeSupport(this);
        //Erzeugen meines parsers //
        this.parser = new PathParser(newArgs);

        //Einlesen der mp3s aus dem Ornder und speichern in einer Playlist(geshuffled) //

        this.playlist = parser.getPlaylist();


        //erstellt Music Player //
        this.player = new MusicPlayer(playlist);
        this.consoleReader = new ConsoleReader(player.getPlaylist(), newArgs);

    }

    public List<File> getPlaylist() {
        return playlist;
    }

    public PropertyChangeSupport getSupport() {
        return support;
    }

    public PathParser getParser() {
        return parser;
    }

    public ConsoleReader getConsoleReader() {
        return consoleReader;
    }


    public MusicPlayer getPlayer() {
        return player;
    }

    public void addPropertyChangeListener(final PropertyChangeListener observer) {
        support.addPropertyChangeListener(observer);
    }

    public void removePropertyChangeListener(final PropertyChangeListener observer) {
        support.removePropertyChangeListener(observer);
    }

    /**
     * Starts program in console mode only.
     */
    public void consoleModus() {

        parser.showPlaylist();


        //Abspielen der mp3s //

        player.playSongs(playlist);

        //User Input Verarbeitung //
        while (consoleReader.read(player.getPlaylist())) {
            System.out.println("\nBefehlseingabe: \n" + "---------------\n");
        }

    }


}
