package de.techfak.gse.ymokrane;

import java.io.File;
import java.util.List;

public final class GSERadio {


    private GSERadio() {


    }


    /**
     * @param args Kommandozeilenargumente
     */
    public static void main(final String... args) {
        try {
            //Erzeugen meines parsers //
            final PathParser parser = new PathParser(args);

            //Einlesen der mp3s aus dem Ornder und speichern in einer Playlist(geshuffled) //

            final List<File> playlist = parser.getPlaylist();


            //erstellt Music Player //
            final MusicPlayer player = new MusicPlayer(playlist);

            //Abspielen der mp3s //


            final ID3Manager id3managaer = new ID3Manager();
            id3managaer.showMeta(parser.getObjectList(playlist), playlist.get(player.getPlaylistIndex()).toString());
            player.playSongs();
            System.out.println("Hello ymokrane!");
        } catch (InvalidPathException  exceptionPath) {
            exceptionPath.printStackTrace();
            System.exit(100);
        }

        catch ( NoMp3FilesException exceptionMp3) {
            exceptionMp3.printStackTrace();
            System.exit(100);
        }
    }

}
