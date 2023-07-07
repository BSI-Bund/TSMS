# CertificatesApi

All URIs are relative to */*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createCertAndUploadBinary**](CertificatesApi.md#createCertAndUploadBinary) | **POST** /certificates | Create a new Certificate and upload corresponding binary data.
[**deleteCert**](CertificatesApi.md#deleteCert) | **DELETE** /certificates/{certificateId} | Delete a certain Certificate. All data, including binary data, is...
[**getCert**](CertificatesApi.md#getCert) | **GET** /certificates/{certificateId} | Get details of a certain Certificate.
[**getCertBinary**](CertificatesApi.md#getCertBinary) | **GET** /certificates/{certificateId}/binary | Get binary data of a certain Certificate.
[**listCertRelatedAppConfigs**](CertificatesApi.md#listCertRelatedAppConfigs) | **GET** /certificates/{certificateId}/application-configs | List all ApplicationConfigs that use a certain Certificate.
[**listCertRelatedFlavors**](CertificatesApi.md#listCertRelatedFlavors) | **GET** /certificates/{certificateId}/flavors | List all Flavors that use a certain Certificate.
[**listCertRelatedServices**](CertificatesApi.md#listCertRelatedServices) | **GET** /certificates/{certificateId}/services | List all Services that use a certain Certificate.
[**listCertRelatedSposConfigs**](CertificatesApi.md#listCertRelatedSposConfigs) | **GET** /certificates/{certificateId}/spos-configs | List all SposConfigs that use a certain Certificate.
[**listCerts**](CertificatesApi.md#listCerts) | **GET** /certificates | List all Certificates of the authenticated ServiceProvider.
[**modifyCertAndOverwriteBinary**](CertificatesApi.md#modifyCertAndOverwriteBinary) | **PUT** /certificates/{certificateId} | Update details and overwrite binary data of an existing Certificate.

<a name="createCertAndUploadBinary"></a>
# **createCertAndUploadBinary**
> Certificate createCertAndUploadBinary(certFilename, certFile)

Create a new Certificate and upload corresponding binary data.

Create a new Certificate and upload corresponding binary data.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.CertificatesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


CertificatesApi apiInstance = new CertificatesApi();
String certFilename = "certFilename_example"; // String | 
File certFile = new File("certFile_example"); // File | 
try {
    Certificate result = apiInstance.createCertAndUploadBinary(certFilename, certFile);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CertificatesApi#createCertAndUploadBinary");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **certFilename** | **String**|  |
 **certFile** | **File**|  |

### Return type

[**Certificate**](Certificate.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

<a name="deleteCert"></a>
# **deleteCert**
> deleteCert(certificateId)

Delete a certain Certificate. All data, including binary data, is...

Delete a certain Certificate. All data, including binary data, is deleted. Deletion SHALL only be possible if the Certificate is not referenced in any ApplicationConfig or SposConfig and thus is not in use anywhere.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.CertificatesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


CertificatesApi apiInstance = new CertificatesApi();
UUID certificateId = new UUID(); // UUID | identifier of the referred Certificate
try {
    apiInstance.deleteCert(certificateId);
} catch (ApiException e) {
    System.err.println("Exception when calling CertificatesApi#deleteCert");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **certificateId** | [**UUID**](.md)| identifier of the referred Certificate |

### Return type

null (empty response body)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getCert"></a>
# **getCert**
> Certificate getCert(certificateId)

Get details of a certain Certificate.

Get details of a certain Certificate.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.CertificatesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


CertificatesApi apiInstance = new CertificatesApi();
UUID certificateId = new UUID(); // UUID | identifier of the referred Certificate
try {
    Certificate result = apiInstance.getCert(certificateId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CertificatesApi#getCert");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **certificateId** | [**UUID**](.md)| identifier of the referred Certificate |

### Return type

[**Certificate**](Certificate.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getCertBinary"></a>
# **getCertBinary**
> File getCertBinary(certificateId)

Get binary data of a certain Certificate.

Get binary data of a certain Certificate.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.CertificatesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


CertificatesApi apiInstance = new CertificatesApi();
UUID certificateId = new UUID(); // UUID | identifier of the referred Certificate
try {
    File result = apiInstance.getCertBinary(certificateId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CertificatesApi#getCertBinary");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **certificateId** | [**UUID**](.md)| identifier of the referred Certificate |

### Return type

[**File**](File.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/octet-stream, application/json

<a name="listCertRelatedAppConfigs"></a>
# **listCertRelatedAppConfigs**
> List&lt;ApplicationConfig&gt; listCertRelatedAppConfigs(certificateId)

List all ApplicationConfigs that use a certain Certificate.

List all ApplicationConfigs that use a certain Certificate.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.CertificatesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


CertificatesApi apiInstance = new CertificatesApi();
UUID certificateId = new UUID(); // UUID | identifier of the referred Certificate
try {
    List<ApplicationConfig> result = apiInstance.listCertRelatedAppConfigs(certificateId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CertificatesApi#listCertRelatedAppConfigs");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **certificateId** | [**UUID**](.md)| identifier of the referred Certificate |

### Return type

[**List&lt;ApplicationConfig&gt;**](ApplicationConfig.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listCertRelatedFlavors"></a>
# **listCertRelatedFlavors**
> List&lt;Flavor&gt; listCertRelatedFlavors(certificateId)

List all Flavors that use a certain Certificate.

List all Flavors that use a certain Certificate.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.CertificatesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


CertificatesApi apiInstance = new CertificatesApi();
UUID certificateId = new UUID(); // UUID | identifier of the referred Certificate
try {
    List<Flavor> result = apiInstance.listCertRelatedFlavors(certificateId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CertificatesApi#listCertRelatedFlavors");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **certificateId** | [**UUID**](.md)| identifier of the referred Certificate |

### Return type

[**List&lt;Flavor&gt;**](Flavor.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listCertRelatedServices"></a>
# **listCertRelatedServices**
> List&lt;Service&gt; listCertRelatedServices(certificateId)

List all Services that use a certain Certificate.

List all Services that use a certain Certificate.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.CertificatesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


CertificatesApi apiInstance = new CertificatesApi();
UUID certificateId = new UUID(); // UUID | identifier of the referred Certificate
try {
    List<Service> result = apiInstance.listCertRelatedServices(certificateId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CertificatesApi#listCertRelatedServices");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **certificateId** | [**UUID**](.md)| identifier of the referred Certificate |

### Return type

[**List&lt;Service&gt;**](Service.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listCertRelatedSposConfigs"></a>
# **listCertRelatedSposConfigs**
> List&lt;SposConfig&gt; listCertRelatedSposConfigs(certificateId)

List all SposConfigs that use a certain Certificate.

List all SposConfigs that use a certain Certificate.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.CertificatesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


CertificatesApi apiInstance = new CertificatesApi();
UUID certificateId = new UUID(); // UUID | identifier of the referred Certificate
try {
    List<SposConfig> result = apiInstance.listCertRelatedSposConfigs(certificateId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CertificatesApi#listCertRelatedSposConfigs");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **certificateId** | [**UUID**](.md)| identifier of the referred Certificate |

### Return type

[**List&lt;SposConfig&gt;**](SposConfig.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listCerts"></a>
# **listCerts**
> List&lt;Certificate&gt; listCerts()

List all Certificates of the authenticated ServiceProvider.

List all Certificates of the authenticated ServiceProvider.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.CertificatesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


CertificatesApi apiInstance = new CertificatesApi();
try {
    List<Certificate> result = apiInstance.listCerts();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CertificatesApi#listCerts");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;Certificate&gt;**](Certificate.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="modifyCertAndOverwriteBinary"></a>
# **modifyCertAndOverwriteBinary**
> Certificate modifyCertAndOverwriteBinary(certFilename, certFile, certificateId)

Update details and overwrite binary data of an existing Certificate.

Update details and overwrite binary data of an existing Certificate.

### Example
```java
// Import classes:
//import de.bund.bsi.tsms.tsmrestapi.ApiClient;
//import de.bund.bsi.tsms.tsmrestapi.ApiException;
//import de.bund.bsi.tsms.tsmrestapi.Configuration;
//import de.bund.bsi.tsms.tsmrestapi.auth.*;
//import de.bund.bsi.tsms.tsmrestapi.api.CertificatesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();


CertificatesApi apiInstance = new CertificatesApi();
String certFilename = "certFilename_example"; // String | 
File certFile = new File("certFile_example"); // File | 
UUID certificateId = new UUID(); // UUID | identifier of the referred Certificate
try {
    Certificate result = apiInstance.modifyCertAndOverwriteBinary(certFilename, certFile, certificateId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CertificatesApi#modifyCertAndOverwriteBinary");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **certFilename** | **String**|  |
 **certFile** | **File**|  |
 **certificateId** | [**UUID**](.md)| identifier of the referred Certificate |

### Return type

[**Certificate**](Certificate.md)

### Authorization

[authToken](../README.md#authToken)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

