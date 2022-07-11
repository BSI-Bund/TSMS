package de.bsi.tsms.tsmapi.results;

import de.bsi.tsms.tsmapi.EServiceInstanceState;
import de.bsi.tsms.tsmapi.EServiceOperation;

import java.util.Objects;

/**
 * Default implementation of {@link IServiceInstance}.
 */
public class ServiceInstance implements IServiceInstance {

    /**
     * ID of the Service.
     */
    private String id;
    /**
     * Current state of the Service Instance.
     */
    private EServiceInstanceState state;
    /**
     * Information about the underlying platform profile and a list of all
     * parameters defined for the Service or overwritten via certain Flavor in the
     * TSM-Backend.
     */
    private ITechnicalInformation technicalInformation;
    /**
     * Indicates the last operation that has been performed on the Service Instance.
     */
    private EServiceOperation lastOperation;
    /**
     * Name of SE reader that can be used to access the secure component.
     */
    private String reader;

    /**
     * Constructor.<br>
     * <br>
     * Initialize all data with empty strings or empty maps.
     */
    public ServiceInstance() {
        id = "";
        state = EServiceInstanceState.UNKNOWN;
        technicalInformation = new TechnicalInformation();
        lastOperation = EServiceOperation.NO_OPERATION;
        reader = "";
    }

    /**
     * Constructor.
     *
     * @param id
     *            ID of the Service.
     * @param state
     *            Current state of the Service Instance.
     * @param technicalInformation
     *            Information about the underlying platform profile and a list of
     *            all parameters defined for the Service or overwritten via certain
     *            Flavor in the TSM-Backend.
     * @param lastOperation
     *            Indicates the last operation that has been performed on the
     *            Service Instance.
     * @param reader
     *            Name of SE reader that can be used to access the secure component.
     */
    public ServiceInstance(final String id, final EServiceInstanceState state,
            final ITechnicalInformation technicalInformation, final EServiceOperation lastOperation,
            final String reader) {
        this.id = id;
        this.state = state;
        this.technicalInformation = technicalInformation;
        this.lastOperation = lastOperation;
        this.reader = reader;
    }

    /**
     * Returns the Service Instance identifier. This ID is required for most TSM-API
     * methods to address the Service for a concrete handset.
     *
     * @return Identifier of service instance.
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Returns the current state of the service instance. <br>
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
     * @return Current state of the service.
     */
    @Override
    public EServiceInstanceState getState() {
        return state;
    }

    /**
     * Returns the information about the underlying platform profile and list of all
     * parameters defined (by spParameters) for the Service or overwritten via
     * certain Flavor in the TSM-Backend.<br>
     *
     * @return Technical information about underlying platform.
     */
    @Override
    public ITechnicalInformation getTechnicalInformation() {
        return technicalInformation;
    }

    /**
     * Indicates the last operation that has been performed on the Service Instance.
     * <br>
     * <br>
     * One of:<br>
     * <br>
     * <ul>
     * <li>10 {@link EServiceOperation#SERVICE_DEPLOYMENT_INSTALLATION}</li>
     * <li>11: {@link EServiceOperation#SERVICE_DEPLOYMENT_PERSONALIZATION}</li>
     * <li>12: {@link EServiceOperation#SERVICE_DEPLOYMENT_ACTIVATION}</li>
     * <li>13: {@link EServiceOperation#SERVICE_DEPLOYMENT_FINALIZE}</li>
     * <li>20: {@link EServiceOperation#SERVICE_UPDATE_INSTALLATION}</li>
     * <li>21: {@link EServiceOperation#SERVICE_UPDATE_PERSONALIZATION}</li>
     * <li>22: {@link EServiceOperation#SERVICE_UPDATE_ACTIVATION}</li>
     * <li>23: {@link EServiceOperation#SERVICE_UPDATE_FINALIZE}</li>
     * <li>30: {@link EServiceOperation#SERVICE_SUSPENSION}</li>
     * <li>31: {@link EServiceOperation#SERVICE_RESUMPTION}</li>
     * <li>40: {@link EServiceOperation#SERVICE_TERMINATION}</li>
     * </ul>
     *
     * @return Last operation applied on the service.
     */
    @Override
    public EServiceOperation getLastOperation() {
        return lastOperation;
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
     * Checks equality of id, state, technicalInformation, lastOperation and reader.
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
        ServiceInstance that = (ServiceInstance) o;
        return Objects.equals(id, that.id) && state == that.state
                && Objects.equals(technicalInformation, that.technicalInformation)
                && lastOperation == that.lastOperation && Objects.equals(reader, that.reader);
    }

    /**
     * Creates hash from id, state, technicalInformation, lastOperation and reader.
     *
     * @return Hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, state, technicalInformation, lastOperation, reader);
    }

    /**
     * Creates string of class name and all members.
     *
     * @return Representation string of this class.
     */
    @Override
    public String toString() {
        return "ServiceInstance{" + "id='" + id + '\'' + ", state=" + state
                + ", technicalInformation=" + technicalInformation + ", lastOperation="
                + lastOperation + ", reader='" + reader + '\'' + '}';
    }
}
