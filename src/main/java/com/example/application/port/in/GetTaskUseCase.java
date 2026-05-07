package com.example.application.port.in;

import com.example.application.dto.TaskResponse;

public interface GetTaskUseCase {
    TaskResponse execute(String taskId);
}
