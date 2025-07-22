# Fapshi Java SDK

Java SDK for integrating with the [Fapshi Payment API](https://docs.fapshi.com/en/api-reference/getting-started).

## Workflows

![Manual Build and Test](https://github.com/iinsys/fapshi-java-sdk/actions/workflows/maven.yml/badge.svg)
![CodeQL](https://github.com/iinsys/fapshi-java-sdk/actions/workflows/codeql-analysis.yml/badge.svg)


## 🔄 Dependency Management

- 🛠️ **Dependabot**: Automatically checks for dependency updates via [`.github/dependabot.yml`](.github/dependabot.yml)
- 🤖 **Renovate**: Alternative to Dependabot for custom and bulk PRs (configurable via `renovate.json`)

## Features
- Generate payment links
- Direct mobile money payments (MTN, Orange)
- Check transaction status
- Handle webhook notifications

## Requirements
- Java 21+
- Maven

## Installation

Add the following to your Maven `pom.xml` to use the latest release from JitPack:

```xml
<repository>
  <id>jitpack.io</id>
  <url>https://jitpack.io</url>
</repository>
<dependency>
  <groupId>com.github.iinsys</groupId>
  <artifactId>fapshi-java-sdk</artifactId>
  <version>v1.0.0</version>
</dependency>
```

## Configuration

```java
import com.fapshi.sdk.FapshiConfig;
import com.fapshi.sdk.FapshiClient;

FapshiConfig config = new FapshiConfig(
    "YOUR_API_KEY",
    "YOUR_API_USER",
    "https://api.fapshi.com"
);
FapshiClient client = new FapshiClient(config);
```

## Generate Payment Link Example

```java
import com.fapshi.sdk.model.PaymentLinkRequest;
import com.fapshi.sdk.model.PaymentLinkResponse;
import com.fapshi.sdk.service.PaymentService;

PaymentService paymentService = new PaymentService(client);

PaymentLinkRequest request = new PaymentLinkRequest();
request.setAmount(1000);
request.setCurrency("XAF");
request.setDescription("Order #1234");
request.setCustomerName("John Doe");
request.setCustomerEmail("john@example.com");
request.setCustomerPhone("2376XXXXXXX");
request.setCallbackUrl("https://yourdomain.com/payment-callback");

PaymentLinkResponse response = paymentService.generatePaymentLink(request);
System.out.println("Payment Link: " + response.getLink());
```

## Direct Payment Example (MTN/Orange)

```java
import com.fapshi.sdk.model.DirectPaymentRequest;
import com.fapshi.sdk.model.DirectPaymentResponse;

DirectPaymentRequest directRequest = new DirectPaymentRequest();
directRequest.setAmount(2000);
directRequest.setCurrency("XAF");
directRequest.setDescription("Order #5678");
directRequest.setCustomerName("Jane Smith");
directRequest.setCustomerEmail("jane@example.com");
directRequest.setCustomerPhone("2376YYYYYYY");
directRequest.setProvider("MTN"); // or "ORANGE"
directRequest.setCallbackUrl("https://yourdomain.com/payment-callback");

DirectPaymentResponse directResponse = paymentService.initiateDirectPayment(directRequest);
System.out.println("Direct Payment Status: " + directResponse.getStatus());
```

## Check Transaction Status Example

```java
import com.fapshi.sdk.model.TransactionStatusResponse;

String transactionId = "txn_abc123";
TransactionStatusResponse status = paymentService.getTransactionStatus(transactionId);
System.out.println("Transaction Status: " + status.getStatus());
```

## Webhook Handling Example

Expose an endpoint in your Spring Boot app:

```java
import org.springframework.web.bind.annotation.*;
import com.fapshi.sdk.model.WebhookNotification;

@RestController
@RequestMapping("/fapshi")
public class FapshiWebhookController {
    @PostMapping("/webhook")
    public void handleWebhook(@RequestBody WebhookNotification notification) {
        // Process the notification
        System.out.println("Received webhook: " + notification);
    }
}
```

## Error Handling

All SDK methods throw `FapshiApiException` on API or network errors. Use try/catch to handle errors gracefully.

```java
try {
    // call SDK method
} catch (FapshiApiException e) {
    // handle error
}
```

## Architecture

![Fapshi Java SDK Architecture](docs/images/fapshi-flow.png)

## Contributing

We welcome contributions! Please see [docs/CONTRIBUTING.md](docs/CONTRIBUTING.md) for guidelines before submitting issues or pull requests.

## License
See [LICENSE](LICENSE). 
