package de.techfak.gse.ymokrane;

import de.techfak.gse.ymokrane.exceptions.*;
import de.techfak.gse.ymokrane.model.Model;
import de.techfak.gse.ymokrane.model.server.WebServer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
        WebServer server=null;
        if (args.length == 0) {
            System.out.println("No options specified!");
            return;
        }

        try {

            final Model model = new Model(args);
            model.optionHandler(args,server);
        } catch (InvalidPathException | NoMp3FilesException e) {
            e.printStackTrace();
            System.exit(error100);
        } catch (WrongPortException e) {
            server.closeAllConnections();
            server.stop();
            e.printStackTrace();
            System.exit(error102);
        } catch (InvalidOptionException e) {
            e.printStackTrace();
            System.exit(error103);
        } catch (PortOccupiedException e) {
            server.closeAllConnections();
            server.stop();
            e.printStackTrace();
            System.exit(error101);
        }

    }


}
 // TCP SOCKETS NOCH FREIGEBEN UND SERVER RESSOURCEN FREIGEBEN
