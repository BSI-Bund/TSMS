package de.bsi.tsms.tsmapi.parameters;

import java.util.Map;

/**
 * A PersonalizeServiceCommand requests the personalization of Service
 * components as configured by the SP via the REST-API. After successful
 * execution, the Service Instance reaches the state
 * {@link de.bsi.tsms.tsmapi.EServiceInstanceState#PERSONALIZED}
 */
public interface IPersonalizeServiceCommand extends IServiceCommand {

    /**
     * An optional map of key value pairs to provide addition data required for the
     * service personalization.
     *
     * @param personalizationData
     *            Map of key value pairs.
     */
    void setPersonalizationData(Map<String, String> personalizationData);

    /**
     * Returns the personalization data.
     *
     * @return Personalization data, optional, might be empty.
     */
    Map<String, String> getPersonalizationData();

    /**
     * Add single personalization key-value data.
     *
     * @param key
     *            Parameter key.
     * @param value
     *            Parameter value.
     */
    void addPersonalizationData(String key, String value);

    /**
     * Remove single personalization data.
     *
     * @param key
     *            Parameter key to remove.
     */
    void removePersonalizationData(String key);
}
