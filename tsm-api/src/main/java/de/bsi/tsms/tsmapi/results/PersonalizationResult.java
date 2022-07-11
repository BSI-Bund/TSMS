package de.bsi.tsms.tsmapi.results;

import java.util.List;
import java.util.Objects;

/**
 * Default implementation of {@link IPersonalizationResult}.
 */
public class PersonalizationResult implements IPersonalizationResult {

    /**
     * AID of the personalized Service Instance. The AID format follows
     * [ISO/IEC7816-4].
     */
    private String applicationInstanceAid;
    /**
     * List of APDU commands executed.
     */
    private List<ICommandResponsePair> results;

    /**
     * Constructor.
     *
     * @param applicationInstanceAid
     *            AID of the personalized Service Instance. The AID format follows
     *            [ISO/IEC7816-4].
     * @param results
     *            List of APDU commands executed.
     */
    public PersonalizationResult(final String applicationInstanceAid,
            final List<ICommandResponsePair> results) {
        this.applicationInstanceAid = applicationInstanceAid;
        this.results = results;
    }

    /**
     * AID of the personalized Service Instance. The AID format follows
     * [ISO/IEC7816-4].
     *
     * @return AID of the secure application personalized on the secure component.
     */
    @Override
    public String getApplicationInstanceAid() {
        return applicationInstanceAid;
    }

    /**
     * A list of CommandResponsePair objects which carry command and response APDUs
     * for individual personalization commands performed on the Service Instance.
     *
     * @return APDU list executed.
     */
    @Override
    public List<ICommandResponsePair> getResults() {
        return results;
    }

    /**
     * Checks equality of applicationInstanceAid and results.
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
        PersonalizationResult that = (PersonalizationResult) o;
        return Objects.equals(applicationInstanceAid, that.applicationInstanceAid)
                && Objects.equals(results, that.results);
    }

    /**
     * Creates hash from applicationInstanceAid and results.
     *
     * @return Hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(applicationInstanceAid, results);
    }

    /**
     * Creates string of class name and all members.
     *
     * @return Representation string of this class.
     */
    @Override
    public String toString() {
        return "PersonalizationResult{" + "applicationInstanceAid='" + applicationInstanceAid + '\''
                + ", results=" + results + '}';
    }
}
