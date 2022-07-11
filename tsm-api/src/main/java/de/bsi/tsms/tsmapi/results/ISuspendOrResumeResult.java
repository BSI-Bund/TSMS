package de.bsi.tsms.tsmapi.results;

/**
 * This class defines the result of the TSM-API method
 * {@link de.bsi.tsms.tsmapi.ITsmApiService#suspendOrResumeService}.
 */
public interface ISuspendOrResumeResult {

    /**
     * Returns the result of the process execution.
     *
     * @return Process execution result.
     */
    IProcessInfo getProcessInfo();
}
