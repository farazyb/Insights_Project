package com.sea.reporter;

import com.sea.reporter.Exception.ConnectionTimeOutException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the application.
 * Provides centralized exception handling across all controllers.
 * 
 * Handles various types of exceptions and converts them to appropriate HTTP responses
 * with proper status codes and error messages.
 */
@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    /**
     * Handles connection timeout exceptions.
     * Returns a 504 Gateway Timeout response with detailed error information.
     *
     * @param connectionTimeOutException The caught connection timeout exception
     * @return ResponseEntity containing error details and HTTP 504 status
     */
    @ExceptionHandler(value = ConnectionTimeOutException.class)
    public ResponseEntity<ErrorResponse> handleConnectionTimeoutException(ConnectionTimeOutException connectionTimeOutException) {
        log.error("Connection timeout occurred: {}", connectionTimeOutException.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.GATEWAY_TIMEOUT.value(),
            "Connection timeout occurred while trying to reach the external service",
            connectionTimeOutException.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.GATEWAY_TIMEOUT);
    }
}

/**
 * Standard error response model for the application.
 * Contains HTTP status code, error message, and detailed error information.
 */
class ErrorResponse {
    private int status;
    private String message;
    private String details;

    /**
     * Creates a new error response with the specified details.
     *
     * @param status HTTP status code
     * @param message User-friendly error message
     * @param details Detailed error information
     */
    public ErrorResponse(int status, String message, String details) {
        this.status = status;
        this.message = message;
        this.details = details;
    }

    /**
     * Gets the HTTP status code.
     *
     * @return HTTP status code
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the HTTP status code.
     *
     * @param status HTTP status code to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets the user-friendly error message.
     *
     * @return Error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the user-friendly error message.
     *
     * @param message Error message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the detailed error information.
     *
     * @return Detailed error information
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets the detailed error information.
     *
     * @param details Detailed error information to set
     */
    public void setDetails(String details) {
        this.details = details;
    }
}
