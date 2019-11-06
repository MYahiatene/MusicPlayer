package de.techfak.gse.ymokrane.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ConsoleReader {

    public static final String EXIT = "exit";

    public static final String SONG = "song";

    public static final String PLAYLIST = "playlist";

    List<File> playlist;

    PathParser parser = new PathParser();


    int index;

    String currentPath;

    private ID3Manager id3Manager = new ID3Manager();

    public ConsoleReader(final List<File> playlist) {
        List<Song> objectList = new ArrayList<>();
        this.playlist = playlist;
        objectList = parser.getObjectList(playlist);

    }


    public boolean read(List<File> currentPlaylist) {
        final Scanner consoleReader = new Scanner(System.in).useDelimiter("\n");
        final String input = consoleReader.next();

        List<Song> objectList = parser.getObjectList(playlist);

        //case anweisung hier

        switch (input) {
            //song mit 2 songs funktioniert noch nicht richtig
            case SONG:
                id3Manager.showMeta(objectList.get(0));
                break;
            //playlist funktioniert noch nicht richtig
            case PLAYLIST: // Playlist umändern, gespieltes Lied ans ende //


                for (Song song : objectList) {
                    id3Manager.showMeta(song);
                }
                break;
            case EXIT:
                return false;

            default:


        }
        return true;
    }


}
