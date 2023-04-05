package de.bund.bsi.tsms.tsmapi.results;

import de.bund.bsi.tsms.tsmapi.EErrorType;
import de.bund.bsi.tsms.tsmapi.EUpdateAvailable;

/**
 * This class defines the result of the TSM-API method
 * {@link de.bund.bsi.tsms.tsmapi.ITsmApiService#checkServiceUpdateAvailable}.
 *
 * @since 1.0
 */
public interface IServiceUpdateAvailableResult {

    /**
     * Result of the update available check. One of:
     * <ul>
     * <li>{@link EUpdateAvailable#NO_UPDATE_AVAILABLE}</li>
     * <li>{@link EUpdateAvailable#UPDATE_AVAILABLE}</li>
     * </ul>
     *
     * @return Update available result.
     */
    EUpdateAvailable getUpdateAvailable();

    /**
     * Version of the Service available for update. Either the version explicitly
     * requested by the function call, or the semantically highest applicable
     * version if a RegExp pattern is used.<br>
     * <br>
     * Is empty if no update is available, or if executionStatus &gt; 0.
     *
     * @return Version available to update to.
     */
    String getNewVersion();

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
