package com.example.application.port.in;

import com.example.application.dto.TaskResponse;

public interface CompleteTaskUseCase {
    TaskResponse execute(String taskId);
}
