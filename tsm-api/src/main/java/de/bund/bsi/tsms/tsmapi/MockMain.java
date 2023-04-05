package de.bund.bsi.tsms.tsmapi;

import de.bund.bsi.tsms.tsmapi.parameters.ActivateServiceCommand;
import de.bund.bsi.tsms.tsmapi.parameters.IServiceCommand;
import de.bund.bsi.tsms.tsmapi.parameters.InstallServiceCommand;
import de.bund.bsi.tsms.tsmapi.results.ICreateServiceInstanceResult;
import de.bund.bsi.tsms.tsmapi.results.IGetServiceInstancesResult;
import de.bund.bsi.tsms.tsmapi.results.IDeployServiceResult;
import de.bund.bsi.tsms.tsmapi.results.IUpdateServiceResult;
import de.bund.bsi.tsms.tsmapi.results.ITerminateServiceResult;
import de.bund.bsi.tsms.tsmapi.results.IServiceInstance;
import de.bund.bsi.tsms.tsmapi.results.IServiceDeploymentAvailableResult;
import de.bund.bsi.tsms.tsmapi.results.IServiceUpdateAvailableResult;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

/**
 * This class provides some code samples how to use the {@link ITsmApiService}
 * to install, update and remove JavaCard applets.<br>
 * <br>
 * Attention: this class uses only the mock implementation of the TSM-API
 * interfaces and does not contain a concrete TSM implementation needed to
 * install applets on secure elements. The code used here just illustrated how
 * the API should be used.
 *
 * @since 1.0
 */
public final class MockMain {

    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(MockMain.class.getName());
    /**
     * Sample service id.
     */
    private static final String SERVICE_ID = "SAMPLE_SERVICE_ID";
    /**
     * Sample version tag.
     */
    private static final String VERSION_TAG = "1.0.0";
    /**
     * Sample version tag for update.
     */
    private static final String VERSION_TAG_NEW = "2.0.0";

    /**
     * Private constructor. Should not be used, since this is class contains a
     * main() method.
     */
    private MockMain() {
    }

    /**
     * Main class to run the sample code with mock data.
     *
     * @param args
     *            Not used.
     */
    public static void main(final String[] args) {
        installApplets();
        updateApplets();
        removeApplets();
    }

    /**
     * Code sample how to install applets with the TSM-API.
     */
    private static void installApplets() {
        ITsmApiService tsmApiService = new MockTsmApiService();

        IServiceInstance serviceInstance = null;
        try {
            IGetServiceInstancesResult data = tsmApiService.getServiceInstances(SERVICE_ID);

            if (data == null) {
                LOG.severe("ERROR: getServiceInstances returned " + data);
            } else if (data.getExecutionStatus() != EErrorType.NO_ERROR) {
                LOG.severe("ERROR: getServiceInstances failed with error: "
                        + data.getExecutionStatus() + " - " + data.getExecutionMessage());
            } else if (!data.getServiceInstances().isEmpty()) {
                serviceInstance = data.getServiceInstances().get(0);
                LOG.info("Service instance already existing");
            }
        } catch (Exception ex) {
            LOG.severe("ERROR: Exception occurred" + ex);
        }

        // 2. Check if a secure component is available for deployment
        EDeploymentAvailable deploymentAvailable = null;
        try {
            CompletableFuture<IServiceDeploymentAvailableResult> future = tsmApiService
                    .checkServiceDeploymenAvailable(SERVICE_ID, VERSION_TAG);
            IServiceDeploymentAvailableResult data = future.get();
            if (data == null) {
                LOG.severe("ERROR: checkServiceDeploymentAvailable() returned " + data);
            } else if (data.getExecutionStatus() != EErrorType.NO_ERROR) {
                LOG.severe("ERROR: checkServiceDeploymentAvailable failed with error: "
                        + data.getExecutionStatus() + " - " + data.getExecutionMessage());
            } else if (data.getDeploymentAvailable() != EDeploymentAvailable.DEPLOYMENT_AVAILABLE) {
                LOG.severe("ERROR: device not supported");
            } else {
                deploymentAvailable = data.getDeploymentAvailable();
            }
        } catch (Exception ex) {
            LOG.severe("ERROR: Exception occurred" + ex);
        }

        // 3. Create a service instance (when not yet existing and deployment is
        // possible):
        if (serviceInstance == null
                && deploymentAvailable == EDeploymentAvailable.DEPLOYMENT_AVAILABLE) {
            try {
                ICreateServiceInstanceResult data = tsmApiService.createServiceInstance(SERVICE_ID,
                        VERSION_TAG);
                if (data == null) {
                    LOG.severe("ERROR: createServiceInstance returned " + data);
                } else if (data.getExecutionStatus() != EErrorType.NO_ERROR) {
                    LOG.severe("ERROR: createServiceInstance failed with error: "
                            + data.getExecutionStatus() + " - " + data.getExecutionMessage());
                } else {
                    serviceInstance = data.getServiceInstance();
                }
            } catch (Exception ex) {
                LOG.severe("ERROR: Exception occurred" + ex);
            }
        }

        // 4. Start the deployment process to install the JavaCard applet(s)
        if (serviceInstance != null) {
            try {
                // configure to use "install" and "activate" commands for deployment
                List<IServiceCommand> commands = Arrays.asList(new InstallServiceCommand(),
                        new ActivateServiceCommand(false));

                CompletableFuture<IDeployServiceResult> future = tsmApiService
                        .deployService(serviceInstance.getId(), commands, true, null);

                IDeployServiceResult data = future.get();

                if (data == null || data.getProcessInfo() == null) {
                    LOG.severe("ERROR: method deployService returned " + data);
                } else if (data.getProcessInfo().getExecutionStatus() != EErrorType.NO_ERROR) {
                    LOG.severe("ERROR: deployService failed with error: "
                            + data.getProcessInfo().getExecutionStatus() + " - "
                            + data.getProcessInfo().getExecutionMessage());
                } else {
                    LOG.info("Process information: " + data.getProcessInfo());
                    LOG.info("Installation state: " + data.getServiceInstanceState());
                    LOG.info("SecureComponent used: " + data.getReader());
                    LOG.info("Technical information: " + data.getTechnicalInformation());
                    LOG.info("Command results: " + data.getServiceCommandResults());
                }
            } catch (Exception ex) {
                LOG.severe("ERROR: Exception occurred" + ex);
            }
        }
    }

    /**
     * Code sample how to update applets with the TSM-API.
     */
    private static void updateApplets() {
        ITsmApiService tsmApiService = new MockTsmApiService();

        // 1. Check for existing service instances on this smartphone
        IServiceInstance serviceInstance = null;
        try {
            IGetServiceInstancesResult data = tsmApiService.getServiceInstances(SERVICE_ID);

            if (data == null) {
                LOG.severe("ERROR: getServiceInstances returned " + data);
            } else if (data.getExecutionStatus() != EErrorType.NO_ERROR) {
                LOG.severe("ERROR: getServiceInstances failed with error: "
                        + data.getExecutionStatus() + " - " + data.getExecutionMessage());
            } else if (data.getServiceInstances().isEmpty()) {
                LOG.severe("ERROR: no service installed yet");
            } else {
                serviceInstance = data.getServiceInstances().get(0);
                LOG.info("ServiceInstance.id: " + serviceInstance.getId());
                LOG.info("Installation state: " + serviceInstance.getState());
                LOG.info("SecureComponent: " + serviceInstance.getReader());
            }
        } catch (Exception ex) {
            LOG.severe("ERROR: Exception occurred" + ex);
        }

        // 2. Check that the smartphone is supported by the new version of the applet
        EUpdateAvailable updateAvailable = null;
        if (serviceInstance != null) {
            try {
                CompletableFuture<IServiceUpdateAvailableResult> future = tsmApiService
                        .checkServiceUpdateAvailable(serviceInstance.getId(), VERSION_TAG_NEW);
                IServiceUpdateAvailableResult data = future.get();

                if (data == null) {
                    LOG.severe("ERROR: checkServiceUpdateAvilable returned " + data);
                } else if (data.getExecutionStatus() != EErrorType.NO_ERROR) {
                    LOG.severe("ERROR: checkServiceUpdateAvilable failed with error: "
                            + data.getExecutionStatus() + " - " + data.getExecutionMessage());
                } else if (data.getUpdateAvailable() != EUpdateAvailable.UPDATE_AVAILABLE) {
                    LOG.severe("ERROR: the device is not eligible for update");
                } else {
                    updateAvailable = data.getUpdateAvailable();
                    LOG.info("updateAvailable: " + data.getUpdateAvailable());
                    LOG.info("newTechnicalInformation: " + data.getNewTechnicalInformation());
                }
            } catch (Exception ex) {
                LOG.severe("ERROR: Exception occurred" + ex);
            }
        }

        // 3. Start the update process to install the updated JavaCard applet(s)
        if (updateAvailable == EUpdateAvailable.UPDATE_AVAILABLE) {
            try {
                // configure to use "install" and "activate" during deployment
                List<IServiceCommand> commands = Arrays.asList(new InstallServiceCommand(),
                        new ActivateServiceCommand(false));

                CompletableFuture<IUpdateServiceResult> future = tsmApiService.updateService(
                        serviceInstance.getId(), VERSION_TAG_NEW, commands, true, null);
                IUpdateServiceResult data = future.get();

                if (data == null || data.getProcessInfo() == null) {
                    LOG.severe("ERROR: updateService returned " + data);
                } else if (data.getProcessInfo().getExecutionStatus() != EErrorType.NO_ERROR) {
                    LOG.severe("ERROR: updateService failed with error: "
                            + data.getProcessInfo().getExecutionStatus() + " - "
                            + data.getProcessInfo().getExecutionMessage());
                } else {
                    LOG.info("Process information: " + data.getProcessInfo());
                    LOG.info("Installation state: " + data.getServiceInstanceState());
                    LOG.info("SecureComponent used: " + data.getReader());
                    LOG.info("Technical information: " + data.getTechnicalInformation());
                    LOG.info("Command results: " + data.getServiceCommandResults());
                }
            } catch (Exception ex) {
                LOG.severe("ERROR: Exception occurred" + ex);
            }
        }
    }

    /**
     * Code sample how to remove applets with the TSM-API.
     */
    private static void removeApplets() {
        ITsmApiService tsmApiService = new MockTsmApiService();

        // 1. Retrieve the service instances existing on this smartphone and choose one
        IServiceInstance serviceInstance = null;
        try {
            IGetServiceInstancesResult data = tsmApiService.getServiceInstances(SERVICE_ID);

            if (data == null) {
                LOG.severe("ERROR: getServiceInstances returned " + data);
            } else if (data.getExecutionStatus() != EErrorType.NO_ERROR) {
                LOG.severe("ERROR: getServiceInstances failed with error: "
                        + data.getExecutionStatus() + " - " + data.getExecutionMessage());
            } else if (data.getServiceInstances().isEmpty()) {
                LOG.severe("ERROR: no service installed yet");
            } else {
                serviceInstance = data.getServiceInstances().get(0);
                LOG.info("ServiceInstance.id: " + serviceInstance.getId());
                LOG.info("Installation state: " + serviceInstance.getState());
                LOG.info("SecureComponent: " + serviceInstance.getReader());
            }
        } catch (Exception ex) {
            LOG.severe("ERROR: Exception occurred" + ex);
        }

        // 2. Start the terminate process to uninstall the JavaCard applets (if
        // serviceInstance exists):
        if (serviceInstance != null) {
            try {
                CompletableFuture<ITerminateServiceResult> future = tsmApiService
                        .terminateService(serviceInstance.getId(), null);
                ITerminateServiceResult data = future.get();

                if (data == null || data.getProcessInfo() == null) {
                    LOG.severe("ERROR: terminateService returned " + data);
                } else if (data.getProcessInfo().getExecutionStatus() != EErrorType.NO_ERROR) {
                    LOG.severe("ERROR: terminateService failed with error: "
                            + data.getProcessInfo().getExecutionStatus() + " - "
                            + data.getProcessInfo().getExecutionMessage());
                } else {
                    LOG.info("Process information: " + data.getProcessInfo());
                }
            } catch (Exception ex) {
                LOG.severe("ERROR: Exception occurred" + ex);
            }
        }
    }
}
