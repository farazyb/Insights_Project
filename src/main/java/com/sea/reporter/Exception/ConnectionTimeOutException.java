package com.sea.reporter.Exception;

/**
 * Custom exception thrown when a connection timeout occurs during external service calls.
 * This exception is typically used when the application fails to establish or maintain
 * a connection with an external service within the expected time frame.
 */
public class ConnectionTimeOutException extends RuntimeException {
    
    /**
     * Creates a new connection timeout exception with the specified message.
     *
     * @param message The error message describing the timeout
     */
    public ConnectionTimeOutException(String message) {
        super(message);
    }

    /**
     * Creates a new connection timeout exception with the specified message and cause.
     *
     * @param message The error message describing the timeout
     * @param cause The underlying cause of the timeout
     */
    public ConnectionTimeOutException(String message, Throwable cause) {
        super(message, cause);
    }
} 