package com.example.application.usecase;

import com.example.application.dto.TaskResponse;
import com.example.application.port.in.ListTasksUseCase;
import com.example.application.port.out.TaskRepositoryPort;

import java.util.List;

public class ListTasksUseCaseImpl implements ListTasksUseCase {

    private final TaskRepositoryPort repository;

    public ListTasksUseCaseImpl(TaskRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<TaskResponse> execute() {
        return repository.findAll().stream()
                .map(TaskResponse::from)
                .toList();
    }
}
