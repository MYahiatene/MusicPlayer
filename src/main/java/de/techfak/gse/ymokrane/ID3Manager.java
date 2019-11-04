package de.techfak.gse.ymokrane;


import java.util.List;

public class ID3Manager {

    /**
     * @param list            Liste mit allen SongObjekten
     * @param currentSongPath Der Path des aktuellen Songs
     */
    public void showMeta(final List<Song> list, final String currentSongPath) {


        for (final Song song : list) {

            if (song.getPath().equals(currentSongPath)) {
                System.out.println(song.getTitle());
                break;
            }

        }

    }


}
