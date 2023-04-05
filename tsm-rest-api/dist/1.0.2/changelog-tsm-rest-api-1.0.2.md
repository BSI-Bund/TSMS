# Changelog
Changelog file for BSI tsm-rest-api.

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


