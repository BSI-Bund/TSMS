package de.bund.bsi.tsms.tsmapi;

/**
 * Result of the {@link ITsmApiService#checkServiceDeploymenAvailable} method.
 *
 * @since 1.0
 */
public enum EDeploymentAvailable {
    /**
     * No deployment available.
     */
    DEVICE_NOT_ELIGIBLE(0),
    /**
     * Deployment is available.
     */
    DEPLOYMENT_AVAILABLE(1);

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
    EDeploymentAvailable(final int number) {
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
     * @return Enum value. Null when number is unknown.
     */
    public static EDeploymentAvailable get(final int number) {
        for (EDeploymentAvailable state : EDeploymentAvailable.values()) {
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
