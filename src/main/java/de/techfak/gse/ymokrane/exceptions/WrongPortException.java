package de.techfak.gse.ymokrane.exceptions;

public class WrongPortException extends Exception {
    private static final long serialVersionUID = 1234532432L;

    public WrongPortException(final String message) {
        super(message);
    }
}
