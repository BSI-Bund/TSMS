# PersonalizationScriptsApi

All URIs are relative to */*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createPersoScriptAndUploadBinary**](PersonalizationScriptsApi.md#createPersoScriptAndUploadBinary) | **POST** /personalization-scripts | Create a new PersonalizationScript and upload corresponding binary...
[**deletePersoScript**](PersonalizationScriptsApi.md#deletePersoScript) | **DELETE** /personalization-scripts/{personalizationScriptId} | Delete a certain PersonalizationScript. All data, including binary...
[**getPersoScript**](PersonalizationScriptsApi.md#getPersoScript) | **GET** /personalization-scripts/{personalizationScriptId} | Get details of a certain PersonalizationScript.
[**getScriptBinary**](PersonalizationScriptsApi.md#getScriptBinary) | **GET** /personalization-scripts/{personalizationScriptId}/binary | Get binary data of a certain PersonalizationScript.
[**listPersoScripts**](PersonalizationScriptsApi.md#listPersoScripts) | **GET** /personalization-scripts | List all PersonalizationScripts of the authenticated ServiceProvider.
[**listScriptRelatedAppConfigs**](PersonalizationScriptsApi.md#listScriptRelatedAppConfigs) | **GET** /personalization-scripts/{personalizationScriptId}/application-configs | List all ApplicationConfigs that use a certain PersonalizationScript.
[**listScriptRelatedFlavors**](PersonalizationScriptsApi.md#listScriptRelatedFlavors) | **GET** /personalization-scripts/{personalizationScriptId}/flavors | List all Flavors that use a certain PersonalizationScript.
[**listScriptRelatedServices**](PersonalizationScriptsApi.md#listScriptRelatedServices) | **GET** /personalization-scripts/{personalizationScriptId}/services | List all Services that use a certain PersonalizationScript.
[**modifyPersoScriptAndOverwriteBinary**](PersonalizationScriptsApi.md#modifyPersoScriptAndOverwriteBinary) | **PUT** /personalization-scripts/{personalizationScriptId} | Update details and overwrite binary data of an existing Personali...

<a name="createPersoScriptAndUploadBinary"></a>
# **createPersoScriptAndUploadBinary**
> PersonalizationScript createPersoScriptAndUploadBinary(scriptFilename, scriptFile)

Create a new PersonalizationScript and upload corresponding binary...

Create a new PersonalizationScript and upload corresponding binary data.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.PersonalizationScriptsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


PersonalizationScriptsApi apiInstance = new PersonalizationScriptsApi();
String scriptFilename = "scriptFilename_example"; // String | 
File scriptFile = new File("scriptFile_example"); // File | 
try {
    PersonalizationScript result = apiInstance.createPersoScriptAndUploadBinary(scriptFilename, scriptFile);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PersonalizationScriptsApi#createPersoScriptAndUploadBinary");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **scriptFilename** | **String**|  |
 **scriptFile** | **File**|  |

### Return type

[**PersonalizationScript**](PersonalizationScript.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="deletePersoScript"></a>
# **deletePersoScript**
> deletePersoScript(personalizationScriptId)

Delete a certain PersonalizationScript. All data, including binary...

Delete a certain PersonalizationScript. All data, including binary data, is deleted. Deletion SHALL only be possible if the PersonalizationScript is not referenced in any ApplicationConfig and thus is not in use anywhere.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.PersonalizationScriptsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


PersonalizationScriptsApi apiInstance = new PersonalizationScriptsApi();
UUID personalizationScriptId = new UUID(); // UUID | identifier of the referred PersonalizationScript
try {
    apiInstance.deletePersoScript(personalizationScriptId);
} catch (ApiException e) {
    System.err.println("Exception when calling PersonalizationScriptsApi#deletePersoScript");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **personalizationScriptId** | [**UUID**](.md)| identifier of the referred PersonalizationScript |

### Return type

null (empty response body)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getPersoScript"></a>
# **getPersoScript**
> PersonalizationScript getPersoScript(personalizationScriptId)

Get details of a certain PersonalizationScript.

Get details of a certain PersonalizationScript.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.PersonalizationScriptsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


PersonalizationScriptsApi apiInstance = new PersonalizationScriptsApi();
UUID personalizationScriptId = new UUID(); // UUID | identifier of the referred PersonalizationScript
try {
    PersonalizationScript result = apiInstance.getPersoScript(personalizationScriptId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PersonalizationScriptsApi#getPersoScript");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **personalizationScriptId** | [**UUID**](.md)| identifier of the referred PersonalizationScript |

### Return type

[**PersonalizationScript**](PersonalizationScript.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getScriptBinary"></a>
# **getScriptBinary**
> File getScriptBinary(personalizationScriptId)

Get binary data of a certain PersonalizationScript.

Get binary data of a certain PersonalizationScript.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.PersonalizationScriptsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


PersonalizationScriptsApi apiInstance = new PersonalizationScriptsApi();
UUID personalizationScriptId = new UUID(); // UUID | identifier of the referred PersonalizationScript
try {
    File result = apiInstance.getScriptBinary(personalizationScriptId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PersonalizationScriptsApi#getScriptBinary");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **personalizationScriptId** | [**UUID**](.md)| identifier of the referred PersonalizationScript |

### Return type

[**File**](File.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/octet-data, application/json

<a name="listPersoScripts"></a>
# **listPersoScripts**
> List&lt;PersonalizationScript&gt; listPersoScripts()

List all PersonalizationScripts of the authenticated ServiceProvider.

List all PersonalizationScripts of the authenticated ServiceProvider.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.PersonalizationScriptsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


PersonalizationScriptsApi apiInstance = new PersonalizationScriptsApi();
try {
    List<PersonalizationScript> result = apiInstance.listPersoScripts();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PersonalizationScriptsApi#listPersoScripts");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;PersonalizationScript&gt;**](PersonalizationScript.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listScriptRelatedAppConfigs"></a>
# **listScriptRelatedAppConfigs**
> List&lt;ApplicationConfig&gt; listScriptRelatedAppConfigs(personalizationScriptId)

List all ApplicationConfigs that use a certain PersonalizationScript.

List all ApplicationConfigs that use a certain PersonalizationScript.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.PersonalizationScriptsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


PersonalizationScriptsApi apiInstance = new PersonalizationScriptsApi();
UUID personalizationScriptId = new UUID(); // UUID | identifier of the referred PersonalizationScript
try {
    List<ApplicationConfig> result = apiInstance.listScriptRelatedAppConfigs(personalizationScriptId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PersonalizationScriptsApi#listScriptRelatedAppConfigs");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **personalizationScriptId** | [**UUID**](.md)| identifier of the referred PersonalizationScript |

### Return type

[**List&lt;ApplicationConfig&gt;**](ApplicationConfig.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listScriptRelatedFlavors"></a>
# **listScriptRelatedFlavors**
> List&lt;Flavor&gt; listScriptRelatedFlavors(personalizationScriptId)

List all Flavors that use a certain PersonalizationScript.

List all Flavors that use a certain PersonalizationScript.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.PersonalizationScriptsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


PersonalizationScriptsApi apiInstance = new PersonalizationScriptsApi();
UUID personalizationScriptId = new UUID(); // UUID | identifier of the referred PersonalizationScript
try {
    List<Flavor> result = apiInstance.listScriptRelatedFlavors(personalizationScriptId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PersonalizationScriptsApi#listScriptRelatedFlavors");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **personalizationScriptId** | [**UUID**](.md)| identifier of the referred PersonalizationScript |

### Return type

[**List&lt;Flavor&gt;**](Flavor.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listScriptRelatedServices"></a>
# **listScriptRelatedServices**
> List&lt;Service&gt; listScriptRelatedServices(personalizationScriptId)

List all Services that use a certain PersonalizationScript.

List all Services that use a certain PersonalizationScript.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.PersonalizationScriptsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


PersonalizationScriptsApi apiInstance = new PersonalizationScriptsApi();
UUID personalizationScriptId = new UUID(); // UUID | identifier of the referred PersonalizationScript
try {
    List<Service> result = apiInstance.listScriptRelatedServices(personalizationScriptId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PersonalizationScriptsApi#listScriptRelatedServices");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **personalizationScriptId** | [**UUID**](.md)| identifier of the referred PersonalizationScript |

### Return type

[**List&lt;Service&gt;**](Service.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="modifyPersoScriptAndOverwriteBinary"></a>
# **modifyPersoScriptAndOverwriteBinary**
> PersonalizationScript modifyPersoScriptAndOverwriteBinary(scriptFilename, scriptFile, personalizationScriptId)

Update details and overwrite binary data of an existing Personali...

Update details and overwrite binary data of an existing PersonalizationScript.

### Example
```java
// Import classes:
//import de.bsi.tsms.tsmrestapi.ApiClient;
//import de.bsi.tsms.tsmrestapi.ApiException;
//import de.bsi.tsms.tsmrestapi.Configuration;
//import de.bsi.tsms.tsmrestapi.auth.*;
//import de.bsi.tsms.tsmrestapi.api.PersonalizationScriptsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


PersonalizationScriptsApi apiInstance = new PersonalizationScriptsApi();
String scriptFilename = "scriptFilename_example"; // String | 
File scriptFile = new File("scriptFile_example"); // File | 
UUID personalizationScriptId = new UUID(); // UUID | identifier of the referred PersonalizationScript
try {
    PersonalizationScript result = apiInstance.modifyPersoScriptAndOverwriteBinary(scriptFilename, scriptFile, personalizationScriptId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PersonalizationScriptsApi#modifyPersoScriptAndOverwriteBinary");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **scriptFilename** | **String**|  |
 **scriptFile** | **File**|  |
 **personalizationScriptId** | [**UUID**](.md)| identifier of the referred PersonalizationScript |

### Return type

[**PersonalizationScript**](PersonalizationScript.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

