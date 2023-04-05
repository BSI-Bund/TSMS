# ExecutableLoadFilesApi

All URIs are relative to */*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createElfAndUploadBinary**](ExecutableLoadFilesApi.md#createElfAndUploadBinary) | **POST** /executable-load-files | Create a new ExecutableLoadFile and upload corresponding binary d...
[**deleteElf**](ExecutableLoadFilesApi.md#deleteElf) | **DELETE** /executable-load-files/{elfId} | Delete a certain ExecutableLoadFile. All data, including binary d...
[**getElf**](ExecutableLoadFilesApi.md#getElf) | **GET** /executable-load-files/{elfId} | Get details of a certain ExecutableLoadFile.
[**getElfBinary**](ExecutableLoadFilesApi.md#getElfBinary) | **GET** /executable-load-files/{elfId}/binary | Get binary data of a certain ExecutableLoadFile.
[**getEm**](ExecutableLoadFilesApi.md#getEm) | **GET** /executable-load-files/{elfId}/executable-modules/{emId} | Get details of a certain ExecutableModule of a certain Executable...
[**listElfRelatedAppConfigs**](ExecutableLoadFilesApi.md#listElfRelatedAppConfigs) | **GET** /executable-load-files/{elfId}/executable-modules/{emId}/application-configs | Return the ApplicationConfigs that apply to a certain ExecutableM...
[**listElfRelatedSecureComponentProfiles**](ExecutableLoadFilesApi.md#listElfRelatedSecureComponentProfiles) | **GET** /executable-load-files/{elfId}/services/{serviceId}/secure-component-profiles | List all SecureComponentProfiles associated to certain Service th...
[**listElfRelatedServices**](ExecutableLoadFilesApi.md#listElfRelatedServices) | **GET** /executable-load-files/{elfId}/services | List all Services that use a certain ExecutableLoadFile.
[**listElfRelatedVersions**](ExecutableLoadFilesApi.md#listElfRelatedVersions) | **GET** /executable-load-files/{elfId}/services/{servideId}/versions | List all Versions of a certain Service that use a certain Executa...
[**listElfs**](ExecutableLoadFilesApi.md#listElfs) | **GET** /executable-load-files | List all ExecutableLoadFiles of the authenticated ServiceProvider.
[**listEms**](ExecutableLoadFilesApi.md#listEms) | **GET** /executable-load-files/{elfId}/executable-modules | List all ExecutableModules of a certain ExecutableLoadFile.
[**modifyElfAndOverwriteBinary**](ExecutableLoadFilesApi.md#modifyElfAndOverwriteBinary) | **PUT** /executable-load-files/{elfId} | Update details and overwrite binary data of an existing Executabl...

<a name="createElfAndUploadBinary"></a>
# **createElfAndUploadBinary**
> ExecutableLoadFile createElfAndUploadBinary(elfFilename, elfFile)

Create a new ExecutableLoadFile and upload corresponding binary d...

Create a new ExecutableLoadFile and upload corresponding binary data. ELF details and binary must both be provided to create a new ExecutableLoadFile.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ExecutableLoadFilesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ExecutableLoadFilesApi apiInstance = new ExecutableLoadFilesApi();
String elfFilename = "elfFilename_example"; // String | 
File elfFile = new File("elfFile_example"); // File | 
try {
    ExecutableLoadFile result = apiInstance.createElfAndUploadBinary(elfFilename, elfFile);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ExecutableLoadFilesApi#createElfAndUploadBinary");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **elfFilename** | **String**|  |
 **elfFile** | **File**|  |

### Return type

[**ExecutableLoadFile**](ExecutableLoadFile.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="deleteElf"></a>
# **deleteElf**
> deleteElf(elfId)

Delete a certain ExecutableLoadFile. All data, including binary d...

Delete a certain ExecutableLoadFile. All data, including binary data, meta-data, and associated TechnicalRequirements, is deleted. Deletion SHALL only be possible if the ExecutableLoadFile is not referenced in any Flavor and thus is not in use anywhere.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ExecutableLoadFilesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ExecutableLoadFilesApi apiInstance = new ExecutableLoadFilesApi();
UUID elfId = new UUID(); // UUID | identifier of the referred Elf
try {
    apiInstance.deleteElf(elfId);
} catch (ApiException e) {
    System.err.println("Exception when calling ExecutableLoadFilesApi#deleteElf");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **elfId** | [**UUID**](.md)| identifier of the referred Elf |

### Return type

null (empty response body)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getElf"></a>
# **getElf**
> ExecutableLoadFile getElf(elfId)

Get details of a certain ExecutableLoadFile.

Get details of a certain ExecutableLoadFile.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ExecutableLoadFilesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ExecutableLoadFilesApi apiInstance = new ExecutableLoadFilesApi();
UUID elfId = new UUID(); // UUID | identifier of the referred Elf
try {
    ExecutableLoadFile result = apiInstance.getElf(elfId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ExecutableLoadFilesApi#getElf");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **elfId** | [**UUID**](.md)| identifier of the referred Elf |

### Return type

[**ExecutableLoadFile**](ExecutableLoadFile.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getElfBinary"></a>
# **getElfBinary**
> File getElfBinary(elfId)

Get binary data of a certain ExecutableLoadFile.

Get binary data of a certain ExecutableLoadFile.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ExecutableLoadFilesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ExecutableLoadFilesApi apiInstance = new ExecutableLoadFilesApi();
UUID elfId = new UUID(); // UUID | identifier of the referred Elf
try {
    File result = apiInstance.getElfBinary(elfId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ExecutableLoadFilesApi#getElfBinary");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **elfId** | [**UUID**](.md)| identifier of the referred Elf |

### Return type

[**File**](File.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/octet-data, application/json

<a name="getEm"></a>
# **getEm**
> ExecutableModule getEm(elfId, emId)

Get details of a certain ExecutableModule of a certain Executable...

Get details of a certain ExecutableModule of a certain ExecutableLoadFile.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ExecutableLoadFilesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ExecutableLoadFilesApi apiInstance = new ExecutableLoadFilesApi();
UUID elfId = new UUID(); // UUID | identifier of the referred Elf
UUID emId = new UUID(); // UUID | identifier of the referred Em
try {
    ExecutableModule result = apiInstance.getEm(elfId, emId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ExecutableLoadFilesApi#getEm");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **elfId** | [**UUID**](.md)| identifier of the referred Elf |
 **emId** | [**UUID**](.md)| identifier of the referred Em |

### Return type

[**ExecutableModule**](ExecutableModule.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listElfRelatedAppConfigs"></a>
# **listElfRelatedAppConfigs**
> List&lt;ApplicationConfig&gt; listElfRelatedAppConfigs(elfId, emId)

Return the ApplicationConfigs that apply to a certain ExecutableM...

Return the ApplicationConfigs that apply to a certain ExecutableModule.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ExecutableLoadFilesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ExecutableLoadFilesApi apiInstance = new ExecutableLoadFilesApi();
UUID elfId = new UUID(); // UUID | identifier of the referred Elf
UUID emId = new UUID(); // UUID | identifier of the referred Em
try {
    List<ApplicationConfig> result = apiInstance.listElfRelatedAppConfigs(elfId, emId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ExecutableLoadFilesApi#listElfRelatedAppConfigs");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **elfId** | [**UUID**](.md)| identifier of the referred Elf |
 **emId** | [**UUID**](.md)| identifier of the referred Em |

### Return type

[**List&lt;ApplicationConfig&gt;**](ApplicationConfig.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listElfRelatedSecureComponentProfiles"></a>
# **listElfRelatedSecureComponentProfiles**
> List&lt;SecureComponentProfile&gt; listElfRelatedSecureComponentProfiles(elfId, serviceId)

List all SecureComponentProfiles associated to certain Service th...

List all SecureComponentProfiles associated to certain Service that use a certain ExecutableLoadFile. The returned list of SecureComponentProfiles SHALL not contain any duplicate entries.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ExecutableLoadFilesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ExecutableLoadFilesApi apiInstance = new ExecutableLoadFilesApi();
UUID elfId = new UUID(); // UUID | identifier of the referred Elf
UUID serviceId = new UUID(); // UUID | identifier of the referred Service
try {
    List<SecureComponentProfile> result = apiInstance.listElfRelatedSecureComponentProfiles(elfId, serviceId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ExecutableLoadFilesApi#listElfRelatedSecureComponentProfiles");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **elfId** | [**UUID**](.md)| identifier of the referred Elf |
 **serviceId** | [**UUID**](.md)| identifier of the referred Service |

### Return type

[**List&lt;SecureComponentProfile&gt;**](SecureComponentProfile.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listElfRelatedServices"></a>
# **listElfRelatedServices**
> List&lt;Service&gt; listElfRelatedServices(elfId)

List all Services that use a certain ExecutableLoadFile.

List all Services that use a certain ExecutableLoadFile.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ExecutableLoadFilesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ExecutableLoadFilesApi apiInstance = new ExecutableLoadFilesApi();
UUID elfId = new UUID(); // UUID | identifier of the referred Elf
try {
    List<Service> result = apiInstance.listElfRelatedServices(elfId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ExecutableLoadFilesApi#listElfRelatedServices");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **elfId** | [**UUID**](.md)| identifier of the referred Elf |

### Return type

[**List&lt;Service&gt;**](Service.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listElfRelatedVersions"></a>
# **listElfRelatedVersions**
> List&lt;Version&gt; listElfRelatedVersions(elfId, servideId)

List all Versions of a certain Service that use a certain Executa...

List all Versions of a certain Service that use a certain ExecutableLoadFile.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ExecutableLoadFilesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ExecutableLoadFilesApi apiInstance = new ExecutableLoadFilesApi();
UUID elfId = new UUID(); // UUID | identifier of the referred Elf
UUID servideId = new UUID(); // UUID | identifier of the referred Servide
try {
    List<Version> result = apiInstance.listElfRelatedVersions(elfId, servideId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ExecutableLoadFilesApi#listElfRelatedVersions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **elfId** | [**UUID**](.md)| identifier of the referred Elf |
 **servideId** | [**UUID**](.md)| identifier of the referred Servide |

### Return type

[**List&lt;Version&gt;**](Version.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listElfs"></a>
# **listElfs**
> List&lt;ExecutableLoadFile&gt; listElfs()

List all ExecutableLoadFiles of the authenticated ServiceProvider.

List all ExecutableLoadFiles of the authenticated ServiceProvider.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ExecutableLoadFilesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ExecutableLoadFilesApi apiInstance = new ExecutableLoadFilesApi();
try {
    List<ExecutableLoadFile> result = apiInstance.listElfs();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ExecutableLoadFilesApi#listElfs");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;ExecutableLoadFile&gt;**](ExecutableLoadFile.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listEms"></a>
# **listEms**
> List&lt;ExecutableModule&gt; listEms(elfId)

List all ExecutableModules of a certain ExecutableLoadFile.

List all ExecutableModules of a certain ExecutableLoadFile.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ExecutableLoadFilesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ExecutableLoadFilesApi apiInstance = new ExecutableLoadFilesApi();
UUID elfId = new UUID(); // UUID | identifier of the referred Elf
try {
    List<ExecutableModule> result = apiInstance.listEms(elfId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ExecutableLoadFilesApi#listEms");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **elfId** | [**UUID**](.md)| identifier of the referred Elf |

### Return type

[**List&lt;ExecutableModule&gt;**](ExecutableModule.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="modifyElfAndOverwriteBinary"></a>
# **modifyElfAndOverwriteBinary**
> ExecutableLoadFile modifyElfAndOverwriteBinary(elfFilename, elfFile, elfId)

Update details and overwrite binary data of an existing Executabl...

Update details and overwrite binary data of an existing ExecutableLoadFile. The binary of an ExecutableLoadFile can only be replaced, as long it is not yet linked to a published Flavor.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.ExecutableLoadFilesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


ExecutableLoadFilesApi apiInstance = new ExecutableLoadFilesApi();
String elfFilename = "elfFilename_example"; // String | 
File elfFile = new File("elfFile_example"); // File | 
UUID elfId = new UUID(); // UUID | identifier of the referred Elf
try {
    ExecutableLoadFile result = apiInstance.modifyElfAndOverwriteBinary(elfFilename, elfFile, elfId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ExecutableLoadFilesApi#modifyElfAndOverwriteBinary");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **elfFilename** | **String**|  |
 **elfFile** | **File**|  |
 **elfId** | [**UUID**](.md)| identifier of the referred Elf |

### Return type

[**ExecutableLoadFile**](ExecutableLoadFile.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

