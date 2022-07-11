package de.bsi.tsms.tsmapi.results;

/**
 * ProcessStart is a callback message and is used in
 * {@link de.bsi.tsms.tsmapi.ITsmProcessListener} indicating that the execution
 * of a process has started.
 */
public interface IProcessStart {

    /**
     * Returns the process identifier.
     *
     * @return Identifier of the process.
     */
    String getProcessId();

    /**
     * The device application identifier which is a hash value of the certificate of
     * the device Application Provider, i.e. the SP.
     *
     * @return App identifier.
     */
    String getCallerId();

    /**
     * Returns the date time when process was started.<br>
     * <br>
     * Datetime strings express Coordinated Universal Time (UTC) including
     * milliseconds with a special UTC designator (“Z”) according to [ISO8601].
     *
     * @return Datetime string (start of process).
     */
    String getStartDate();

}
