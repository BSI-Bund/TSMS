# ApplicationConfig

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) | Unique identification of the ApplicationConfig. |  [optional]
**spId** | [**UUID**](UUID.md) | ID of the ServiceProvider owning the ApplicationConfig. |  [optional]
**instanceAid** | **String** | AID of the running application instance that shall be created from this ApplicationConfig. This AID is also the AID that is used for selecting a selectable application on the SC. The AID format follows [ISO/IEC7816-4]. | 
**name** | **String** | Name of this ApplicationConfig. |  [optional]
**description** | **String** | Description of this ApplicationConfig. |  [optional]
**installConfig** | [**InstallConfig**](InstallConfig.md) |  |  [optional]
**activationConfig** | [**ActivationConfig**](ActivationConfig.md) |  |  [optional]
**personalizationConfig** | [**PersonalizationConfig**](PersonalizationConfig.md) |  |  [optional]
