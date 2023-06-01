package de.bund.bsi.tsms.tsmapi.results;

import de.bund.bsi.tsms.tsmapi.EErrorType;

import java.util.Objects;

/**
 * Default implementation of {@link ISetAccessTokenResult}.
 *
 * @since 1.0
 */
public class SetAccessTokenResult implements ISetAccessTokenResult {

    /**
     * Error code.
     */
    private EErrorType executionStatus;
    /**
     * Error message.
     */
    private String executionMessage;

    /**
     * Constructor for successful execution.
     */
    public SetAccessTokenResult() {
        this(EErrorType.NO_ERROR, "");
    }

    /**
     * Constructor for error case.
     *
     * @param executionStatus
     *            Error code.
     * @param executionMessage
     *            Error message.
     */
    public SetAccessTokenResult(final EErrorType executionStatus, final String executionMessage) {
        this.executionStatus = executionStatus;
        this.executionMessage = executionMessage;
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
     * Checks equality of serviceInstances and executionStatus.
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
        SetAccessTokenResult that = (SetAccessTokenResult) o;
        return executionStatus == that.executionStatus;
    }

    /**
     * Creates hash from serviceInstances and executionStatus.
     *
     * @return Hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(executionStatus);
    }

    /**
     * Creates string of class name and all members.
     *
     * @return Representation string of this class.
     */
    @Override
    public String toString() {
        return "SetCustomAccessTokenResult{" + "executionStatus=" + executionStatus
                + ", executionMessage='" + executionMessage + '\'' + '}';
    }
}
