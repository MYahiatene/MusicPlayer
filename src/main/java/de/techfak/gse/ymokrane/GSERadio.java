package de.techfak.gse.ymokrane;

import java.io.File;
import java.util.List;

public final class GSERadio {

    private GSERadio() {


    }

    public static void main(final String... args) {
        //Erzeugen meines parsers //
        final PathParser parser = new PathParser(args);
        final String pfad = parser.getPfad();

        //Einlesen der mp3s aus dem Ornder und speichern in einer Playlist //

        final List<File> playlist = parser.getPlaylist();

        //erstellt Music Player //
        final MusicPlayer player = new MusicPlayer();
        //player.play();

        System.out.println("Hello ymokrane!");
    }

}
