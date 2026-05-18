package com.example;

import com.example.adapter.controller.TaskController;
import com.example.adapter.repository.InMemoryTaskRepository;
import com.example.application.port.out.TaskRepositoryPort;
import com.example.application.usecase.CompleteTaskUseCaseImpl;
import com.example.application.usecase.CreateTaskUseCaseImpl;
import com.example.application.usecase.DeleteTaskUseCaseImpl;
import com.example.application.usecase.GetTaskUseCaseImpl;
import com.example.application.usecase.ListTasksUseCaseImpl;

/**
 * Composition Root — único lugar onde as dependências são montadas.
 * Aqui "colamos" as peças: repositório concreto -> casos de uso -> controller.
 *
 * Em um projeto com Spring Boot, este papel seria do container de DI.
 * Sem Spring, fazemos manualmente, e isso deixa claro como a injeção funciona.
 */
public class Main {

    public static void main(String[] args) {

        // --- Camada de infraestrutura ---
        TaskRepositoryPort repository = new InMemoryTaskRepository();

        // --- Camada de aplicação (casos de uso) ---
        var createTask   = new CreateTaskUseCaseImpl(repository);
        var getTask      = new GetTaskUseCaseImpl(repository);
        var listTasks    = new ListTasksUseCaseImpl(repository);
        var completeTask = new CompleteTaskUseCaseImpl(repository);
        var deleteTask   = new DeleteTaskUseCaseImpl(repository);

        // --- Adapter de entrada (CLI) ---
        TaskController controller = new TaskController(
                createTask, getTask, listTasks, completeTask, deleteTask
        );

        controller.run();
    }
}
