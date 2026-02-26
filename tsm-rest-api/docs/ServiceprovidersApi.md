# ServiceProvidersApi

All URIs are relative to */*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getAccountInformation**](ServiceProvidersApi.md#getAccountInformation) | **GET** /service-providers/current | Get details of the SP account.

<a name="getAccountInformation"></a>
# **getAccountInformation**
> ServiceProvider getAccountInformation()

Get details of the SP account.

Get details of the SP account.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServiceProvidersApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServiceProvidersApi apiInstance = new ServiceProvidersApi();
try {
    ServiceProvider result = apiInstance.getAccountInformation();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServiceProvidersApi#getAccountInformation");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ServiceProvider**](ServiceProvider.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

