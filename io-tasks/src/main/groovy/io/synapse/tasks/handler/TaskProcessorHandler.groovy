package io.synapse.tasks.handler

import io.synapse.tasks.util.EventState

interface TaskProcessorHandler {
    EventState handle(String payload)
}
