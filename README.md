# PayPay SDK - JAVA

[![License](https://img.shields.io/:license-apache-orange.svg)](https://opensource.org/licenses/Apache-2.0)
[![Maven Central](https://img.shields.io/maven-central/v/jp.ne.paypay/paypayopa)](https://search.maven.org/artifact/jp.ne.paypay/paypayopa)
[![javadoc](https://javadoc.io/badge2/jp.ne.paypay/paypayopa/javadoc.svg)](https://javadoc.io/doc/jp.ne.paypay/paypayopa)
[![Build Status](https://travis-ci.org/paypay/paypayopa-sdk-java.svg?branch=master)](https://travis-ci.org/paypay/paypayopa-sdk-java)
[![Coverage Status](https://coveralls.io/repos/github/paypay/paypayopa-sdk-java/badge.svg?branch=master)](https://coveralls.io/github/paypay/paypayopa-sdk-java?branch=master)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/paypay/paypayopa-sdk-java.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/paypay/paypayopa-sdk-java/context:java)
[![Black Duck Security Risk](https://copilot.blackducksoftware.com/github/repos/paypay/paypayopa-sdk-java/branches/master/badge-risk.svg)](https://copilot.blackducksoftware.com/github/repos/paypay/paypayopa-sdk-java/branches/master)
[![Maintainability](https://api.codeclimate.com/v1/badges/64c7339473ea7711415c/maintainability)](https://codeclimate.com/github/paypay/paypayopa-sdk-java/maintainability)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fpaypay%2Fpaypayopa-sdk-java.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fpaypay%2Fpaypayopa-sdk-java?ref=badge_shield)
[![Snyk Vulnerabilities for GitHub Repo](https://img.shields.io/snyk/vulnerabilities/github/paypay/paypayopa-sdk-java)](https://snyk.io/)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/701cdfe4502d48f7bb4063a94592b7ac)](https://app.codacy.com/gh/paypay/paypayopa-sdk-java?utm_source=github.com&utm_medium=referral&utm_content=paypay/paypayopa-sdk-java&utm_campaign=Badge_Grade_Dashboard)
[![BCH compliance](https://bettercodehub.com/edge/badge/paypay/paypayopa-sdk-java?branch=master)](https://bettercodehub.com/)
[![CII Best Practices](https://bestpractices.coreinfrastructure.org/projects/4306/badge)](https://bestpractices.coreinfrastructure.org/projects/4306)

Java SDK for interacting with PayPay APIs

This is the quickest way to integrate PayPay payment services. This is primarily meant for merchants who wish to perform interactions with PayPay API programmatically.
With PayPay's OPA SDK, you can build a custom Payment checkout process to suit your unique business needs and branding guidelines.

## Integrating with PayPay OPA

## Prerequisites
Before integrating with the SDK, run through this checklist:
- [*] Understand the payment flow
- [*] Sign up for a PayPay developer/merchant Account
- [*] Generate the API keys from the Developer Panel. Use the sandbox API Keys to test out the integration

## HMAC Signature Verification
Signature verification is a mandatory step to ensure that the callback is sent by PayPay and the payment is received from an authentic source.
### Generate a Signature
The PayPay signature, returned to you by the Checkout form on successful payment, can be regenerated by your system and verified as follows:

Step 1: Hash the body and content-type with MD5 algorithm

Note : If there is no request body, for instance, the HTTP GET method case, no need of generating MD5. Instead, hash value is set as "empty".
The value of authHeader is passed in HttpHeader.AUTHORIZATION. With the authHeader will decode back the data added and with the HTTP request object and based on data available for api-key in the system, 
we will recreate the SHA256("key", requestParams) which gives macData. This macData is verified against the value passed in the header.

Note: HMAC authorization will be done internally by the SDK, so client don't need to worry about it.

## Requirements
Building the API client library requires Gradle to be installed.

## Installation
Add this dependency to your project's build.gradle file:

```groovy
compile "jp.ne.paypay:paypayopa:0.9.0"
```

## Getting Started
Please follow the [installation](#installation) instruction and execute the following Java code:
> Please refer jp.ne.paypay.example.* for usage of APIs

You need to set up your API key and secret using the following:

```java
    ApiClient apiClient = new Configuration().getDefaultApiClient();
    apiClient.setProductionMode(false); //true for production and false for sandbox. Default is sandbox
    apiClient.setApiKey("YOUR_API_KEY");
    apiClient.setApiSecretKey("YOUR_API_SECRET");
    apiClient.setAssumeMerchant("YOUR_MERCHANT_KEY");
```

## Documentation for API Endpoints
Title | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*PaymentApi* | [**createAccountLinkQRCode**](docs/PaymentApi.md#createAccountLinkQRCode) | **POST** /v1/qr/sessions | Create an ACCOUNT LINK QR and display it to the user
*PaymentApi* | [**cancelPayment**](docs/PaymentApi.md#cancelPayment) | **DELETE** /v2/payments/{merchantPaymentId} | Cancel a payment
*PaymentApi* | [**createPayment**](docs/PaymentApi.md#createPayment) | **POST** /v2/payments | Create a payment
*PaymentApi* | [**createPaymentAuthorization**](docs/PaymentApi.md#createPaymentAuthorization) | **POST** /v2/payments/preauthorize | Create a payment authorization to block the money 
*PaymentApi* | [**createContinuousPayment**](docs/PaymentApi.md#createContinuousPayment) | **POST** /v1/subscription/payments | Create a continuous payment and start the money transfer
*PaymentApi* | [**revertAuth**](docs/PaymentApi.md#revertAuth) | **POST** /v2/payments/preauthorize/revert | Revert a payment authorization 
*PaymentApi* | [**capturePaymentAuth**](docs/PaymentApi.md#capturePaymentAuth) | **POST** /v2/payments/capture | Capture a payment authorization                                                                                                                                  
*PaymentApi* | [**createQRCode**](docs/PaymentApi.md#createQRCode) | **POST** /v2/codes | Create a Code
*PaymentApi* | [**deleteQRCode**](docs/PaymentApi.md#deleteQRCode) | **DELETE** /v2/codes/{codeId} | Delete a Code
*PaymentApi* | [**getPaymentDetails**](docs/PaymentApi.md#getPaymentDetails) | **GET** /v2/payments/{merchantPaymentId} | Get payment details
*PaymentApi* | [**getCodesPaymentDetails**](docs/PaymentApi.md#getCodesPaymentDetails) | **GET** /v2/codes/payments/{merchantPaymentId} | Get payment details for QR code
*PaymentApi* | [**getRefundDetails**](docs/PaymentApi.md#getRefundDetails) | **GET** /v2/refunds/{merchantRefundId} | Get refund details
*PaymentApi* | [**refundPayment**](docs/PaymentApi.md#refundPayment) | **POST** /v2/refunds | Refund a payment
*PaymentApi* | [**createPendingPayment**](docs/PendingPaymentApi.md#createPendingPayment) | **POST** /v1/requestOrder | Create a pending payment
*PaymentApi* | [**getPaymentDetails**](docs/PendingPaymentApi.md#getPaymentDetails) | **GET** /v1/requestOrder/{merchantPaymentId} | Get payment details (Pending Payment)
*PaymentApi* | [**cancelPendingOrder**](docs/PendingPaymentApi.md#cancelPendingOrder) | **DELETE** /v1/requestOrder/{merchantPaymentId} | Cancel a Pending Order
*PaymentApi* | [**getRefundDetails**](docs/PendingPaymentApi.md#getRefundDetails) | **GET** /v2/refunds/{merchantRefundId} | Get refund details (Pending Payment)
*PaymentApi* | [**refundPayment**](docs/PendingPaymentApi.md#refundPayment) | **POST** /v1/requestOrder/refunds | Refund a payment (Pending Payment)
*WalletApi* | [**checkWalletBalance**](docs/WalletApi.md#checkWalletBalance) | **GET** /v2/wallet/check_balance | Check user wallet balance 
*UserApi* | [**getMaskedUserProfile**](docs/UserApi.md#getMaskedUserProfile) | **GET** /v2/user/profile/secure?userAuthorizationId&#x3D;{userAuthorizationId} | Get masked user profile
*UserApi* | [**getUserAuthorizationStatus**](docs/UserApi.md#getUserAuthorizationStatus) | **GET** /v2/user/authorizations?userAuthorizationId&#x3D;{userAuthorizationId} | Get user authorization status
*UserApi* | [**unlinkUser**](docs/UserApi.md#unlinkUser) | **DELETE** /v2/user/authorizations/{userAuthorizationId} | Unlink user

## Documentation for Models
 - [Capture](docs/Capture.md)
 - [CaptureObject](docs/CaptureObject.md)
 - [PaymentDetails](docs/PaymentDetails.md)
 - [RefundDetails](docs/RefundDetails.md)
 - [WalletBalance](docs/WalletBalance.md)
 - [BalanceData](docs/BalanceData.md)
 - [QRCodeDetails](docs/QRCodeDetails.md)
 - [MerchantOrderItem](docs/MerchantOrderItem.md)
 - [MerchantOrderItemResponse](docs/MerchantOrderItemResponse.md)
 - [MoneyAmount](docs/MoneyAmount.md)
 - [NotDataResponse](docs/NotDataResponse.md)
 - [Payment](docs/Payment.md)
 - [PaymentDetails](docs/PaymentDetails.md)
 - [PaymentMethodDetails](docs/PaymentMethodDetails.md)
 - [PaymentMethodListDetails](docs/PaymentMethodListDetails.md)
 - [PaymentOrder](docs/PaymentOrder.md)
 - [PaymentOrderDetails](docs/PaymentOrderDetails.md)
 - [PaymentState](docs/PaymentState.md)
 - [PaymentStateCaptures](docs/PaymentStateCaptures.md)
 - [PaymentStateRefunds](docs/PaymentStateRefunds.md)
 - [PaymentStateRevert](docs/PaymentStateRevert.md)
 - [ProductType](docs/ProductType.md)
 - [QRCode](docs/QRCode.md)
 - [QRCodeResponse](docs/QRCodeResponse.md)
 - [Refund](docs/Refund.md)
 - [RefundOrder](docs/RefundOrder.md)
 - [RefundState](docs/RefundState.md)
 - [ResultInfo](docs/ResultInfo.md)
 - [AccountLinkQRCode](docs/AccountLinkQRCode.md)


## License
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fpaypay%2Fpaypayopa-sdk-java.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2Fpaypay%2Fpaypayopa-sdk-java?ref=badge_large)
