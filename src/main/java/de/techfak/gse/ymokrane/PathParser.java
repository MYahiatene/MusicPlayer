package de.techfak.gse.ymokrane;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathParser {


    private String pfad;

    private List<Song> songObjektListe = new ArrayList<>();

    private List<File> mp3List = new ArrayList<>();

    /**
     * @param pfad Pfad des einzulesenden Ordners
     */
    public PathParser(final String... pfad) {
        if (pfad.length == 0) {

            this.pfad = System.getProperty("user.dir") + "/src/main/java/de/techfak/gse/ymokrane/resources";
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
            throw new InvalidPathException("Ungueltiger Pfad " + pfad);
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
     * @param playlist Liste vom Typ File
     * @return songObjektListe returned Liste mit Objekten vom Typ Song
     */
    public List<Song> getObjectList(final List<File> playlist) {

        for (final File file : playlist) {

            final Song song = new Song(file.toString());
            songObjektListe.add(song);

        }
        return songObjektListe;

    }


}


