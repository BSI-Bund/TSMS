package de.bsi.tsms.tsmapi.results;

import de.bsi.tsms.tsmapi.EServiceOperation;

/**
 * ProcessProgress is a message and is used in
 * {@link de.bsi.tsms.tsmapi.ITsmProcessListener} indicating the progress of the
 * process execution.
 */
public interface IProcessProgress {
    /**
     * Returns the process identifier.
     *
     * @return Identifier of the process.
     */
    String getProcessId();

    /**
     * A number representing a percentage value to indicate the relative progress of
     * the action execution.
     *
     * @return Percentage (value between 0-100).
     */
    int getProgress();

    /**
     * Returns the executed operation.
     *
     * @return Operation currently executed.
     */
    EServiceOperation getOperation();
}
