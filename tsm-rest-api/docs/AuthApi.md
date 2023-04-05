# AuthApi

All URIs are relative to */*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createAccessToken**](AuthApi.md#createAccessToken) | **POST** /auth | Authenticate to the TSM-Backend by sending a long-term token, and...

<a name="createAccessToken"></a>
# **createAccessToken**
> String createAccessToken()

Authenticate to the TSM-Backend by sending a long-term token, and...

Authenticate to the TSM-Backend by sending a long-term token, and receive a short-term bearer token. The short-term bearer token is used to access the other API functions. The long-term token is provided out of band.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.AuthApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


AuthApi apiInstance = new AuthApi();
try {
    String result = apiInstance.createAccessToken();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling AuthApi#createAccessToken");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**String**

### Authorization

[LongtermToken](../README.md#LongtermToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

