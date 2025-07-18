package com.fapshi.sdk;

import org.springframework.web.client.RestTemplate;

/**
 * Main entry point for interacting with the Fapshi Java SDK.
 * Holds configuration and HTTP client for API requests.
 */
public class FapshiClient {
    private final FapshiConfig config;
    private final RestTemplate restTemplate;

    /**
     * Constructs a FapshiClient with the given configuration.
     * @param config FapshiConfig containing API credentials and base URL
     */
    public FapshiClient(FapshiConfig config) {
        this.config = config;
        this.restTemplate = new RestTemplate();
    }

    /**
     * Returns the SDK configuration.
     */
    public FapshiConfig getConfig() {
        return config;
    }

    /**
     * Returns the RestTemplate used for HTTP requests.
     */
    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
} 