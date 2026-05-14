package com.example.adapter.repository;

import com.example.application.port.out.TaskRepositoryPort;
import com.example.domain.entity.Task;
import com.example.domain.valueobject.TaskId;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementação concreta do repositório usando um Map em memória.
 * Em produção, esta classe seria substituída por uma que usa JDBC, JPA, etc.
 * O domínio e a aplicação nunca precisam saber qual implementação está ativa.
 */
public class InMemoryTaskRepository implements TaskRepositoryPort {

    private final Map<String, Task> store = new LinkedHashMap<>();

    @Override
    public void save(Task task) {
        store.put(task.getId().getValue(), task);
    }

    @Override
    public Optional<Task> findById(TaskId id) {
        return Optional.ofNullable(store.get(id.getValue()));
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteById(TaskId id) {
        store.remove(id.getValue());
    }

    @Override
    public boolean existsById(TaskId id) {
        return store.containsKey(id.getValue());
    }
}
