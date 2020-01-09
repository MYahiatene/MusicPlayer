package de.techfak.gse.ymokrane.exceptions;

public class InvalidOptionException extends Exception {
    private static final long serialVersionUID = 123153323423111L;

    public InvalidOptionException(final String message) {
        super(message);
    }
}
