package de.bsi.tsms.tsmapi;

import de.bsi.tsms.tsmapi.parameters.IServiceCommand;
import de.bsi.tsms.tsmapi.results.ICreateServiceInstanceResult;
import de.bsi.tsms.tsmapi.results.ITechnicalInformation;
import de.bsi.tsms.tsmapi.results.IServiceInstance;
import de.bsi.tsms.tsmapi.results.IGetServiceInstancesResult;
import de.bsi.tsms.tsmapi.results.IDeployServiceResult;
import de.bsi.tsms.tsmapi.results.IUpdateServiceResult;
import de.bsi.tsms.tsmapi.results.ITerminateServiceResult;
import de.bsi.tsms.tsmapi.results.IServiceDeploymentAvailableResult;
import de.bsi.tsms.tsmapi.results.IServiceUpdateAvailableResult;
import de.bsi.tsms.tsmapi.results.ISuspendOrResumeResult;
import de.bsi.tsms.tsmapi.results.ProcessInfo;
import de.bsi.tsms.tsmapi.results.TechnicalInformation;
import de.bsi.tsms.tsmapi.results.ServiceInstance;
import de.bsi.tsms.tsmapi.results.CreateServiceInstanceResult;
import de.bsi.tsms.tsmapi.results.GetServiceInstancesResult;
import de.bsi.tsms.tsmapi.results.IServiceCommandResult;
import de.bsi.tsms.tsmapi.results.ServiceCommandResult;
import de.bsi.tsms.tsmapi.results.DeployServiceResult;
import de.bsi.tsms.tsmapi.results.UpdateServiceResult;
import de.bsi.tsms.tsmapi.results.SuspendOrResumeResult;
import de.bsi.tsms.tsmapi.results.TerminateServiceResult;
import de.bsi.tsms.tsmapi.results.ServiceDeploymentAvailableResult;
import de.bsi.tsms.tsmapi.results.ServiceUpdateAvailableResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * This class is a mock implementation of an {@link ITsmApiService}.<br>
 * <br>
 * It does not execute any process. It just returns mock data. Goal of this
 * class is to use it for demo or testing purpose.<br>
 */
public class MockTsmApiService implements ITsmApiService {

    /**
     * Constructor.
     */
    public MockTsmApiService() {
    }

    /**
     * Mock implementation for demo &amp; testing purpose.
     *
     * @param serviceId
     *            Input is set to technicalInformation.
     * @param version
     *            Input is set to technicalInformation.
     * @return Mock data with:
     *         <ul>
     *         <li>serviceInstance.id: '${serviceId}Instance'</li>
     *         <li>serviceInstance.state:
     *         {@link EServiceInstanceState#OPERATIONAL}</li>
     *         <li>serviceInstance.technicalInformation.spParameters: []'</li>
     *         <li>serviceInstance.technicalInformation.serviceId:
     *         '${serviceId}'</li>
     *         <li>serviceInstance.technicalInformation.version: '${version}'</li>
     *         <li>serviceInstance.technicalInformation.flavorId: 'flavor1'</li>
     *         <li>serviceInstance.technicalInformation.flavorName: 'myFlavor'</li>
     *         <li>serviceInstance.technicalInformation.profile: 'profile1'</li>
     *         <li>serviceInstance.lastOperation:
     *         {@link EServiceOperation#NO_OPERATION}</li>
     *         <li>serviceInstance.reader: 'eSE1'</li>
     *         <li>executionStatus: {@link EErrorType#NO_ERROR}</li>
     *         <li>executionMessage: ''</li>
     *         </ul>
     */
    @Override
    public ICreateServiceInstanceResult createServiceInstance(final String serviceId,
            final String version) {
        ITechnicalInformation technicalInformation = new TechnicalInformation(new LinkedHashMap<>(),
                serviceId, version, "flavor1", "myFlavor", "profile1");
        IServiceInstance serviceInstance = new ServiceInstance(serviceId + "Instance",
                EServiceInstanceState.OPERATIONAL, technicalInformation,
                EServiceOperation.NO_OPERATION, "eSE1");

        ICreateServiceInstanceResult result = new CreateServiceInstanceResult(serviceInstance,
                EErrorType.NO_ERROR, "");

        return result;
    }

    /**
     * Mock implementation for demo &amp; testing purpose.
     *
     * @param serviceId
     *            Input is set to technicalInformation.
     * @return Mock data with:
     *         <ul>
     *         <li>serviceInstance[0].id: '${serviceId}Instance'</li>
     *         <li>serviceInstance[0].state:
     *         {@link EServiceInstanceState#OPERATIONAL}</li>
     *         <li>serviceInstance[0].technicalInformation.spParameters: []'</li>
     *         <li>serviceInstance[0].technicalInformation.serviceId:
     *         '${serviceId}'</li>
     *         <li>serviceInstance[0].technicalInformation.version:
     *         '${version}'</li>
     *         <li>serviceInstance[0].technicalInformation.flavorId: 'flavor1'</li>
     *         <li>serviceInstance[0].technicalInformation.flavorName:
     *         'myFlavor'</li>
     *         <li>serviceInstance[0].technicalInformation.profile: 'profile1'</li>
     *         <li>serviceInstance[0].lastOperation:
     *         {@link EServiceOperation#NO_OPERATION}</li>
     *         <li>serviceInstance[0].reader: 'eSE1'</li>
     *         <li>executionStatus: {@link EErrorType#NO_ERROR}</li>
     *         <li>executionMessage: ''</li>
     *         </ul>
     */
    @Override
    public IGetServiceInstancesResult getServiceInstances(final String serviceId) {
        TechnicalInformation technicalInformation = new TechnicalInformation(new LinkedHashMap<>(),
                serviceId, "1.0.0", "flavor1", "myFlavor", "profile1");

        IServiceInstance serviceInstance = new ServiceInstance(serviceId + "Instance",
                EServiceInstanceState.OPERATIONAL, technicalInformation,
                EServiceOperation.NO_OPERATION, "eSE1");

        List<IServiceInstance> serviceInstances = new ArrayList<>();
        serviceInstances.add(serviceInstance);

        GetServiceInstancesResult result = new GetServiceInstancesResult(serviceInstances,
                EErrorType.NO_ERROR, "");

        return result;
    }

    /**
     * Mock implementation for demo &amp; testing purpose.
     *
     * @param serviceInstanceId
     *            Input is ignored.
     * @param serviceCommands
     *            Input is ignored.
     * @param finalizeDeployment
     *            Input is ignored.
     * @param listener
     *            Callback object. May be null.
     * @return Mock data with:
     *         <ul>
     *         <li>processInfo.processInfo: 'process1'</li>
     *         <li>processInfo.startDate: new Date()</li>
     *         <li>processInfo.endDate: new Date()</li>
     *         <li>serviceInstance: 'serviceInstance1'</li>
     *         <li>serviceInstanceState:
     *         {@link EServiceInstanceState#OPERATIONAL}</li>
     *         <li>reader: 'eSE1'</li>
     *         <li>lastOperation:
     *         {@link EServiceOperation#SERVICE_DEPLOYMENT_FINALIZE}</li>
     *         <li>technicalInformation.service: 'service1'</li>
     *         <li>technicalInformation.version: '1.0.0'</li>
     *         <li>technicalInformation.flavorId: 'flavor1'</li>
     *         <li>technicalInformation.flavorName: 'myFlavor'</li>
     *         <li>technicalInformation.profileId: 'profile1'</li>
     *         <li>serviceCommandResults[0]:
     *         {@link ECommandResultType#EXECUTION_SUCCESS}</li>
     *         <li>processInfo.executionStatus: {@link EErrorType#NO_ERROR}</li>
     *         <li>processInfo.executionMessage:''</li>
     *         </ul>
     */
    @Override
    public CompletableFuture<IDeployServiceResult> deployService(final String serviceInstanceId,
            final List<IServiceCommand> serviceCommands, final boolean finalizeDeployment,
            final ITsmProcessListener listener) {
        CompletableFuture<IDeployServiceResult> future = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            ProcessInfo processInfo = new ProcessInfo("process1", new Date(), new Date());

            TechnicalInformation technicalInformation = new TechnicalInformation(
                    new LinkedHashMap<>(), "service1", "1.0.0", "flavor1", "myFlavor", "profile1");

            List<IServiceCommandResult> serviceCommandResults = new ArrayList<>();
            serviceCommandResults
                    .add(new ServiceCommandResult(ECommandResultType.EXECUTION_SUCCESS));

            DeployServiceResult result = new DeployServiceResult(processInfo,
                    EServiceInstanceState.OPERATIONAL, technicalInformation, serviceCommandResults,
                    "eSE1");

            future.complete(result);
            return null;
        });

        return future;
    }

    /**
     * Mock implementation for demo &amp; testing purpose.
     *
     * @param serviceInstanceId
     *            Input is ignored.
     * @param newVersion
     *            Input is set to technicalInformation.
     * @param serviceCommands
     *            Input is ignored.
     * @param finalizeDeployment
     *            Input is ignored.
     * @param listener
     *            Callback object. May be null.
     * @return Mock data with:
     *         <ul>
     *         <li>processInfo.processInfo: 'process2'</li>
     *         <li>processInfo.startDate: new Date()</li>
     *         <li>processInfo.endDate: new Date()</li>
     *         <li>serviceInstance: 'serviceInstance1'</li>
     *         <li>serviceInstanceState:
     *         {@link EServiceInstanceState#OPERATIONAL}</li>
     *         <li>reader: 'eSE1'</li>
     *         <li>lastOperation:
     *         {@link EServiceOperation#SERVICE_DEPLOYMENT_FINALIZE}</li>
     *         <li>technicalInformation.service: 'service1'</li>
     *         <li>technicalInformation.version: ${newVersion}</li>
     *         <li>technicalInformation.flavorId: 'flavor1'</li>
     *         <li>technicalInformation.flavorName: 'myFlavor'</li>
     *         <li>technicalInformation.profileId: 'profile1'</li>
     *         <li>serviceCommandResults[0]:
     *         {@link ECommandResultType#EXECUTION_SUCCESS}</li>
     *         <li>processInfo.executionStatus: {@link EErrorType#NO_ERROR}</li>
     *         <li>processInfo.executionMessage: ''</li>
     *         </ul>
     */
    @Override
    public CompletableFuture<IUpdateServiceResult> updateService(final String serviceInstanceId,
            final String newVersion, final List<IServiceCommand> serviceCommands,
            final boolean finalizeDeployment, final ITsmProcessListener listener) {
        CompletableFuture<IUpdateServiceResult> future = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            ProcessInfo processInfo = new ProcessInfo("process2", new Date(), new Date());

            TechnicalInformation technicalInformation = new TechnicalInformation(
                    new LinkedHashMap<>(), "service1", newVersion, "flavor1", "myFlavor",
                    "profile1");

            List<IServiceCommandResult> serviceCommandResults = new ArrayList<>();
            serviceCommandResults
                    .add(new ServiceCommandResult(ECommandResultType.EXECUTION_SUCCESS));

            UpdateServiceResult result = new UpdateServiceResult(processInfo,
                    EServiceInstanceState.OPERATIONAL, technicalInformation, serviceCommandResults,
                    "eSE1");

            future.complete(result);
            return null;
        });

        return future;
    }

    /**
     * Mock implementation for demo &amp; testing purpose.
     *
     * @param serviceInstanceId
     *            Input is ignored.
     * @param suspensionControl
     *            Input is ignored.
     * @param listener
     *            Callback object. May be null.
     * @return Mock data with:
     *         <ul>
     *         <li>processInfo.processInfo: 'process3'</li>
     *         <li>processInfo.startDate: new Date()</li>
     *         <li>processInfo.endDate: new Date()</li>
     *         <li>processInfo.executionStatus: {@link EErrorType#NO_ERROR}</li>
     *         <li>processInfo.executionMessage: ''</li>
     *         </ul>
     */
    @Override
    public CompletableFuture<ISuspendOrResumeResult> suspendOrResumeService(
            final String serviceInstanceId, final boolean suspensionControl,
            final ITsmProcessListener listener) {
        CompletableFuture<ISuspendOrResumeResult> future = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            ProcessInfo processInfo = new ProcessInfo("process3", new Date(), new Date());
            SuspendOrResumeResult result = new SuspendOrResumeResult(processInfo);
            future.complete(result);
            return null;
        });

        return future;
    }

    /**
     * Mock implementation for demo &amp; testing purpose.
     *
     * @param serviceInstanceId
     *            Input is ignored.
     * @param listener
     *            Callback object. May be null.
     * @return Mock data with:
     *         <ul>
     *         <li>processInfo.processInfo: 'process4'</li>
     *         <li>processInfo.startDate: new Date()</li>
     *         <li>processInfo.endDate: new Date()</li>
     *         <li>processInfo.executionStatus: {@link EErrorType#NO_ERROR}</li>
     *         <li>processInfo.executionMessage: ''</li>
     *         </ul>
     */
    @Override
    public CompletableFuture<ITerminateServiceResult> terminateService(
            final String serviceInstanceId, final ITsmProcessListener listener) {
        CompletableFuture<ITerminateServiceResult> future = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            ProcessInfo processInfo = new ProcessInfo("process4", new Date(), new Date());
            TerminateServiceResult result = new TerminateServiceResult(processInfo);
            future.complete(result);
            return null;
        });

        return future;
    }

    /**
     * Mock implementation for demo &amp; testing purpose.
     *
     * @param serviceId
     *            Input is ignored.
     * @param serviceVersionTag
     *            Input is ignored.
     * @return Mock data with:
     *         <ul>
     *         <li>deploymentAvailable:
     *         {@link EDeploymentAvailable#DEPLOYMENT_AVAILABLE}</li>
     *         <li>version: '1.0.0'</li>
     *         <li>technicalInformation.service: 'service1'</li>
     *         <li>technicalInformation.version: '1.0.0'</li>
     *         <li>technicalInformation.flavorId: 'flavor1'</li>
     *         <li>technicalInformation.flavorName: 'myFlavor'</li>
     *         <li>technicalInformation.profileId: 'profile1'</li>
     *         <li>executionStatus: {@link EErrorType#NO_ERROR}</li>
     *         <li>executionMessage: ''</li>
     *         </ul>
     */
    @Override
    public CompletableFuture<IServiceDeploymentAvailableResult> checkServiceDeploymenAvailable(
            final String serviceId, final String serviceVersionTag) {
        CompletableFuture<IServiceDeploymentAvailableResult> future = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            TechnicalInformation newTechnicalInformation = new TechnicalInformation(
                    new LinkedHashMap<>(), "service1", "1.0.0", "flavor1", "myFlavor", "profile1");

            ServiceDeploymentAvailableResult result = new ServiceDeploymentAvailableResult(
                    EDeploymentAvailable.DEPLOYMENT_AVAILABLE, "1.0.0", newTechnicalInformation,
                    EErrorType.NO_ERROR, "");

            future.complete(result);
            return null;
        });

        return future;
    }

    /**
     * Mock implementation for demo &amp; testing purpose.
     *
     * @param serviceInstanceId
     *            Input is ignored.
     * @param newVersion
     *            Input is ignored.
     * @return Mock data with:
     *         <ul>
     *         <li>updateAvailable: {@link EUpdateAvailable#UPDATE_AVAILABLE}</li>
     *         <li>newVersion: '2.0.0'</li>
     *         <li>technicalInformation.service: 'service1'</li>
     *         <li>technicalInformation.version: '2.0.0'</li>
     *         <li>technicalInformation.flavorId: 'flavor1'</li>
     *         <li>technicalInformation.flavorName: 'myFlavor'</li>
     *         <li>technicalInformation.profileId: 'profile1'</li>
     *         <li>executionStatus: {@link EErrorType#NO_ERROR}</li>
     *         <li>executionMessage: ''</li>
     *         </ul>
     */
    @Override
    public CompletableFuture<IServiceUpdateAvailableResult> checkServiceUpdateAvailable(
            final String serviceInstanceId, final String newVersion) {
        CompletableFuture<IServiceUpdateAvailableResult> future = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            TechnicalInformation newTechnicalInformation = new TechnicalInformation(
                    new LinkedHashMap<>(), "service1", "2.0.0", "flavor1", "myFlavor", "profile1");

            ServiceUpdateAvailableResult result = new ServiceUpdateAvailableResult(
                    EUpdateAvailable.UPDATE_AVAILABLE, "2.0.0", newTechnicalInformation,
                    EErrorType.NO_ERROR, "");

            future.complete(result);
            return null;
        });

        return future;
    }
}
