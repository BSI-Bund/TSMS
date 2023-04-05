package de.bund.bsi.tsms.tsmapi.results;

import java.util.List;

/**
 * This class defines the result of the TSM-API method
 * {@link de.bund.bsi.tsms.tsmapi.ITsmApiService#getServiceInstances}.
 *
 * @since 1.0
 */
public interface IGetServiceInstancesResult {

    /**
     * Returns a list of ServiceInstances present on the device.<br>
     * <br>
     * Is empty if no Service Instance exists.
     *
     * @return Service instances existing.
     */
    List<IServiceInstance> getServiceInstances();

    /**
     * An integer value indicating the process execution result.<br>
     * <br>
     * Possible values are:<br>
     * <br>
     * <ul>
     * <li>0: Successful Execution</li>
     * <li>&gt;0: error, see {@link de.bund.bsi.tsms.tsmapi.EErrorType}</li>
     * </ul>
     *
     * @return Execution result status.
     */
    de.bund.bsi.tsms.tsmapi.EErrorType getExecutionStatus();

    /**
     * Returns an execution message, see {@link de.bund.bsi.tsms.tsmapi.EErrorType}.
     *
     * @return Error message, in case an error occurred, otherwise empty string.
     */
    String getExecutionMessage();
}
