package io.course.jk99.model.event;

import io.course.jk99.model.Task;

import java.time.Clock;

public class TaskUndone extends TaskEvent {
   TaskUndone(final Task source) {
        super(source.getId(), Clock.systemDefaultZone());
    }
}
