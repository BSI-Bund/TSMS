package de.bund.bsi.tsms.tsmapi.results;

import de.bund.bsi.tsms.tsmapi.EDeploymentAvailable;
import de.bund.bsi.tsms.tsmapi.EErrorType;

/**
 * This class defines the result of the TSM-API method
 * {@link de.bund.bsi.tsms.tsmapi.ITsmApiService#checkServiceDeploymenAvailable}.
 *
 * @since 1.0
 */
public interface IServiceDeploymentAvailableResult {

    /**
     * Result of the deployment available check. One of:
     * <ul>
     * <li>{@link EDeploymentAvailable#DEVICE_NOT_ELIGIBLE}</li>
     * <li>{@link EDeploymentAvailable#DEPLOYMENT_AVAILABLE}</li>
     * </ul>
     *
     * @return Deployment available result.
     */
    EDeploymentAvailable getDeploymentAvailable();

    /**
     * Returns the version of the Service available for deployment. Either the
     * version explicitly requested by the function call, or the semantically
     * highest applicable version if a RegExp pattern is used.<br>
     * <br>
     * Is empty if device is not eligible, if no Version is available, or if
     * executionStatus &gt; 0.
     *
     * @return Version available for deployment.
     */
    String getVersion();

    /**
     * Returns the information about the underlying platform (JavaCard version, CSP
     * version, etc.) and list of all parameters defined (by spParameters) for the
     * Service or overwritten via certain Flavor in the TSM-Backend.<br>
     *
     * @return Technical information about underlying platform.
     */
    ITechnicalInformation getNewTechnicalInformation();

    /**
     * An integer value indicating the process execution result.<br>
     * <br>
     * Possible values are:<br>
     * <br>
     * <ul>
     * <li>0: Successful Execution</li>
     * <li>&gt;0: error, see {@link EErrorType}</li>
     * </ul>
     *
     * @return Execution result status.
     */
    EErrorType getExecutionStatus();

    /**
     * Returns an execution message, see {@link EErrorType}.
     *
     * @return Error message, in case an error occurred, otherwise empty string.
     */
    String getExecutionMessage();
}
