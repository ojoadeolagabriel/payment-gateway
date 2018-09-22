package io.synapse.tasks.service.tasks

import io.synapse.tasks.handler.TaskCompletionHandler
import io.synapse.tasks.handler.TaskProcessorHandler
import io.synapse.tasks.valueobjects.Event
import io.synapse.tasks.valueobjects.TaskParam

class IgniteTaskService implements TaskService{

    Map<TaskParam, String> configuration

    IgniteTaskService(Map<TaskParam, String> configuration) {
        this.configuration = configuration
    }

    @Override
    void startTask(Event event, TaskCompletionHandler handler) {

    }

    @Override
    void processTask(String topic, TaskProcessorHandler handler) {

    }
}
