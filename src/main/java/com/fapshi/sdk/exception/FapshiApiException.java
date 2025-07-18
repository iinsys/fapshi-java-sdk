package com.fapshi.sdk.exception;

/**
 * Custom exception for errors returned by the Fapshi API or network issues.
 */
public class FapshiApiException extends RuntimeException {
    /**
     * Constructs a new FapshiApiException with the specified detail message.
     * @param message the detail message
     */
    public FapshiApiException(String message) {
        super(message);
    }
    /**
     * Constructs a new FapshiApiException with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public FapshiApiException(String message, Throwable cause) {
        super(message, cause);
    }
} 