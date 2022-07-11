package de.bsi.tsms.tsmapi.parameters;

/**
 * A ServiceCommand is an abstract type which defines a command to be executed
 * as part of the deployService or updateService functions. <br>
 * <br>
 * The following subtypes exist and are described in the following:<br>
 * <br>
 * <ul>
 * <li>{@link IInstallServiceCommand}</li>
 * <li>{@link IPersonalizeServiceCommand}</li>
 * <li>{@link IActivateServiceCommand}</li>
 * </ul>
 * <br>
 * The States, which can be reached, using those functions are described in
 * {@link de.bsi.tsms.tsmapi.EServiceInstanceState}.
 */
public interface IServiceCommand {
}
