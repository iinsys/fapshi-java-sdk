package com.fapshi.sdk.model;

import lombok.Data;

/**
 * Model for webhook notifications sent by the Fapshi API to your backend.
 */
@Data
public class WebhookNotification {
    /** Type of event (e.g., PAYMENT_COMPLETED). */
    private String eventType;
    /** Transaction ID related to the event. */
    private String transactionId;
    /** Payment amount in the smallest currency unit. */
    private int amount;
    /** Currency code (e.g., XAF). */
    private String currency;
    /** Status of the transaction. */
    private String status;
    /** Optional message from the API. */
    private String message;
    // Add more fields as needed
} 