package de.bsi.tsms.tsmapi.results;

import de.bsi.tsms.tsmapi.EErrorType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Default implementation of {@link IGetServiceInstancesResult}.
 */
public class GetServiceInstancesResult implements IGetServiceInstancesResult {

    /**
     * Service instances existing on the device.
     */
    private List<IServiceInstance> serviceInstances;
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
     * @param serviceInstances
     *            Service instances existing on the device.
     */
    public GetServiceInstancesResult(final List<IServiceInstance> serviceInstances) {
        this(serviceInstances, EErrorType.NO_ERROR, "");
    }

    /**
     * Constructor for error case.<br>
     * <br>
     * Initializes serviceInstanceSs with empty {@link ArrayList}.
     *
     * @param executionStatus
     *            Error code.
     * @param executionMessage
     *            Error message.
     */
    public GetServiceInstancesResult(final EErrorType executionStatus,
            final String executionMessage) {
        this(new ArrayList<>(), executionStatus, executionMessage);
    }

    /**
     * Constructor.
     *
     * @param serviceInstances
     *            Service instances existing on the device.
     * @param executionStatus
     *            Execution status code.
     * @param executionMessage
     *            Execution message.
     */
    public GetServiceInstancesResult(final List<IServiceInstance> serviceInstances,
            final EErrorType executionStatus, final String executionMessage) {
        this.serviceInstances = serviceInstances;
        this.executionStatus = executionStatus;
        this.executionMessage = executionMessage;
    }

    /**
     * Returns a list of ServiceInstances present on the device.<br>
     * <br>
     * Is empty if no Service Instance exists.
     *
     * @return Service instances existing.
     */
    @Override
    public List<IServiceInstance> getServiceInstances() {
        return serviceInstances;
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
     * Checks equality of serviceInstances and executionStatus.
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
        GetServiceInstancesResult that = (GetServiceInstancesResult) o;
        return serviceInstances == that.serviceInstances && executionStatus == that.executionStatus;
    }

    /**
     * Creates hash from serviceInstances and executionStatus.
     *
     * @return Hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(serviceInstances, executionStatus);
    }

    /**
     * Creates string of class name and all members.
     *
     * @return Representation string of this class.
     */
    @Override
    public String toString() {
        return "GetServiceInstanceStateResult{" + "serviceInstanceState=" + serviceInstances
                + ", executionStatus=" + executionStatus + ", executionMessage='" + executionMessage
                + '\'' + '}';
    }
}
