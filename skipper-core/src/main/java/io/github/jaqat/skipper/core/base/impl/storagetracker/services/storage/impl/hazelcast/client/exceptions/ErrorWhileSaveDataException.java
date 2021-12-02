package io.github.jaqat.skipper.core.base.impl.storagetracker.services.storage.impl.hazelcast.client.exceptions;

/**
 * exception to throw in problem in save data occurred
 */
public class ErrorWhileSaveDataException extends RuntimeException {
    public ErrorWhileSaveDataException(String message) {
        super(message);
    }

    public ErrorWhileSaveDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
