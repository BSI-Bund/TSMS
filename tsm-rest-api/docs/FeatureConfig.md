# FeatureConfig

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**useCspFull** | **Boolean** | True if a CSP available on the SC is supported and configured by the TSM, so that the CSP can be used by the applets installed with this Service, false otherwise. If omitted, default value false will be used. |  [optional]
**genericOptions** | **Map&lt;String, Boolean&gt;** | Possibility to configure further feature options (e.g. key-agreement-algorithm, message-digest). If omitted, default value empty list will be used. |  [optional]
**keyProvisioningMode** | **Integer** | The type of the key provisioning mode for the security domain of the Service. One of -&gt; 0 None -&gt; 1 BASIC_DIVERSIFIED_CREATE -&gt; 2 BASIC_CREATE -&gt; 3 BASIC_RANDOM_CREATE If provideAttestationToken of at least one corresponding PersonalizationConfig is set to true, the keyProvisioningMode must be one of 1, 2 or 3. If no Attestation Token is configured, all available options 0 - 3 can be used. If omitted, default value 0 will be used. |  [optional]
**keyIndex** | **String** | A key index for provisioning of the security domain of the Service. Depending on the keyProvisioningMode the keyIndex represents either the master key index when used in mode 1 (Basic Diversified Created) or it represents the transport key index when used in mode 2 (Basic Create) and 3 (Basic Random Create). If keyProvisioningMode is one of 1, 2 or 3, a keyIndex must be provided. If keyProvisioningMode is set to 0, keyIndex must be empty. If omitted, default value empty string will be used. |  [optional]
