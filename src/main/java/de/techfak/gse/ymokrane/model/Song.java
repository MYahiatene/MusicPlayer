package de.techfak.gse.ymokrane.model;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.media.InfoApi;
import uk.co.caprica.vlcj.media.Media;
import uk.co.caprica.vlcj.media.Meta;
import uk.co.caprica.vlcj.media.MetaData;
import uk.co.caprica.vlcj.waiter.media.ParsedWaiter;

public class Song {
    /*default*/ private Integer votes;

    /* default */ private final String path;

    /* default */ private final String genre;

    /* default */  private final String artist;

    /* default */ private final String title;

    /* default */ private final long duration;

    /* default */ private final String album;


    private MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();

    /**
     * @param pfad Pfad der mp3s
     */
    public Song(final String pfad) {
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

    public int getVotes() {
        return votes;
    }


    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public long getDuration() {
        return duration;
    }

    public String getAlbum() {
        return album;
    }

    public String getPath() {
        return path;
    }

    public String getGenre() {
        return genre;
    }


}
