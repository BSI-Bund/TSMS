# ServicesApi

All URIs are relative to */*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createFlavor**](ServicesApi.md#createFlavor) | **POST** /services/{serviceId}/flavors | Create a new Flavor for a certain Service.
[**createService**](ServicesApi.md#createService) | **POST** /services | Create a new Service.
[**createVersion**](ServicesApi.md#createVersion) | **POST** /services/{serviceId}/versions | Create a new Version of a certain Service.
[**deleteFlavor**](ServicesApi.md#deleteFlavor) | **DELETE** /services/{serviceId}/flavors/{flavorId} | Delete a certain Flavor. All data, including associated Applicati...
[**deleteService**](ServicesApi.md#deleteService) | **DELETE** /services/{serviceId} | Delete a certain Service. All data, including associated Versions...
[**deleteVersion**](ServicesApi.md#deleteVersion) | **DELETE** /services/{serviceId}/versions/{tag} | Delete a certain Version. All data is deleted. Referenced Flavors...
[**getFlavor**](ServicesApi.md#getFlavor) | **GET** /services/{serviceId}/flavors/{flavorId} | Get details of a certain Flavor.
[**getService**](ServicesApi.md#getService) | **GET** /services/{serviceId} | Get details of a certain Service.
[**getVersion**](ServicesApi.md#getVersion) | **GET** /services/{serviceId}/versions/{tag} | Get details of a certain Version.
[**linkElfs**](ServicesApi.md#linkElfs) | **POST** /services/{serviceId}/flavors/{flavorId}/executable-load-files | Add additional ExecutableLoadFiles to a certain Flavor. In case E...
[**linkFlavors**](ServicesApi.md#linkFlavors) | **POST** /services/{serviceId}/versions/{tag}/flavors | Add additional Flavors to a certain Version and configure the Sec...
[**linkSecureComponentProfiles**](ServicesApi.md#linkSecureComponentProfiles) | **POST** /services/{serviceId}/versions/{tag}/secure-component-profiles | Add additional SecureComponentProfiles to a certain Version and c...
[**listAssociatedSecureComponentProfiles**](ServicesApi.md#listAssociatedSecureComponentProfiles) | **GET** /services/{serviceId}/versions/{tag}/flavors/{flavorId}/secure-component-profiles | List SecureComponentProfiles associated to a certain Flavor of a ...
[**listElfRelatedFlavors**](ServicesApi.md#listElfRelatedFlavors) | **GET** /services/{serviceId}/executable-load-files/{elfId}/flavors | List all Flavors that use a certain ExecutableLoadFile.
[**listFlavors**](ServicesApi.md#listFlavors) | **GET** /services/{serviceId}/flavors | List all Flavors of a certain Service.
[**listLinkedElfs**](ServicesApi.md#listLinkedElfs) | **GET** /services/{serviceId}/flavors/{flavorId}/executable-load-files | List all ExecutableLoadFiles used by a certain Flavor.
[**listLinkedFlavors**](ServicesApi.md#listLinkedFlavors) | **GET** /services/{serviceId}/versions/{tag}/flavors | List all Flavors used by a certain Version.
[**listLinkedSecureComponentProfiles**](ServicesApi.md#listLinkedSecureComponentProfiles) | **GET** /services/{serviceId}/versions/{tag}/secure-component-profiles | List all SecureComponentProfiles used by a certain Version.
[**listServiceRelatedAppConfigs**](ServicesApi.md#listServiceRelatedAppConfigs) | **GET** /services/{serviceId}/flavors/{flavorId}/application-configs | List all ApplicationConfigs that use a certain Flavor.
[**listServiceRelatedVersions**](ServicesApi.md#listServiceRelatedVersions) | **GET** /services/{serviceId}/flavors/{flavorId}/versions | List all Versions that use a certain Flavor.
[**listServices**](ServicesApi.md#listServices) | **GET** /services | List all Services of the authenticated ServiceProvider.
[**listVersions**](ServicesApi.md#listVersions) | **GET** /services/{serviceId}/versions | List all Versions of a certain Service.
[**modifyFlavor**](ServicesApi.md#modifyFlavor) | **PUT** /services/{serviceId}/flavors/{flavorId} | Update details of an existing Flavor.
[**modifyService**](ServicesApi.md#modifyService) | **PUT** /services/{serviceId} | Update details of an existing Service.
[**modifyVersion**](ServicesApi.md#modifyVersion) | **PUT** /services/{serviceId}/versions/{tag} | Update details of an existing Version.
[**publishFlavor**](ServicesApi.md#publishFlavor) | **POST** /services/{serviceId}/flavors/{flavorId}/publish | Publish a Flavor. After publishing, the Flavor can be used for in...
[**unlinkElfs**](ServicesApi.md#unlinkElfs) | **PUT** /services/{serviceId}/flavors/{flavorId}/executable-load-files | Remove ExecutableLoadFiles from a certain Flavor. In case ELF Ids...
[**unlinkFlavors**](ServicesApi.md#unlinkFlavors) | **PUT** /services/{serviceId}/versions/{tag}/flavors | Remove Flavors from a certain Version. In case Flavor Ids provide...
[**unlinkSecureComponentProfiles**](ServicesApi.md#unlinkSecureComponentProfiles) | **PUT** /services/{serviceId}/versions/{tag}/secure-component-profiles | Remove SecureComponentProfiles from a certain Version. In case Se...

<a name="createFlavor"></a>
# **createFlavor**
> Flavor createFlavor(body, serviceId)

Create a new Flavor for a certain Service.

Create a new Flavor for a certain Service.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
Flavor body = new Flavor(); // Flavor | 
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
try {
    Flavor result = apiInstance.createFlavor(body, serviceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#createFlavor");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**Flavor**](Flavor.md)|  |
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |

### Return type

[**Flavor**](Flavor.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="createService"></a>
# **createService**
> Service createService(body)

Create a new Service.

Create a new Service.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
Service body = new Service(); // Service | 
try {
    Service result = apiInstance.createService(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#createService");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**Service**](Service.md)|  |

### Return type

[**Service**](Service.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="createVersion"></a>
# **createVersion**
> Version createVersion(body, serviceId)

Create a new Version of a certain Service.

Create a new Version of a certain Service.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
Version body = new Version(); // Version | 
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
try {
    Version result = apiInstance.createVersion(body, serviceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#createVersion");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**Version**](Version.md)|  |
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |

### Return type

[**Version**](Version.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteFlavor"></a>
# **deleteFlavor**
> deleteFlavor(serviceId, flavorId)

Delete a certain Flavor. All data, including associated Applicati...

Delete a certain Flavor. All data, including associated ApplicationInstantiationConfigs, is deleted. Referenced ELFs and ApplicationConfigs are not deleted. Deletion SHALL only be possible if the Flavor is not referenced in any Version and thus is not in use anywhere.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
UUID flavorId = new UUID(); // UUID | identifier of the referred Flavor
try {
    apiInstance.deleteFlavor(serviceId, flavorId);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#deleteFlavor");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |
 **flavorId** | [**UUID**](.md)| identifier of the referred Flavor |

### Return type

null (empty response body)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="deleteService"></a>
# **deleteService**
> deleteService(serviceId)

Delete a certain Service. All data, including associated Versions...

Delete a certain Service. All data, including associated Versions, Flavors, and ApplicationInstantiationConfigs is deleted. Referenced ELFs, ApplicationConfigs, and SposConfigs are not deleted.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
try {
    apiInstance.deleteService(serviceId);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#deleteService");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |

### Return type

null (empty response body)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="deleteVersion"></a>
# **deleteVersion**
> deleteVersion(serviceId, tag)

Delete a certain Version. All data is deleted. Referenced Flavors...

Delete a certain Version. All data is deleted. Referenced Flavors are not deleted.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
String tag = "tag_example"; // String | identifier of the referred Tag
try {
    apiInstance.deleteVersion(serviceId, tag);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#deleteVersion");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |
 **tag** | **String**| identifier of the referred Tag |

### Return type

null (empty response body)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getFlavor"></a>
# **getFlavor**
> Flavor getFlavor(serviceId, flavorId)

Get details of a certain Flavor.

Get details of a certain Flavor.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
UUID flavorId = new UUID(); // UUID | identifier of the referred Flavor
try {
    Flavor result = apiInstance.getFlavor(serviceId, flavorId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#getFlavor");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |
 **flavorId** | [**UUID**](.md)| identifier of the referred Flavor |

### Return type

[**Flavor**](Flavor.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getService"></a>
# **getService**
> Service getService(serviceId)

Get details of a certain Service.

Get details of a certain Service.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
try {
    Service result = apiInstance.getService(serviceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#getService");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |

### Return type

[**Service**](Service.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getVersion"></a>
# **getVersion**
> Version getVersion(serviceId, tag)

Get details of a certain Version.

Get details of a certain Version.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
String tag = "tag_example"; // String | identifier of the referred Tag
try {
    Version result = apiInstance.getVersion(serviceId, tag);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#getVersion");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |
 **tag** | **String**| identifier of the referred Tag |

### Return type

[**Version**](Version.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="linkElfs"></a>
# **linkElfs**
> Flavor linkElfs(body, serviceId, flavorId)

Add additional ExecutableLoadFiles to a certain Flavor. In case E...

Add additional ExecutableLoadFiles to a certain Flavor. In case ELF Ids provided are already linked to this Flavor, method will still be successful.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
List<String> body = Arrays.asList("body_example"); // List<String> | Ids of the ELFs to be added to the flavor (elfIds)
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
UUID flavorId = new UUID(); // UUID | identifier of the referred Flavor
try {
    Flavor result = apiInstance.linkElfs(body, serviceId, flavorId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#linkElfs");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**List&lt;String&gt;**](String.md)| Ids of the ELFs to be added to the flavor (elfIds) |
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |
 **flavorId** | [**UUID**](.md)| identifier of the referred Flavor |

### Return type

[**Flavor**](Flavor.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="linkFlavors"></a>
# **linkFlavors**
> Version linkFlavors(body, serviceId, tag)

Add additional Flavors to a certain Version and configure the Sec...

Add additional Flavors to a certain Version and configure the SecureComponentProfiles supported by the Flavor. In case Flavor Ids provided are already linked to this Version, the method will still be successful and it will just modify the list of supported SecureComponentProfiles.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
Map<String, List<String>> body = new Map(); // Map<String, List<String>> | Map key: flavorId, Map values: list of secureComponentProfileIds
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
String tag = "tag_example"; // String | identifier of the referred Tag
try {
    Version result = apiInstance.linkFlavors(body, serviceId, tag);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#linkFlavors");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**Map&lt;String, List&lt;String&gt;&gt;**](Map.md)| Map key: flavorId, Map values: list of secureComponentProfileIds |
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |
 **tag** | **String**| identifier of the referred Tag |

### Return type

[**Version**](Version.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="linkSecureComponentProfiles"></a>
# **linkSecureComponentProfiles**
> Version linkSecureComponentProfiles(body, serviceId, tag)

Add additional SecureComponentProfiles to a certain Version and c...

Add additional SecureComponentProfiles to a certain Version and configure the corresponding Flavors. In case SecureComponentProfile Ids provided are already linked to this Version, the method will still be successful and it will just modify the list of supported Flavors.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
Map<String, String> body = new Map(); // Map<String, String> | Map key: secureComponentProfileId, Map value: flavorId of the corresponding Flavor
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
String tag = "tag_example"; // String | identifier of the referred Tag
try {
    Version result = apiInstance.linkSecureComponentProfiles(body, serviceId, tag);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#linkSecureComponentProfiles");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**Map&lt;String, String&gt;**](Map.md)| Map key: secureComponentProfileId, Map value: flavorId of the corresponding Flavor |
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |
 **tag** | **String**| identifier of the referred Tag |

### Return type

[**Version**](Version.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="listAssociatedSecureComponentProfiles"></a>
# **listAssociatedSecureComponentProfiles**
> List&lt;SecureComponentProfile&gt; listAssociatedSecureComponentProfiles(serviceId, tag, flavorId)

List SecureComponentProfiles associated to a certain Flavor of a ...

List SecureComponentProfiles associated to a certain Flavor of a certain Version.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
String tag = "tag_example"; // String | identifier of the referred Tag
UUID flavorId = new UUID(); // UUID | identifier of the referred Flavor
try {
    List<SecureComponentProfile> result = apiInstance.listAssociatedSecureComponentProfiles(serviceId, tag, flavorId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#listAssociatedSecureComponentProfiles");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |
 **tag** | **String**| identifier of the referred Tag |
 **flavorId** | [**UUID**](.md)| identifier of the referred Flavor |

### Return type

[**List&lt;SecureComponentProfile&gt;**](SecureComponentProfile.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listElfRelatedFlavors"></a>
# **listElfRelatedFlavors**
> List&lt;Flavor&gt; listElfRelatedFlavors(serviceId, elfId)

List all Flavors that use a certain ExecutableLoadFile.

List all Flavors that use a certain ExecutableLoadFile.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
UUID elfId = new UUID(); // UUID | identifier of the referred Elf
try {
    List<Flavor> result = apiInstance.listElfRelatedFlavors(serviceId, elfId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#listElfRelatedFlavors");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |
 **elfId** | [**UUID**](.md)| identifier of the referred Elf |

### Return type

[**List&lt;Flavor&gt;**](Flavor.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listFlavors"></a>
# **listFlavors**
> List&lt;Flavor&gt; listFlavors(serviceId)

List all Flavors of a certain Service.

List all Flavors of a certain Service.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
try {
    List<Flavor> result = apiInstance.listFlavors(serviceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#listFlavors");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |

### Return type

[**List&lt;Flavor&gt;**](Flavor.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listLinkedElfs"></a>
# **listLinkedElfs**
> List&lt;Object&gt; listLinkedElfs(serviceId, flavorId)

List all ExecutableLoadFiles used by a certain Flavor.

List all ExecutableLoadFiles used by a certain Flavor.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
UUID flavorId = new UUID(); // UUID | identifier of the referred Flavor
try {
    List<Object> result = apiInstance.listLinkedElfs(serviceId, flavorId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#listLinkedElfs");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |
 **flavorId** | [**UUID**](.md)| identifier of the referred Flavor |

### Return type

**List&lt;Object&gt;**

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listLinkedFlavors"></a>
# **listLinkedFlavors**
> List&lt;Flavor&gt; listLinkedFlavors(serviceId, tag)

List all Flavors used by a certain Version.

List all Flavors used by a certain Version.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
String tag = "tag_example"; // String | identifier of the referred Tag
try {
    List<Flavor> result = apiInstance.listLinkedFlavors(serviceId, tag);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#listLinkedFlavors");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |
 **tag** | **String**| identifier of the referred Tag |

### Return type

[**List&lt;Flavor&gt;**](Flavor.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listLinkedSecureComponentProfiles"></a>
# **listLinkedSecureComponentProfiles**
> List&lt;SecureComponentProfile&gt; listLinkedSecureComponentProfiles(serviceId, tag)

List all SecureComponentProfiles used by a certain Version.

List all SecureComponentProfiles used by a certain Version.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
String tag = "tag_example"; // String | identifier of the referred Tag
try {
    List<SecureComponentProfile> result = apiInstance.listLinkedSecureComponentProfiles(serviceId, tag);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#listLinkedSecureComponentProfiles");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |
 **tag** | **String**| identifier of the referred Tag |

### Return type

[**List&lt;SecureComponentProfile&gt;**](SecureComponentProfile.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listServiceRelatedAppConfigs"></a>
# **listServiceRelatedAppConfigs**
> List&lt;ApplicationConfig&gt; listServiceRelatedAppConfigs(serviceId, flavorId)

List all ApplicationConfigs that use a certain Flavor.

List all ApplicationConfigs that use a certain Flavor.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
UUID flavorId = new UUID(); // UUID | identifier of the referred Flavor
try {
    List<ApplicationConfig> result = apiInstance.listServiceRelatedAppConfigs(serviceId, flavorId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#listServiceRelatedAppConfigs");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |
 **flavorId** | [**UUID**](.md)| identifier of the referred Flavor |

### Return type

[**List&lt;ApplicationConfig&gt;**](ApplicationConfig.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listServiceRelatedVersions"></a>
# **listServiceRelatedVersions**
> List&lt;Version&gt; listServiceRelatedVersions(serviceId, flavorId)

List all Versions that use a certain Flavor.

List all Versions that use a certain Flavor.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
UUID flavorId = new UUID(); // UUID | identifier of the referred Flavor
try {
    List<Version> result = apiInstance.listServiceRelatedVersions(serviceId, flavorId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#listServiceRelatedVersions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |
 **flavorId** | [**UUID**](.md)| identifier of the referred Flavor |

### Return type

[**List&lt;Version&gt;**](Version.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listServices"></a>
# **listServices**
> List&lt;Service&gt; listServices()

List all Services of the authenticated ServiceProvider.

List all Services of the authenticated ServiceProvider.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
try {
    List<Service> result = apiInstance.listServices();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#listServices");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;Service&gt;**](Service.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listVersions"></a>
# **listVersions**
> List&lt;Version&gt; listVersions(serviceId)

List all Versions of a certain Service.

List all Versions of a certain Service.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
try {
    List<Version> result = apiInstance.listVersions(serviceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#listVersions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |

### Return type

[**List&lt;Version&gt;**](Version.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="modifyFlavor"></a>
# **modifyFlavor**
> Flavor modifyFlavor(body, serviceId, flavorId)

Update details of an existing Flavor.

Update details of an existing Flavor.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
Flavor body = new Flavor(); // Flavor | 
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
UUID flavorId = new UUID(); // UUID | identifier of the referred Flavor
try {
    Flavor result = apiInstance.modifyFlavor(body, serviceId, flavorId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#modifyFlavor");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**Flavor**](Flavor.md)|  |
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |
 **flavorId** | [**UUID**](.md)| identifier of the referred Flavor |

### Return type

[**Flavor**](Flavor.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="modifyService"></a>
# **modifyService**
> Service modifyService(body, serviceId)

Update details of an existing Service.

Update details of an existing Service.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
Service body = new Service(); // Service | 
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
try {
    Service result = apiInstance.modifyService(body, serviceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#modifyService");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**Service**](Service.md)|  |
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |

### Return type

[**Service**](Service.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="modifyVersion"></a>
# **modifyVersion**
> Version modifyVersion(body, serviceId, tag)

Update details of an existing Version.

Update details of an existing Version.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
Version body = new Version(); // Version | 
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
String tag = "tag_example"; // String | identifier of the referred Tag
try {
    Version result = apiInstance.modifyVersion(body, serviceId, tag);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#modifyVersion");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**Version**](Version.md)|  |
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |
 **tag** | **String**| identifier of the referred Tag |

### Return type

[**Version**](Version.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="publishFlavor"></a>
# **publishFlavor**
> Flavor publishFlavor(serviceId, flavorId)

Publish a Flavor. After publishing, the Flavor can be used for in...

Publish a Flavor. After publishing, the Flavor can be used for installation on a handset and certain attributes cannot be modified anymore (see Section 4.1.6.4.6). The publishing status of a Flavor can be checked with the attribute publish of the Flavor. When a Flavor is once published, it is not possible to undo this process. It is valid to call this method multiple times, even if a Flavor is already published.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
UUID flavorId = new UUID(); // UUID | identifier of the referred Flavor
try {
    Flavor result = apiInstance.publishFlavor(serviceId, flavorId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#publishFlavor");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |
 **flavorId** | [**UUID**](.md)| identifier of the referred Flavor |

### Return type

[**Flavor**](Flavor.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="unlinkElfs"></a>
# **unlinkElfs**
> Flavor unlinkElfs(body, serviceId, flavorId)

Remove ExecutableLoadFiles from a certain Flavor. In case ELF Ids...

Remove ExecutableLoadFiles from a certain Flavor. In case ELF Ids provided are not linked to this Flavor, method will still be successful.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
List<String> body = Arrays.asList("body_example"); // List<String> | Ids of the ELFs to be removed from the flavor (elfIds)
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
UUID flavorId = new UUID(); // UUID | identifier of the referred Flavor
try {
    Flavor result = apiInstance.unlinkElfs(body, serviceId, flavorId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#unlinkElfs");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**List&lt;String&gt;**](String.md)| Ids of the ELFs to be removed from the flavor (elfIds) |
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |
 **flavorId** | [**UUID**](.md)| identifier of the referred Flavor |

### Return type

[**Flavor**](Flavor.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="unlinkFlavors"></a>
# **unlinkFlavors**
> Version unlinkFlavors(body, serviceId, tag)

Remove Flavors from a certain Version. In case Flavor Ids provide...

Remove Flavors from a certain Version. In case Flavor Ids provided are not linked to this Version, method will still be successful.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
List<String> body = Arrays.asList("body_example"); // List<String> | Ids of the flavors to be removed from the version (flavorIds)
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
String tag = "tag_example"; // String | identifier of the referred Tag
try {
    Version result = apiInstance.unlinkFlavors(body, serviceId, tag);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#unlinkFlavors");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**List&lt;String&gt;**](String.md)| Ids of the flavors to be removed from the version (flavorIds) |
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |
 **tag** | **String**| identifier of the referred Tag |

### Return type

[**Version**](Version.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="unlinkSecureComponentProfiles"></a>
# **unlinkSecureComponentProfiles**
> Version unlinkSecureComponentProfiles(body, serviceId, tag)

Remove SecureComponentProfiles from a certain Version. In case Se...

Remove SecureComponentProfiles from a certain Version. In case SecureComponentProfile Ids provided are not linked to this Version, method will still be successful.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ServicesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ServicesApi apiInstance = new ServicesApi();
List<String> body = Arrays.asList("body_example"); // List<String> | Ids of the secureComponentProfiles to be removed from the version (secureComponentProfileIds)
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
String tag = "tag_example"; // String | identifier of the referred Tag
try {
    Version result = apiInstance.unlinkSecureComponentProfiles(body, serviceId, tag);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ServicesApi#unlinkSecureComponentProfiles");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**List&lt;String&gt;**](String.md)| Ids of the secureComponentProfiles to be removed from the version (secureComponentProfileIds) |
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |
 **tag** | **String**| identifier of the referred Tag |

### Return type

[**Version**](Version.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

