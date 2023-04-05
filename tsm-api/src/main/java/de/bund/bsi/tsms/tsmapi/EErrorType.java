package de.bund.bsi.tsms.tsmapi;

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
 * {@link de.bund.bsi.tsms.tsmapi.results.IProcessInfo} object with information
 * about the status of the process, including also the attributes
 * executionStatus and executionMessage. <br>
 * For both method types executionStatus contains the error category and
 * executionMessage contains a human-readable error message in English.<br>
 * <br>
 * In case no error occurred, the executionStatus is 0 and the executionMessage
 * is empty.
 *
 * @since 1.0
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
     * INVALID_ARGUMENT shall be thrown when interface method was called with
     * invalid parameters, e.g. attempt to access a non-existing resource, or
     * request of an invalid action. Example error messages are:
     * <ul>
     * <li>Invalid argument: serviceId in GetServiceInstancesRequest has invalid
     * format</li>
     * <li>Invalid argument: serviceId in CreateServiceInstanceRequest has invalid
     * format</li>
     * <li>Invalid argument: serviceInstanceId in DeployServiceRequest has invalid
     * format</li>
     * <li>Invalid argument: serviceCommands in DeployServiceRequest is only allowed
     * to be empty if the finalize deployment flag is set</li>
     * <li>Invalid argument: serviceCommands in DeployServiceRequest contain
     * multiple PersonalizeServiceCommands</li>
     * <li>Invalid argument: version in CheckServiceDeploymentAvailableRequest has
     * invalid format</li>
     * <li>Invalid argument: version in CheckServiceDeploymentAvailableRequest uses
     * an unsupported filter pattern</li>
     * </ul>
     */
    INVALID_ARGUMENT(4),
    /**
     * NOT_AUTHENTICATED shall be thrown in case of authentication related issues,
     * e.g. the attempt to access a resource without authorization, or the attempt
     * of an unauthorized app to access a security component. An example error
     * message is:
     * <ul>
     * <li>Not authenticated: authentication failed</li>
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
     * SECURE_COMPONENT_ERROR shall be thrown on issues related to the content of
     * the Secure Component. Reason for this could be unfulfilled service
     * dependencies. Example error messages are:
     * <ul>
     * <li>Secure Component error: no space available</li>
     * <li>Secure component error: SC is not accessible</li>
     * <li>Secure component error: invalid response</li>
     * </ul>
     */
    SECURE_COMPONENT_ERROR(7),
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
     * NOT_ALLOWED shall be thrown if methods were called for a Service Instance
     * that is in a state not supported by the method. Example error messages are:
     * <ul>
     * <li>Not allowed: invalid state transfer from DeployedOperational to
     * UnderDeploymentInstalled</li>
     * <li>Not allowed: invalid state transfer from NotDeployed to
     * UnderDeploymentActivated</li>
     * <li>Not allowed: invalid state transfer from UnderDeploymentPersonalized to
     * UnderDeploymentPersonalized</li>
     * <li>Not allowed: invalid service command 'install' has already been
     * executed</li>
     * </ul>
     */
    NOT_ALLOWED(13),
    /**
     * ALREADY_EXISTS shall be thrown if a Service Provider tries to create a
     * Service Instance although there already exists a Service Instance on one of
     * the accessible Secure Components. Example error messages are:
     * <ul>
     * <li>Already exists: service already instantiated</li>
     * </ul>
     */
    ALREADY_EXISTS(14),
    /**
     * UNAUTHORIZED shall be thrown if a Service Provider tries to access a Service
     * or Service Instance which he does not own. Example error messages are:
     * <ul>
     * <li>Unauthorized: app not authorized</li>
     * </ul>
     */
    UNAUTHORIZED(15),
    /**
     * ISSUER_ERROR shall be thrown when an error related to a Secure Component
     * issuer infrastructure occurs. Example error messages are:
     * <ul>
     * <li>Issuer error: issuer TSM not available</li>
     * <li>Issuer error: SCI key management system caused an unexpected error</li>
     * </ul>
     */
    ISSUER_ERROR(16),
    /**
     * NOT_FOUND shall be thrown when an operation, with a resource that does not
     * exist, is requested via the TSM-API. Example error messages are:
     * <ul>
     * <li>Not found: serviceId 910bbd5a-1749-4737-b855-a520f1bc9da4 does not
     * exist</li>
     * <li>Not found: serviceInstanceId d8c791a1-c39e-41f7-a2ec-f8cdfb57406a does
     * not exist</li>
     * <li>Not found: version 1.1.1 does not exist</li>
     * </ul>
     */
    NOT_FOUND(17),
    /**
     * OVERLOAD_PROTECTION shall be thrown if the TSM backend is rejecting requests
     * due to a high system load.
     */
    OVERLOAD_PROTECTION(18),
    /**
     * UNDER_MAINTENANCE shall be thrown if requests cannot be processed because of
     * an ongoing maintenance.
     */
    UNDER_MAINTENANCE(19),
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
