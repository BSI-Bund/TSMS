package de.bund.bsi.tsms.tsmapi.results;

/**
 * A CommandResponsePair object contains command and response APDUs for
 * individual personalization commands performed on the Service Instance.<br>
 * <br>
 * This class is used as result data object for {@link IPersonalizationResult}.
 *
 * @since 1.0
 */
public interface ICommandResponsePair {
    /**
     * Returns the tag name for the personalization result.
     *
     * @return Tag name.
     */
    String getTag();

    /**
     * Returns the hex string representation of a Command APDU.
     *
     * @return APDU command in hex.
     */
    String getCommand();

    /**
     * Returns the hex string representation of a Response APDU.
     *
     * @return APDU response in hex.
     */
    String getResponse();

}
