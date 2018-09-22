package io.synapse.tasks.service.tasks

import io.synapse.tasks.valueobjects.TaskParam
import io.synapse.tasks.valueobjects.TaskType

class TaskServiceFactory {

    /**
     * startTask task impl
     * @param processorType
     * @param configuration
     * @return
     */
    static TaskService create(TaskType processorType, Map<TaskParam, String> configuration) {
        switch (processorType) {
            case TaskType.VERTX:
                return new VertxTaskService(configuration)
            case TaskType.IGNITE:
                return new IgniteTaskService(configuration)
            default:
                return new VertxTaskService(configuration)
        }
    }
}
