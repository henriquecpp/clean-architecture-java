package com.example.application.port.out;

import com.example.domain.entity.Task;
import com.example.domain.valueobject.TaskId;

import java.util.List;
import java.util.Optional;

/**
 * Porta de saída: a camada de aplicação define o contrato que o repositório
 * concreto (adapter) deve satisfazer. A dependência aponta para dentro.
 */
public interface TaskRepositoryPort {
    void save(Task task);
    Optional<Task> findById(TaskId id);
    List<Task> findAll();
    void deleteById(TaskId id);
    boolean existsById(TaskId id);
}
