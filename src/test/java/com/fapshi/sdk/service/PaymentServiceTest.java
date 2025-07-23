package com.fapshi.sdk.service;

import com.fapshi.sdk.FapshiClient;
import com.fapshi.sdk.FapshiConfig;
import com.fapshi.sdk.model.*;
import com.fapshi.sdk.model.WebhookNotification;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentServiceTest {
    private FapshiClient client;
    private RestTemplate restTemplate;
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        FapshiConfig config = new FapshiConfig("apiKey", "apiUser", "https://api.fapshi.com");
        restTemplate = mock(RestTemplate.class);
        client = mock(FapshiClient.class);
        when(client.getConfig()).thenReturn(config);
        when(client.getRestTemplate()).thenReturn(restTemplate);
        paymentService = new PaymentService(client);
    }

    @Test
    void testGeneratePaymentLink_success() {
        PaymentLinkRequest request = new PaymentLinkRequest();
        PaymentLinkResponse response = new PaymentLinkResponse();
        response.setLink("https://pay.fapshi.com/abc");
        response.setStatus("PENDING");
        response.setTransactionId("txn123");
        response.setMessage("OK");
        when(restTemplate.postForEntity(anyString(), any(), eq(PaymentLinkResponse.class)))
                .thenReturn(ResponseEntity.ok(response));
        PaymentLinkResponse result = paymentService.generatePaymentLink(request);
        assertEquals("https://pay.fapshi.com/abc", result.getLink());
        assertEquals("PENDING", result.getStatus());
        assertEquals("txn123", result.getTransactionId());
    }

    @Test
    void testGeneratePaymentLink_apiError() {
        PaymentLinkRequest request = new PaymentLinkRequest();
        when(restTemplate.postForEntity(anyString(), any(), eq(PaymentLinkResponse.class)))
                .thenThrow(new RuntimeException("API error"));
        assertThrows(com.fapshi.sdk.exception.FapshiApiException.class, () -> paymentService.generatePaymentLink(request));
    }

    @Test
    void testInitiateDirectPayment_success() {
        DirectPaymentRequest request = new DirectPaymentRequest();
        request.setAmount(1000); // required
        request.setPhone("237670000000"); // required
        DirectPaymentResponse response = new DirectPaymentResponse();
        response.setStatus("SUCCESS");
        response.setTransactionId("txn456");
        response.setMessage("Payment completed");
        when(restTemplate.postForEntity(anyString(), any(), eq(DirectPaymentResponse.class)))
                .thenReturn(ResponseEntity.ok(response));
        DirectPaymentResponse result = paymentService.initiateDirectPayment(request);
        assertEquals("SUCCESS", result.getStatus());
        assertEquals("txn456", result.getTransactionId());
    }

    @Test
    void testGetTransactionStatus_success() {
        String txnId = "txn789";
        TransactionStatusResponse response = new TransactionStatusResponse();
        response.setStatus("COMPLETED");
        response.setTransId(txnId);
        response.setAmount(1000);
        response.setMedium("mobile money");
        response.setPayerName("John Doe");
        response.setEmail("john@example.com");
        when(restTemplate.exchange(anyString(), any(), any(), eq(TransactionStatusResponse.class)))
                .thenReturn(ResponseEntity.ok(response));
        TransactionStatusResponse result = paymentService.getTransactionStatus(txnId);
        assertEquals("COMPLETED", result.getStatus());
        assertEquals(txnId, result.getTransId());
        assertEquals(1000, result.getAmount());
        assertEquals("mobile money", result.getMedium());
        assertEquals("John Doe", result.getPayerName());
        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    void testGetTransactionStatus_notFound() {
        String txnId = "notfound";
        when(restTemplate.exchange(anyString(), any(), any(), eq(TransactionStatusResponse.class)))
            .thenThrow(new RuntimeException("404 Not Found"));
        assertThrows(com.fapshi.sdk.exception.FapshiApiException.class, () -> paymentService.getTransactionStatus(txnId));
    }

    @Test
    void testWebhookNotificationDeserialization() throws Exception {
        String json = "{ \"transId\": \"txn123\", \"status\": \"COMPLETED\", \"medium\": \"mobile money\", \"amount\": 1000, \"payerName\": \"John Doe\", \"email\": \"john@example.com\" }";
        ObjectMapper mapper = new ObjectMapper();
        WebhookNotification notification = mapper.readValue(json, WebhookNotification.class);
        assertEquals("txn123", notification.getTransId());
        assertEquals("COMPLETED", notification.getStatus());
        assertEquals("mobile money", notification.getMedium());
        assertEquals(1000, notification.getAmount());
        assertEquals("John Doe", notification.getPayerName());
        assertEquals("john@example.com", notification.getEmail());
    }
} 