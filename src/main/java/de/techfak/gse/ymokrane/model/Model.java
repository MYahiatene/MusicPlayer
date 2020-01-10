package de.techfak.gse.ymokrane.model;

import de.techfak.gse.ymokrane.exceptions.InvalidPathException;
import de.techfak.gse.ymokrane.exceptions.NoMp3FilesException;
import de.techfak.gse.ymokrane.exceptions.WrongPortException;
import de.techfak.gse.ymokrane.model.server.WebServer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Model {


    /*default */ MusicPlayer player;

    /*default */ List<File> playlist;

    /*default*/ PathParser parser;
    private String pfad;

    private WebServer server;

    private boolean running = false;
    private PropertyChangeSupport support;

    /**
     * @param pfad the song path
     * @throws InvalidPathException Own exception thrown when path is invalid.
     * @throws NoMp3FilesException  Own exception thrown when there are no mp3s in directory.
     */
    public Model(final String pfad) throws InvalidPathException, NoMp3FilesException {
        final List<File> mp3List = new ArrayList<>();
        support = new PropertyChangeSupport(this);
        //Erzeugen meines parsers //
        this.pfad = pfad;
        this.parser = new PathParser(pfad);
        this.player = new MusicPlayer(parser.createPlaylist());

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

    public WebServer getServer() {
        return server;
    }


    public MusicPlayer getPlayer() {
        return this.player;
    }

    public void addPropertyChangeListener(final PropertyChangeListener observer) {
        support.addPropertyChangeListener(observer);
    }

    public void removePropertyChangeListener(final PropertyChangeListener observer) {
        support.removePropertyChangeListener(observer);
    }

    public void setPlayer(MusicPlayer player) {
        this.player = player;
    }


    /**
     * @param port plays the playlist with the specified port.
     * @throws WrongPortException throws an Exception when Port is wrong.
     */
    public void serverMode(final String port) throws WrongPortException {

        player.streamSongs(port);

    }

    /**
     * plays playlist in console mode (deprecated).
     */
    /*
    public void consoleModus() {

        parser.showPlaylist();


        //Abspielen der mp3s //

        player.playSongs();

        //User Input Verarbeitung //
        while (consoleReader.read(player.getPlaylist())) {
            System.out.println("\nBefehlseingabe: \n" + "---------------\n");
        }
        consoleReader.getConsoleReader().close();
        player.getMediaPlayerFactory().release();
        player.getMediaPlayer().release();

    }
*/


}
