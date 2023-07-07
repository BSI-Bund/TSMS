# Changelog
Changelog file for BSI tsm-rest-api.

## [1.0.4] - 07.07.2023
* modifications to be compliant to BSI TR-03165 v1.0.4:
  * PersonalizationConfig: added attribute certificateId and removed it from PersonalizationScript
  * corrected URL in 4.1.6.5.10 for method list related flavors from /services/{serviceId}/executable-load-files/{elfId}/flavors to /executable-load-files/{elfId}/services/{serviceId}/flavors (#264)
  * corrected URL in 4.1.6.5.12 for method list related versions from /executable-load-files/{elfId}/services/{servideId}/versions to /executable-load-files/{elfId}/services/{serviceId}/versions (#264)
* changed media type "application/octed-data" to "application/octed-stream" (#266)


## [1.0.3] - 26.05.2023
* modifications to be compliant to BSI TR-03165 v1.0.3:
  * applicationConfig: changed installConfig from "Mandatory" to "Optional" (#235)
  * widened scope of Error Category 1002
  * changed API method parameter in linkSecureComponentProfiles from Map<string,string[]> to Map<string,string> (#240)
  * changed parameters spId, serviceId, elfId, certificateId from "Mandatory" to "Optional" (#242)
* removed discriminator from ExecutableLoadFile (#232)
* removed pattern restriction for SecureComponentProfile#osVersion  (#234)
* REST-API methods using ExecutableLoadFile should support Polymorphism (and should accept CAP). Affected methods (#232):
  * GET /secure-component-profiles/{scpId}/elfs
  * GET /services/{serviceId}/flavors/{flavorId}/executable-load-files:
  * GET /executable-load-files
  * POST /executable-load-files
  * GET /executable-load-files/{elfId}
  * PUT /executable-load-files/{elfId}

## [1.0.2] - 05.04.2023
* modifications to be compliant to BSI TR-03165 v1.0.2:
  * renamed attribute cspFull to useCSPFull of FeatureConfig
  * added new attribute keyProvisioningMode to FeatureConfig
  * added new attribute keyIndex to FeatureConfig
  * renamed attribute includeKeyDiversificationData to includeSecurityDomainDiversificationData of PersonalizationConfig
  * added new attribute spId to Service, ELF, ApplicationConfig, PersonalizationScript, Certificate and SposConfig
  * added new attribute serviceId to Version and Flavor
  * added new attribute elfId to EM
  * added new attribute certificateId to ApplicationConfig
* changed maven groupId from de.bsi.tsms to de.bund.bsi.tsms
* changed java package from de.bsi.tsms to de.bund.bsi.tsms
* corrected spell issues
* changed jackson-databind dependency in yaml2json/pom.xml from 2.9.5 to [2.12.6.1,) to address GitHub dependency alerts 

## [1.0.0] - 07.06.2022
* created project
* compliant to BSI TR-03165 v1.0


