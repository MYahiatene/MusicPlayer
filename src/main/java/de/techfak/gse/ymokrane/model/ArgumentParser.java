package de.techfak.gse.ymokrane.model;

import de.techfak.gse.ymokrane.GSERadioApplication;
import de.techfak.gse.ymokrane.exceptions.*;
import de.techfak.gse.ymokrane.model.server.WebServer;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArgumentParser {
    public CmdLineParser parser;
    @Argument
    public List<String> arguments = new ArrayList<>();
    List<File> playlist = new ArrayList<>();
    @Option(name = "--client", usage = "client mode")
    private boolean client;
    @Option(name = "--gui", aliases = "-g", usage = "starts gui")
    private boolean gui;
    @Option(name = "--server", usage = "starts REST server")
    private boolean server;
    @Option(name = "--port", usage = "specifies the port for REST")
    private boolean port;
    @Option(name = "--streaming", usage = "streams music with specified port")
    private boolean streaming;
    private String[] args;


    public ArgumentParser() {


    }

    public void parse(String... args) throws CmdLineException {
        CmdLineParser parser = new CmdLineParser(this);
        parser.parseArgument(args);
        this.args = args;


        for (String s : arguments) {
            System.err.println(s);
        }
    }

    public String checkOptions() throws InvalidOptionException, IOException, InterruptedException, InvalidPathException, NoMp3FilesException, WrongPortException, PortOccupiedException {

        String pfad = "";

        switch (args[0]) {

            case "--gui":
            case "-g":
                for (String s : args) {
                    if (s.equals("--server") || s.equals("--client") || s.contains("--port")) {
                        throw new InvalidOptionException("Invalid option");
                    }
                }
                if (args.length > 1) {
                    pfad = args[1];
                    GSERadioApplication.main(args);
                }
                GSERadioApplication.main(args);
                break;

            case "--server":
                try {
                    PathParser pathParser;
                    String port = "";
                    String streamingPort = "";
                    for (String s : args
                    ) {
                        if (s.equals("--gui") || s.equals("-g") || s.equals("--client")) {
                            throw new InvalidOptionException("Invalid Option");
                        }
                    }


                    if (args.length > 1 && args[1].contains("--streaming")) {
                        streamingPort = arguments.get(0);
                        pathParser = new PathParser(pfad);
                        MusicPlayer player = new MusicPlayer(pathParser.createPlaylist());
                        player.streamSongs(streamingPort);
                        if (args.length > 2 && args[2].contains("--port")) {
                            port = arguments.get(1);
                            WebServer server = new WebServer(Integer.parseInt(port));
                        }
                        if (args.length > 3 && args[3] == null) {

                        } else {
                            if (args.length > 3) {
                                pfad = args[3];
                            }
                        }
                    } else if (args.length > 1 && args[1].contains("--port")) {
                        port = arguments.get(0);
                        if (args.length > 2 && args[2].contains("--streaming")) {
                            streamingPort = arguments.get(1);
                            pathParser = new PathParser(pfad);
                            MusicPlayer player = new MusicPlayer(pathParser.createPlaylist());
                            player.streamSongs(streamingPort);
                        }
                        WebServer server = new WebServer(Integer.parseInt(port));
                    }
                    WebServer server = new WebServer(Integer.parseInt("8080"));
                } catch (Exception e) {
                    throw new PortOccupiedException("Port belegt");
                }
                break;

            case "--client":
                if (args.length > 1) {
                    throw new InvalidOptionException("Invalid Option");
                } else {
                    //GSERadioApplication.main(args);
                }
                break;

            default:
                throw new InvalidOptionException("No option specified");


        }
        return pfad;
    }
}
