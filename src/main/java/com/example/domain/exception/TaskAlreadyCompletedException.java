package com.example.domain.exception;

import com.example.domain.valueobject.TaskId;

public class TaskAlreadyCompletedException extends RuntimeException {

    public TaskAlreadyCompletedException(TaskId id) {
        super("Task is already completed: " + id.getValue());
    }
}
