package de.techfak.gse.ymokrane.model;


public class ID3Manager {
    /**
     * @param song Object with type Song.
     */

    public void showMeta(final Song song) {

        if (song.getArtist() != null) {
            System.out.println("Interpret: " + song.getArtist());
        }
        if (song.getTitle() != null) {

            System.out.println("Titel: " + song.getTitle());
        }
        if (song.getAlbum() != null) {

            System.out.println("Album: " + song.getAlbum());
        }
        if (song.getGenre() != null) {

            System.out.println("Genre: " + song.getGenre());
        }
        System.out.println("Länge: " + song.getDuration());
        System.out.println("_________________________________");


    }

}



