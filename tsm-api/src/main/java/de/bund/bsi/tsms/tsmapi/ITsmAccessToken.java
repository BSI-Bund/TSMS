package de.bund.bsi.tsms.tsmapi;

/**
 * The AccessToken is an optional interface to register a custom access token
 * for message authentication with TSM-Backend. The SP may provide an
 * implementation of the AccessToken and register it via the TSM-API method
 * {@link de.bund.bsi.tsms.tsmapi.ITsmApiService#setAccessToken}. When
 * registered, the TSM-API-SDK will call the method getToken of the AccessToken
 * to retrieve the authentication token for TSMS communication. The
 * {@link #getToken} method is called for each TSM-Backend request. Caching the
 * token and re-questing a new one when expired, must be implemented by the SP.
 *
 * @since 1.0.3
 */
public interface ITsmAccessToken {

    /**
     * Callback method called for each request to TSM-Backend. The implementation of
     * this interface handles token caching and token expiration. The access token
     * is only active, if the implementation is registered via TSM-API method
     * {@link de.bund.bsi.tsms.tsmapi.ITsmApiService#setAccessToken}.
     *
     * @return A currently valid token.
     */
    String getToken();

}
