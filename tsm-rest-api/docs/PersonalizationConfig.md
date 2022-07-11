# PersonalizationConfig

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**certificateId** | [**UUID**](UUID.md) | ID of the Certificate, if needed for a PersonalizationScript. |  [optional]
**personalizationScriptId** | [**UUID**](UUID.md) | ID of the PersonalizationScript used for personalization. |  [optional]
**provideAttestationToken** | **Boolean** | Flag, whether an AttestationToken shall be included in the application specific install parameters. If omitted, default value false will be used. |  [optional]
**includeKeyDiversificationData** | **Boolean** | Flag, whether Key Diversification Data shall be included in the Attestation Token. Can only be applied if provideAttestationToken is true. If omitted, default value false will be used. |  [optional]
