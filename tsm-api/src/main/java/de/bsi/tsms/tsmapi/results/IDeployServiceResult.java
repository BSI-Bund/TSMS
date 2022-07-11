package de.bsi.tsms.tsmapi.results;

import de.bsi.tsms.tsmapi.EServiceInstanceState;

import java.util.List;

/**
 * This class defines the result of the TSM-API method
 * {@link de.bsi.tsms.tsmapi.ITsmApiService#deployService}.
 */
public interface IDeployServiceResult {

    /**
     * Returns the result of the process execution.
     *
     * @return Process execution result.
     */
    IProcessInfo getProcessInfo();

    /**
     * Returns the current state of the Service Instance.<br>
     * <br>
     * One of:<br>
     * <br>
     * <ul>
     * <li>1: {@link EServiceInstanceState#NOT_DEPLOYED}</li>
     * <li>10: {@link EServiceInstanceState#INITIALIZED}</li>
     * <li>11: {@link EServiceInstanceState#INSTALLED}</li>
     * <li>12: {@link EServiceInstanceState#PERSONALIZED}</li>
     * <li>14: {@link EServiceInstanceState#ACTIVATED}</li>
     * <li>21: {@link EServiceInstanceState#OPERATIONAL}</li>
     * <li>22: {@link EServiceInstanceState#SUSPENDED}</li>
     * <li>25: {@link EServiceInstanceState#IN_ERROR}</li>
     * </ul>
     *
     * @return Current state of the Service Instance.
     */
    EServiceInstanceState getServiceInstanceState();

    /**
     * Returns the information about the underlying platform (JavaCard version, CSP
     * version, etc.) and list of all parameters defined (by spParameters) for the
     * Service or overwritten via certain Flavor in the TSM-Backend.<br>
     *
     * @return Technical information about underlying platform.
     */
    ITechnicalInformation getTechnicalInformation();

    /**
     * Returns the list of {@link IServiceCommandResult} that represent the results
     * of each requested service commands.
     *
     * @return A result for each
     *         {@link de.bsi.tsms.tsmapi.parameters.IServiceCommand}.
     */
    List<IServiceCommandResult> getServiceCommandResults();

    /**
     * Returns the name of SE reader that can be used via OpenMobileAPI to access
     * the service components.<br>
     * See also section 4.2.6 in "GlobalPlatform Technology, Open Mobile API
     * Specification, V3.3", 2018.<br>
     *
     * @return OMAPI reader name.
     */
    String getReader();
}
