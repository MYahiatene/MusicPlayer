package de.techfak.gse.ymokrane.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ConsoleReader {

    public static final String EXIT = "exit";

    public static final String SONG = "song";

    public static final String PLAYLIST = "playlist";

    List<File> playlist = new ArrayList<>();

    PathParser parser = new PathParser();

    List<Song> objectList;

    int index;

    String currentPath;

    private ID3Manager id3Manager = new ID3Manager();

    public ConsoleReader(final List<File> playlist) {

        this.playlist = playlist;
        this.objectList = parser.getObjectList(playlist);

    }


    public boolean read(int index) {
        final Scanner consoleReader = new Scanner(System.in).useDelimiter("\n");
        final String input = consoleReader.next();
        currentPath = playlist.get(index).toString();
        //case anweisung hier

        switch (input) {

            case SONG:
                id3Manager.showMeta(parser.getObjectList(playlist), currentPath);
                break;

            case PLAYLIST: // Playlist umändern, gespieltes Lied ans ende //
                for (Song song : objectList) {
                    //showmeta funktion
                    break;
                }
            case EXIT:
                return false;

            default:


        }
        return true;
    }


}
