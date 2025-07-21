package com.fapshi.sdk.model;

import lombok.Data;

/**
 * Response model for checking the status of a payment transaction via Fapshi API.
 */
@Data
public class TransactionStatusResponse {
    /** Status of the transaction (e.g., COMPLETED, PENDING). */
    private String status;
    /** Transaction ID being checked. */
    private String transactionId;
    /** Payment amount in the smallest currency unit. */
    private int amount;
    /** Currency code (e.g., XAF). */
    private String currency;
    /** Optional message from the API. */
    private String message;
} 
