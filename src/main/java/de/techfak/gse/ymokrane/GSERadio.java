package de.techfak.gse.ymokrane;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class GSERadio {

    private GSERadio() {


    }

    public static void main(final String... args) {
        //Erzeugen meines parsers //
        PathParser parser = new PathParser(args);
        String pfad = parser.getPfad();

        //Einlesen der mp3s aus dem Ornder und speichern in einer Playlist //

        List<File> playlist = parser.getPlaylist();

        //erstellt Music Player //
        MusicPlayer Player = new MusicPlayer(pfad);
        //Player.play();

        System.out.println("Hello ymokrane!");
    }

}
