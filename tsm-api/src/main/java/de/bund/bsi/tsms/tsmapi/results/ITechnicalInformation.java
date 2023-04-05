package de.bund.bsi.tsms.tsmapi.results;

import java.util.Map;

/**
 * A TechnicalInformation object contains information about the underlying
 * platform and the service parameters.<br>
 * <br>
 * <p>
 * These technical information are provided by:
 * <ul>
 * <li>{@link IGetServiceInstancesResult}</li>
 * <li>{@link IDeployServiceResult}</li>
 * <li>{@link IUpdateServiceResult}</li>
 * </ul>
 *
 * @since 1.0
 */
public interface ITechnicalInformation {

    /**
     * List of parameters of the Service. The parameters can be defined generally
     * for the Service or separately for each Flavor via the TSM-Backend.<br>
     * <br>
     * Empty if no spParameters are defined.
     *
     * @return Service parameters as key value pairs.
     */
    Map<String, String> getSpParameters();

    /**
     * Returns the ID of the installed Service.<br>
     * <br>
     * Empty if no Service is installed.
     *
     * @return ID of service installed.
     */
    String getServiceId();

    /**
     * Returns the version tag of the installed Service.<br>
     * <br>
     * Empty if no Service and thus no applet(s) are installed.
     *
     * @return Version of service installed.
     */
    String getServiceVersionTag();

    /**
     * Returns the ID of the installed Flavor for this Service.<br>
     * <br>
     * Empty if no Service and thus no applet(s) are installed.
     *
     * @return ID of the flavor installed.
     */
    String getFlavorId();

    /**
     * Returns the name of the installed Flavor for this Service.<br>
     * <br>
     * Empty if no service and thus no applet(s) are installed.
     *
     * @return Name of the flavor installed.
     */
    String getFlavorName();

    /**
     * Returns the ID of the Secure Component Profile. <br>
     * Empty if no Service and thus no applet(s) are installed.
     *
     * @return Profile ID
     */
    String getProfileId();
}
