package com.fapshi.sdk.model;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model for webhook notifications sent by the Fapshi API to your backend.
 * Matches the official webhook payload structure.
 */
@Data
public class WebhookNotification {
    @JsonProperty("transId")
    private String transId;
    private String status;
    private String medium;
    private String serviceName;
    private int amount;
    private int revenue;
    private String payerName;
    private String email;
    private String redirectUrl;
    private String externalId;
    private String userId;
    private String webhook;
    private String financialTransId;
    private String dateInitiated;
    private String dateConfirmed;
} 