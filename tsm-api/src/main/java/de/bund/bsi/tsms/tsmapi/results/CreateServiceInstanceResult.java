package de.bund.bsi.tsms.tsmapi.results;

import de.bund.bsi.tsms.tsmapi.EErrorType;

import java.util.Objects;

/**
 * Default implementation of {@link ICreateServiceInstanceResult}.
 *
 * @since 1.0
 */
public class CreateServiceInstanceResult implements ICreateServiceInstanceResult {

    /**
     * Unique service instance identifier.
     */
    private IServiceInstance serviceInstance;
    /**
     * Error code.
     */
    private EErrorType executionStatus;
    /**
     * Error message.
     */
    private String executionMessage;

    /**
     * Constructor for successful execution.<br>
     * <br>
     * Initializes executionStatus with {@link EErrorType#NO_ERROR} and
     * executionMessage with empty string.
     *
     * @param serviceInstance
     *            Unique service instance identifier.
     */
    public CreateServiceInstanceResult(final IServiceInstance serviceInstance) {
        this(serviceInstance, EErrorType.NO_ERROR, "");
    }

    /**
     * Constructor for error case. <br>
     * <br>
     * Initializes serviceInstance with null.
     *
     * @param executionStatus
     *            Error code.
     * @param executionMessage
     *            Error message.
     */
    public CreateServiceInstanceResult(final EErrorType executionStatus,
            final String executionMessage) {
        this(null, executionStatus, executionMessage);
    }

    /**
     * Constructor.
     *
     * @param serviceInstance
     *            Unique service instance identifier.
     * @param executionStatus
     *            Execution status code.
     * @param executionMessage
     *            Execution message.
     */
    public CreateServiceInstanceResult(final IServiceInstance serviceInstance,
            final EErrorType executionStatus, final String executionMessage) {
        this.serviceInstance = serviceInstance;
        this.executionStatus = executionStatus;
        this.executionMessage = executionMessage;
    }

    /**
     * Returns the ServiceInstance created.<br>
     * <br>
     * Is null if no Service Instance could be created.
     *
     * @return Service instance.
     */
    @Override
    public IServiceInstance getServiceInstance() {
        return serviceInstance;
    }

    /**
     * An integer value indicating the process execution result.<br>
     * <br>
     * Possible values are:<br>
     * <br>
     * <ul>
     * <li>0: Successful Execution</li>
     * <li>&gt;0: error, see {@link EErrorType}</li>
     * </ul>
     *
     * @return Execution result status.
     */
    @Override
    public EErrorType getExecutionStatus() {
        return executionStatus;
    }

    /**
     * Returns an execution message, see {@link EErrorType}.
     *
     * @return Error message, in case an error occurred, otherwise empty string.
     */
    @Override
    public String getExecutionMessage() {
        return executionMessage;
    }

    /**
     * Checks equality of serviceInstance and executionStatus.
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
        CreateServiceInstanceResult that = (CreateServiceInstanceResult) o;
        return Objects.equals(serviceInstance, that.serviceInstance)
                && executionStatus == that.executionStatus;
    }

    /**
     * Creates hash from serviceInstance and executionStatus.
     *
     * @return Hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(serviceInstance, executionStatus);
    }

    /**
     * Creates string of class name and all members.
     *
     * @return Representation string of this class.
     */
    @Override
    public String toString() {
        return "GetServiceInstanceIDResult{" + "serviceInstance='" + serviceInstance + '\''
                + executionStatus + ", executionMessage='" + executionMessage + '\'' + '}';
    }
}
