package com.example.application.dto;

/**
 * Command (entrada) para o caso de uso de criação de tarefa.
 * DTOs de comando nunca carregam ID — quem gera o ID é o domínio.
 */
public record CreateTaskCommand(String title, String description) {

    public CreateTaskCommand {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
    }
}
