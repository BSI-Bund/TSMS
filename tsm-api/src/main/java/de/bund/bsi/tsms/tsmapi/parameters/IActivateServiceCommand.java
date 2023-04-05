package de.bund.bsi.tsms.tsmapi.parameters;

/**
 * An ActivateServiceCommand requests the activation of Service components, e.g.
 * setting application states configured by the SP via the REST-API.<br>
 * <br>
 * After successful execution, the Service instance reaches the
 * {@link de.bund.bsi.tsms.tsmapi.EServiceInstanceState#ACTIVATED} state.<br>
 * Depending on the value of suspensionControl, the Service instance reaches
 * either {@link de.bund.bsi.tsms.tsmapi.EServiceInstanceState#OPERATIONAL} or
 * {@link de.bund.bsi.tsms.tsmapi.EServiceInstanceState#SUSPENDED} once the
 * deployment, respective update, is complete.
 *
 * @since 1.0
 */
public interface IActivateServiceCommand extends IServiceCommand {

    /**
     * Indicates whether the Service Instance state shall be set to
     * {@link de.bund.bsi.tsms.tsmapi.EServiceInstanceState#OPERATIONAL} or
     * {@link de.bund.bsi.tsms.tsmapi.EServiceInstanceState#SUSPENDED} after the
     * deployment process.<br>
     * <br>
     * <ul>
     * <li>If true, the Service Instance shall be suspended.</li>
     * <li>If false, the Service Instance shall be resumed.</li>
     * </ul>
     *
     * @param suspensionControl
     *            Use false to activate service in resume mode.
     */
    void setSuspensionControl(boolean suspensionControl);

    /**
     * Shall the service instance be suspended?
     *
     * @return When true, then the Service Instance shall be suspended.
     */
    boolean isSuspensionControl();
}
