package com.example.domain.exception;

import com.example.domain.valueobject.TaskId;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(TaskId id) {
        super("Task not found: " + id.getValue());
    }
}
