package de.techfak.gse.ymokrane.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.techfak.gse.ymokrane.exceptions.InvalidPathException;
import de.techfak.gse.ymokrane.exceptions.NoMp3FilesException;

public class PathParser {


    private String pfad;


    private List<File> mp3List = new ArrayList<>();

    /**
     * @param pfad Pfad des einzulesenden Ordners
     */
    public PathParser(final String... pfad) {
        if (pfad.length == 0) {

            this.pfad = System.getProperty("user.dir") + "/src/main/resources";
        } else {

            this.pfad = pfad[0];
        }
    }

    public String getPfad() {


        return pfad;


    }

    /**
     * @return return der playlist geshuffled
     */
    public List<File> getPlaylist() throws InvalidPathException, NoMp3FilesException {

        final File files = new File(pfad);

        if (!files.isDirectory()) {
            throw new InvalidPathException("Ungueltiger Pfad: " + pfad);
        }


        for (final File file : files.listFiles()) {

            if (!file.getAbsoluteFile().isDirectory() && file.getAbsoluteFile().toString().endsWith(".mp3")) {

                mp3List.add(file);

            }

            if (mp3List.isEmpty()) {

                throw new NoMp3FilesException("Keine validen Mp3s im Pfad: " + pfad);

            }


        }


        Collections.shuffle(mp3List);
        return mp3List;
    }

    /**
     * Print playlist on console.
     */
    public void showPlaylist() {

        final List<Song> songObjektListe = getObjectList(mp3List);

        System.out.println("Playlist:");
        for (final Song song : songObjektListe) {
            System.out.println("Interpret: " + song.getArtist());
            System.out.println("Titel: " + song.getTitle());
            System.out.println("Album: " + song.getAlbum());
            System.out.println("Genre: " + song.getGenre());
            System.out.println("Länge: " + song.getDuration());
            System.out.println("______________________________");
        }
    }

    /**
     * @param playlist Liste vom Typ File
     * @return songObjektListe returned Liste mit Objekten vom Typ Song
     */
    public List<Song> getObjectList(final List<File> playlist) {
        final List<Song> songObjectList = new ArrayList<>();
        for (final File file : playlist) {

            final Song song = new Song(file.toString());
            songObjectList.add(song);

        }
        return songObjectList;

    }
}


