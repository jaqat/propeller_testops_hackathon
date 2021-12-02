package io.github.jaqat.skipper.core.base.impl.storagetracker.services.storage.impl.hazelcast.client.exceptions;

/**
 * exception to throw in problem in delete data occurred
 */
public class ErrorWhileDeleteDataException extends RuntimeException {
    public ErrorWhileDeleteDataException(String message) {
        super(message);
    }

    public ErrorWhileDeleteDataException(String message, Throwable cause) {
        super(message, cause);
    }

}