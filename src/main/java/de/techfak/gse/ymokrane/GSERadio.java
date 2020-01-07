package de.techfak.gse.ymokrane;

import de.techfak.gse.ymokrane.exceptions.InvalidPathException;
import de.techfak.gse.ymokrane.exceptions.NoMp3FilesException;
import de.techfak.gse.ymokrane.exceptions.WrongPortException;
import de.techfak.gse.ymokrane.model.Model;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.List;

public final class GSERadio {
    @Option(name = "-g", aliases = "--gui", usage = "starts the app in gui mode")
    private boolean gui;
    @Option(name = "--client", usage = "starts the app as client")
    private boolean client;
    @Option(name = "--server", usage = "starts the app in server mode")
    private boolean server;


    private GSERadio(String... arguments) {
        final CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(arguments);
        } catch (CmdLineException e) {
            e.printStackTrace();
            System.out.println("Unable to parse command-line options ");
        }
    }

    /**
     * @param args command line arguments.
     */
    public static void main(final String[] args) throws InvalidPathException, NoMp3FilesException {
        GSERadio gseRadio = new GSERadio(args);
        final int error100 = 100;
        final String gui = "--gui";
        final String gui2 = "-g";
        String[] newArgs = args;

        try {
            if (gseRadio.server) {
                if (List.of(args).subList(1, args.length).isEmpty()) {
                    final Model model = new Model(List.of(args).subList(1, args.length));
                    model.serverMode(List.of(newArgs), "");
                } else {
                    final Model model = new Model(List.of(args).subList(1, args.length));
                    String port = List.of(args).subList(1, args.length).get(0).substring(12);
                    model.serverMode(List.of(newArgs), port);
                }
            }

            if (gseRadio.client) {
                GSERadioApplication.main(List.of(args).subList(1, args.length).toArray(new String[List.of(args).subList(1, args.length).size()]));


            }

            if (gseRadio.gui) {
                GSERadioApplication.main(List.of(args).subList(1, args.length).toArray(new String[List.of(args).subList(1, args.length).size()]));
            }


            if (!gseRadio.server && !gseRadio.client && !gseRadio.gui) {
                final Model model = new Model(List.of(args).subList(1, args.length));
                model.consoleModus();
            }
        } catch (InvalidPathException | NoMp3FilesException e) {
            e.printStackTrace();
            System.exit(error100);
        } catch (WrongPortException e) {
            e.printStackTrace();
            System.exit(102);
        }


    }
}
