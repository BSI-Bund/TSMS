package de.bsi.tsms.tsmapi.parameters;

import java.util.Map;

/**
 * An InstallServiceCommand requests the loading and installation of Service
 * components as configured by the SP via the REST-API. After successful
 * execution, the Service Instance reaches the state
 * {@link de.bsi.tsms.tsmapi.EServiceInstanceState#INSTALLED}.
 */
public interface IInstallServiceCommand extends IServiceCommand {

    /**
     * An optional map of key value pairs to provide additional data required for
     * the service installation.
     *
     * @param installationData
     *            Map of key value pairs to configure installation.
     */
    void setInstallationData(Map<String, String> installationData);

    /**
     * Returns the additional installation data.
     *
     * @return Installation data, optional, might be empty.
     */
    Map<String, String> getInstallationData();

    /**
     * Add single installation data.
     *
     * @param key
     *            Parameter key.
     * @param value
     *            Parameter value.
     */
    void addInstallationData(String key, String value);

    /**
     * Remove single installation data.
     *
     * @param key
     *            Parameter key to remove.
     */
    void removeInstallationData(String key);
}
