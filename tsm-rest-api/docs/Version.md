# Version

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**tag** | **String** | Tag of the Version in the format &lt;Major&gt;.&lt;Minor&gt;.&lt;Revision&gt; The tag must be unique for a Service, since tag is used to reference the Version. | 
**allowedDeployments** | [**Map&lt;String, List&lt;String&gt;&gt;**](List.md) | Map, which associates Flavors (see 4.1.4.4) to one or multiple SecureComponentProfiles (see 4.1.4.5). By this, Flavors linked to this Version are assigned to concrete hardware platforms as 1-n association. The key of the map contains the FlavorId. The value of the map is a list of SecureComponentProfileIds. Specific FlavorIds and SecureComponentProfileIds can only be used once inside a Version, i.e., inside a Version it is not supported to assign a SecureComponentProfile to multiple Flavors or vice versa. | 
