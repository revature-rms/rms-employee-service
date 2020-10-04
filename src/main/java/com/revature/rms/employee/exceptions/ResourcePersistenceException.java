package com.revature.rms.employee.exceptions;

public class ResourcePersistenceException extends RuntimeException {

    public ResourcePersistenceException() {
        super("Resource could not be persisted!");
    }

    public ResourcePersistenceException(String message) {
        super(message);
    }

    public ResourcePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

}
