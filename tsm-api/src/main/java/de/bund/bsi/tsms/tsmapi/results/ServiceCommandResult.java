package de.bund.bsi.tsms.tsmapi.results;

import de.bund.bsi.tsms.tsmapi.ECommandResultType;

import java.util.Arrays;
import java.util.Objects;

/**
 * Default implementation of {@link IServiceCommandResult}.
 *
 * @since 1.0
 */
public class ServiceCommandResult implements IServiceCommandResult {

    /**
     * Execution result of the
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IServiceCommand}.
     */
    private ECommandResultType commandExecutionStatus;
    /**
     * Result for a
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IPersonalizeServiceCommand}.
     */
    private IPersonalizationResult[] personalizationResults;

    /**
     * Constructor. <br>
     * <br>
     * Initializes personalizationResults with empty array.
     *
     * @param commandExecutionStatus
     *            Execution result of the
     *            {@link de.bund.bsi.tsms.tsmapi.parameters.IServiceCommand}.
     */
    public ServiceCommandResult(final ECommandResultType commandExecutionStatus) {
        this(commandExecutionStatus, new IPersonalizationResult[]{});
    }

    /**
     * Constructor.
     *
     * @param commandExecutionStatus
     *            Execution result of the
     *            {@link de.bund.bsi.tsms.tsmapi.parameters.IServiceCommand}.
     * @param personalizationResults
     *            Result for a
     *            {@link de.bund.bsi.tsms.tsmapi.parameters.IPersonalizeServiceCommand}.
     */
    public ServiceCommandResult(final ECommandResultType commandExecutionStatus,
            final IPersonalizationResult[] personalizationResults) {
        this.commandExecutionStatus = commandExecutionStatus;
        this.personalizationResults = personalizationResults;
    }

    /**
     * An Integer value indicating the process execution result.<br>
     * <br>
     * Possible values are:<br>
     * <br>
     * <ul>
     * <li>0: Successful Execution</li>
     * <li>&gt; 0: error, see {@link ECommandResultType}</li>
     * </ul>
     *
     * @return Execution result status for each command.
     */
    @Override
    public ECommandResultType getCommandExecutionStatus() {
        return commandExecutionStatus;
    }

    /**
     * Additional data originating from the execution of
     * PersonalizeServiceCommand.<br>
     * <br>
     * This attribute is only relevant for a personalize request and thus empty for
     * install and activate requests.
     *
     * @return Personalization result data, is empty list when command was not a
     *         {@link de.bund.bsi.tsms.tsmapi.parameters.IPersonalizeServiceCommand}.
     */
    @Override
    public IPersonalizationResult[] getPersonalizationResults() {
        return new IPersonalizationResult[0];
    }

    /**
     * Checks equality of commandExecutionStatus and personalizationResults.
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
        ServiceCommandResult that = (ServiceCommandResult) o;
        return commandExecutionStatus == that.commandExecutionStatus
                && Arrays.equals(personalizationResults, that.personalizationResults);
    }

    /**
     * Creates hash from commandExecutionStatus and personalizationResults.
     *
     * @return Hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(commandExecutionStatus, personalizationResults);
    }

    /**
     * Creates string of class name and all members.
     *
     * @return Representation string of this class.
     */
    @Override
    public String toString() {
        return "ServiceCommandResult{" + "commandExecutionStatus=" + commandExecutionStatus
                + ", personalizationResults=" + Arrays.toString(personalizationResults) + '}';
    }
}
