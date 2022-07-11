# ApplicationInstantiationConfig

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**priority** | **Integer** | Priority, which specifies the order in which an application should be instantiated. Must be in the range from 1 to 255. A lower value means a higher priority. Default value is 255. |  [optional]
**executableModuleId** | [**UUID**](UUID.md) | ID of the EM the referenced ApplicationConfig shall be applied to. | 
**applicationConfigId** | [**UUID**](UUID.md) | ID of the ApplicationConfig that shall be applied to the referenced EM. | 
