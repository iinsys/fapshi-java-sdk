package com.fapshi.sdk.model;

import lombok.Data;

/**
 * Response model for a direct payment transaction from the Fapshi API.
 */
@Data
public class DirectPaymentResponse {
    /** Status of the direct payment transaction. */
    private String status;
    /** Transaction ID associated with the payment. */
    private String transactionId;
    /** Optional message from the API. */
    private String message;
    // Add more fields as needed
} 