# Adyen Drop-In Payment Gateway Integration

This repository includes a Java Spring Boot Maven project which replicates a simplified version of a shopping cart of an e-commerce website and integrates Adyen's payment gateway for completing payments online.

[![Preview]]({Run Project Preview.mov} "Preview")

![Preview](preview.gif)

## Requirements

* Java 14+
* Maven 3.6.3
* Whitelist IP address of your localhost in [Customer Area](https://authn-test.adyen.com/authn/index.html?request=eyJBdXRoblJlcXVlc3QiOnsiYWN0aXZpdHlHcm91cCI6IkJPX0NBIiwiY3JlZHNSZWFzb24iOlsiTG9nZ2luZyBpbiB0byBhcHBsaWNhdGlvbiBjYSJdLCJmb3JjZU5ld1Nlc3Npb24iOiJmYWxzZSIsImZvcmdvdFBhc3N3b3JkVXJsIjoiaHR0cHM6XC9cL2NhLXRlc3QuYWR5ZW4uY29tXC9jYVwvbG9iYnlcL3Bhc3N3b3JkLXJlc2V0XC9mb3Jnb3QtcGFzc3dvcmQiLCJyZXF1ZXN0VGltZSI6IjIwMjEtMDUtMjhUMDM6Mzg6MzUrMDI6MDAiLCJyZXF1ZXN0ZWRDcmVkZW50aWFscyI6W3siUmVxdWVzdGVkQ3JlZGVudGlhbCI6eyJhY2NlcHRlZEFjdGl2aXR5IjpbeyJBY2NlcHRlZEFjdGl2aXR5Ijp7ImFjdGl2aXR5R3JvdXAiOiJCT19DQSIsImFjdGl2aXR5VHlwZSI6IklNUExJQ0lUIiwibWlsbGlzZWNvbmRzQWdvIjo5MDAwMDB9fV0sInR5cGUiOiJQQVNTV09SRCJ9fSx7IlJlcXVlc3RlZENyZWRlbnRpYWwiOnsiYWNjZXB0ZWRBY3Rpdml0eSI6W3siQWNjZXB0ZWRBY3Rpdml0eSI6eyJhY3Rpdml0eUdyb3VwIjoiQk9fQ0EiLCJhY3Rpdml0eVR5cGUiOiJHUkFDRV9DT09LSUUiLCJtaWxsaXNlY29uZHNBZ28iOjB9fV0sInR5cGUiOiJUV09fRkFDVE9SIn19XSwicmVxdWVzdGluZ0FwcCI6ImNhIiwicmV0dXJuVXJsIjoiaHR0cHM6XC9cL2NhLXRlc3QuYWR5ZW4uY29tXC9jYVwvY2FcL292ZXJ2aWV3XC9kZWZhdWx0LnNodG1sIiwic2lnbmF0dXJlIjoiVjAwM1NQcSt0aEV6Y2dHSzJQcTN5VGtwWGZ3RVYzSld6YjRrdjJUeDA2T0ZCM0djPSJ9fQ%3D%3D)

## Usage

1. Clone this repo:
```bash
https://github.com/arantachatterjee/adyen-payment-gateway-integration
```
2. Navigate to the root directory and run:
```bash
mvn clean install
```
3. Setup environment variables.
Set environment variables for your [API key, Client Key](https://authn-test.adyen.com/authn/index.html?request=eyJBdXRoblJlcXVlc3QiOnsiYWN0aXZpdHlHcm91cCI6IkJPX0NBIiwiY3JlZHNSZWFzb24iOlsiTG9nZ2luZyBpbiB0byBhcHBsaWNhdGlvbiBjYSJdLCJmb3JjZU5ld1Nlc3Npb24iOiJmYWxzZSIsImZvcmdvdFBhc3N3b3JkVXJsIjoiaHR0cHM6XC9cL2NhLXRlc3QuYWR5ZW4uY29tXC9jYVwvbG9iYnlcL3Bhc3N3b3JkLXJlc2V0XC9mb3Jnb3QtcGFzc3dvcmQiLCJyZXF1ZXN0VGltZSI6IjIwMjEtMDUtMjRUMDE6MDY6MzErMDI6MDAiLCJyZXF1ZXN0ZWRDcmVkZW50aWFscyI6W3siUmVxdWVzdGVkQ3JlZGVudGlhbCI6eyJhY2NlcHRlZEFjdGl2aXR5IjpbeyJBY2NlcHRlZEFjdGl2aXR5Ijp7ImFjdGl2aXR5R3JvdXAiOiJCT19DQSIsImFjdGl2aXR5VHlwZSI6IklNUExJQ0lUIiwibWlsbGlzZWNvbmRzQWdvIjo5MDAwMDB9fV0sInR5cGUiOiJQQVNTV09SRCJ9fSx7IlJlcXVlc3RlZENyZWRlbnRpYWwiOnsiYWNjZXB0ZWRBY3Rpdml0eSI6W3siQWNjZXB0ZWRBY3Rpdml0eSI6eyJhY3Rpdml0eUdyb3VwIjoiQk9fQ0EiLCJhY3Rpdml0eVR5cGUiOiJHUkFDRV9DT09LSUUiLCJtaWxsaXNlY29uZHNBZ28iOjB9fV0sInR5cGUiOiJUV09fRkFDVE9SIn19XSwicmVxdWVzdGluZ0FwcCI6ImNhIiwicmV0dXJuVXJsIjoiaHR0cHM6XC9cL2NhLXRlc3QuYWR5ZW4uY29tXC9jYVwvY2FcL292ZXJ2aWV3XC9kZWZhdWx0LnNodG1sIiwic2lnbmF0dXJlIjoiVjAwM1MwNzlNTng5bDJpcjh3SFlCQnFOZXBOd0VBUHc1eXBHYTVLMXhuY1JnQWxZPSJ9fQ%3D%3D) - Remember to add http://localhost:8080 as an origin for client key, and merchant account name:

```bash
export ADYEN_API_KEY=yourAdyenApiKey
export ADYEN_MERCHANT_ACCOUNT=yourAdyenMerchantAccount
export ADYEN_CLIENT_KEY=yourAdyenClientKey
export ADYEN_ORIGIN_KEY=yourAdyenOriginKey
```

On Windows CMD you can use below commands instead

```bash
set ADYEN_API_KEY=yourAdyenApiKey
set ADYEN_MERCHANT_ACCOUNT=yourAdyenMerchantAccount
set ADYEN_CLIENT_KEY=yourAdyenClientKey
set ADYEN_ORIGIN_KEY=yourAdyenOriginKey
```
5. Build and run project.
6. Visit http://localhost:8080/ to complete a test payment.

[Mastercard test card numbers](https://docs.adyen.com/development-resources/test-cards/test-card-numbers#mastercard)
[Visa test card numbers](https://docs.adyen.com/development-resources/test-cards/test-card-numbers#visa)

Successful transactions can be viewed in Customer Area.
