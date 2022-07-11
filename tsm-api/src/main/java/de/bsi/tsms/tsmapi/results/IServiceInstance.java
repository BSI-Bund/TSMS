package de.bsi.tsms.tsmapi.results;

import de.bsi.tsms.tsmapi.EServiceInstanceState;
import de.bsi.tsms.tsmapi.EServiceOperation;

/**
 * A ServiceInstance represents an instance of a Service on a concrete Secure
 * Component. It holds information about the current life-cycle state of the
 * Service and the underlying platform used.<br>
 * <br>
 * The ServiceInstance is created via the method
 * {@link de.bsi.tsms.tsmapi.ITsmApiService#createServiceInstance}. Once it is
 * created, it can be retrieved via the method
 * {@link de.bsi.tsms.tsmapi.ITsmApiService#getServiceInstances}.
 */
public interface IServiceInstance {

    /**
     * Returns the Service Instance identifier. This ID is required for most TSM-API
     * methods to address the Service for a concrete handset.
     *
     * @return Identifier of service instance.
     */
    String getId();

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
    EServiceInstanceState getState();

    /**
     * Returns the information about the underlying platform profile and list of all
     * parameters defined (by spParameters) for the Service or overwritten via
     * certain Flavor in the TSM-Backend.<br>
     *
     * @return Technical information about underlying platform.
     */
    ITechnicalInformation getTechnicalInformation();

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
    EServiceOperation getLastOperation();

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
