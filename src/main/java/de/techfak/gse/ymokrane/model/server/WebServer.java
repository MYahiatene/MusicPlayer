package de.techfak.gse.ymokrane.model.server;

import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class WebServer extends NanoHTTPD {
    /**
     * @param port Der Port, auf den der Server hört
     * @throws IOException
     * @throws InterruptedException
     */
    public WebServer(final int port) throws IOException, InterruptedException {
        super(port);
        //Server starten, mit normalem Timeout und nicht als Daemon (dh. er läuft bis man ihn explizit beendet)
        start(SOCKET_READ_TIMEOUT, false);
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

        // Den String an den Client zurückgeben
        return newFixedLengthResponse(Response.Status.OK, MIME_PLAINTEXT, sb.toString());
    }
}
