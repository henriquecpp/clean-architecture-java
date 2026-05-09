package com.example.application.dto;

import com.example.domain.entity.Task;

import java.time.LocalDateTime;

/**
 * DTO de saída. Isola a camada de apresentação da entidade de domínio.
 */
public record TaskResponse(
        String id,
        String title,
        String description,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static TaskResponse from(Task task) {
        return new TaskResponse(
                task.getId().getValue(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus().name(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }
}
