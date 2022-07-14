package de.bsi.tsms.tsmapi;

/**
 * During function calls, different error types may occur. Error information can
 * be retrieved in the following ways:<br>
 * <ul>
 * <li>from ProcessInfo as Future return value of all asynchronous process
 * executions</li>
 * <li>from return value on all synchronous interface methods</li>
 * </ul>
 * <br>
 * All synchronous interface methods SHALL return the attributes executionStatus
 * and executionMessage. All asynchronous interface methods SHALL return a
 * Future object, which returns a
 * {@link de.bsi.tsms.tsmapi.results.IProcessInfo} object with information about
 * the status of the process, including also the attributes executionStatus and
 * executionMessage. <br>
 * For both method types executionStatus contains the error category and
 * executionMessage contains a human-readable error message in English.<br>
 * <br>
 * In case no error occurred, the executionStatus is 0 and the executionMessage
 * is empty.
 */
public enum EErrorType {
    /**
     * No error occurred.
     */
    NO_ERROR(0),
    /**
     * TSMS_NOT_AVAILABLE shall be thrown when TSM is not available. The SP may want
     * to contact the TSM-Support.
     */
    TSM_NOT_AVAILABLE(1),
    /**
     * INTERNAL_ERROR shall be thrown when an internal error occurred during process
     * execution.
     */
    INTERNAL_ERROR(2),
    /**
     * NETWORK_CONNECTION_ERROR shall be thrown when establishing a connection to
     * the TSM or SCI backend is not possible. Reason for network failures could be
     * bad cellular connection or wrong firewall configuration. Example error
     * messages are:
     * <ul>
     * <li>Network connection error: host 'tsmService' not found</li>
     * </ul>
     */
    NETWORK_CONNECTION_ERROR(3),
    /**
     * INVALID_REQUEST shall be thrown when interface method was called with invalid
     * parameters, e.g. attempt to access a non-existing resource, or request of an
     * invalid action. Example error messages are:
     * <ul>
     * <li>Invalid request: service '12345' does not exist</li>
     * <li>Invalid request: serviceInstnceId '12345' does not exist</li>
     * <li>Invalid request: version '1.1.1' does not exist</li>
     * <li>Invalid request: no flavors defined for service '12345'</li>
     * <li>Invalid request: no flavors allocated to version '1.1.1'</li>
     * <li>Invalid request: unsupported version RegExp '1.x.1'</li>
     * </ul>
     */
    INVALID_REQUEST(4),
    /**
     * NOT_AUTHENTICATED shall be thrown in case of authentication related issues,
     * e.g. the attempt to access a resource without authorization, or the attempt
     * of an unauthorized app to access a security component. An example error
     * message is:
     * <ul>
     * <li>Invalid request: authentication failed</li>
     * </ul>
     */
    NOT_AUTHENTICATED(5),
    /**
     * EXECUTION_INTERRUPTED shall be thrown when a running process execution has
     * been interrupted. The reason for this could be a broken connection, or that
     * the user has manually stopped a service. Example error messages are:
     * <ul>
     * <li>Execution interrupted: connection to 'tsmService' interrupted</li>
     * <li>Execution interrupted: manually cancelled</li>
     * </ul>
     */
    EXECUTION_INTERRUPTED(6),
    /**
     * CONTENT_RELATED_ERROR shall be thrown on issues related to the content of the
     * Secure Component. Reason for this could be unfulfilled service dependencies.
     */
    CONTENT_RELATED_ERROR(7),
    /**
     * NO_ELIGIBLE_SC shall be thrown when eligibility check failed and no eligible
     * Secure Component is available on the handset. Example error messages are:
     * <ul>
     * <li>No eligible SC: service is already deployed</li>
     * <li>No eligible SC: service is already updated to requested version</li>
     * </ul>
     */
    NO_ELIGIBLE_SC(8),
    /**
     * SC_INACCESSIBLE shall be thrown when a secure component is present but cannot
     * be accessed. Example error messages are:
     * <ul>
     * <li>SC inaccessible: app not authorized to access SC</li>
     * <li>SC inaccessible: access to SC failed</li>
     * </ul>
     */
    SC_INACCESSIBLE(9),
    /**
     * SC_CHANNEL_NOT_AVAILABLE shall be thrown when TSM-API is unable to open a
     * basic or logical channel to the according Secure Component and thus cannot
     * access the SC via the Open Mobile API. The SP app may try to close active
     * channels (if present) and may try to request the process execution again.
     */
    SC_CHANNEL_NOT_AVAILABLE(10),
    /**
     * NFC_NOT_ACTIVATED shall be thrown, when NFC is currently not active, but the
     * Secure Component requires NFC to be activated. The SP app may request the
     * handset user to enable NFC. Example error messages are:
     * <ul>
     * <li>NFC not activated: please activate NFC</li>
     * </ul>
     */
    NFC_NOT_ACTIVATED(11),
    /**
     * ORPHANED_SERVICE_INSTANCE shall be thrown if an invalid Service Operation was
     * requested for a Service Instance whose Flavor it was created from is deleted.
     * The only permissible service operation for such a Service Instance is
     * terminate.
     */
    ORPHANED_SERVICE_INSTANCE(12),
    /**
     * INVALID_STATE shall be thrown if methods were called for a Service Instance
     * that is in a state not supported by the method. Example error messages are:
     * <ul>
     * <li>Invalid state: service instance is in error state</li>
     * <li>Invalid state: service is already deployed and finalized</li>
     * <li>Invalid state: service not deployed yet</li>
     * <li>Invalid state: service deployment not finalized yet</li>
     * <li>Invalid state: service command ‘install’ has already been executed</li>
     * <li>Invalid state: service command ‘activate’ has already been executed</li>
     * <li>Invalid state: service command ‘personalize’ has already been
     * executed</li>
     * </ul>
     */
    INVALID_STATE(13),
    /**
     * UNSPECIFIED shall be thrown for errors which fit no other category. Reasons
     * for such errors may be, e.g., that an OEM component caused an unexpected
     * error. The SP may want to access the device/application logs for more details
     * or contact the TSM-Support. Example error messages are:
     * <ul>
     * <li>Unspecified: SCI key management system caused an unexpected error</li>
     * </ul>
     */
    UNSPECIFIED(100);

    /**
     * Internal integer code for the type.
     */
    private final int number;

    /**
     * Constructor.
     *
     * @param number
     *            Integer representation of the enum value.
     */
    EErrorType(final int number) {
        this.number = number;
    }

    /**
     * Returns the integer representation of this enum value.
     *
     * @return Int value for the enum.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Static method to get the enum value from its integer representation.
     *
     * @param number
     *            Integer representation of the enum value.
     * @return Enum value, null when number is unknown.
     */
    public static EErrorType get(final int number) {
        for (EErrorType state : EErrorType.values()) {
            if (number == state.getNumber()) {
                return state;
            }
        }
        return null;
    }

    /**
     * Returns a string containing the enum name and its integer representation.
     *
     * @return Enum name + ( + number + ).
     */
    @Override
    public String toString() {
        return name() + "(" + number + ")";
    }
}
