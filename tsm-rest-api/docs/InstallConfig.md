# InstallConfig

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**applicationSpecificInstallParameter** | **String** | List of application specific parameters needed for installation. If omitted, empty application specific parameters will be used. Format &lt;TLV-Structures&gt;. Default value is empty string. |  [optional]
**privileges** | **List&lt;String&gt;** | List of required privileges for an application instance. Subset of -&gt; &#x27;CVMManagement&#x27; -&gt; &#x27;ContactlessSelfActivation&#x27; -&gt; &#x27;GlobalService&#x27; -&gt; &#x27;PrivacyTrusted&#x27; Default value is empty list. |  [optional]
