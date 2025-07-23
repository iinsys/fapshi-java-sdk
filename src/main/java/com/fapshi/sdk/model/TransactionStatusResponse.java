package com.fapshi.sdk.model;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response model for checking the status of a payment transaction via Fapshi API.
 * Matches the official /payment-status endpoint documentation.
 */
@Data
public class TransactionStatusResponse {
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
