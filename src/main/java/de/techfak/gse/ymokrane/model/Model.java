package de.techfak.gse.ymokrane.model;

import de.techfak.gse.ymokrane.GSERadioApplication;
import de.techfak.gse.ymokrane.exceptions.*;
import de.techfak.gse.ymokrane.model.server.WebServer;
import fi.iki.elonen.NanoHTTPD;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Model {
    /*default */ final ConsoleReader consoleReader;


    /*default */ MusicPlayer player;

    /*default */ List<File> playlist;

    /*default*/ PathParser parser;
    private String pfad;

    private WebServer server;


    private PropertyChangeSupport support;


    /**
     * @param newArgs args from main class without first element as list.
     * @throws InvalidPathException Own exception thrown when path is invalid.
     * @throws NoMp3FilesException  Own exception thrown when there are no mp3s in directory.
     */
    public Model(final String... newArgs) throws InvalidPathException, NoMp3FilesException, InvalidOptionException {
        final List<File> mp3List = new ArrayList<>();
        final String[] newargs2 = Arrays.copyOfRange(newArgs, 1, newArgs.length);
        if ((List.of(newArgs).contains("--server") && List.of(newArgs).contains("--client")) ||
            (List.of(newArgs).contains("--server") && List.of(newArgs).contains("-g")) ||
            (List.of(newArgs).contains("--server") && List.of(newArgs).contains("--gui")) ||
            (List.of(newArgs).contains("--client") && List.of(newArgs).contains("--streaming")) ||
            (List.of(newArgs).contains("--client") && List.of(newArgs).contains("--port"))) {
            throw new InvalidOptionException("Invalid options specified");
        }
        for (final String s : newargs2
        ) {
            if (!s.contains("--streaming=") && !s.contains("--port")) {
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
        this.parser = new PathParser(List.of(newargs2));

        //Einlesen der mp3s aus dem Ornder und speichern in einer Playlist(geshuffled) //

        this.playlist = parser.getPlaylist();


        //erstellt Music Player //
        this.player = new MusicPlayer(playlist);
        this.consoleReader = new ConsoleReader(player.getPlaylist(), List.of(newargs2));

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

    public void optionHandler(String[] args,WebServer server) throws WrongPortException, PortOccupiedException {
        final int streamingCutter = 12;
        final String optionerror = "No valid option specified";
        int index = -1;
        String port = "";
        final String[] newargs = Arrays.copyOfRange(args, 1, args.length);
        if (args.length == 0) {
            System.out.println(optionerror);
            return;
        }


        for (int i = 0; i < args.length; i++) {
            if (args[i].indexOf("--streaming") >= 0) {
                index = i;
                break;
            }
        }
        if (index > 0) {
            port = args[index].substring(streamingCutter);
            getPlayer().streamSongs(port);
        }
        index = -1;


        switch (args[0]) {

            case "--server":

                for (int j = 0; j < args.length; j++) {
                    if (index >= 0) {
                        break;
                    }
                    if (args[j].contains("--port=")) {
                        for (int i = 0; i < args.length; i++) {
                            if (args[i].indexOf("--port") >= 0) {
                                index = i;
                                break;
                            }
                        }
                    }

                }

                try {
                    if (index >= 0) {
                        port = args[index];
                        String serverPort = port.substring(7);
                         server = new WebServer(Integer.parseInt(serverPort));
                        this.server=server;
                    } else {
                         server = new WebServer(8080);
                        this.server=server;
                    }
                } catch (Exception e) {
                    throw new PortOccupiedException("Port belegt");
                }

                break;

            case "--gui":
                // if (args[0].equals("--server")) { }
                GSERadioApplication.main(newargs);
                break;
            case "-g":
                // if (args[0].equals("--server")) { }
                GSERadioApplication.main(newargs);
                break;
            default:
                System.out.println(optionerror);
                return;
        }
    }


}
