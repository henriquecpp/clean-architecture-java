package com.example.application.port.in;

import com.example.application.dto.TaskResponse;

import java.util.List;

public interface ListTasksUseCase {
    List<TaskResponse> execute();
}
