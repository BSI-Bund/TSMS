package de.bund.bsi.tsms.tsmapi.results;

import de.bund.bsi.tsms.tsmapi.ECommandResultType;

/**
 * A ServiceCommandResult object is used to provide the result of execution of
 * an {@link de.bund.bsi.tsms.tsmapi.parameters.IServiceCommand}.
 *
 * @since 1.0
 */
public interface IServiceCommandResult {

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
    ECommandResultType getCommandExecutionStatus();

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
    IPersonalizationResult[] getPersonalizationResults();
}
