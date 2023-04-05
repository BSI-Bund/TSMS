package de.bund.bsi.tsms.tsmapi.parameters;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Default implementation of {@link IInstallServiceCommand}.
 *
 * @since 1.0
 */
public class InstallServiceCommand implements IInstallServiceCommand {

    /**
     * Key-value map of install parameters.
     */
    private Map<String, String> installationData;

    /**
     * Constructor with default member initialization.<br>
     * <br>
     * Initialized installationData with empty map.
     */
    public InstallServiceCommand() {
        this(new LinkedHashMap<>());
    }

    /**
     * Constructor.
     *
     * @param installationData
     *            Optional map of key value pairs to provide additional data
     *            required for the service installation.
     */
    public InstallServiceCommand(final Map<String, String> installationData) {
        this.installationData = installationData;
    }

    /**
     * An optional map of key value pairs to provide additional data required for
     * the service installation.
     *
     * @param installationData
     *            Map of key value pairs to configure installation.
     */
    @Override
    public void setInstallationData(final Map<String, String> installationData) {
        this.installationData = installationData;
    }

    /**
     * Returns the additional installation data.
     *
     * @return Installation data, optional, might be empty.
     */
    @Override
    public Map<String, String> getInstallationData() {
        return installationData;
    }

    /**
     * Add single installation data.
     *
     * @param key
     *            Parameter key.
     * @param value
     *            Parameter value.
     */
    @Override
    public void addInstallationData(final String key, final String value) {
        if (installationData == null) {
            installationData = new LinkedHashMap<>();
        }
        installationData.put(key, value);
    }

    /**
     * Remove single installation data.
     *
     * @param key
     *            Parameter key to remove.
     */
    @Override
    public void removeInstallationData(final String key) {
        if (installationData != null) {
            installationData.remove(key);
        }
    }

    /**
     * Checks equality of installationData.
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
        InstallServiceCommand that = (InstallServiceCommand) o;
        return Objects.equals(installationData, that.installationData);
    }

    /**
     * Creates hash from installationData.
     *
     * @return Hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(installationData);
    }

    /**
     * Creates string of class name and all members.
     *
     * @return Representation string of this class.
     */
    @Override
    public String toString() {
        return "InstallServiceCommand{" + "installationData=" + installationData + '}';
    }
}
