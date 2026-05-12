package com.example.application.usecase;

import com.example.application.port.in.DeleteTaskUseCase;
import com.example.application.port.out.TaskRepositoryPort;
import com.example.domain.exception.TaskNotFoundException;
import com.example.domain.valueobject.TaskId;

public class DeleteTaskUseCaseImpl implements DeleteTaskUseCase {

    private final TaskRepositoryPort repository;

    public DeleteTaskUseCaseImpl(TaskRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String taskId) {
        TaskId id = TaskId.of(taskId);
        if (!repository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
