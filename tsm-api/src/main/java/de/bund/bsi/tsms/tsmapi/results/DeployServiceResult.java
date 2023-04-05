package de.bund.bsi.tsms.tsmapi.results;

import de.bund.bsi.tsms.tsmapi.EServiceInstanceState;

import java.util.List;
import java.util.Objects;

/**
 * Default implementation of {@link IDeployServiceResult}.
 *
 * @since 1.0
 */
public class DeployServiceResult implements IDeployServiceResult {

    /**
     * Information about the executed process.
     */
    private IProcessInfo processInfo;
    /**
     * State of the service instance after process execution.
     */
    private EServiceInstanceState serviceInstanceState;
    /**
     * Technical information about the used device.
     */
    private ITechnicalInformation technicalInformation;
    /**
     * Result status for each service command
     * ({@link de.bund.bsi.tsms.tsmapi.parameters.IInstallServiceCommand},
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IActivateServiceCommand} and
     * {@link de.bund.bsi.tsms.tsmapi.parameters.IPersonalizeServiceCommand}).
     */
    private List<IServiceCommandResult> serviceCommandResults;
    /**
     * OMAPI reader name.
     */
    private String reader;

    /**
     * Constructor.
     *
     * @param processInfo
     *            Information about the executed process.
     * @param serviceInstanceState
     *            State of the service instance after process execution.
     * @param technicalInformation
     *            Technical information about the used device.
     * @param serviceCommandResults
     *            Result status for each service command
     *            ({@link de.bund.bsi.tsms.tsmapi.parameters.IInstallServiceCommand},
     *            {@link de.bund.bsi.tsms.tsmapi.parameters.IActivateServiceCommand}
     *            and
     *            {@link de.bund.bsi.tsms.tsmapi.parameters.IPersonalizeServiceCommand}).
     * @param reader
     *            OMAPI reader name.
     */
    public DeployServiceResult(final ProcessInfo processInfo,
            final EServiceInstanceState serviceInstanceState,
            final ITechnicalInformation technicalInformation,
            final List<IServiceCommandResult> serviceCommandResults, final String reader) {
        this.processInfo = processInfo;
        this.serviceInstanceState = serviceInstanceState;
        this.technicalInformation = technicalInformation;
        this.serviceCommandResults = serviceCommandResults;
        this.reader = reader;
    }

    /**
     * Returns the result of the process execution.
     *
     * @return Process execution result.
     */
    @Override
    public IProcessInfo getProcessInfo() {
        return processInfo;
    }

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
    @Override
    public EServiceInstanceState getServiceInstanceState() {
        return serviceInstanceState;
    }

    /**
     * Returns the information about the underlying platform (JavaCard version, CSP
     * version, etc.) and list of all parameters defined (by spParameters) for the
     * Service or overwritten via certain Flavor in the TSM-Backend.<br>
     *
     * @return Technical information about underlying platform.
     */
    @Override
    public ITechnicalInformation getTechnicalInformation() {
        return technicalInformation;
    }

    /**
     * Returns the list of {@link IServiceCommandResult} that represent the results
     * of each requested service commands.
     *
     * @return A result for each
     *         {@link de.bund.bsi.tsms.tsmapi.parameters.IServiceCommand}.
     */
    @Override
    public List<IServiceCommandResult> getServiceCommandResults() {
        return serviceCommandResults;
    }

    /**
     * Returns the name of SE reader that can be used via OpenMobileAPI to access
     * the service components.<br>
     * See also section 4.2.6 in "GlobalPlatform Technology, Open Mobile API
     * Specification, V3.3", 2018.<br>
     *
     * @return OMAPI reader name.
     */
    @Override
    public String getReader() {
        return reader;
    }

    /**
     * Checks equality of processInfo, serviceInstanceState, serviceCommandResults,
     * technicalInformation and reader.
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
        DeployServiceResult that = (DeployServiceResult) o;
        return processInfo.equals(that.processInfo)
                && serviceInstanceState == that.serviceInstanceState
                && Objects.equals(serviceCommandResults, that.serviceCommandResults)
                && Objects.equals(technicalInformation, that.technicalInformation)
                && Objects.equals(reader, that.reader);
    }

    /**
     * Creates hash from processInfo.
     *
     * @return Hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(processInfo);
    }

    /**
     * Creates string of class name and all members.
     *
     * @return Representation string of this class.
     */
    @Override
    public String toString() {
        return "DeployServiceResult{" + "processInfo=" + processInfo + ", serviceInstanceState="
                + serviceInstanceState + ", serviceCommandResults=" + serviceCommandResults
                + ", technicalInformation=" + technicalInformation + ", reader='" + reader + '\''
                + '}';
    }
}
