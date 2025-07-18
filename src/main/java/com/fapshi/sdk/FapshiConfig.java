package com.fapshi.sdk;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Configuration class for Fapshi SDK credentials and API base URL.
 */
@Data
@AllArgsConstructor
public class FapshiConfig {
    /** API key for authenticating requests. */
    private String apiKey;
    /** API user for authenticating requests. */
    private String apiUser;
    /** Base URL for the Fapshi API. */
    private String baseUrl;
} 