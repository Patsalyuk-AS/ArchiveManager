package com.github.patsalyukas.archivemanager.exceptions;

public class BoxExist extends RuntimeException {

    public BoxExist() {
    }

    public BoxExist(String message) {
        super(message);
    }

    public BoxExist(String message, Throwable cause) {
        super(message, cause);
    }

    public BoxExist(Throwable cause) {
        super(cause);
    }

    public BoxExist(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
