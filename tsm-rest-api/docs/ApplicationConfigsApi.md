# ApplicationConfigsApi

All URIs are relative to */*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createAppConfig**](ApplicationConfigsApi.md#createAppConfig) | **POST** /application-configs | Create a new ApplicationConfig.
[**deleteAppConfig**](ApplicationConfigsApi.md#deleteAppConfig) | **DELETE** /application-configs/{applicationConfigId} | Delete a certain ApplicationConfig. All data is deleted. Reference...
[**getAppConfig**](ApplicationConfigsApi.md#getAppConfig) | **GET** /application-configs/{applicationConfigId} | Get details of a certain ApplicationConfig.
[**listAppConfigRelatedEms**](ApplicationConfigsApi.md#listAppConfigRelatedEms) | **GET** /application-configs/{applicationConfigId}/executable-modules | List all ExecutableModules that use a certain ApplicationConfig.
[**listAppConfigRelatedFlavors**](ApplicationConfigsApi.md#listAppConfigRelatedFlavors) | **GET** /application-configs/{applicationConfigId}/flavors | List all Flavors that use a certain ApplicationConfig.
[**listAppConfigRelatedServices**](ApplicationConfigsApi.md#listAppConfigRelatedServices) | **GET** /application-configs/{applicationConfigId}/services | List all Services that use a certain ApplicationConfig.
[**listAppConfigs**](ApplicationConfigsApi.md#listAppConfigs) | **GET** /application-configs | List all ApplicationConfigs of the authenticated ServiceProvider.
[**modifyAppConfig**](ApplicationConfigsApi.md#modifyAppConfig) | **PUT** /application-configs/{applicationConfigId} | Update details of an existing ApplicationConfig.

<a name="createAppConfig"></a>
# **createAppConfig**
> ApplicationConfig createAppConfig(body)

Create a new ApplicationConfig.

Create a new ApplicationConfig.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.ApplicationConfigsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ApplicationConfigsApi apiInstance = new ApplicationConfigsApi();
ApplicationConfig body = new ApplicationConfig(); // ApplicationConfig | 
try {
    ApplicationConfig result = apiInstance.createAppConfig(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ApplicationConfigsApi#createAppConfig");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ApplicationConfig**](ApplicationConfig.md)|  |

### Return type

[**ApplicationConfig**](ApplicationConfig.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteAppConfig"></a>
# **deleteAppConfig**
> deleteAppConfig(applicationConfigId)

Delete a certain ApplicationConfig. All data is deleted. Reference...

Delete a certain ApplicationConfig. All data is deleted. Referenced Certificates and PersonalizationScripts are not deleted. Deletion SHALL only be possible if the ApplicationConfig is not referenced in any Flavor and thus is not in use anywhere.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.ApplicationConfigsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ApplicationConfigsApi apiInstance = new ApplicationConfigsApi();
UUID applicationConfigId = new UUID(); // UUID | identifier of the referred ApplicationConfig
try {
    apiInstance.deleteAppConfig(applicationConfigId);
} catch (ApiException e) {
    System.err.println("Exception when calling ApplicationConfigsApi#deleteAppConfig");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **applicationConfigId** | [**UUID**](.md)| identifier of the referred ApplicationConfig |

### Return type

null (empty response body)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getAppConfig"></a>
# **getAppConfig**
> ApplicationConfig getAppConfig(applicationConfigId)

Get details of a certain ApplicationConfig.

Get details of a certain ApplicationConfig.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.ApplicationConfigsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ApplicationConfigsApi apiInstance = new ApplicationConfigsApi();
UUID applicationConfigId = new UUID(); // UUID | identifier of the referred ApplicationConfig
try {
    ApplicationConfig result = apiInstance.getAppConfig(applicationConfigId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ApplicationConfigsApi#getAppConfig");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **applicationConfigId** | [**UUID**](.md)| identifier of the referred ApplicationConfig |

### Return type

[**ApplicationConfig**](ApplicationConfig.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listAppConfigRelatedEms"></a>
# **listAppConfigRelatedEms**
> List&lt;ExecutableModule&gt; listAppConfigRelatedEms(applicationConfigId)

List all ExecutableModules that use a certain ApplicationConfig.

List all ExecutableModules that use a certain ApplicationConfig.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.ApplicationConfigsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ApplicationConfigsApi apiInstance = new ApplicationConfigsApi();
UUID applicationConfigId = new UUID(); // UUID | identifier of the referred ApplicationConfig
try {
    List<ExecutableModule> result = apiInstance.listAppConfigRelatedEms(applicationConfigId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ApplicationConfigsApi#listAppConfigRelatedEms");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **applicationConfigId** | [**UUID**](.md)| identifier of the referred ApplicationConfig |

### Return type

[**List&lt;ExecutableModule&gt;**](ExecutableModule.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listAppConfigRelatedFlavors"></a>
# **listAppConfigRelatedFlavors**
> List&lt;Flavor&gt; listAppConfigRelatedFlavors(applicationConfigId)

List all Flavors that use a certain ApplicationConfig.

List all Flavors that use a certain ApplicationConfig.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.ApplicationConfigsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ApplicationConfigsApi apiInstance = new ApplicationConfigsApi();
UUID applicationConfigId = new UUID(); // UUID | identifier of the referred ApplicationConfig
try {
    List<Flavor> result = apiInstance.listAppConfigRelatedFlavors(applicationConfigId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ApplicationConfigsApi#listAppConfigRelatedFlavors");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **applicationConfigId** | [**UUID**](.md)| identifier of the referred ApplicationConfig |

### Return type

[**List&lt;Flavor&gt;**](Flavor.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listAppConfigRelatedServices"></a>
# **listAppConfigRelatedServices**
> List&lt;Service&gt; listAppConfigRelatedServices(applicationConfigId)

List all Services that use a certain ApplicationConfig.

List all Services that use a certain ApplicationConfig.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.ApplicationConfigsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ApplicationConfigsApi apiInstance = new ApplicationConfigsApi();
UUID applicationConfigId = new UUID(); // UUID | identifier of the referred ApplicationConfig
try {
    List<Service> result = apiInstance.listAppConfigRelatedServices(applicationConfigId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ApplicationConfigsApi#listAppConfigRelatedServices");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **applicationConfigId** | [**UUID**](.md)| identifier of the referred ApplicationConfig |

### Return type

[**List&lt;Service&gt;**](Service.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listAppConfigs"></a>
# **listAppConfigs**
> List&lt;ApplicationConfig&gt; listAppConfigs()

List all ApplicationConfigs of the authenticated ServiceProvider.

List all ApplicationConfigs of the authenticated ServiceProvider.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.ApplicationConfigsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ApplicationConfigsApi apiInstance = new ApplicationConfigsApi();
try {
    List<ApplicationConfig> result = apiInstance.listAppConfigs();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ApplicationConfigsApi#listAppConfigs");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;ApplicationConfig&gt;**](ApplicationConfig.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="modifyAppConfig"></a>
# **modifyAppConfig**
> ApplicationConfig modifyAppConfig(body, applicationConfigId)

Update details of an existing ApplicationConfig.

Update details of an existing ApplicationConfig.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.ApplicationConfigsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ApplicationConfigsApi apiInstance = new ApplicationConfigsApi();
ApplicationConfig body = new ApplicationConfig(); // ApplicationConfig | 
UUID applicationConfigId = new UUID(); // UUID | identifier of the referred ApplicationConfig
try {
    ApplicationConfig result = apiInstance.modifyAppConfig(body, applicationConfigId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ApplicationConfigsApi#modifyAppConfig");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ApplicationConfig**](ApplicationConfig.md)|  |
 **applicationConfigId** | [**UUID**](.md)| identifier of the referred ApplicationConfig |

### Return type

[**ApplicationConfig**](ApplicationConfig.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

