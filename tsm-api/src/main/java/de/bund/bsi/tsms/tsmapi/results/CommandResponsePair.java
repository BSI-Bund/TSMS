package de.bund.bsi.tsms.tsmapi.results;

import java.util.Objects;

/**
 * Default implementation of {@link ICommandResponsePair}.
 *
 * @since 1.0
 */
public class CommandResponsePair implements ICommandResponsePair {

    /**
     * APDU command tag.
     */
    private String tag;
    /**
     * APDU command operation.
     */
    private String command;
    /**
     * APDU response.
     */
    private String response;

    /**
     * Constructor.
     *
     * @param tag
     *            APDU command tag.
     * @param command
     *            APDU command operation.
     * @param response
     *            APDU response.
     */
    public CommandResponsePair(final String tag, final String command, final String response) {
        this.tag = tag;
        this.command = command;
        this.response = response;
    }

    /**
     * Returns the tag name for the personalization result.
     *
     * @return Tag name.
     */
    @Override
    public String getTag() {
        return tag;
    }

    /**
     * Returns the hex string representation of a Command APDU.
     *
     * @return APDU command in hex.
     */
    @Override
    public String getCommand() {
        return command;
    }

    /**
     * Returns the hex string representation of a Response APDU.
     *
     * @return APDU response in hex.
     */
    @Override
    public String getResponse() {
        return response;
    }

    /**
     * Checks equality of tag, command and response.
     *
     * @param o
     *            Other object to compare with.
     * @return True, when all are equal.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommandResponsePair that = (CommandResponsePair) o;
        return Objects.equals(tag, that.tag) && Objects.equals(command, that.command)
                && Objects.equals(response, that.response);
    }

    /**
     * Creates hash from tag, command and response.
     *
     * @return Hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(tag, command, response);
    }

    /**
     * Creates string of class name and all members.
     *
     * @return Representation string of this class.
     */
    @Override
    public String toString() {
        return "CommandResponsePair{" + "tag='" + tag + '\'' + ", command='" + command + '\''
                + ", response='" + response + '\'' + '}';
    }
}
