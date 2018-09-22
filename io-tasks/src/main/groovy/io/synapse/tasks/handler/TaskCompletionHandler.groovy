package io.synapse.tasks.handler

import io.synapse.tasks.util.EventState

interface TaskCompletionHandler {
    void handle(EventState state)
}
