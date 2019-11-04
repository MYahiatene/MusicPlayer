package de.techfak.gse.ymokrane;

import java.io.File;
import java.util.List;

public final class GSERadio {

    private GSERadio() {


    }

    public static void main(final String... args) {
        //Erzeugen meines parsers //
        final PathParser parser = new PathParser(args);

        //Einlesen der mp3s aus dem Ornder und speichern in einer Playlist(geshuffled) //

        final List<File> playlist = parser.getPlaylist();

        //erstellt Music Player //
        final MusicPlayer player = new MusicPlayer(playlist);

        //Abspielen der mp3s //
        player.playSongs();


        System.out.println("Hello ymokrane!");
    }

}
