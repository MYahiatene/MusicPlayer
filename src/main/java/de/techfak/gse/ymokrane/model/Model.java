package de.techfak.gse.ymokrane.model;

import de.techfak.gse.ymokrane.exceptions.InvalidPathException;
import de.techfak.gse.ymokrane.exceptions.NoMp3FilesException;
import de.techfak.gse.ymokrane.exceptions.WrongPortException;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Model {
    /*default */ final ConsoleReader consoleReader;

    /*default */ MusicPlayer player;

    /*default */ List<File> playlist;

    /*default*/ PathParser parser;
    private String pfad;


    private PropertyChangeSupport support;


    /**
     * @param newArgs args from main class without first element as list.
     * @throws InvalidPathException Own exception thrown when path is invalid.
     * @throws NoMp3FilesException  Own exception thrown when there are no mp3s in directory.
     */
    public Model(final String... newArgs) throws InvalidPathException, NoMp3FilesException {
        final List<File> mp3List = new ArrayList<>();
        for (final String s : newArgs
        ) {
            if (!s.contains("--streaming=")) {
                this.pfad = s;
                break;
            }
        }
        if (this.pfad != null) {
            final File file = new File(this.pfad);
            if (!file.isDirectory()) {
                throw new InvalidPathException("Ungueltiger Pfad: " + pfad);
            }
            for (final File f : file.listFiles()) {

                if (!f.getAbsoluteFile().isDirectory() && f.getAbsoluteFile().toString().endsWith(".mp3")) {

                    mp3List.add(file);

                }

            }
            if (mp3List.isEmpty()) {

                throw new NoMp3FilesException("Keine validen Mp3s im Pfad: " + pfad);

            }
        } else {
            this.pfad = System.getProperty("user.dir");
        }
        support = new PropertyChangeSupport(this);
        //Erzeugen meines parsers //
        this.parser = new PathParser(List.of(newArgs));

        //Einlesen der mp3s aus dem Ornder und speichern in einer Playlist(geshuffled) //

        this.playlist = parser.getPlaylist();


        //erstellt Music Player //
        this.player = new MusicPlayer(playlist);
        this.consoleReader = new ConsoleReader(player.getPlaylist(), List.of(newArgs));

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
        return this.player;
    }

    public void addPropertyChangeListener(final PropertyChangeListener observer) {
        support.addPropertyChangeListener(observer);
    }

    public void removePropertyChangeListener(final PropertyChangeListener observer) {
        support.removePropertyChangeListener(observer);
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


}
