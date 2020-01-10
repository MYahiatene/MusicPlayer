package de.techfak.gse.ymokrane.model;

import de.techfak.gse.ymokrane.exceptions.InvalidPathException;
import de.techfak.gse.ymokrane.exceptions.NoMp3FilesException;
import de.techfak.gse.ymokrane.exceptions.WrongPortException;
import javafx.application.Platform;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;


public class MusicPlayer {

    static final String NEWSONGSTRING = "newSong";
    private static final String NEWPLAYLIST = "newPlaylist";
    private static final String SERVER = ":sout=#rtp{dst=127.0.0.1,port=5235,mux=ts}";
    /*default*/ Song newSong;
    private Song currentSong;


    private MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory("--aout=alsa");

    private MediaPlayer mediaPlayer = mediaPlayerFactory.mediaPlayers().newMediaPlayer();

    private int playlistIndex = 0;

    private List<Song> songList;
    private List<File> playlist;
    private List<Song> tmplist;

    private PropertyChangeSupport support;

    private PathParser parser;

    /**
     * @param playlist Erhält die geshuffelte playlist
     */
    public MusicPlayer(final List<File> playlist) throws InvalidPathException, NoMp3FilesException {
        support = new PropertyChangeSupport(this);
        this.playlist = playlist;

        this.parser = new PathParser("convertFiletoPlaylist");
        this.songList = parser.getObjectList(playlist);
        this.tmplist = songList;
    }

    public Song getCurrentSong() {
        return songList.get(0);
    }

    public List<Song> getTmplist() {
        return tmplist;
    }


    /*default*/ List<String> convertFiletoPlaylist() {
        final List<String> stringPlaylist = new ArrayList<>();
        for (final File file : playlist) {
            stringPlaylist.add(file.toString());
        }
        return stringPlaylist;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public int getPlaylistIndex() {
        return playlistIndex;
    }

    public PropertyChangeSupport getSupport() {
        return support;
    }

    public MediaPlayerFactory getMediaPlayerFactory() {
        return mediaPlayerFactory;
    }

    public void addPropertyChangeListener(final PropertyChangeListener observer) {
        support.addPropertyChangeListener(observer);
    }

    public void removePropertyChangeListener(final PropertyChangeListener observer) {
        support.removePropertyChangeListener(observer);
    }

    public List<Song> getSongList() {
        return songList;
    }

    public List<File> getPlaylist() {
        return this.playlist;

    }

    public Song getNewSong() {
        return newSong;
    }

    public void setSongList(final List<Song> songList) {
        this.songList = songList;
    }

    /**
     * Spielt die songs in repeat ab.
     *
     * @param port streams the song with the specified port.
     */
    public void streamSongs(final String port) throws WrongPortException {
        final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        final int lowerBoundPort = 1024;
        final int upperBoundPort = 49151;
        final boolean patternmatched = pattern.matcher(port).matches();
        final String server = ":sout=#rtp{dst=127.0.0.1,port=" + port + ",mux=ts}";
        if (!patternmatched || Integer.parseInt(port) < lowerBoundPort || Integer.parseInt(port) > upperBoundPort) {
            throw new WrongPortException("Invalid port: " + port);
        }
        mediaPlayer.submit(new Runnable() {
            @Override
            public void run() {

                mediaPlayer.media().play(songList.get(0).getPath(), server);


            }
        });

        mediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            /**
             * @param mediaPlayer
             */
            @Override
            public void finished(final MediaPlayer mediaPlayer) {

                songList.add(songList.get(0));
                songList.remove(0);

                mediaPlayer.submit(new Runnable() {
                    @Override
                    public void run() {

                        mediaPlayer.media().play(songList.get(0).getPath(), server);
                        sort();
                        songList.get(0).setVotes(0);

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {

                                support.firePropertyChange(NEWSONGSTRING, true, false);
                                support.firePropertyChange(NEWPLAYLIST, true, false);


                            }
                        });

                    }
                });
            }

        });


    }

    /**
     * plays songs as client.
     */

    public void playSongs() {


        mediaPlayer.submit(new Runnable() {
            @Override
            public void run() {

                mediaPlayer.media().play(songList.get(0).getPath());
            }
        });

        mediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            /**
             * @param mediaPlayer
             */
            @Override
            public void finished(final MediaPlayer mediaPlayer) {

                songList.add(songList.get(0));
                songList.remove(0);

                mediaPlayer.submit(new Runnable() {
                    @Override
                    public void run() {

                        mediaPlayer.media().play(songList.get(0).getPath());

                        sort();
                        songList.get(0).setVotes(0);

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {

                                support.firePropertyChange(NEWSONGSTRING, true, false);
                                support.firePropertyChange(NEWPLAYLIST, true, false);


                            }
                        });

                    }
                });
            }

        });


    }

    public void setPlaylist(final List<File> playlist) {
        this.playlist = playlist;
    }


    /**
     * @param dropDown selected Item from my dropdown menu in my GUI.
     */
    public void countVote(final String dropDown) {
        final String minus = " - ";
        for (final Song song : songList) {
            final String tmp = song.getArtist() + minus + song.getTitle();
            if (tmp.equals(dropDown)) {
                song.setVotes(song.getVotes() + 1);
            }
        }

        support.firePropertyChange(NEWPLAYLIST, true, false);
        sort();

    }

    /**
     * sorts my songlist excluding the first Song.
     */
    public void sort() {
        Collections.sort(songList.subList(1, songList.size()), new Comparator<Song>() {
            @Override
            public int compare(final Song song, final Song t1) {
                return song.compareTo(t1);
            }
        });

    }

    /*default*/
    public void resetVote() {
        songList.get(0).setVotes(0);

    }

}
