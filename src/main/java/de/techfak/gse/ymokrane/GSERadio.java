package de.techfak.gse.ymokrane;

import de.techfak.gse.ymokrane.exceptions.*;
import de.techfak.gse.ymokrane.model.ArgumentParser;
import de.techfak.gse.ymokrane.model.Model;
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

            final Model model = new Model(pfad);


        } catch (InvalidPathException | NoMp3FilesException | CmdLineException e) {
            e.printStackTrace();
            System.exit(error100);
        } catch (InvalidOptionException e) {
            e.printStackTrace();
            System.exit(error103);
        } catch (WrongPortException e) {
            server.closeAllConnections();
            server.stop();
            e.printStackTrace();
            System.exit(error102);
        } catch (PortOccupiedException e) {
            server.closeAllConnections();
            server.stop();
            e.printStackTrace();
            System.exit(error101);
        }
    }


}
