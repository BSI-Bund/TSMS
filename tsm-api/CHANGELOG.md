# Changelog
Changelog file for BSI tsm-api.

## [1.0.4] - 07.07.2023
* the version of tsm-api (v1.0.4) is identical to v1.0.3, as there were no differences in the TSM-API specifications between BSI-TR-03165 v1.0.3 and v1.0.4
* the version number (v1.0.4) was only created to maintain consistency across BSI-TR-03165, tsm-api, and tsm-rest-api.

## [1.0.3] - 26.05.2023
* renamed method setCustomAccessToken to setAccessToken and added callback strategy for token creation

## [1.0.2] - 05.04.2023
* the version of tsm-api (v1.0.2) is identical to v1.0.1, as there were no differences in the TSM-API specifications between BSI-TR-03165 v1.0.1 and v1.0.2
* the version number (v1.0.2) was only created to maintain consistency across BSI-TR-03165, tsm-api, and tsm-rest-api.

## [1.0.1] - 14.02.2023
* modifications to be compliant to BSI TR-03165 v1.0.1:
  * added new method setCustomAccessToken
  * EErrorTypes: renamed INVALID_REQUEST to INVALID_ARGUMENT
  * EErrorTypes: renamed CONTENT_RELATED_ERROR to SECURE_COMPONENT_ERROR
  * EErrorTypes: renamed INVALID_STATE to NOT_ALLOWED
  * EErrorTypes: added new error types ALREADY_EXISTS, UNAUTHORIZED, ISSUER_ERROR, NOT_FOUND, OVERLOAD_PROTECTION, UNDER_MAINTENANCE
* changed maven groupId and java package from de.bsi.tsms to de.bund.bsi.tsms
* corrected spell issues
* updated maven plugins to latest stable versions
 
## [1.0.0] - 07.06.2022
* created project
* compliant to BSI TR-03165 v1.0
  

