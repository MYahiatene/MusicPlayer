package de.techfak.gse.ymokrane.model;


public class ID3Manager {

    public void showMeta(Song song) {


        System.out.println(song.getArtist());
        System.out.println(song.getTitle());
        System.out.println(song.getAlbum());
        System.out.println(song.getGenre());
        System.out.println(song.getDuration());
        System.out.println("_________________________________");


    }

}


