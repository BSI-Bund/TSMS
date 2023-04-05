# SecureComponentProfilesApi

All URIs are relative to */*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getSecureComponentProfile**](SecureComponentProfilesApi.md#getSecureComponentProfile) | **GET** /secure-component-profiles/{scpId} | Get details of a certain SecureComponentProfile.
[**listScpRelatedElfs**](SecureComponentProfilesApi.md#listScpRelatedElfs) | **GET** /secure-component-profiles/{scpId}/elfs | List all ELFs that use a certain SecureComponentProfile.
[**listScpRelatedFlavors**](SecureComponentProfilesApi.md#listScpRelatedFlavors) | **GET** /secure-component-profiles/{scpId}/services/{serviceId}/flavors | List all Flavors of a certain Service that use a certain SecureCo...
[**listScpRelatedServices**](SecureComponentProfilesApi.md#listScpRelatedServices) | **GET** /secure-component-profiles/{scpId}/services | List all Services that use a certain SecureComponentProfile.
[**listScpRelatedVersions**](SecureComponentProfilesApi.md#listScpRelatedVersions) | **GET** /secure-component-profiles/{scpId}/services/{serviceId}/versions | List all Versions of a certain Service that use a certain SecureC...
[**listSecureComponentProfiles**](SecureComponentProfilesApi.md#listSecureComponentProfiles) | **GET** /secure-component-profiles | List all available SecureComponentProfiles.

<a name="getSecureComponentProfile"></a>
# **getSecureComponentProfile**
> SecureComponentProfile getSecureComponentProfile(scpId)

Get details of a certain SecureComponentProfile.

Get details of a certain SecureComponentProfile.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.SecureComponentProfilesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


SecureComponentProfilesApi apiInstance = new SecureComponentProfilesApi();
UUID scpId = new UUID(); // UUID | identifier of the referred Scp
try {
    SecureComponentProfile result = apiInstance.getSecureComponentProfile(scpId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SecureComponentProfilesApi#getSecureComponentProfile");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **scpId** | [**UUID**](.md)| identifier of the referred Scp |

### Return type

[**SecureComponentProfile**](SecureComponentProfile.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listScpRelatedElfs"></a>
# **listScpRelatedElfs**
> List&lt;ExecutableLoadFile&gt; listScpRelatedElfs(scpId)

List all ELFs that use a certain SecureComponentProfile.

List all ELFs that use a certain SecureComponentProfile.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.SecureComponentProfilesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


SecureComponentProfilesApi apiInstance = new SecureComponentProfilesApi();
UUID scpId = new UUID(); // UUID | identifier of the referred Scp
try {
    List<ExecutableLoadFile> result = apiInstance.listScpRelatedElfs(scpId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SecureComponentProfilesApi#listScpRelatedElfs");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **scpId** | [**UUID**](.md)| identifier of the referred Scp |

### Return type

[**List&lt;ExecutableLoadFile&gt;**](ExecutableLoadFile.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listScpRelatedFlavors"></a>
# **listScpRelatedFlavors**
> List&lt;Flavor&gt; listScpRelatedFlavors(scpId, serviceId)

List all Flavors of a certain Service that use a certain SecureCo...

List all Flavors of a certain Service that use a certain SecureComponentProfile.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.SecureComponentProfilesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


SecureComponentProfilesApi apiInstance = new SecureComponentProfilesApi();
UUID scpId = new UUID(); // UUID | identifier of the referred Scp
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
try {
    List<Flavor> result = apiInstance.listScpRelatedFlavors(scpId, serviceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SecureComponentProfilesApi#listScpRelatedFlavors");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **scpId** | [**UUID**](.md)| identifier of the referred Scp |
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |

### Return type

[**List&lt;Flavor&gt;**](Flavor.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listScpRelatedServices"></a>
# **listScpRelatedServices**
> List&lt;Service&gt; listScpRelatedServices(scpId)

List all Services that use a certain SecureComponentProfile.

List all Services that use a certain SecureComponentProfile.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.SecureComponentProfilesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


SecureComponentProfilesApi apiInstance = new SecureComponentProfilesApi();
UUID scpId = new UUID(); // UUID | identifier of the referred Scp
try {
    List<Service> result = apiInstance.listScpRelatedServices(scpId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SecureComponentProfilesApi#listScpRelatedServices");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **scpId** | [**UUID**](.md)| identifier of the referred Scp |

### Return type

[**List&lt;Service&gt;**](Service.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listScpRelatedVersions"></a>
# **listScpRelatedVersions**
> List&lt;Version&gt; listScpRelatedVersions(scpId, serviceId)

List all Versions of a certain Service that use a certain SecureC...

List all Versions of a certain Service that use a certain SecureComponentProfile.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.SecureComponentProfilesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


SecureComponentProfilesApi apiInstance = new SecureComponentProfilesApi();
UUID scpId = new UUID(); // UUID | identifier of the referred Scp
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
try {
    List<Version> result = apiInstance.listScpRelatedVersions(scpId, serviceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SecureComponentProfilesApi#listScpRelatedVersions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **scpId** | [**UUID**](.md)| identifier of the referred Scp |
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |

### Return type

[**List&lt;Version&gt;**](Version.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listSecureComponentProfiles"></a>
# **listSecureComponentProfiles**
> List&lt;SecureComponentProfile&gt; listSecureComponentProfiles()

List all available SecureComponentProfiles.

List all available SecureComponentProfiles.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.SecureComponentProfilesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


SecureComponentProfilesApi apiInstance = new SecureComponentProfilesApi();
try {
    List<SecureComponentProfile> result = apiInstance.listSecureComponentProfiles();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling SecureComponentProfilesApi#listSecureComponentProfiles");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;SecureComponentProfile&gt;**](SecureComponentProfile.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

