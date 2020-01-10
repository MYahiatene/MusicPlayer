package de.techfak.gse.ymokrane.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.media.InfoApi;
import uk.co.caprica.vlcj.media.Media;
import uk.co.caprica.vlcj.media.Meta;
import uk.co.caprica.vlcj.media.MetaData;
import uk.co.caprica.vlcj.waiter.media.ParsedWaiter;

public class Song {


    /*default*/ private int id;
    /*default*/ private Integer votes;

    /* default */ private String path;

    /* default */ private String genre;

    /* default */  private String artist;

    /* default */ private String title;

    /* default */ private long duration;

    /* default */ private String album;

    private MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();

    public Song() {
    }

    /**
     * @param id   Id des songs
     * @param pfad Pfad der mp3s
     */
    public Song(final String pfad, int id) {
        this.id = id;
        this.votes = 0;

        this.path = pfad;

        final Media media = mediaPlayerFactory.media().newMedia(pfad);

        final ParsedWaiter parsed = new ParsedWaiter(media) {
            @Override
            protected boolean onBefore(final Media component) {
                return media.parsing().parse();
            }
        };

        try {
            parsed.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final MetaData metaData = media.meta().asMetaData();
        final InfoApi info = media.info();

        this.artist = metaData.get(Meta.ARTIST);
        this.title = metaData.get(Meta.TITLE);
        this.album = metaData.get(Meta.ALBUM);
        this.genre = metaData.get(Meta.GENRE);
        this.duration = info.duration();


    }


    public void setPath(String path) {
        this.path = path;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setAlbum(String album) {
        this.album = album;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonIgnore
    public MediaPlayerFactory getMediaPlayerFactory() {
        return mediaPlayerFactory;
    }


    public void setVotes(final Integer votes) {
        this.votes = votes;
    }

    @JsonIgnore
    public int getVotes() {
        return votes;
    }


    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    @JsonIgnore
    public long getDuration() {
        return duration;
    }

    @JsonIgnore
    public String getAlbum() {
        return album;
    }

    @JsonIgnore
    public String getPath() {
        return path;
    }

    @JsonIgnore
    public String getGenre() {
        return genre;
    }

    /**
     * @param o2 the object you compare to.
     * @return returns an int according to the comparation in conformity with Comparator.
     */
    public int compareTo(final Song o2) {

        if (this.votes < o2.votes) {
            final int lessNo = 100;
            return lessNo;
        }
        if (this.votes > o2.votes) {
            final int greaterNo = -100;
            return greaterNo;
        } else {
            return 0;
        }
    }
}
