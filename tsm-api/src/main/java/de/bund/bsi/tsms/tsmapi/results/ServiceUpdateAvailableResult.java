package de.bund.bsi.tsms.tsmapi.results;

import de.bund.bsi.tsms.tsmapi.EErrorType;
import de.bund.bsi.tsms.tsmapi.EUpdateAvailable;

import java.util.Objects;

/**
 * Default implementation of {@link IServiceUpdateAvailableResult}.
 *
 * @since 1.0
 */
public class ServiceUpdateAvailableResult implements IServiceUpdateAvailableResult {

    /**
     * Result of the update available check.
     */
    private EUpdateAvailable updateAvailable;
    /**
     * Version of the Service available for update. Either the version explicitly
     * requested by the function call, or the semantically highest applicable
     * version if a RegExp pattern is used.
     */
    private String newVersion;
    /**
     * TechnicalInformation of the updated Service Instance (not the currently
     * present Service Instance). Information about the underlying platform profile
     * and list of all parameters defined for the Service (by spParameters) or
     * overwritten via certain Flavor in the TSM-Backend.
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
     * @param updateAvailable
     *            Result of the update available check.
     * @param newVersion
     *            Version of the Service available for update. Either the version
     *            explicitly requested by the function call, or the semantically
     *            highest applicable version if a RegExp pattern is used.<br>
     *            <br>
     *            Is empty if no update is available, or if executionStatus &gt; 0.
     * @param newTechnicalInformation
     *            TechnicalInformation of the updated Service Instance (not the
     *            currently present Service Instance). Information about the
     *            underlying platform profile and list of all parameters defined for
     *            the Service (by spParameters) or overwritten via certain Flavor in
     *            the TSM-Backend.<br>
     *            <br>
     *            Is null if no update is available or when executionStatus &gt; 0.
     */
    public ServiceUpdateAvailableResult(final EUpdateAvailable updateAvailable,
            final String newVersion, final ITechnicalInformation newTechnicalInformation) {
        this(updateAvailable, newVersion, newTechnicalInformation, EErrorType.NO_ERROR, "");
    }

    /**
     * Constructor for error case. <br>
     * <br>
     * Initializes updateAvailable with
     * {@link EUpdateAvailable#NO_UPDATE_AVAILABLE}.<br>
     * Initializes newVersion with empty string.<br>
     * Initializes newTechnicalInformation with new empty
     * {@link TechnicalInformation}.<br>
     *
     * @param executionStatus
     *            Error code.
     * @param executionMessage
     *            Error message.
     */
    public ServiceUpdateAvailableResult(final EErrorType executionStatus,
            final String executionMessage) {
        this(EUpdateAvailable.NO_UPDATE_AVAILABLE, "", new TechnicalInformation(), executionStatus,
                executionMessage);
    }

    /**
     * Constructor.
     *
     * @param updateAvailable
     *            Result of the update available check.
     * @param newVersion
     *            Version of the Service available for update. Either the version
     *            explicitly requested by the function call, or the semantically
     *            highest applicable version if a RegExp pattern is used.<br>
     *            <br>
     *            Is empty if no update is available, or if executionStatus &gt; 0.
     * @param newTechnicalInformation
     *            TechnicalInformation of the updated Service Instance (not the
     *            currently present Service Instance). Information about the
     *            underlying platform profile and list of all parameters defined for
     *            the Service (by spParameters) or overwritten via certain Flavor in
     *            the TSM-Backend.<br>
     *            <br>
     *            Is null if no update is available or when executionStatus &gt; 0.
     * @param executionStatus
     *            Execution status code.
     * @param executionMessage
     *            Execution message.
     */
    public ServiceUpdateAvailableResult(final EUpdateAvailable updateAvailable,
            final String newVersion, final ITechnicalInformation newTechnicalInformation,
            final EErrorType executionStatus, final String executionMessage) {
        this.updateAvailable = updateAvailable;
        this.newVersion = newVersion;
        this.newTechnicalInformation = newTechnicalInformation;
        this.executionStatus = executionStatus;
        this.executionMessage = executionMessage;
    }

    /**
     * Result of the update available check. One of:
     * <ul>
     * <li>{@link EUpdateAvailable#NO_UPDATE_AVAILABLE}</li>
     * <li>{@link EUpdateAvailable#UPDATE_AVAILABLE}</li>
     * </ul>
     *
     * @return Update available result.
     */
    @Override
    public EUpdateAvailable getUpdateAvailable() {
        return updateAvailable;
    }

    /**
     * Version of the Service available for update. Either the version explicitly
     * requested by the function call, or the semantically highest applicable
     * version if a RegExp pattern is used.<br>
     * <br>
     * Is empty if no update is available, or if executionStatus &gt; 0.
     *
     * @return Version available to update to.
     */
    @Override
    public String getNewVersion() {
        return newVersion;
    }

    /**
     * TechnicalInformation of the updated Service Instance (not the currently
     * present Service Instance). Information about the underlying platform profile
     * and list of all parameters defined for the Service (by spParameters) or
     * overwritten via certain Flavor in the TSM-Backend.<br>
     * <br>
     * Is null if no update is available or when executionStatus &gt; 0.
     *
     * @return Technical information about underlying platform which would fit after
     *         update.
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
     * Checks equality of updateAvailable, newVersion and executionStatus.
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
        ServiceUpdateAvailableResult that = (ServiceUpdateAvailableResult) o;
        return updateAvailable == that.updateAvailable
                && Objects.equals(newVersion, that.newVersion)
                && executionStatus == that.executionStatus;
    }

    /**
     * Creates hash from updateAvailable, newVersion and executionStatus.
     *
     * @return Hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(updateAvailable, newVersion, executionStatus);
    }

    /**
     * Creates string of class name and all members.
     *
     * @return Representation string of this class.
     */
    @Override
    public String toString() {
        return "ServiceUpdateAvailableResult{" + "updateAvailable=" + updateAvailable
                + ", newVersion='" + newVersion + '\'' + ", newTechnicalInformation="
                + newTechnicalInformation + ", executionStatus=" + executionStatus
                + ", executionMessage='" + executionMessage + '\'' + '}';
    }
}
