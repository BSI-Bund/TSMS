package de.bund.bsi.tsms.tsmapi;

import de.bund.bsi.tsms.tsmapi.results.IProcessProgress;
import de.bund.bsi.tsms.tsmapi.results.IProcessStart;

/**
 * This is a mock implementation of an {@link ITsmProcessListener}.<br>
 * <br>
 * It does nothing on process feedback.
 *
 * @since 1.0
 */
public final class MockTsmProcessListener implements ITsmProcessListener {

    /**
     * Singleton instance.
     */
    private static MockTsmProcessListener instance = null;

    /**
     * Singleton constructor. Please use {@link #getInstance()}.
     */
    private MockTsmProcessListener() {
    }

    private synchronized void initInstance() {
        if (instance == null) {
            instance = new MockTsmProcessListener();
        }
    }

    /**
     * Gets the singleton instance.
     *
     * @return Singleton instance.
     */
    public MockTsmProcessListener getInstance() {
        if (instance == null) {
            initInstance();
        }
        return instance;
    }

    /**
     * Does nothing.
     *
     * @param processStart
     *            Parameter is ignored.
     */
    @Override
    public void onProcessStart(final IProcessStart processStart) {

    }

    /**
     * Does nothing.
     *
     * @param processProgress
     *            Parameter is ignored.
     */
    @Override
    public void onProcessProgress(final IProcessProgress processProgress) {

    }
}
