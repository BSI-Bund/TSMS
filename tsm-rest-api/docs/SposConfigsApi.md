# SposConfigsApi

All URIs are relative to */*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createSposConfig**](SposConfigsApi.md#createSposConfig) | **POST** /spos-configs | Create a new SposConfig and set all corresponding details.
[**deleteSposConfig**](SposConfigsApi.md#deleteSposConfig) | **DELETE** /spos-configs/{sposConfigId} | Delete a certain SposConfig. All data is deleted. Deletion SHALL ...
[**getSposConfig**](SposConfigsApi.md#getSposConfig) | **GET** /spos-configs/{sposConfigId} | Get details of a certain SposConfig.
[**listSposConfigRelatedServices**](SposConfigsApi.md#listSposConfigRelatedServices) | **GET** /spos-configs/{sposConfigId}/services | List all Services that use a certain SposConfig.
[**listSposConfigs**](SposConfigsApi.md#listSposConfigs) | **GET** /spos-configs | List all SposConfigs of the authenticated ServiceProvider.
[**modifySposConfig**](SposConfigsApi.md#modifySposConfig) | **PUT** /spos-configs/{sposConfigId} | Update details of an existing SposConfig.

<a name="createSposConfig"></a>
# **createSposConfig**
> SposConfig createSposConfig(body)

Create a new SposConfig and set all corresponding details.

Create a new SposConfig and set all corresponding details.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.SposConfigsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


SposConfigsApi apiInstance = new SposConfigsApi();
SposConfig body = new SposConfig(); // SposConfig | 
try {
    SposConfig result = apiInstance.createSposConfig(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SposConfigsApi#createSposConfig");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SposConfig**](SposConfig.md)|  |

### Return type

[**SposConfig**](SposConfig.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteSposConfig"></a>
# **deleteSposConfig**
> deleteSposConfig(sposConfigId)

Delete a certain SposConfig. All data is deleted. Deletion SHALL ...

Delete a certain SposConfig. All data is deleted. Deletion SHALL only be possible if the SposConfig is not referenced in any Service and thus is not in use anywhere.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.SposConfigsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


SposConfigsApi apiInstance = new SposConfigsApi();
UUID sposConfigId = new UUID(); // UUID | identifier of the referred SposConfig
try {
    apiInstance.deleteSposConfig(sposConfigId);
} catch (ApiException e) {
    System.err.println("Exception when calling SposConfigsApi#deleteSposConfig");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sposConfigId** | [**UUID**](.md)| identifier of the referred SposConfig |

### Return type

null (empty response body)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getSposConfig"></a>
# **getSposConfig**
> SposConfig getSposConfig(sposConfigId)

Get details of a certain SposConfig.

Get details of a certain SposConfig.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.SposConfigsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


SposConfigsApi apiInstance = new SposConfigsApi();
UUID sposConfigId = new UUID(); // UUID | identifier of the referred SposConfig
try {
    SposConfig result = apiInstance.getSposConfig(sposConfigId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SposConfigsApi#getSposConfig");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sposConfigId** | [**UUID**](.md)| identifier of the referred SposConfig |

### Return type

[**SposConfig**](SposConfig.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listSposConfigRelatedServices"></a>
# **listSposConfigRelatedServices**
> List&lt;Service&gt; listSposConfigRelatedServices(sposConfigId)

List all Services that use a certain SposConfig.

List all Services that use a certain SposConfig.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.SposConfigsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


SposConfigsApi apiInstance = new SposConfigsApi();
UUID sposConfigId = new UUID(); // UUID | identifier of the referred SposConfig
try {
    List<Service> result = apiInstance.listSposConfigRelatedServices(sposConfigId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SposConfigsApi#listSposConfigRelatedServices");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sposConfigId** | [**UUID**](.md)| identifier of the referred SposConfig |

### Return type

[**List&lt;Service&gt;**](Service.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listSposConfigs"></a>
# **listSposConfigs**
> List&lt;SposConfig&gt; listSposConfigs()

List all SposConfigs of the authenticated ServiceProvider.

List all SposConfigs of the authenticated ServiceProvider.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.SposConfigsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


SposConfigsApi apiInstance = new SposConfigsApi();
try {
    List<SposConfig> result = apiInstance.listSposConfigs();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SposConfigsApi#listSposConfigs");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;SposConfig&gt;**](SposConfig.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="modifySposConfig"></a>
# **modifySposConfig**
> SposConfig modifySposConfig(body, sposConfigId)

Update details of an existing SposConfig.

Update details of an existing SposConfig.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.SposConfigsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


SposConfigsApi apiInstance = new SposConfigsApi();
SposConfig body = new SposConfig(); // SposConfig | 
UUID sposConfigId = new UUID(); // UUID | identifier of the referred SposConfig
try {
    SposConfig result = apiInstance.modifySposConfig(body, sposConfigId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SposConfigsApi#modifySposConfig");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SposConfig**](SposConfig.md)|  |
 **sposConfigId** | [**UUID**](.md)| identifier of the referred SposConfig |

### Return type

[**SposConfig**](SposConfig.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

