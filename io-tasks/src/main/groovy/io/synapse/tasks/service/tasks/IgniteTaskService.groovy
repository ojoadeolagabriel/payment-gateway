package io.synapse.tasks.service.tasks

import io.synapse.tasks.handler.TaskCompletionHandler
import io.synapse.tasks.handler.TaskProcessorHandler
import io.synapse.tasks.valueobjects.Event
import io.synapse.tasks.valueobjects.TaskParam
import org.apache.ignite.Ignite
import org.apache.ignite.Ignition

class IgniteTaskService implements TaskService{

    Map<TaskParam, String> configuration
	Ignite ignite

    IgniteTaskService(Map<TaskParam, String> configuration) {
        this.configuration = configuration
    }

    @Override
    void startTask(Event event, TaskCompletionHandler handler) {
        initiateIgnite()
    }

    void initiateIgnite() {
	    ignite = Ignition.start()
    }

    @Override
    void processTask(String topic, TaskProcessorHandler handler) {

    }
}
