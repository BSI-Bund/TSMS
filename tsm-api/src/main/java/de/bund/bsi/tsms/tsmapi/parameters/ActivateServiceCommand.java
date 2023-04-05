package de.bund.bsi.tsms.tsmapi.parameters;

import java.util.Objects;

/**
 * Default implementation of {@link IActivateServiceCommand}.
 *
 * @since 1.0
 */
public class ActivateServiceCommand implements IActivateServiceCommand {

    /**
     * Member to hold the suspension state. False means not suspended. True means
     * suspended.
     */
    private boolean suspensionControl;

    /**
     * Constructor with default member initialization.<br>
     * <br>
     * Initializes suspensionControl with false (not suspended).
     */
    public ActivateServiceCommand() {
        this(false);
    }

    /**
     * Constructor.
     *
     * @param suspensionControl
     *            When true, then the Service Instance shall be suspended.
     */
    public ActivateServiceCommand(final boolean suspensionControl) {
        this.suspensionControl = suspensionControl;
    }

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
    @Override
    public void setSuspensionControl(final boolean suspensionControl) {
        this.suspensionControl = suspensionControl;
    }

    /**
     * Shall the service instance be suspended?
     *
     * @return When true, then the Service Instance shall be suspended.
     */
    @Override
    public boolean isSuspensionControl() {
        return suspensionControl;
    }

    /**
     * Checks equality of suspensionControl.
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
        ActivateServiceCommand that = (ActivateServiceCommand) o;
        return suspensionControl == that.suspensionControl;
    }

    /**
     * Creates hash from suspensionControl.
     *
     * @return Hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(suspensionControl);
    }

    /**
     * Creates string of class name and all members.
     *
     * @return Representation string of this class.
     */
    @Override
    public String toString() {
        return "ActivateServiceCommand{" + "suspensionControl=" + suspensionControl + '}';
    }
}
