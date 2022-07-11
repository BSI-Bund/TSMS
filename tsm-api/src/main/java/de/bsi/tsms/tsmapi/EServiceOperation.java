package de.bsi.tsms.tsmapi;

/**
 * List of possible operations performed on a service.
 */
public enum EServiceOperation {
    /**
     * No operation executed.
     */
    NO_OPERATION(0),
    /**
     * Operation is a {@link ITsmApiService#deployService} method call with
     * {@link de.bsi.tsms.tsmapi.parameters.IInstallServiceCommand} as last
     * serviceCommand and finalize false.
     */
    SERVICE_DEPLOYMENT_INSTALLATION(10),
    /**
     * Operation is a {@link ITsmApiService#deployService} method call with
     * {@link de.bsi.tsms.tsmapi.parameters.IPersonalizeServiceCommand} as last
     * serviceCommand and finalize false.
     */
    SERVICE_DEPLOYMENT_PERSONALIZATION(11),
    /**
     * Operation is a {@link ITsmApiService#deployService} method call with
     * {@link de.bsi.tsms.tsmapi.parameters.IActivateServiceCommand} as last
     * serviceCommand and finalize false.
     */
    SERVICE_DEPLOYMENT_ACTIVATION(12),
    /**
     * Operation is a {@link ITsmApiService#deployService} method call with finalize
     * true.
     */
    SERVICE_DEPLOYMENT_FINALIZE(13),
    /**
     * Operation is a {@link ITsmApiService#updateService} method call with
     * {@link de.bsi.tsms.tsmapi.parameters.IInstallServiceCommand} as last
     * serviceCommand and finalize false.
     */
    SERVICE_UPDATE_INSTALLATION(20),
    /**
     * Operation is a {@link ITsmApiService#updateService} method call with
     * {@link de.bsi.tsms.tsmapi.parameters.IPersonalizeServiceCommand} command as
     * last serviceCommand and finalize false.
     */
    SERVICE_UPDATE_PERSONALIZATION(21),
    /**
     * Operation is a {@link ITsmApiService#updateService} method call with
     * {@link de.bsi.tsms.tsmapi.parameters.IActivateServiceCommand} as last
     * serviceCommand and finalize false.
     */
    SERVICE_UPDATE_ACTIVATION(22),
    /**
     * Operation is a {@link ITsmApiService#updateService} method call with finalize
     * true.
     */
    SERVICE_UPDATE_FINALIZE(23),
    /**
     * Operation is a {@link ITsmApiService#suspendOrResumeService} method call with
     * suspensionControl true.
     */
    SERVICE_SUSPENSION(30),
    /**
     * Operation is a {@link ITsmApiService#suspendOrResumeService} method call with
     * suspensionControl false.
     */
    SERVICE_RESUMPTION(31),
    /**
     * Operation is a {@link ITsmApiService#terminateService} method call.
     */
    SERVICE_TERMINATION(40);

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
    EServiceOperation(final int number) {
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
    public static EServiceOperation get(final int number) {
        for (EServiceOperation state : EServiceOperation.values()) {
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
