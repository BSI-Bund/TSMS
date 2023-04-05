package de.bund.bsi.tsms.tsmapi.results;

import java.util.Objects;

/**
 * Default implementation of {@link IProcessStart}.
 *
 * @since 1.0
 */
public class ProcessStart implements IProcessStart {

    /**
     * Identifier of the executed process.
     */
    private String processId;
    /**
     * Identifier of the executed process.
     */
    private String callerId;
    /**
     * Date time when process was started.
     */
    private String startDate;

    /**
     * Constructor.
     *
     * @param processId
     *            Identifier of the executed process.
     * @param callerId
     *            Identifier of the app.
     * @param startDate
     *            Date time when process was started.
     */
    public ProcessStart(final String processId, final String callerId, final String startDate) {
        this.processId = processId;
        this.callerId = callerId;
        this.startDate = startDate;
    }

    /**
     * Returns the process identifier.
     *
     * @return Identifier of the process.
     */
    @Override
    public String getProcessId() {
        return null;
    }

    /**
     * The device application identifier which is a hash value of the certificate of
     * the device Application Provider, i.e. the SP.
     *
     * @return App identifier.
     */
    @Override
    public String getCallerId() {
        return null;
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
        return null;
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
        ProcessStart that = (ProcessStart) o;
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
        return "ProcessStart{" + "processId='" + processId + '\'' + ", callerId='" + callerId + '\''
                + ", startDate='" + startDate + '\'' + '}';
    }
}
