package de.techfak.gse.ymokrane.model.server;

import de.techfak.gse.ymokrane.exceptions.JsonException;
import de.techfak.gse.ymokrane.model.Model;
import de.techfak.gse.ymokrane.model.MusicPlayer;
import de.techfak.gse.ymokrane.model.Song;
import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class WebServer extends NanoHTTPD {
    private Song song;
    private List<Song> songList;
    private Model model;
    private String pfad;
    private MusicPlayer player;

    /**
     * @param port Der Port, auf den der Server hört
     * @throws IOException
     * @throws InterruptedException
     */
    public WebServer(final int port, MusicPlayer player) throws IOException {
        super(port);
        //Server starten, mit normalem Timeout und nicht als Daemon (dh. er läuft bis man ihn explizit beendet)
        start(SOCKET_READ_TIMEOUT, false);
        this.pfad = pfad;
        this.player = player;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * wird eine Anfrage an den Server gesendet, dann ist diese Methode dafür zuständig, die Anfrage zu bearbeiten und
     * eine Antwort an den Client zu senden.
     *
     * @param session Die Session, die die Parameter der Anfrage bereithält
     * @return die Antwort, die vom Server an den Client gesendet wird.
     */
    @Override
    public Response serve(final IHTTPSession session) {
        JsonParser parser = new JsonParser();
/*
        final StringBuilder sb = new StringBuilder();
        sb.append("Der Server hat eine HTTP-Anfrage erhalten:\n");

        // Die Methode der Anfrage
        sb.append(String.format("Die Methode der Anfrage war %s \n", session.getMethod()));

        // Die URI der Anfrage
        sb.append(String.format("Die URI der Anfrage war %s \n", session.getUri()));

        // Die Anzahl der Parameter
        sb.append(String.format("Die %s  Parameter der Anfrage waren: \n", session.getParameters().size()));

        final Map<String, List<String>> parameters = session.getParameters();
        // Die einzelnen Parameter ausgeben
        parameters.forEach((s, strings) -> {
            sb.append(String.format("Parameter: %s\n\t%s", s, String.join(", ", strings)));
        });
*/
        // Den String an den Client zurückgeben

        switch (session.getUri()) {

            case "/current-song":
                try {
                    return newFixedLengthResponse(Response.Status.OK, "application/json", parser.toJSON(player.getSongList().get(0)));

                } catch (JsonException e) {
                    e.printStackTrace();
                }
                //}
                //catch (JsonException e){
                //e.printStackTrace();

                //}
                break;

            case "/playlist":
                List<Song> songList = new ArrayList<>();
                for (Song s : this.player.getSongList()) {
                    songList.add(s);
                }
                return newFixedLengthResponse(Response.Status.OK, "application/json", songList.toString());

        }

        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, "GSE Radio\n" + Response.Status.OK.getDescription());

    }

}
