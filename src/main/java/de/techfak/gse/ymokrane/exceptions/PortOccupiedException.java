package de.techfak.gse.ymokrane.exceptions;

public class PortOccupiedException extends Exception {
    private final static long serialVersionUID = 2134253121555L;

    public PortOccupiedException(String message) {
        super(message);
    }
}
