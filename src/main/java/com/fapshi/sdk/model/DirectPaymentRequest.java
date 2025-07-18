package com.fapshi.sdk.model;

import lombok.Data;

/**
 * Request model for initiating a direct mobile money payment (MTN or Orange) via Fapshi API.
 */
@Data
public class DirectPaymentRequest {
    /** Payment amount in the smallest currency unit (e.g., XAF cents). */
    private int amount;
    /** Currency code (e.g., XAF). */
    private String currency;
    /** Description of the payment. */
    private String description;
    /** Customer's full name. */
    private String customerName;
    /** Customer's email address. */
    private String customerEmail;
    /** Customer's phone number. */
    private String customerPhone;
    /** Mobile money provider ("MTN" or "ORANGE"). */
    private String provider; // 'MTN' or 'ORANGE'
    /** Optional callback URL for payment status updates. */
    private String callbackUrl; // optional
} 