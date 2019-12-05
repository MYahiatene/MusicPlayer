package de.techfak.gse.ymokrane.model;

import java.io.File;
import java.util.List;
import java.util.Scanner;


public class ConsoleReader {

    public static final String EXIT = "exit";

    public static final String SONG = "song";

    public static final String PLAYLIST = "playlist";

    /*default*/ List<File> playlist;

    /*default*/ PathParser parser;


    /*default*/ int index;

    /*default*/ String currentPath;

    private ID3Manager id3Manager = new ID3Manager();

    /**
     * @param playlist playlist as parameter from main class.
     * @param args     args from main class.
     */

    public ConsoleReader(final List<File> playlist, final List<String> args) {

        this.playlist = playlist;

        this.parser = new PathParser(args);
    }

    /**
     * @param currentPlaylist current playlist with changed order.
     * @return boolean returns false if "exit" was typed by the user
     */

    public boolean read(final List<File> currentPlaylist) {
        final Scanner consoleReader = new Scanner(System.in).useDelimiter("\n");
        final String input = consoleReader.next();

        final List<Song> objectList = parser.getObjectList(playlist);

        //case anweisung hier

        switch (input) {
            //song mit 2 songs funktioniert noch nicht richtig
            case SONG:
                id3Manager.showMeta(objectList.get(0));
                break;
            //playlist funktioniert noch nicht richtig
            case PLAYLIST: // Playlist umändern, gespieltes Lied ans ende //


                for (final Song song : objectList) {
                    id3Manager.showMeta(song);
                }
                break;
            case EXIT:
                System.exit(0);
                break;


            default:

                System.out.println("Invalid command! Please use the following commands:  \n"
                                   + "\"song\": Display ID3Tags of the current song. \n"
                                   + "\"playlist\": Displays complete playlist. \n"
                                   + "\"exit\": Exits the player. ");
                break;


        }
        return true;
    }


}
