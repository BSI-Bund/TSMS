# Flavor

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) | Unique identification of the Flavor. The id shall be used to check the necessity of applet reinstallation during the Version update process, for use cases when the same Flavor is used for different Versions. |  [optional]
**serviceId** | [**UUID**](UUID.md) | ID of the Service owning the Flavor. |  [optional]
**name** | **String** | Name of this Flavor. |  [optional]
**description** | **String** | Additional description for this Flavor. |  [optional]
**creationDate** | [**DateTime**](DateTime.md) | A datetime string (creation of Flavor). |  [optional]
**published** | **Boolean** | A Flavor can only be used for deployment if it is published. Once published, modification of the following attributes is no longer possible -&gt; executableLoadFileIds -&gt; applicationInstantationConfigs Default value is false. The value can be changed to true via the interface method Publish Flavor (see Section 4.1.6.4.15). Once published (value set to true), a Flavor cannot be reversed to an unpublished state again. |  [optional]
**executableLoadFileIds** | [**List&lt;UUID&gt;**](UUID.md) | List of IDs of ELFs used by this Flavor. Modifications are only possible, as long as the Flavor is not yet published. |  [optional]
**applicationInstantiationConfigs** | [**List&lt;ApplicationInstantiationConfig&gt;**](ApplicationInstantiationConfig.md) | List to link and prioritize EMs and ApplicationConfigs. Modifications are only possible, as long as the Flavor is not yet published. |  [optional]
**spParameters** | **Map&lt;String, String&gt;** | Key value definitions used as parameters of a service. Those parameters can be retrieved via TSM-API. For the parameters returned by TSM-API the key value pairs are combined with key values pairs of spParameters contained in the Service definition. For pairs with identical keys, the key value pairs of the given flavor take presence over the corresponding pairs contained in spParameters of the Service definition.. |  [optional]
**featureConfig** | [**FeatureConfig**](FeatureConfig.md) |  |  [optional]
**contextSpecificAttributes** | **Map&lt;String, String&gt;** | Additional context specific configuration settings (e.g. platform specific CSP patch level). Possible options are defined bilateral between SP and TSMS provider. |  [optional]
