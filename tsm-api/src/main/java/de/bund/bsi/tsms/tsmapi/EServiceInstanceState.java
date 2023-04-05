package de.bund.bsi.tsms.tsmapi;

/**
 * List of possible states of a Service Instance.<br>
 * <br>
 * Service Instances, the practical realization of secure applications, are
 * configured via the TSM-Backend REST-API and created from Services through
 * function calls via the TSM-API. Service Instances have a manageable
 * life-cycle, which is described in the following.<br>
 * <br>
 * A SP can retrieve the current life-cycle state of a Service Instance at any
 * time with a call to {@link ITsmApiService#getServiceInstances} or as a result
 * of a process execution. In case of an error during the process execution that
 * results in an erroneous state of the Service Instance, the state before the
 * process start is not automatically restored but the targeted Service Instance
 * transitions to the life-cycle state InError. A SP can then decide whether the
 * erroneous Service Instance shall be terminated, or missing steps shall be
 * performed to reach the targeted life-cycle state, e.g.,
 * UnderDeployment:Installed. The life-cycle of a Service Instance begins with a
 * successful {@link ITsmApiService#createServiceInstance} request by the SP and
 * ends with a successful call to {@link ITsmApiService#terminateService}.<br>
 * <br>
 * There is no separate state for TERMINATED, since the Service Instance is
 * removed during termination. A new Service Instance may be created through a
 * call to {@link ITsmApiService#createServiceInstance}, the resulting state is
 * {@link EServiceInstanceState#NOT_DEPLOYED}.
 *
 * @since 1.0
 */
public enum EServiceInstanceState {

    /**
     * The state of the service instance is unknown.
     */
    UNKNOWN(0, "Unknown"),
    /**
     * The state Not deployed is the initial state for each Service Instance. In
     * this state, no components of the Service are deployed on the SC. In state Not
     * deployed a new Service Instance ID might be created through a successful call
     * to {@link ITsmApiService#createServiceInstance}. Here the Service Instance ID
     * is only re-created if the previous Service Instance ID was invalid, otherwise
     * it returns the last valid Service Instance ID.
     */
    NOT_DEPLOYED(1, "NotDeployed"),
    /**
     * Service Instance reaches the UnderDeployment:Initialized state once the TSM
     * has sent an {@link ITsmProcessListener#onProcessStart} notification for a
     * {@link ITsmApiService#deployService} or {@link ITsmApiService#updateService}
     * request. In this state, the eligibility check is performed and the deployment
     * of a Service has started but not all Service components have been loaded
     * and/or installed.
     */
    INITIALIZED(10, "UnderDeploymentInitialized"),
    /**
     * A Service Instance reaches the UnderDeployment:Installed state when the
     * execution of the
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IInstallServiceCommand} of a
     * {@link ITsmApiService#deployService} or {@link ITsmApiService#updateService}
     * function call has finished. In this state the loading and installing of all
     * Service components as defined by the SP has been performed.
     */
    INSTALLED(11, "UnderDeploymentInstalled"),
    /**
     * A Service Instance reaches the optional UnderDeployment:Personalized state
     * when the execution of the last
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IPersonalizeServiceCommand} of a
     * {@link ITsmApiService#deployService} or {@link ITsmApiService#updateService}
     * function call has finished. In this state, Service components have been
     * personalized (e.g., by executing personalization scripts) as required by the
     * SP.
     */
    PERSONALIZED(12, "UnderDeploymentPersonalized"),
    /**
     * A Service Instance reaches the optional UnderDeployment:Activated state when
     * the execution of the
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IActivateServiceCommand} of a
     * {@link ITsmApiService#deployService} or {@link ITsmApiService#updateService}
     * function call has finished. The transition from UnderDeployment states to
     * Deployed states is triggered when the execution of a
     * {@link ITsmApiService#deployService} or {@link ITsmApiService#updateService}
     * function call that indicates the finalization of the service deployment has
     * finished successfully. A SP can decide if the personalization and/or
     * activation of a Service needs to be performed for the Service Instance to be
     * considered Deployed.
     */
    ACTIVATED(14, "UnderDeploymentActivated"),
    /**
     * A Service Instance reaches the Deployed:Operational state when the execution
     * of a {@link ITsmApiService#deployService} or
     * {@link ITsmApiService#updateService} function call that indicates the
     * finalization of the service deployment has finished successfully. Another
     * possibility to reach Deployed:Operational is the successful execution of a
     * {@link ITsmApiService#suspendOrResumeService} function call from the state
     * {@link EServiceInstanceState#SUSPENDED}. In this state, the Service Instance
     * is ready to be used by the SP.
     */
    OPERATIONAL(21, "DeployedOperational"),
    /**
     * A Service Instance reaches the Deployed:Suspended state when the execution of
     * a {@link ITsmApiService#deployService} or
     * {@link ITsmApiService#updateService} function call that has indicated the
     * finalization of the Service deployment and that contains an
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IActivateServiceCommand} with
     * SuspensionControl set to true has finished. Another possibility to reach
     * Deployed:Suspended is the successful execution of a
     * {@link ITsmApiService#suspendOrResumeService} function call from the state
     * Deployed:Operational. In this state, the service instance cannot be used by
     * the SP, e.g., because the service components are locked.
     */
    SUSPENDED(22, "DeployedSuspended"),
    /**
     * A Service Instance reaches the InError state when an error occurs during the
     * execution of a function call that caused a change to service components. If
     * the error occurred at a time when no change to service components has been
     * applied, the service instance remains in the current life-cycle state. In the
     * InError state, function calls can be made to perform a transition to other
     * life-cycle states.
     */
    IN_ERROR(25, "InError");

    /**
     * Internal integer code for the type.
     */
    private final int number;

    /**
     * Long representation name of the state.
     */
    private final String representation;

    /**
     * Constructor.
     *
     * @param number
     *            Integer representation of the enum value.
     * @param representation
     *            A description of the state.
     */
    EServiceInstanceState(final int number, final String representation) {
        this.number = number;
        this.representation = representation;
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
     * @return Enum value. Null when number is unknown.
     */
    public static EServiceInstanceState get(final int number) {
        for (EServiceInstanceState state : EServiceInstanceState.values()) {
            if (number == state.getNumber()) {
                return state;
            }
        }
        return null;
    }

    /**
     * Returns the long representation name of the state.
     *
     * @return Long name.
     */
    public String getRepresentation() {
        return representation;
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
