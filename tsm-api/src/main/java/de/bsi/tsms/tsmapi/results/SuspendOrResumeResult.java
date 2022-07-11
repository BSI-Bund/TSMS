package de.bsi.tsms.tsmapi.results;

import java.util.Objects;

/**
 * Default implementation of {@link ISuspendOrResumeResult}.
 */
public class SuspendOrResumeResult implements ISuspendOrResumeResult {

    /**
     * Information about the executed process.
     */
    private IProcessInfo processInfo;

    /**
     * Constructor.
     *
     * @param processInfo
     *            Information about the executed process.
     */
    public SuspendOrResumeResult(final IProcessInfo processInfo) {
        this.processInfo = processInfo;
    }

    /**
     * Returns the result of the process execution.
     *
     * @return Process execution result.
     */
    @Override
    public IProcessInfo getProcessInfo() {
        return processInfo;
    }

    /**
     * Checks equality of processInfo.
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
        SuspendOrResumeResult that = (SuspendOrResumeResult) o;
        return Objects.equals(processInfo, that.processInfo);
    }

    /**
     * Creates hash from processInfo.
     *
     * @return Hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(processInfo);
    }

    /**
     * Creates string of class name and all members.
     *
     * @return Representation string of this class.
     */
    @Override
    public String toString() {
        return "SuspendOrResumeResult{" + "processInfo=" + processInfo + '}';
    }
}
