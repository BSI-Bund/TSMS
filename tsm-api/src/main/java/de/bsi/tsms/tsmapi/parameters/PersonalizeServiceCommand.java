package de.bsi.tsms.tsmapi.parameters;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Default implementation of {@link IPersonalizeServiceCommand}.
 */
public class PersonalizeServiceCommand implements IPersonalizeServiceCommand {

    /**
     * Key-value data pairs to define personalization parameters.
     */
    private Map<String, String> personalizationData;

    /**
     * Constructor with default member initialization.<br>
     * <br>
     * Initialized installationData with empty map.
     */
    public PersonalizeServiceCommand() {
        this(new LinkedHashMap<>());
    }

    /**
     * Constructor.
     *
     * @param personalizationData
     *            Optional map of key value pairs to provide addition data required
     *            for the service personalization.
     */
    public PersonalizeServiceCommand(final Map<String, String> personalizationData) {
        this.personalizationData = personalizationData;
    }

    /**
     * An optional map of key value pairs to provide addition data required for the
     * service personalization.
     *
     * @param personalizationData
     *            Map of key value pairs.
     */
    @Override
    public void setPersonalizationData(final Map<String, String> personalizationData) {
        this.personalizationData = personalizationData;
    }

    /**
     * Returns the personalization data.
     *
     * @return Personalization data, optional, might be empty.
     */
    @Override
    public Map<String, String> getPersonalizationData() {
        return personalizationData;
    }

    /**
     * Add single personalization key-value data.
     *
     * @param key
     *            Parameter key.
     * @param value
     *            Parameter value.
     */
    @Override
    public void addPersonalizationData(final String key, final String value) {
        if (personalizationData == null) {
            personalizationData = new LinkedHashMap<>();
        }
        personalizationData.put(key, value);
    }

    /**
     * Remove single personalization data.
     *
     * @param key
     *            Parameter key to remove.
     */
    @Override
    public void removePersonalizationData(final String key) {
        if (personalizationData != null) {
            personalizationData.remove(key);
        }
    }

    /**
     * Checks equality of personalizationData.
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
        PersonalizeServiceCommand that = (PersonalizeServiceCommand) o;
        return Objects.equals(personalizationData, that.personalizationData);
    }

    /**
     * Creates hash from personalizationData.
     *
     * @return Hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(personalizationData);
    }

    /**
     * Creates string of class name and all members.
     *
     * @return Representation string of this class.
     */
    @Override
    public String toString() {
        return "PersonalizeServiceCommand{" + "personalizationData=" + personalizationData + '}';
    }
}
