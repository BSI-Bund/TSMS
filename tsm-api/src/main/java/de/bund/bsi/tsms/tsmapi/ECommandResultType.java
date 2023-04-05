package de.bund.bsi.tsms.tsmapi;

/**
 * The CommandResultType is an enumeration defining the result values for an
 * {@link de.bund.bsi.tsms.tsmapi.results.IServiceCommandResult}. It is used as
 * return value for the following API methods:
 * <ul>
 * <li>{@link ITsmApiService#deployService}</li>
 * <li>{@link ITsmApiService#updateService}</li>
 * </ul>
 *
 * @since 1.0
 */
public enum ECommandResultType {
    /**
     * Command execution was successful.
     */
    EXECUTION_SUCCESS(0),
    /**
     * Command execution failed.
     */
    EXECUTION_FAILED(1),
    /**
     * Command was not executed.
     */
    NOT_EXECUTED(2);

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
    ECommandResultType(final int number) {
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
    public static ECommandResultType get(final int number) {
        for (ECommandResultType state : ECommandResultType.values()) {
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
