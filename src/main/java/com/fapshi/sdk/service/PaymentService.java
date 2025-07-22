package com.fapshi.sdk.service;

import com.fapshi.sdk.FapshiClient;
import com.fapshi.sdk.model.PaymentLinkRequest;
import com.fapshi.sdk.model.PaymentLinkResponse;
import com.fapshi.sdk.exception.FapshiApiException;
import org.springframework.http.*;
import com.fapshi.sdk.model.DirectPaymentRequest;
import com.fapshi.sdk.model.DirectPaymentResponse;
import com.fapshi.sdk.model.TransactionStatusResponse;

/**
 * Service class for handling Fapshi payment operations.
 * Provides methods to generate payment links, initiate direct payments, and check transaction status.
 * All requests are authenticated using the API key and user from FapshiConfig.
 */
public class PaymentService {
    private final FapshiClient client;

    /**
     * Constructs a PaymentService with the given FapshiClient.
     * @param client FapshiClient instance
     */
    public PaymentService(FapshiClient client) {
        this.client = client;
    }

    /**
     * Generates a payment link for the provided request details.
     * @param request PaymentLinkRequest containing payment details
     * @return PaymentLinkResponse with the generated link and status
     * @throws FapshiApiException if the API call fails
     */
    public PaymentLinkResponse generatePaymentLink(PaymentLinkRequest request) {
        String url = client.getConfig().getBaseUrl() + "/v1/payment-link";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", client.getConfig().getApiKey());
        headers.set("X-API-USER", client.getConfig().getApiUser());
        HttpEntity<PaymentLinkRequest> entity = new HttpEntity<>(request, headers);
        try {
            ResponseEntity<PaymentLinkResponse> response = client.getRestTemplate().postForEntity(
                url, entity, PaymentLinkResponse.class);
            return response.getBody();
        } catch (Exception e) {
            throw new FapshiApiException("Failed to generate payment link", e);
        }
    }

    /**
     * Initiates a direct mobile money payment (MTN or Orange).
     * @param request DirectPaymentRequest containing payment details
     * @return DirectPaymentResponse with transaction status
     * @throws FapshiApiException if the API call fails
     */
    public DirectPaymentResponse initiateDirectPayment(DirectPaymentRequest request) {
        String url = client.getConfig().getBaseUrl() + "/direct-pay";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-KEY", client.getConfig().getApiKey());
        headers.set("X-API-USER", client.getConfig().getApiUser());
        HttpEntity<DirectPaymentRequest> entity = new HttpEntity<>(request, headers);
        try {
            ResponseEntity<DirectPaymentResponse> response = client.getRestTemplate().postForEntity(
                url, entity, DirectPaymentResponse.class);
            return response.getBody();
        } catch (Exception e) {
            throw new FapshiApiException("Failed to initiate direct payment", e);
        }
    }

    /**
     * Retrieves the status of a payment transaction by its ID.
     * @param transactionId The transaction ID to check
     * @return TransactionStatusResponse with transaction details
     * @throws FapshiApiException if the API call fails
     */
    public TransactionStatusResponse getTransactionStatus(String transactionId) {
        String url = client.getConfig().getBaseUrl() + "/v1/transaction-status/" + transactionId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", client.getConfig().getApiKey());
        headers.set("X-API-USER", client.getConfig().getApiUser());
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<TransactionStatusResponse> response = client.getRestTemplate().exchange(
                url, HttpMethod.GET, entity, TransactionStatusResponse.class);
            return response.getBody();
        } catch (Exception e) {
            throw new FapshiApiException("Failed to get transaction status", e);
        }
    }
} 
