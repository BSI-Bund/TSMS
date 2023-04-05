package de.bund.bsi.tsms.tsmapi;

import de.bund.bsi.tsms.tsmapi.results.IProcessProgress;
import de.bund.bsi.tsms.tsmapi.results.IProcessStart;

/**
 * The {@link ITsmProcessListener} is an interface for a callback object
 * containing asynchronous status information about {@link ITsmApiService}
 * method calls. It is used in the following methods:<br>
 * <br>
 * <ul>
 * <li>{@link ITsmApiService#deployService}</li>
 * <li>{@link ITsmApiService#updateService}</li>
 * <li>{@link ITsmApiService#suspendOrResumeService}</li>
 * <li>{@link ITsmApiService#terminateService}</li>
 * </ul>
 *
 * @since 1.0
 */
public interface ITsmProcessListener {
    /**
     * Callback method called once the execution of the requested process has
     * started.
     *
     * @param processStart
     *            Information about the process start.
     */
    void onProcessStart(IProcessStart processStart);

    /**
     * Callback method called when an update regarding the progress of the process
     * execution is available. Can be called multiple times until process is
     * finished.
     *
     * @param processProgress
     *            Information about the process progress.
     */
    void onProcessProgress(IProcessProgress processProgress);
}
