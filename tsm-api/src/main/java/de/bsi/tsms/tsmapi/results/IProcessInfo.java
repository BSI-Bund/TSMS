package de.bsi.tsms.tsmapi.results;

import de.bsi.tsms.tsmapi.EErrorType;

/**
 * A ProcessInfo object is used to provide the result of the process
 * execution.<br>
 * <br>
 * ProcessInfo is returned for<br>
 * <br>
 * <ul>
 * <li>{@link IDeployServiceResult#getProcessInfo()} from
 * {@link de.bsi.tsms.tsmapi.ITsmApiService#deployService}</li>
 * <li>{@link IUpdateServiceResult#getProcessInfo()} from
 * {@link de.bsi.tsms.tsmapi.ITsmApiService#updateService}</li>
 * <li>{@link ISuspendOrResumeResult#getProcessInfo()} from
 * {@link de.bsi.tsms.tsmapi.ITsmApiService#suspendOrResumeService}</li>
 * <li>{@link ITerminateServiceResult#getProcessInfo()} from
 * {@link de.bsi.tsms.tsmapi.ITsmApiService#terminateService}</li>
 * </ul>
 */
public interface IProcessInfo {
    /**
     * Returns a string containing the identifier of the process. <br>
     * The value of processId is empty if the request failed before a Process has
     * been created.
     *
     * @return Identifier of the process, is never null, can be empty String.
     */
    String getProcessId();

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

    /**
     * Returns the date time when process was started.<br>
     * <br>
     * Datetime strings express Coordinated Universal Time (UTC) including
     * milliseconds with a special UTC designator (“Z”) according to [ISO8601].
     *
     * @return Datetime string (start of process).
     */
    String getStartDate();

    /**
     * Returns the date time when process has finished.<br>
     * <br>
     * Datetime strings express Coordinated Universal Time (UTC) including
     * milliseconds with a special UTC designator (“Z”) according to [ISO8601].
     *
     * @return Datetime string (end of process).
     */
    String getEndDate();
}
