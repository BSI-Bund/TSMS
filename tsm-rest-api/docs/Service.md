# Service

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) | Unique identification of the Service. |  [optional]
**spId** | [**UUID**](UUID.md) | ID of the ServiceProvider owning the Service. | 
**name** | **String** | Name of the Service. | 
**creationDate** | [**DateTime**](DateTime.md) | A datetime string (creation of Service). |  [optional]
**sdAid** | **String** | AID of the specific security domain that is created for every Service Instance. The AID format follows [ISO/IEC7816-4]. |  [optional]
**accessAuthorizedDeviceApps** | **List&lt;String&gt;** | List of apps for which an access rule is created when an instance of this Service is activated. |  [optional]
**sposConfigId** | [**UUID**](UUID.md) | ID of the SposConfig used for this Service. |  [optional]
**spParameters** | **Map&lt;String, String&gt;** | Key value definitions used as parameters of a Service. Those parameters can be retrieved via TSM-API. The parameters can be overwritten for each Flavor. |  [optional]
