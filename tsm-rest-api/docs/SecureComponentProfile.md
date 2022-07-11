# SecureComponentProfile

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) | Unique identification of the Secure Component Profile assigned automatically by TSMS. |  [optional]
**name** | **String** | Name of the SecureComponentProfile. | 
**scType** | **Integer** | Secure component type. One of -&gt; 1 EMBEDDED_SE -&gt; 2 EMBEDDED_UICC -&gt; 3 REMOVABLE_EUICC -&gt; 4 UICC | 
**hardwarePlatform** | **String** | Name of hardware platform / chip. Sample values are P62G98, S9FD2EE. | 
**os** | **String** | Secure component operating system name, e.g. GTO, JCOP. | 
**osVersion** | **String** | Version of operating system with vendor specific encoding. Sample values are 4.7, 3.1. | 
**javaCardVersion** | **String** | JavaCard version. Sample values are 3.0.5, 3.0.4. | 
**javaCardFeatures** | [**Map&lt;String, List&lt;String&gt;&gt;**](List.md) | Features provided by the JavaCard. The key contains the name of the feature. The value contains a list of supported algorithm for each feature. Sample keys are -&gt; cypher -&gt; signature -&gt; messageDigest -&gt; randomData -&gt; keyBuilder -&gt; keyAgreement -&gt; checksum -&gt; keyPairAlgRsaOnCardGeneration -&gt; keyPairAlgRsaCrtOnCardGeneration -&gt; keyPairAlgDsaOnCardGeneration -&gt; keyPairAlgEcF2MOnCardGeneration -&gt; keyPairAlgEcFpOnCardGeneration -&gt; aeadCipher | 
**gpSpecVersions** | **Map&lt;String, String&gt;** | Global Platform Specification versions. The key contains an identifier for the GP specification document. The value contains the version for each specification. Sample keys are -&gt; card -&gt; contactlessServices -&gt; scp03 -&gt; executableLoadFileUpgrade Sample values are 2.3.1, 2.3, 2.2.1. | 
**gpApiVersions** | **Map&lt;String, String&gt;** | GlobalPlatform API versions. The key contains an identifier for the GP API. The value contains the version for each API. Sample keys are -&gt; card -&gt; contactless -&gt; elfUpgrade Sample values are 1.7, 1.6. | 
**csp** | **Map&lt;String, String&gt;** | Supported CSP. Empty when no CSP is available. Key contains an identifier for additional information about the CSP. Value contains the additional CSP information. Sample keys are -&gt; apiVersion -&gt; vendor | 
**certifications** | **Map&lt;String, String&gt;** | Platform certification level. The key contains the identifier of the certification. The value contains the link to the letter of approval. Sample keys are -&gt; BSI-CC-PP-0084-2014 -&gt; BSI-CC-PP-0089-2015 -&gt; BSI-CC-PP-0099-2017 -&gt; BSI-CC-PP-0100-2018 -&gt; BSI-CC-PP-0104-2019 -&gt; BSI-CC-PP-0117-2022 | 
