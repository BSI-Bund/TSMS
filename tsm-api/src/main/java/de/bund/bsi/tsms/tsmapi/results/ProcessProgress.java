package de.bund.bsi.tsms.tsmapi.results;

import de.bund.bsi.tsms.tsmapi.EServiceOperation;

import java.util.Objects;

/**
 * Default implementation of {@link IProcessProgress}.
 *
 * @since 1.0
 */
public class ProcessProgress implements IProcessProgress {

    /**
     * Identifier of the executed process.
     */
    private String processId;
    /**
     * Representing a percentage (value between 0-100) to indicate the relative
     * progress of the action execution.
     */
    private int progress;
    /**
     * Type of the operation executed.
     */
    private EServiceOperation operation;

    /**
     * Constructor.
     *
     * @param processId
     *            Identifier of the executed process.
     * @param progress
     *            Representing a percentage (value between 0-100) to indicate the
     *            relative progress of the action execution.
     * @param operation
     *            Type of the operation executed.
     */
    public ProcessProgress(final String processId, final int progress,
            final EServiceOperation operation) {
        this.processId = processId;
        this.progress = progress;
        this.operation = operation;
    }

    /**
     * Returns the process identifier.
     *
     * @return Identifier of the process.
     */
    @Override
    public String getProcessId() {
        return processId;
    }

    /**
     * A number representing a percentage value to indicate the relative progress of
     * the action execution.
     *
     * @return Percentage (value between 0-100).
     */
    @Override
    public int getProgress() {
        return progress;
    }

    /**
     * Returns the executed operation.
     *
     * @return Operation currently executed.
     */
    @Override
    public EServiceOperation getOperation() {
        return operation;
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
        ProcessProgress that = (ProcessProgress) o;
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
        return "ProcessProgress{" + "processId='" + processId + '\'' + ", progress=" + progress
                + ", operation=" + operation + '}';
    }
}
