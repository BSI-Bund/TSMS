package de.bund.bsi.tsms.tsmapi;

/**
 * Result of the {@link ITsmApiService#checkServiceUpdateAvailable} method.
 *
 * @since 1.0
 */
public enum EUpdateAvailable {
    /**
     * No update available.
     */
    NO_UPDATE_AVAILABLE(0),
    /**
     * Update to a new version is available. (When new version contains same flavor,
     * still UPDATE_AVAILABLE result is provided, please compare flavorIDs
     * separately when you plan to distinguish here)
     */
    UPDATE_AVAILABLE(1);

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
    EUpdateAvailable(final int number) {
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
    public static EUpdateAvailable get(final int number) {
        for (EUpdateAvailable state : EUpdateAvailable.values()) {
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
