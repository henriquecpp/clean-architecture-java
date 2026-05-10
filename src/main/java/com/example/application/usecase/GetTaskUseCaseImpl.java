package com.example.application.usecase;

import com.example.application.dto.TaskResponse;
import com.example.application.port.in.GetTaskUseCase;
import com.example.application.port.out.TaskRepositoryPort;
import com.example.domain.exception.TaskNotFoundException;
import com.example.domain.valueobject.TaskId;

public class GetTaskUseCaseImpl implements GetTaskUseCase {

    private final TaskRepositoryPort repository;

    public GetTaskUseCaseImpl(TaskRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public TaskResponse execute(String taskId) {
        TaskId id = TaskId.of(taskId);
        return repository.findById(id)
                .map(TaskResponse::from)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }
}
