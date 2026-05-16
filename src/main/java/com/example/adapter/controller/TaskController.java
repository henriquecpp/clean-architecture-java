package com.example.adapter.controller;

import com.example.application.dto.CreateTaskCommand;
import com.example.application.dto.TaskResponse;
import com.example.application.port.in.CompleteTaskUseCase;
import com.example.application.port.in.CreateTaskUseCase;
import com.example.application.port.in.DeleteTaskUseCase;
import com.example.application.port.in.GetTaskUseCase;
import com.example.application.port.in.ListTasksUseCase;

import java.util.List;
import java.util.Scanner;

/**
 * Adapter de entrada: traduz interação via linha de comando em chamadas
 * aos casos de uso. Poderia ser trocado por um HttpController, gRPC, etc.
 * sem alterar nada na camada de aplicação ou domínio.
 */
public class TaskController {

    private final CreateTaskUseCase createTask;
    private final GetTaskUseCase getTask;
    private final ListTasksUseCase listTasks;
    private final CompleteTaskUseCase completeTask;
    private final DeleteTaskUseCase deleteTask;
    private final Scanner scanner;

    public TaskController(CreateTaskUseCase createTask,
                          GetTaskUseCase getTask,
                          ListTasksUseCase listTasks,
                          CompleteTaskUseCase completeTask,
                          DeleteTaskUseCase deleteTask) {
        this.createTask = createTask;
        this.getTask = getTask;
        this.listTasks = listTasks;
        this.completeTask = completeTask;
        this.deleteTask = deleteTask;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("=== Gerenciador de Tarefas (Clean Architecture) ===\n");

        boolean running = true;
        while (running) {
            printMenu();
            String option = scanner.nextLine().trim();

            try {
                switch (option) {
                    case "1" -> handleCreate();
                    case "2" -> handleList();
                    case "3" -> handleGet();
                    case "4" -> handleComplete();
                    case "5" -> handleDelete();
                    case "0" -> {
                        System.out.println("Encerrando...");
                        running = false;
                    }
                    default -> System.out.println("Opcao invalida.\n");
                }
            } catch (Exception e) {
                System.out.println("[ERRO] " + e.getMessage() + "\n");
            }
        }
    }

    private void handleCreate() {
        System.out.print("Titulo: ");
        String title = scanner.nextLine();
        System.out.print("Descricao: ");
        String description = scanner.nextLine();

        TaskResponse response = createTask.execute(new CreateTaskCommand(title, description));
        System.out.println("\nTarefa criada com sucesso!");
        printTask(response);
    }

    private void handleList() {
        List<TaskResponse> tasks = listTasks.execute();
        if (tasks.isEmpty()) {
            System.out.println("\nNenhuma tarefa encontrada.\n");
            return;
        }
        System.out.println("\n--- Lista de Tarefas ---");
        tasks.forEach(this::printTask);
    }

    private void handleGet() {
        System.out.print("ID da tarefa: ");
        String id = scanner.nextLine().trim();
        TaskResponse response = getTask.execute(id);
        printTask(response);
    }

    private void handleComplete() {
        System.out.print("ID da tarefa para completar: ");
        String id = scanner.nextLine().trim();
        TaskResponse response = completeTask.execute(id);
        System.out.println("\nTarefa marcada como concluida!");
        printTask(response);
    }

    private void handleDelete() {
        System.out.print("ID da tarefa para deletar: ");
        String id = scanner.nextLine().trim();
        deleteTask.execute(id);
        System.out.println("\nTarefa deletada com sucesso!\n");
    }

    private void printMenu() {
        System.out.println("----------------------------");
        System.out.println("1. Criar tarefa");
        System.out.println("2. Listar tarefas");
        System.out.println("3. Buscar tarefa por ID");
        System.out.println("4. Completar tarefa");
        System.out.println("5. Deletar tarefa");
        System.out.println("0. Sair");
        System.out.print("Opcao: ");
    }

    private void printTask(TaskResponse task) {
        System.out.println();
        System.out.println("  ID:        " + task.id());
        System.out.println("  Titulo:    " + task.title());
        System.out.println("  Descricao: " + task.description());
        System.out.println("  Status:    " + task.status());
        System.out.println("  Criado em: " + task.createdAt());
        System.out.println();
    }
}
