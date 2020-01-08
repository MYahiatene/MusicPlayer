package de.techfak.gse.ymokrane;

import de.techfak.gse.ymokrane.exceptions.InvalidPathException;
import de.techfak.gse.ymokrane.exceptions.NoMp3FilesException;
import de.techfak.gse.ymokrane.exceptions.WrongPortException;
import de.techfak.gse.ymokrane.model.Model;

import java.util.Arrays;
import java.util.List;

public final class GSERadio {

    private GSERadio() {
    }

    /**
     * @param args command line arguments.
     */
    public static void main(final String[] args) throws InvalidPathException, NoMp3FilesException {
        final int error102 = 102;
        final int streamingCutter = 12;
        final String optionerror = "No valid option specified";
        int index = -1;
        String port = "";
        final int error100 = 100;
        final String[] newargs = Arrays.copyOfRange(args, 1, args.length);

        try {
            if (args.length == 0) {
                System.out.println(optionerror);
                return;
            }
            final Model model = new Model(newargs);
            switch (args[0]) {

                case "--server":
                    if (List.of(args).toString().contains("--streaming=")) {
                        for (int i = 0; i < args.length; i++) {
                            if (args[i].indexOf("--streaming") >= 0) {
                                index = i;
                            }
                        }
                        if (index < 0) {
                            port = "";
                        }
                        port = args[index].substring(streamingCutter);
                    } else {
                        port = "8080";
                    }
                    model.getPlayer().streamSongs(port);
                    break;

                case "--gui":
                    // if (args[0].equals("--server")) { }
                    GSERadioApplication.main(newargs);
                    break;
                case "-g":
                    // if (args[0].equals("--server")) { }
                    GSERadioApplication.main(newargs);
                    break;
                default:
                    System.out.println(optionerror);
                    return;
            }
        } catch (InvalidPathException | NoMp3FilesException e) {
            e.printStackTrace();
            System.exit(error100);
        } catch (WrongPortException e) {
            e.printStackTrace();
            System.exit(error102);
        }


    }
}
