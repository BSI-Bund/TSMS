package de.bund.bsi.tsms.tsmapi.results;

import de.bund.bsi.tsms.tsmapi.EErrorType;

import java.util.Date;
import java.util.Objects;

/**
 * Default implementation of {@link IProcessInfo}.
 *
 * @since 1.0
 */
public class ProcessInfo implements IProcessInfo {

    /**
     * Identifier of the executed process.
     */
    private String processId;
    /**
     * Date time when process was started.
     */
    private String startDate;
    /**
     * Date time when process has finished.
     */
    private String endDate;
    /**
     * Execution status code.
     */
    private EErrorType executionStatus;
    /**
     * Execution message.
     */
    private String executionMessage;

    /**
     * Constructor for successful execution.<br>
     * <br>
     * Initializes executionStatus with {@link EErrorType#NO_ERROR} and
     * executionMessage with empty string.
     *
     * @param processId
     *            Identifier of the executed process.
     * @param startDate
     *            Date time when process was started.
     * @param endDate
     *            Date time when process has finished.
     */
    public ProcessInfo(final String processId, final Date startDate, final Date endDate) {
        this(processId, Objects.toString(startDate), Objects.toString(endDate), EErrorType.NO_ERROR,
                "");
    }

    /**
     * Constructor for successful execution.<br>
     * <br>
     * Initializes executionStatus with {@link EErrorType#NO_ERROR} and
     * executionMessage with empty string.
     *
     * @param processId
     *            Identifier of the executed process.
     * @param startDate
     *            Date time when process was started.
     * @param endDate
     *            Date time when process has finished.
     */
    public ProcessInfo(final String processId, final String startDate, final String endDate) {
        this(processId, startDate, endDate, EErrorType.NO_ERROR, "");
    }

    /**
     * Constructor.
     *
     * @param processId
     *            Identifier of the executed process.
     * @param startDate
     *            Date time when process was started.
     * @param endDate
     *            Date time when process has finished.
     * @param executionStatus
     *            Execution status code.
     * @param executionMessage
     *            Execution message.
     */
    public ProcessInfo(final String processId, final String startDate, final String endDate,
            final EErrorType executionStatus, final String executionMessage) {
        this.processId = processId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.executionStatus = executionStatus;
        this.executionMessage = executionMessage;
    }

    /**
     * Constructor for error case. <br>
     * <br>
     * Initializes auth-token with empty string.
     *
     * @param executionStatus
     *            Error code.
     * @param executionMessage
     *            Error message.
     */
    public ProcessInfo(final EErrorType executionStatus, final String executionMessage) {
        this("", "", "", executionStatus, executionMessage);
    }

    /**
     * Returns a string containing the identifier of the process. <br>
     * The value of processId is empty if the request failed before a Process has
     * been created.
     *
     * @return Identifier of the process, is never null, can be empty String.
     */
    @Override
    public String getProcessId() {
        return processId;
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
     * Returns the date time when process was started.<br>
     * <br>
     * Datetime strings express Coordinated Universal Time (UTC) including
     * milliseconds with a special UTC designator (“Z”) according to [ISO8601].
     *
     * @return Datetime string (start of process).
     */
    @Override
    public String getStartDate() {
        return startDate;
    }

    /**
     * Returns the date time when process has finished.<br>
     * <br>
     * Datetime strings express Coordinated Universal Time (UTC) including
     * milliseconds with a special UTC designator (“Z”) according to [ISO8601].
     *
     * @return Datetime string (end of process).
     */
    @Override
    public String getEndDate() {
        return endDate;
    }

    /**
     * Checks equality of processId.
     *
     * @param o
     *            Other object to compare with.
     * @return True, when it is equal.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProcessInfo that = (ProcessInfo) o;
        return Objects.equals(processId, that.processId);
    }

    /**
     * Creates hash from processId.
     *
     * @return Hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(processId);
    }

    /**
     * Creates string of class name and all members.
     *
     * @return Representation string of this class.
     */
    @Override
    public String toString() {
        return "ProcessInfo{" + "processId='" + processId + '\'' + ", executionStatus="
                + executionStatus + ", executionMessage='" + executionMessage + '\''
                + ", startDate='" + startDate + '\'' + ", endDate='" + endDate + '\'' + '}';
    }
}
