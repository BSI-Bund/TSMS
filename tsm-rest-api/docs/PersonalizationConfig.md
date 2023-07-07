# PersonalizationConfig

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**personalizationScriptId** | [**UUID**](UUID.md) | ID of the PersonalizationScript used for personalization. |  [optional]
**certificateId** | [**UUID**](UUID.md) | ID of the Certificate used in this PersonalizationConfig. Empty, if no Certificate is used. |  [optional]
**provideAttestationToken** | **Boolean** | Flag, whether an AttestationToken shall be included in the application specific install parameters. If omitted, default value false will be used. When set to true, all corresponding FeatureConfigs must configure keyProvisioningMode and keyIndex. |  [optional]
**includeSecurityDomainDiversificationData** | **Boolean** | Flag, whether MasterKeyIndex and Key Diversification Data used for a Basic Diversified Create of the Service Security Domain shall be included in the Attestation Token. Can only be applied if -&gt; provideAttestationToken is true and -&gt; FeatureConfig.keyProvisioningMode is BASIC_DIVERSIFIED_CREATE and -&gt; FeatureConfig.keyIndex is not empty If omitted, default value false will be used. |  [optional]
