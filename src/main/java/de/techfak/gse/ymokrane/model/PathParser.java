package de.techfak.gse.ymokrane.model;

import de.techfak.gse.ymokrane.exceptions.InvalidPathException;
import de.techfak.gse.ymokrane.exceptions.NoMp3FilesException;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathParser {
    private final String userdir = "user.dir";
    private int count = 0;
    private String pfad;

    private ID3Manager id3Manager = new ID3Manager();

    private List<File> mp3List = new ArrayList<>();

    /**
     * @param pfad Pfad des einzulesenden Ordners
     */
    public PathParser(final String pfad) {
        if (pfad.isEmpty()) {

            this.pfad = System.getProperty(userdir);
        } else {

            this.pfad = pfad;

        }

    }

    public List<File> getMp3List() {
        return mp3List;
    }

    public String getPfad() {


        return pfad;


    }

    /**
     * @return return der playlist geshuffled
     */
    public List<File> createPlaylist() throws InvalidPathException, NoMp3FilesException {

        final File files = new File(pfad);

        if (!files.isDirectory()) {
            throw new InvalidPathException("Ungueltiger Pfad: " + pfad);
        }


        for (final File file : files.listFiles()) {

            if (!file.getAbsoluteFile().isDirectory() && file.getAbsoluteFile().toString().endsWith(".mp3")) {

                mp3List.add(file);

            }

        }
        if (mp3List.isEmpty()) {

            throw new NoMp3FilesException("Keine validen Mp3s im Pfad: " + pfad);

        }


        Collections.shuffle(mp3List);
        return mp3List;
    }

    /**
     * Print playlist on console.
     */
    public void showPlaylist() {

        final List<Song> songObjektListe = getObjectList(mp3List);

        System.out.println("Playlist: \n"
            + "--------------");

        for (final Song song : songObjektListe) {
            id3Manager.showMeta(song);
        }
    }

    /**
     * @param playlist Liste vom Typ File
     * @return songObjektListe returned Liste mit Objekten vom Typ Song
     */
    public List<Song> getObjectList(final List<File> playlist) {
        final List<Song> songObjectList = new ArrayList<>();
        for (final File file : playlist) {

            final Song song = new Song(file.toString(), count);
            songObjectList.add(song);
            count++;

        }
        return songObjectList;

    }

    /**
     * @param songList List fo Song objects .
     * @return List of File objects.
     */
    public List<File> getPlaylistFromSong(final List<Song> songList) {
        final List<File> fileList = new ArrayList<>();
        for (final Song song : songList
        ) {
            final File file = new File(song.getPath());
            fileList.add(file);

        }
        return fileList;
    }


}
