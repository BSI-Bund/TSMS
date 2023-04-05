package de.bund.bsi.tsms.tsmapi.results;

import de.bund.bsi.tsms.tsmapi.EDeploymentAvailable;
import de.bund.bsi.tsms.tsmapi.EErrorType;

import java.util.Objects;

/**
 * Default implementation of {@link IServiceDeploymentAvailableResult}.
 *
 * @since 1.0
 */
public class ServiceDeploymentAvailableResult implements IServiceDeploymentAvailableResult {

    /**
     * Result of the deployment available check.
     */
    private EDeploymentAvailable deploymentAvailable;
    /**
     * Version of the Service available for deployment. Either the version
     * explicitly requested by the function call, or the semantically highest
     * applicable version if a RegExp pattern is used.
     */
    private String version;
    /**
     * Technical Information of the to be deployed Service Instance. Information
     * about the underlying platform profile and list of all parameters defined for
     * the Service (by spParameters) or overwritten via certain Flavor in the
     * TSM-Backend.
     */
    private ITechnicalInformation newTechnicalInformation;
    /**
     * Error code.
     */
    private EErrorType executionStatus;
    /**
     * Error message.
     */
    private String executionMessage;

    /**
     * Constructor for successful execution.<br>
     * <br>
     * Initializes executionStatus with {@link EErrorType#NO_ERROR} and
     * executionMessage with empty string.
     *
     * @param deploymentAvailable
     *            Result of the deployment available check.
     * @param version
     *            Version of the Service available for deployment. Either the
     *            version explicitly requested by the function call, or the
     *            semantically highest applicable version if a RegExp pattern is
     *            used.<br>
     *            Is empty if device is not eligible, if no Version is available, or
     *            if executionStatus &gt; 0.
     * @param newTechnicalInformation
     *            Technical Information of the to be deployed Service Instance.
     *            Information about the underlying platform profile and list of all
     *            parameters defined for the Service (by spParameters) or
     *            overwritten via certain Flavor in the TSM-Backend.<br>
     *            Is null if device is not eligible, if no Version is available, or
     *            if executionStatus &gt; 0.
     */
    public ServiceDeploymentAvailableResult(final EDeploymentAvailable deploymentAvailable,
            final String version, final ITechnicalInformation newTechnicalInformation) {
        this(deploymentAvailable, version, newTechnicalInformation, EErrorType.NO_ERROR, "");
    }

    /**
     * Constructor for error case. <br>
     * <br>
     * Initializes deploymentAvailable with
     * {@link EDeploymentAvailable#DEVICE_NOT_ELIGIBLE}.<br>
     * Initializes version with ''.<br>
     * Initializes technicalInformation with new {@link TechnicalInformation}.<br>
     *
     * @param executionStatus
     *            Error code.
     * @param executionMessage
     *            Error message.
     */
    public ServiceDeploymentAvailableResult(final EErrorType executionStatus,
            final String executionMessage) {
        this(EDeploymentAvailable.DEVICE_NOT_ELIGIBLE, "", new TechnicalInformation(),
                executionStatus, executionMessage);
    }

    /**
     * Constructor.
     *
     * @param deploymentAvailable
     *            Result of the deployment available check.
     * @param version
     *            Version of the Service available for deployment. Either the
     *            version explicitly requested by the function call, or the
     *            semantically highest applicable version if a RegExp pattern is
     *            used.<br>
     *            Is empty if device is not eligible, if no Version is available, or
     *            if executionStatus &gt; 0.
     * @param newTechnicalInformation
     *            Technical Information of the to be deployed Service Instance.
     *            Information about the underlying platform profile and list of all
     *            parameters defined for the Service (by spParameters) or
     *            overwritten via certain Flavor in the TSM-Backend.<br>
     *            Is null if device is not eligible, if no Version is available, or
     *            if executionStatus &gt; 0.
     * @param executionStatus
     *            Execution status code.
     * @param executionMessage
     *            Execution message.
     */
    public ServiceDeploymentAvailableResult(final EDeploymentAvailable deploymentAvailable,
            final String version, final ITechnicalInformation newTechnicalInformation,
            final EErrorType executionStatus, final String executionMessage) {
        this.deploymentAvailable = deploymentAvailable;
        this.version = version;
        this.newTechnicalInformation = newTechnicalInformation;
        this.executionStatus = executionStatus;
        this.executionMessage = executionMessage;
    }

    /**
     * Result of the deployment available check. One of:
     * <ul>
     * <li>{@link EDeploymentAvailable#DEVICE_NOT_ELIGIBLE}</li>
     * <li>{@link EDeploymentAvailable#DEPLOYMENT_AVAILABLE}</li>
     * </ul>
     *
     * @return Deployment available result.
     */
    @Override
    public EDeploymentAvailable getDeploymentAvailable() {
        return deploymentAvailable;
    }

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
    @Override
    public String getVersion() {
        return version;
    }

    /**
     * Returns the information about the underlying platform (JavaCard version, CSP
     * version, etc.) and list of all parameters defined (by spParameters) for the
     * Service or overwritten via certain Flavor in the TSM-Backend.<br>
     *
     * @return Technical information about underlying platform.
     */
    @Override
    public ITechnicalInformation getNewTechnicalInformation() {
        return newTechnicalInformation;
    }

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
    @Override
    public EErrorType getExecutionStatus() {
        return executionStatus;
    }

    /**
     * Returns an execution message, see {@link EErrorType}.
     *
     * @return Error message, in case an error occurred, otherwise empty string.
     */
    @Override
    public String getExecutionMessage() {
        return executionMessage;
    }

    /**
     * Checks equality of deploymentAvailable, version and executionStatus.
     *
     * @param o
     *            Other object to compare with.
     * @return True, when all are equal.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServiceDeploymentAvailableResult that = (ServiceDeploymentAvailableResult) o;
        return deploymentAvailable == that.deploymentAvailable
                && Objects.equals(version, that.version) && executionStatus == that.executionStatus;
    }

    /**
     * Creates hash from deploymentAvailable, version and executionStatus.
     *
     * @return Hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(deploymentAvailable, version, executionStatus);
    }

    /**
     * Creates string of class name and all members.
     *
     * @return Representation string of this class.
     */
    @Override
    public String toString() {
        return "ServiceDeploymentAvailableResult{" + "deploymentAvailable=" + deploymentAvailable
                + ", version='" + version + '\'' + ", newTechnicalInformation="
                + newTechnicalInformation + ", executionStatus=" + executionStatus
                + ", executionMessage='" + executionMessage + '\'' + '}';
    }
}
