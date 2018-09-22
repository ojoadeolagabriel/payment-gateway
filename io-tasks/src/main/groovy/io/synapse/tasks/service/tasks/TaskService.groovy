package io.synapse.tasks.service.tasks

import io.synapse.tasks.handler.TaskCompletionHandler
import io.synapse.tasks.handler.TaskProcessorHandler
import io.synapse.tasks.valueobjects.Event

interface TaskService {
    void startTask(Event event, TaskCompletionHandler completionHandler)

    void processTask(String topic, TaskProcessorHandler handler)
}