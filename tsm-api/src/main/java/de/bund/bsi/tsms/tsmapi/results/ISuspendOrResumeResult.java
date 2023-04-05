package de.bund.bsi.tsms.tsmapi.results;

/**
 * This class defines the result of the TSM-API method
 * {@link de.bund.bsi.tsms.tsmapi.ITsmApiService#suspendOrResumeService}.
 *
 * @since 1.0
 */
public interface ISuspendOrResumeResult {

    /**
     * Returns the result of the process execution.
     *
     * @return Process execution result.
     */
    IProcessInfo getProcessInfo();
}
