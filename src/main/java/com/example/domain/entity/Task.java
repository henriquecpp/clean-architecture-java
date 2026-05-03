package com.example.domain.entity;

import com.example.domain.exception.TaskAlreadyCompletedException;
import com.example.domain.valueobject.TaskId;

import java.time.LocalDateTime;

/**
 * Entidade central do domínio. Contém apenas regras de negócio puras,
 * sem dependência de frameworks, banco de dados ou qualquer infraestrutura.
 */
public class Task {

    public enum Status {
        PENDING, IN_PROGRESS, COMPLETED
    }

    private final TaskId id;
    private String title;
    private String description;
    private Status status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Task(TaskId id, String title, String description, LocalDateTime createdAt) {
        validateTitle(title);
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = Status.PENDING;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

    public static Task create(String title, String description) {
        return new Task(TaskId.generate(), title, description, LocalDateTime.now());
    }

    // Reconstitui uma Task a partir do repositório (sem gerar novo ID)
    public static Task reconstitute(TaskId id, String title, String description,
                                    Status status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        Task task = new Task(id, title, description, createdAt);
        task.status = status;
        task.updatedAt = updatedAt;
        return task;
    }

    // --- Comportamentos do domínio ---

    public void start() {
        if (this.status == Status.COMPLETED) {
            throw new TaskAlreadyCompletedException(id);
        }
        this.status = Status.IN_PROGRESS;
        this.updatedAt = LocalDateTime.now();
    }

    public void complete() {
        if (this.status == Status.COMPLETED) {
            throw new TaskAlreadyCompletedException(id);
        }
        this.status = Status.COMPLETED;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateTitle(String newTitle) {
        validateTitle(newTitle);
        this.title = newTitle;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isCompleted() {
        return this.status == Status.COMPLETED;
    }

    // --- Getters ---

    public TaskId getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Status getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Task title cannot be blank");
        }
        if (title.length() > 100) {
            throw new IllegalArgumentException("Task title must be at most 100 characters");
        }
    }
}
