package com.fapshi.sdk.model;

import lombok.Data;

/**
 * Response model for a generated payment link from the Fapshi API.
 */
@Data
public class PaymentLinkResponse {
    /** The generated payment link URL. */
    private String link;
    /** Status of the payment link generation. */
    private String status;
    /** Transaction ID associated with the payment. */
    private String transactionId;
    /** Optional message from the API. */
    private String message;
} 