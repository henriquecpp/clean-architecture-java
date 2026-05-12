package com.example.application.usecase;

import com.example.application.dto.TaskResponse;
import com.example.application.port.in.CompleteTaskUseCase;
import com.example.application.port.out.TaskRepositoryPort;
import com.example.domain.exception.TaskNotFoundException;
import com.example.domain.valueobject.TaskId;

public class CompleteTaskUseCaseImpl implements CompleteTaskUseCase {

    private final TaskRepositoryPort repository;

    public CompleteTaskUseCaseImpl(TaskRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public TaskResponse execute(String taskId) {
        TaskId id = TaskId.of(taskId);
        var task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        task.complete();
        repository.save(task);
        return TaskResponse.from(task);
    }
}
