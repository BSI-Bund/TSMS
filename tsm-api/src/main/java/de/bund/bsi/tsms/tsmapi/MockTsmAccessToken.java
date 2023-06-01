package de.bund.bsi.tsms.tsmapi;

/**
 * This is a mock implementation of an {@link ITsmAccessToken}.<br>
 * <br>
 * It returns a static token string.
 *
 * @since 1.0.3
 */
public final class MockTsmAccessToken implements ITsmAccessToken {

    /**
     * Singleton instance.
     */
    private static MockTsmAccessToken instance = null;

    /**
     * Singleton constructor. Please use {@link #getInstance()}.
     */
    private MockTsmAccessToken() {
    }

    private synchronized void initInstance() {
        if (instance == null) {
            instance = new MockTsmAccessToken();
        }
    }

    /**
     * Gets the singleton instance.
     *
     * @return Singleton instance.
     */
    public MockTsmAccessToken getInstance() {
        if (instance == null) {
            initInstance();
        }
        return instance;
    }

    /**
     * Does nothing.
     *
     * @return Returns static string 'tokenSample'.
     */
    @Override
    public String getToken() {
        return "tokenSample";
    }
}
