package com.example.application.usecase;

import com.example.application.dto.CreateTaskCommand;
import com.example.application.dto.TaskResponse;
import com.example.application.port.in.CreateTaskUseCase;
import com.example.application.port.out.TaskRepositoryPort;
import com.example.domain.entity.Task;

public class CreateTaskUseCaseImpl implements CreateTaskUseCase {

    private final TaskRepositoryPort repository;

    public CreateTaskUseCaseImpl(TaskRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public TaskResponse execute(CreateTaskCommand command) {
        Task task = Task.create(command.title(), command.description());
        repository.save(task);
        return TaskResponse.from(task);
    }
}
