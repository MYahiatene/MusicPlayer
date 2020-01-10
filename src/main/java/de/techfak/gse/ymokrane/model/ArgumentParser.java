package de.techfak.gse.ymokrane.model;

import de.techfak.gse.ymokrane.GSERadioApplication;
import de.techfak.gse.ymokrane.exceptions.InvalidOptionException;
import de.techfak.gse.ymokrane.exceptions.PortOccupiedException;
import de.techfak.gse.ymokrane.model.server.WebServer;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class ArgumentParser {

    private final String gui2 = "--gui";
    private final String g2 = "-g";
    private final String server2 = "--server";
    private final String client2 = "--client";
    private final String port2 = "--port";
    private final String invalidOption = "Invalid Option";
    private String[] args;
    private final String streaming2 = "--streaming";
    private WebServer webServer;
    private Model model;
    private MusicPlayer player;
    private String pfad = "";
    private final int number3 = 3;
    @Argument
    private List<String> arguments = new ArrayList<>();
    @Option(name = client2, usage = "client mode")
    private boolean client;
    @Option(name = gui2, aliases = "-g", usage = "starts gui")
    private boolean gui;
    @Option(name = server2, usage = "starts REST server")
    private boolean server;
    @Option(name = port2, usage = "specifies the port for REST")
    private boolean port;
    @Option(name = streaming2, usage = "streams music with specified port")
    private boolean streaming;
    private CmdLineParser parser;
    private List<File> playlist = new ArrayList<>();

    public ArgumentParser() {


    }

    public MusicPlayer getPlayer() {
        return player;
    }


    public void setModel(Model model) {
        this.model = model;
    }

    public WebServer getWebServer() {
        return webServer;
    }


    /**
     * @param args cmdline arguments
     * @throws CmdLineException Exception thrown when parsing error
     */
    public void parse(String... args) throws CmdLineException {
        CmdLineParser parser = new CmdLineParser(this);
        parser.parseArgument(args);
        this.args = args;


        for (String s : arguments) {
            System.err.println(s);
        }
    }

    /**
     * @return returns pfad
     * @throws InvalidOptionException thrown when invalid opion
     * @throws PortOccupiedException  thrown when port already used
     */
    public String checkOptions() throws InvalidOptionException, PortOccupiedException {


        switch (args[0]) {

            case gui2:
            case g2:
                for (String s : args) {
                    if (s.equals(server2) || s.equals(client2) || s.contains(port2)) {
                        throw new InvalidOptionException(invalidOption);
                    }
                }
                if (args.length > 1) {
                    this.pfad = args[1];
                    GSERadioApplication.main(args);
                }
                GSERadioApplication.main(args);
                break;

            case server2:
                try {
                    PathParser pathParser;
                    String port = "";
                    String streamingPort = "";
                    for (String s : args
                    ) {
                        if (s.equals(gui2) || s.equals(g2) || s.equals(client2)) {
                            throw new InvalidOptionException(invalidOption);
                        }
                    }


                    if (args.length > 1 && args[1].contains(streaming2)) {
                        streamingPort = arguments.get(0);
                        if (args.length > 2
                            && !args.toString().contains(port2)) {
                            this.pfad = args[2];
                        }
                        pathParser = new PathParser(this.pfad);
                        player = new MusicPlayer(pathParser.createPlaylist());
                        player.streamSongs(streamingPort);
                        if (args.length > 2 && args[2].contains(port2)) {
                            port = arguments.get(1);
                            if (args.length > number3) {
                                this.pfad = args[number3];
                            }
                            this.webServer = new WebServer(Integer.parseInt(port), player);

                        }
                        if (args.length > number3 && args[number3] == null) {
                            int i = 1 + 1;
                        } else {
                            if (args.length > number3) {
                                pfad = args[number3];
                            }
                        }
                    } else if (args.length > 1 && args[1].contains(port2)) {
                        port = arguments.get(0);
                        if (args.length > 2 && args[2].contains(streaming2)) {
                            streamingPort = arguments.get(1);
                            pathParser = new PathParser(pfad);
                            player = new MusicPlayer(pathParser.createPlaylist());
                            player.streamSongs(streamingPort);

                        }
                        this.webServer = new WebServer(Integer.parseInt(port), player);
                    }
                    this.webServer = new WebServer(Integer.parseInt("8080"), player);
                } catch (Exception e) {
                    throw new PortOccupiedException("Port belegt");
                }
                break;

            case client2:
                if (args.length > 1) {
                    throw new InvalidOptionException(invalidOption);
                } else {
                    GSERadioApplication.main(args);
                }

                break;

            default:

                throw new InvalidOptionException("No option specified");


        }
        return pfad;
    }
}
