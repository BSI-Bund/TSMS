package de.bsi.tsms.tsmapi.results;

import java.util.List;

/**
 * A PersonalizationResult contains data of an executed
 * {@link de.bsi.tsms.tsmapi.parameters.IPersonalizeServiceCommand}.<br>
 * <br>
 * This class is used as result data object for {@link IDeployServiceResult} and
 * {@link IUpdateServiceResult}.
 */
public interface IPersonalizationResult {

    /**
     * AID of the personalized Service Instance. The AID format follows
     * [ISO/IEC7816-4].
     *
     * @return AID of the secure application personalized on the secure component.
     */
    String getApplicationInstanceAid();

    /**
     * A list of CommandResponsePair objects which carry command and response APDUs
     * for individual personalization commands performed on the Service Instance.
     *
     * @return APDU list executed.
     */
    List<ICommandResponsePair> getResults();

}
