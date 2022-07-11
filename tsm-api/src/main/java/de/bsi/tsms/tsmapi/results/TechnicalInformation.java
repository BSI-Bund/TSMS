package de.bsi.tsms.tsmapi.results;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Default implementation of {@link ITechnicalInformation}.
 */
public class TechnicalInformation implements ITechnicalInformation {

    /**
     * List of service/flavor specific parameters defined via the TSM-Backend.
     */
    private Map<String, String> spParameters;
    /**
     * ID of the installed Service, empty when no service is installed.
     */
    private String serviceId;
    /**
     * Version tag of the installed Service, empty when no service is installed.
     */
    private String serviceVersionTag;
    /**
     * ID of the installed Flavor, empty when no service is installed.
     */
    private String flavorId;
    /**
     * ame of the installed Flavor, empty when no service is installed.
     */
    private String flavorName;
    /**
     * ID of the secure component profile, empty when no service is installed.
     */
    private String profileId;

    /**
     * Constructor.<br>
     * <br>
     * Initialize all data with empty strings or empty maps.
     */
    public TechnicalInformation() {
        spParameters = new LinkedHashMap<>();
        serviceId = "";
        serviceVersionTag = "";
        flavorId = "";
        flavorName = "";
        profileId = "";
    }

    /**
     * Constructor.
     *
     * @param spParameters
     *            List of service/flavor specific parameters defined via the
     *            TSM-Backend.
     * @param serviceId
     *            ID of the installed Service, empty when no service is installed.
     * @param serviceVersionTag
     *            Version tag of the installed Service, empty when no service is
     *            installed.
     * @param flavorId
     *            ID of the installed Flavor, empty when no service is installed.
     * @param flavorName
     *            Name of the installed Flavor, empty when no service is installed.
     * @param profileId
     *            ID of the secure component profile, empty when no service is
     *            installed.
     */
    public TechnicalInformation(final Map<String, String> spParameters, final String serviceId,
            final String serviceVersionTag, final String flavorId, final String flavorName,
            final String profileId) {
        this.spParameters = spParameters;
        this.serviceId = serviceId;
        this.serviceVersionTag = serviceVersionTag;
        this.flavorId = flavorId;
        this.flavorName = flavorName;
        this.profileId = profileId;
    }

    /**
     * List of parameters of the Service. The parameters can be defined generally
     * for the Service or separately for each Flavor via the TSM-Backend.<br>
     * <br>
     * Empty if no spParameters are defined.
     *
     * @return Service parameters as key value pairs.
     */
    @Override
    public Map<String, String> getSpParameters() {
        return spParameters;
    }

    /**
     * Returns the ID of the installed Service.<br>
     * <br>
     * Empty if no Service is installed.
     *
     * @return ID of service installed.
     */
    @Override
    public String getServiceId() {
        return serviceId;
    }

    /**
     * Returns the version tag of the installed Service.<br>
     * <br>
     * Empty if no Service and thus no applet(s) are installed.
     *
     * @return Version of service installed.
     */
    @Override
    public String getServiceVersionTag() {
        return serviceVersionTag;
    }

    /**
     * Returns the ID of the installed Flavor for this Service.<br>
     * <br>
     * Empty if no Service and thus no applet(s) are installed.
     *
     * @return ID of the flavor installed.
     */
    @Override
    public String getFlavorId() {
        return flavorId;
    }

    /**
     * Returns the name of the installed Flavor for this Service.<br>
     * <br>
     * Empty if no service and thus no applet(s) are installed.
     *
     * @return Name of the flavor installed.
     */
    @Override
    public String getFlavorName() {
        return flavorName;
    }

    /**
     * Returns the ID of the Secure Component Profile. <br>
     * Empty if no Service and thus no applet(s) are installed.
     *
     * @return Profile ID
     */
    @Override
    public String getProfileId() {
        return profileId;
    }

    /**
     * Checks equality of spParameters, serviceId, serviceVersionTag, flavorId,
     * flavorName, profileId.
     *
     * @param o
     *            Other object to compare with.
     * @return True, when it is equal.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TechnicalInformation that = (TechnicalInformation) o;
        return Objects.equals(spParameters, that.spParameters)
                && Objects.equals(serviceId, that.serviceId)
                && Objects.equals(serviceVersionTag, that.serviceVersionTag)
                && Objects.equals(flavorId, that.flavorId)
                && Objects.equals(flavorName, that.flavorName)
                && Objects.equals(profileId, that.profileId);
    }

    /**
     * Creates hash from spParameters, serviceId, serviceVersionTag, flavorId,
     * flavorName, profileId.
     *
     * @return Hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(spParameters, serviceId, serviceVersionTag, flavorId, flavorName,
                profileId);
    }

    /**
     * Creates string of class name and all members.
     *
     * @return Representation string of this class.
     */
    @Override
    public String toString() {
        return "TechnicalInformation{" + "spParameters=" + spParameters + ", serviceId='"
                + serviceId + '\'' + ", serviceVersionTag='" + serviceVersionTag + '\''
                + ", flavorId='" + flavorId + '\'' + ", flavorName='" + flavorName + '\''
                + ", profileId='" + profileId + '\'' + '}';
    }
}
