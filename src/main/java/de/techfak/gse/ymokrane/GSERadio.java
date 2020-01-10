package de.techfak.gse.ymokrane;

import de.techfak.gse.ymokrane.exceptions.InvalidOptionException;
import de.techfak.gse.ymokrane.exceptions.PortOccupiedException;
import de.techfak.gse.ymokrane.model.ArgumentParser;
import de.techfak.gse.ymokrane.model.server.WebServer;
import org.kohsuke.args4j.CmdLineException;

import java.io.IOException;


public final class GSERadio {


    private GSERadio() {

    }

    /**
     * @param args command line arguments.
     */
    public static void main(final String[] args) throws IOException, InterruptedException {


        final int error100 = 100;
        final int error101 = 101;
        final int error102 = 102;
        final int error103 = 103;
        WebServer server = null;


        try {
            ArgumentParser parser = new ArgumentParser();
            parser.parse(args);
            String pfad = parser.checkOptions();

            server = parser.getWebServer();
        } catch (CmdLineException e) {
            e.printStackTrace();

        } catch (InvalidOptionException e) {
            if (server != null) {
                server.closeAllConnections();
                server.stop();
            }

            e.printStackTrace();
            System.exit(error103);
        } catch (PortOccupiedException e) {
            if (server != null) {
                server.closeAllConnections();
                server.stop();
            }
            e.printStackTrace();
            System.exit(error101);
        }
    }


}
