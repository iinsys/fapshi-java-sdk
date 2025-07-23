package com.fapshi.sdk.model;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for initiating a direct mobile money payment via Fapshi API.
 * Matches the official /direct-pay endpoint documentation.
 */
@Data
public class DirectPaymentRequest {
    /** Amount to be paid (minimum 100 XAF). */
    private int amount;
    /** Phone number to request payment from. */
    @JsonProperty("phone")
    private String phone;
    /** Payment medium: "mobile money" or "orange money" (optional). */
    private String medium;
    /** Payer’s name (optional). */
    private String name;
    /** Payer’s email to receive receipt (optional). */
    private String email;
    /** Your system’s user ID (optional). */
    private String userId;
    /** Transaction/order ID for reconciliation (optional). */
    private String externalId;
    /** Reason for payment (optional). */
    private String message;
} 