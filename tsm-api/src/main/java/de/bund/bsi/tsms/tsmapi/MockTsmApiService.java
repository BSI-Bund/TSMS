package de.bund.bsi.tsms.tsmapi;

import de.bund.bsi.tsms.tsmapi.parameters.IServiceCommand;
import de.bund.bsi.tsms.tsmapi.results.ICreateServiceInstanceResult;
import de.bund.bsi.tsms.tsmapi.results.IGetServiceInstancesResult;
import de.bund.bsi.tsms.tsmapi.results.IServiceInstance;
import de.bund.bsi.tsms.tsmapi.results.CreateServiceInstanceResult;
import de.bund.bsi.tsms.tsmapi.results.GetServiceInstancesResult;
import de.bund.bsi.tsms.tsmapi.results.IDeployServiceResult;
import de.bund.bsi.tsms.tsmapi.results.DeployServiceResult;
import de.bund.bsi.tsms.tsmapi.results.IUpdateServiceResult;
import de.bund.bsi.tsms.tsmapi.results.UpdateServiceResult;
import de.bund.bsi.tsms.tsmapi.results.ISuspendOrResumeResult;
import de.bund.bsi.tsms.tsmapi.results.SuspendOrResumeResult;
import de.bund.bsi.tsms.tsmapi.results.ITerminateServiceResult;
import de.bund.bsi.tsms.tsmapi.results.TerminateServiceResult;
import de.bund.bsi.tsms.tsmapi.results.IServiceDeploymentAvailableResult;
import de.bund.bsi.tsms.tsmapi.results.ServiceDeploymentAvailableResult;
import de.bund.bsi.tsms.tsmapi.results.IServiceUpdateAvailableResult;
import de.bund.bsi.tsms.tsmapi.results.ServiceUpdateAvailableResult;
import de.bund.bsi.tsms.tsmapi.results.ISetAccessTokenResult;
import de.bund.bsi.tsms.tsmapi.results.SetAccessTokenResult;
import de.bund.bsi.tsms.tsmapi.results.ITechnicalInformation;
import de.bund.bsi.tsms.tsmapi.results.TechnicalInformation;
import de.bund.bsi.tsms.tsmapi.results.ServiceInstance;
import de.bund.bsi.tsms.tsmapi.results.ProcessInfo;
import de.bund.bsi.tsms.tsmapi.results.IServiceCommandResult;
import de.bund.bsi.tsms.tsmapi.results.ServiceCommandResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.RejectedExecutionException;
import java.util.function.Supplier;

/**
 * This class is a mock implementation of an {@link ITsmApiService}.<br>
 * <br>
 * It does not execute any process. It just returns mock data. Goal of this
 * class is to use it for demo or testing purpose.
 *
 * @since 1.0
 */
public class MockTsmApiService implements ITsmApiService {

    /**
     * Bounded executor to avoid resource exhaustion.
     */
    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(4, // core threads
            8, // max threads
            30L, TimeUnit.SECONDS, // keep alive
            new ArrayBlockingQueue<>(200), // bounded queue
            new ThreadFactory() {
                private final ThreadFactory def = Executors.defaultThreadFactory();

                @Override
                public Thread newThread(final Runnable r) {
                    Thread t = def.newThread(r);
                    t.setName("mock-tsmapi-" + t.getId());
                    t.setDaemon(true);
                    return t;
                }
            }, new ThreadPoolExecutor.AbortPolicy());
    static {
        EXECUTOR.allowCoreThreadTimeOut(true);
    }

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

        return async(() -> {
            ProcessInfo processInfo = new ProcessInfo("process1", new Date(), new Date());

            TechnicalInformation technicalInformation = new TechnicalInformation(
                    new LinkedHashMap<>(), "service1", "1.0.0", "flavor1", "myFlavor", "profile1");

            List<IServiceCommandResult> serviceCommandResults = new ArrayList<>();
            serviceCommandResults
                    .add(new ServiceCommandResult(ECommandResultType.EXECUTION_SUCCESS));

            return new DeployServiceResult(processInfo, EServiceInstanceState.OPERATIONAL,
                    technicalInformation, serviceCommandResults, "eSE1");
        });
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

        return async(() -> {
            ProcessInfo processInfo = new ProcessInfo("process2", new Date(), new Date());

            TechnicalInformation technicalInformation = new TechnicalInformation(
                    new LinkedHashMap<>(), "service1", newVersion, "flavor1", "myFlavor",
                    "profile1");

            List<IServiceCommandResult> serviceCommandResults = new ArrayList<>();
            serviceCommandResults
                    .add(new ServiceCommandResult(ECommandResultType.EXECUTION_SUCCESS));

            return new UpdateServiceResult(processInfo, EServiceInstanceState.OPERATIONAL,
                    technicalInformation, serviceCommandResults, "eSE1");
        });
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
        return async(() -> {
            ProcessInfo processInfo = new ProcessInfo("process3", new Date(), new Date());
            return new SuspendOrResumeResult(processInfo);
        });
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
        return async(() -> {
            ProcessInfo processInfo = new ProcessInfo("process4", new Date(), new Date());
            return new TerminateServiceResult(processInfo);
        });
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
        return async(() -> {
            TechnicalInformation ti = new TechnicalInformation(new LinkedHashMap<>(), "service1",
                    "1.0.0", "flavor1", "myFlavor", "profile1");

            return new ServiceDeploymentAvailableResult(EDeploymentAvailable.DEPLOYMENT_AVAILABLE,
                    "1.0.0", ti, EErrorType.NO_ERROR, "");
        });
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
        return async(() -> {
            TechnicalInformation ti = new TechnicalInformation(new LinkedHashMap<>(), "service1",
                    "2.0.0", "flavor1", "myFlavor", "profile1");

            return new ServiceUpdateAvailableResult(EUpdateAvailable.UPDATE_AVAILABLE, "2.0.0", ti,
                    EErrorType.NO_ERROR, "");
        });
    }

    /**
     * Mock implementation for demo &amp; testing purpose.
     *
     * @param token
     *            Input is ignored.
     * @return Mock data with:
     *         <ul>
     *         <li>executionStatus: {@link EErrorType#NO_ERROR}</li>
     *         <li>executionMessage: ''</li>
     *         </ul>
     */
    @Override
    public ISetAccessTokenResult setAccessToken(final ITsmAccessToken token) {
        SetAccessTokenResult result = new SetAccessTokenResult(EErrorType.NO_ERROR, "");

        return result;
    }

    /**
     * Executes the given task asynchronously using a shared, bounded executor.
     * <p>
     * If the executor rejects execution, the returned {@link CompletableFuture} is
     * completed exceptionally with {@link RejectedExecutionException}.
     * </p>
     *
     * @param <T>
     *            the result type
     * @param supplier
     *            the task to execute asynchronously
     * @return a {@link CompletableFuture} representing the asynchronous execution
     */
    private static <T> CompletableFuture<T> async(final Supplier<T> supplier) {
        try {
            return CompletableFuture.supplyAsync(supplier, EXECUTOR);
        } catch (RejectedExecutionException ex) {
            CompletableFuture<T> f = new CompletableFuture<>();
            f.completeExceptionally(ex);
            return f;
        }
    }
}
