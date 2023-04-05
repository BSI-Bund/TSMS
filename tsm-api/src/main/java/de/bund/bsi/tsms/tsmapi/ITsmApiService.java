package de.bund.bsi.tsms.tsmapi;

import de.bund.bsi.tsms.tsmapi.parameters.IServiceCommand;
import de.bund.bsi.tsms.tsmapi.results.ICreateServiceInstanceResult;
import de.bund.bsi.tsms.tsmapi.results.IGetServiceInstancesResult;
import de.bund.bsi.tsms.tsmapi.results.IDeployServiceResult;
import de.bund.bsi.tsms.tsmapi.results.IUpdateServiceResult;
import de.bund.bsi.tsms.tsmapi.results.ISuspendOrResumeResult;
import de.bund.bsi.tsms.tsmapi.results.ITerminateServiceResult;
import de.bund.bsi.tsms.tsmapi.results.IServiceDeploymentAvailableResult;
import de.bund.bsi.tsms.tsmapi.results.IServiceUpdateAvailableResult;
import de.bund.bsi.tsms.tsmapi.results.ISetCustomAccessTokenResult;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * The TSM-API is an interface that provides methods to trigger life-cycle
 * management processes of secure applications on secure components.<br>
 * <br>
 * The general flow of interaction with the TSM-API for life-cycle management is
 * as follows:
 * <ol>
 * <li>A SP app calls a function of the TSM-API for life-cycle management. The
 * SP app specifies the Service Instance on which the life-cycle state transfer
 * shall be performed and the parameters that are necessary for the
 * execution.</li>
 * <li>If the request is valid, the TSM-API submits a request to the
 * TSM-Backend. As soon as the process execution starts, the TSM-API sends a
 * callback with a ProcessStart message to the SP app.</li>
 * <li>During the process execution, the TSM-API sends one or more callbacks
 * with a ProcessProgress message containing information about the progress of
 * the process execution, which can be used to display status information to a
 * user.</li>
 * <li>After the execution of the process has finished, the TSM-API function
 * call provides a result message to the SP app that contains the execution
 * status of the process (success or failure) and may contain specific result
 * data.</li>
 * </ol>
 * <br>
 * If an error occurs at some point after the SP app has made a request and
 * before the process execution has started, the TSM-API returns a result with
 * an empty Process ID and status “Not executed” to the SP app that has made the
 * request.
 *
 * @since 1.0
 */
public interface ITsmApiService {

    /**
     * Creates a Service Instance that is used for life-cycle management.<br>
     * <br>
     * This method instantiates a Service on a SC of the device which is resulting
     * from the eligibility check performed as part of the operation. The
     * instantiation creates a new Service Instance which is associated with the SC
     * on which the Service with the given id and version is eligible for
     * deployment. The result of the performed eligibility check remains valid until
     * the Service is terminated.<br>
     * <br>
     * Note, however, this method does not include the execution of any service
     * commands for deployment. These need to be performed by subsequent
     * deployService (and updateService) invocations. The Service Instance ID
     * created with this method can be retrieved via the getServiceInstances method
     * and must be used in all other interface methods to address the Service for
     * this handset. In case of multiple available Secure Components on the device,
     * the TSM chooses one eligible Secure Component. Hereby, the TSM uses a
     * decision matrix which will always have the same result of the same set of
     * accessible Secure Components.<br>
     * <br>
     * Once a TSM created a Service Instance for a certain Service, the Service
     * Instance ID for this concrete Service on this concrete handset will be always
     * the same, until the Service Instance is terminated. After the Termination of
     * a Service Instance, this method must be used to create a new Service Instance
     * ID.<br>
     * <br>
     * In this way, a Service provisioned prior to a reinstallation of the SP app
     * can be checked for its life-cycle state. With this, secure applications can
     * be completed, updated, used or removed after a reinstallation of the
     * corresponding SP app during any life-cycle state.
     *
     * @param serviceId
     *            Service for which a new service instance shall be created.
     * @param version
     *            Version of the Service that shall be instantiated and for an
     *            eligible SC is determined.
     * @return Object containing execution results.
     */
    ICreateServiceInstanceResult createServiceInstance(String serviceId, String version);

    /**
     * Checks for all Secure Components that can be accessed at the time of the
     * request whether Service Instances for a specific Service already exists. All
     * Service Instances found are returned in the form of a list. This function can
     * be used to check whether a Service Instance has already been provided on one
     * of the available SCs. If no Service Instance exists for this specific device,
     * an empty list is returned.<br>
     * <br>
     * The IDs of the Service Instances returned by this method are used in all
     * other interface methods to address specific instances. If no Service Instance
     * exists, the SP may create a new Service Instance via the
     * createServiceInstance method.
     *
     * @param serviceId
     *            Service for which the existence of Service Instances shall be
     *            checked.
     * @return Object containing execution results.
     */
    IGetServiceInstancesResult getServiceInstances(String serviceId);

    /**
     * Deploy a Service and transfer the Service Instance to the desired state. All
     * parts of the secure application (e.g. multiple Java Card Applets, if
     * applicable) are deployed on the device.<br>
     * <br>
     * This method is used to deploy a Service Instance for the first time. If a
     * Service Instance is already deployed on the device, the method will fail and
     * the #updateService method shall be used in this case. This means the method
     * will only be successful, when the Service Instance is at least in one of the
     * following states:
     * <ul>
     * <li>{@link EServiceInstanceState#NOT_DEPLOYED}</li>
     * <li>{@link EServiceInstanceState#INSTALLED}</li>
     * <li>{@link EServiceInstanceState#ACTIVATED}</li>
     * <li>{@link EServiceInstanceState#PERSONALIZED}</li>
     * </ul>
     * <br>
     * A SP app can specify the ServiceCommands for the deployment. Depending on the
     * needs of a SP app, either the Install, Activate or Personalize commands can
     * be executed for deployment. It is also possible to execute all the commands
     * in the same request or to skip them partly. Usually, one to three unique
     * service commands are present. But it is also possible to provide an empty
     * list of service commands to finalize a deployment later. Common for all
     * options is that they will fail, if the Service does not match the life-cycle.
     * This means the order of ServiceCommands determines the order in which the
     * associated life-cycle states shall be reached. For example: a SP may request
     * the installation, personalization and activation of a Service Instance in a
     * single request or perform the installation and personalization in one request
     * and then separately request the activation and finalization of the
     * deployment. The following combinations are supported: <br>
     * <ul>
     * <li>{@link EServiceInstanceState#NOT_DEPLOYED}: [
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IInstallServiceCommand},
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IActivateServiceCommand},
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IPersonalizeServiceCommand} ]</li>
     * <li>{@link EServiceInstanceState#NOT_DEPLOYED}: [
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IInstallServiceCommand},
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IActivateServiceCommand} ]</li>
     * <li>{@link EServiceInstanceState#NOT_DEPLOYED}: [
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IInstallServiceCommand},
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IPersonalizeServiceCommand},
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IActivateServiceCommand} ]</li>
     * <li>{@link EServiceInstanceState#NOT_DEPLOYED}: [
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IInstallServiceCommand},
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IPersonalizeServiceCommand} ]</li>
     * <li>{@link EServiceInstanceState#NOT_DEPLOYED}: [
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IInstallServiceCommand} ]</li>
     * <li>{@link EServiceInstanceState#INSTALLED}: [
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IActivateServiceCommand},
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IPersonalizeServiceCommand} ]</li>
     * <li>{@link EServiceInstanceState#INSTALLED} or
     * {@link EServiceInstanceState#PERSONALIZED}: [
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IActivateServiceCommand} ]</li>
     * <li>{@link EServiceInstanceState#INSTALLED}: [
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IPersonalizeServiceCommand} ,
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IActivateServiceCommand} ]</li>
     * <li>{@link EServiceInstanceState#ACTIVATED}: [
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IPersonalizeServiceCommand} ]</li>
     * <li>{@link EServiceInstanceState#ACTIVATED} or
     * {@link EServiceInstanceState#PERSONALIZED}: []</li>
     * </ul>
     *
     * @param serviceInstanceId
     *            Denotes the service instance for which deployment is requested.
     * @param serviceCommands
     *            A list of service commands to be executed list must contain at
     *            least 0 or maximum 3 service command entries of:
     *            <ul>
     *            <li>{@link de.bund.bsi.tsms.tsmapi.parameters.IInstallServiceCommand}</li>
     *            <li>{@link de.bund.bsi.tsms.tsmapi.parameters.IPersonalizeServiceCommand}</li>
     *            <li>{@link de.bund.bsi.tsms.tsmapi.parameters.IActivateServiceCommand}</li>
     *            </ul>
     *            The order of the commands depends on the live-cycle state of the
     *            Service Instance.
     * @param finalizeDeployment
     *            Indicates whether the service deployment or update shall be
     *            finalized with the successful execution of the last command.
     * @param listener
     *            Callback object; may be null.
     * @return A proxy object indicating deferred execution which will be fulfilled
     *         when the expected {@link IDeployServiceResult} response is available.
     */
    CompletableFuture<IDeployServiceResult> deployService(String serviceInstanceId,
            List<IServiceCommand> serviceCommands, boolean finalizeDeployment,
            ITsmProcessListener listener);

    /**
     * Update a Service Instance to the specified Version. Updating a Service is in
     * most parts identical to deploying a Service. The only difference is that an
     * update will remove a previously installed Service Instance before installing
     * the new Version. The SP app must be aware that, depending on capabilities of
     * the SC and the secure application, e.g. support of Global Platform
     * Specification Amendment H, the update might lead to data loss.<br>
     * <br>
     * If applicable, the TSM will check parts of the secure application (e.g.
     * multiple Java Card Applets) separately for modifications. A Service Instance
     * will only be removed and reinstalled, either if its binary data changed or if
     * configurations and parameters of the install, activate or personalize
     * commands changed. To prevent data loss during the update (see above), a SP
     * may want to use a separate part of the secure application just for data
     * management that is not modified during updates.<br>
     * <br>
     * The method will fail if no Service Instance is deployed, i.e. if install,
     * activate or personalize commands from the deployService method are not
     * finished yet. This means the method will only be successful, when the Service
     * Instance is in one of the following states:
     * <ul>
     * <li>{@link EServiceInstanceState#OPERATIONAL}</li>
     * <li>{@link EServiceInstanceState#SUSPENDED}</li>
     * </ul>
     * <br>
     * The method will fail if the requested Version of the service is already
     * installed.<br>
     * <br>
     * For the service commands, the same restrictions as described in the
     * {@link #deployService} method are valid.
     *
     * @param serviceInstanceId
     *            Denotes the Service Instance for which the update is requested.
     * @param newVersion
     *            The new Version to which the existing Service Instance shall be
     *            updated.
     * @param serviceCommands
     *            A list of service commands to be executed list must contain at
     *            least 0 or maximum 3 service command entries of:
     *            <ul>
     *            <li>{@link de.bund.bsi.tsms.tsmapi.parameters.IInstallServiceCommand}</li>
     *            <li>{@link de.bund.bsi.tsms.tsmapi.parameters.IPersonalizeServiceCommand}</li>
     *            <li>{@link de.bund.bsi.tsms.tsmapi.parameters.IActivateServiceCommand}</li>
     *            </ul>
     *            The order of the commands depends on the live-cycle state of the
     *            Service Instance.
     * @param finalizeDeployment
     *            Indicates whether the service deployment or update shall be
     *            finalized with the successful execution of the last command.
     * @param listener
     *            Callback object; may be null.
     * @return A proxy object indicating deferred execution which will be fulfilled
     *         when the expected {@link IUpdateServiceResult} response is available.
     */
    CompletableFuture<IUpdateServiceResult> updateService(String serviceInstanceId,
            String newVersion, List<IServiceCommand> serviceCommands, boolean finalizeDeployment,
            ITsmProcessListener listener);

    /**
     * Suspend or resume a Service Instance. This method is used to suspend or
     * resume a deployed Service Instance. If this method called with no Service
     * deployed on the handset, it will fail (executionStatus &gt; 0). The method
     * will not fail, if a Service Instance is s suspended repeatedly in a row or
     * resumed repeatedly in a row. This means the method will only be successful,
     * when the Service Instance is in one of the following states:
     * <ul>
     * <li>{@link EServiceInstanceState#OPERATIONAL}</li>
     * <li>{@link EServiceInstanceState#SUSPENDED}</li>
     * </ul>
     *
     * @param serviceInstanceId
     *            Denotes the Service Instance for which the suspension/resumption
     *            is requested.
     * @param suspensionControl
     *            Indicates whether the deployed Service Instance shall be
     *            transferred to Active or Suspended state.<br>
     *            If true, the Service shall be suspended.<br>
     *            If false, the Service shall be resumed.
     * @param listener
     *            Callback object; may be null.
     * @return A proxy object indicating deferred execution which will be fulfilled
     *         when the expected {@link ISuspendOrResumeResult} response is
     *         available.
     */
    CompletableFuture<ISuspendOrResumeResult> suspendOrResumeService(String serviceInstanceId,
            boolean suspensionControl, ITsmProcessListener listener);

    /**
     * Uninstall a Service Instance. Removes all installation files on the
     * handset.<br>
     * <br>
     * This method is used to terminate a Service Instance and to invalidate the
     * Service Instance ID.<br>
     * <br>
     * Under rare circumstances, the Service Instance ID may also get invalidated
     * while processInfo.executionStatus returns a value greater than zero. This
     * means fetching a new Service Instance ID might be necessary even though the
     * terminate request was not successful.<br>
     * <br>
     * If terminate is called on a device where no Service is installed, the method
     * will still finish successfully and the Service Instance ID gets invalidated.
     * The method can be used for all Service Instance states.
     *
     * @param serviceInstanceId
     *            Denotes the Service Instance whose termination is requested.
     * @param listener
     *            Callback object; may be null.
     * @return A proxy object indicating deferred execution which will be fulfilled
     *         when the expected {@link ITerminateServiceResult} response is
     *         available.
     */
    CompletableFuture<ITerminateServiceResult> terminateService(String serviceInstanceId,
            ITsmProcessListener listener);

    /**
     * Check if there is at least one secure component present on the handset for
     * which an applicable Service deployment is available. A deployment is
     * considered applicable if the SP has provided at least one entry in the
     * allowedDeployments of the requested Version that maps a Flavor to a
     * SecureComponentProfile instantiated by the secure components present on the
     * handset.<br>
     * <br>
     * This method is used to estimate the eligibility of a device for the
     * deployment of the indicated Service. The result might be invalidated when a
     * full eligibility check is conducted via createServiceInstance. This is due to
     * the fact that a device check may operate on a subset of the relevant
     * eligibility check parameters, in order to provide a lightweight check before
     * deciding whether or not a Service shall be instantiated on a device.<br>
     * <br>
     * This method does not evaluate the lifecycle state of any currently available
     * installation. This means it will not consider if a Service Instance is
     * already present on the device nor will it check its current state. In
     * particular, DEPLOYMENT_AVAILABLE may be returned even though a Service
     * Instance is already present, in which case a full eligibility check will fail
     * if a deployment is requested without a prior termination of the existing
     * Service Instance.<br>
     * <br>
     * The check can either be used to retrieve the latest available and applicable
     * version or to check if a certain version of a Service can be deployed. This
     * is realized with a RegExp pattern where some digits of the provided version
     * can be replaced with a placeholder ‘x’ to check for a semantically higher
     * deployable version.<br>
     * <br>
     * If the device is not supported, e.g. if no SC exists or if no Flavor is
     * applicable for the device, the returned version will be empty, but the method
     * will still be successful (executionStatus=0).<br>
     *
     * @param serviceId
     *            Service for which the availability of a deployment shall be
     *            checked.
     * @param serviceVersionTag
     *            Version of the Service that shall be checked. Can be either a
     *            concrete version or a RegExp pattern to retrieve the semantically
     *            higher version that is available for deployment. The following
     *            combinations are supported (1 fixed integers, x variable integer):
     *            <ul>
     *            <li>a.b.c (fixed version, e.g. 1.3.0)</li>
     *            <li>a.b.x (patch level equal or higher than 0, but major and minor
     *            version level fixed, e.g. 1.3.x)</li>
     *            <li>a.x.x (patch level and minor version level equal or higher
     *            than 0, but major version level fixed, e.g. 1.x.x)</li>
     *            <li>x.x.x (any version)</li>
     *            </ul>
     *            The special char “x” can be uppercase and/or lowercase. Other
     *            combinations are not supported, e.g.
     *            <ul>
     *            <li>x.1.x (unsupported)</li>
     *            <li>x.x.1 (unsupported)</li>
     *            <li>1.x (unsupported)</li>
     *            <li>1 (unsupported)</li>
     *            <li>x (unsupported)</li>
     *            <li>null / empty (unsupported)</li>
     *            </ul>
     * @return A proxy object indicating deferred execution which will be fulfilled
     *         when the expected {@link IServiceDeploymentAvailableResult} response
     *         is available.
     */
    CompletableFuture<IServiceDeploymentAvailableResult> checkServiceDeploymenAvailable(
            String serviceId, String serviceVersionTag);

    /**
     * Check if there is an applicable update for the specified Service Instance. An
     * update is considered applicable if the SP has provided at least one entry in
     * the allowedDeployments of the requested Version that maps a Flavor to the
     * SecureComponentProfile instantiated by the secure component on which the
     * Service Instance is created.<br>
     * <br>
     * This method is used to estimate the eligibility of a device for the
     * deployment of the indicated Service. It is in many regards similar to the
     * method Check Service Deployment Available, but it requires a Service Instance
     * to be already instantiated on the device and restricts the check to the SC
     * where this Service Instance is instantiated. However, the method does not
     * evaluate the lifecycle state of the Service Instance.<br>
     * <br>
     * The check can either be used to retrieve the latest available and applicable
     * version or to check if a certain version of a Service can be deployed on the
     * particular SC. This is realized with a RegExp pattern where some digits of
     * the provided version can be replaced with a placeholder ‘x’ to check for a
     * semantically higher deployable version. If the requested version (explicitly
     * or via RegExp pattern) is equal or lower to the version of the current
     * Service Instance, the method will respond with NO_UPDATE_AVAILABLE, but will
     * still be successful (executionStatus=0).<br>
     * <br>
     * Even with a higher version of the service available (and thus an update
     * available), the Flavor of the current Service Instance and the Flavor of the
     * future Service Instance may not differ. In this case, depending on the
     * motivation for the update, a SP may decide not to perform an update. A SP can
     * check for this situation by comparing the FlavorIds of the current and the
     * future Service Instance prior to an update.
     *
     * @param serviceInstanceId
     *            Service Instance for which the availability of an update shall be
     *            checked.
     * @param newVersion
     *            Version of the Service that shall be checked. Can be either a
     *            concrete version or a RegExp pattern to retrieve the semantically
     *            higher version that is available for deployment. The following
     *            combinations are supported (1 fixed integers, x variable integer):
     *            <ul>
     *            <li>a.b.c (fixed version, e.g. 1.3.0)</li>
     *            <li>a.b.x (patch level equal or higher than 0, but major and minor
     *            version level fixed, e.g. 1.3.x)</li>
     *            <li>a.x.x (patch level and minor version level equal or higher
     *            than 0, but major version level fixed, e.g. 1.x.x)</li>
     *            <li>x.x.x (any version)</li>
     *            </ul>
     *            The special char “x” can be uppercase and/or lowercase. Other
     *            combinations are not supported, e.g.
     *            <ul>
     *            <li>x.1.x (unsupported)</li>
     *            <li>x.x.1 (unsupported)</li>
     *            <li>1.x (unsupported)</li>
     *            <li>1 (unsupported)</li>
     *            <li>x (unsupported)</li>
     *            <li>null / empty (unsupported)</li>
     *            </ul>
     * @return A proxy object indicating deferred execution which will be fulfilled
     *         when the expected {@link IServiceUpdateAvailableResult} response is
     *         available.
     */
    CompletableFuture<IServiceUpdateAvailableResult> checkServiceUpdateAvailable(
            String serviceInstanceId, String newVersion);

    /**
     * The TSM-API-SDK SHOULD have a default implementation to authenticate
     * communication-requests with external servers like TSM-Backend. This means,
     * all API methods ({@link #getServiceInstances}, {@link #deployService} etc.)
     * are authenticated bia built-in access control, also when the SP does not
     * provide a custom access token.<br>
     * <br>
     * In case an SP does not want to use the default built-in authentication
     * mechanism, the method setCustomAccessToken can be used to set a custom access
     * token. The SDK uses this long-term token to create a short-term bearer token
     * for each a method call to external servers.
     *
     * @param token
     *            The new token.
     * @return A proxy object indicating deferred execution which will be fulfilled
     *         when the expected {@link ISetCustomAccessTokenResult} response is
     *         available.
     */
    CompletableFuture<ISetCustomAccessTokenResult> setCustomAccessToken(String token);

}
