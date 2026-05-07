package com.example.application.port.in;

import com.example.application.dto.CreateTaskCommand;
import com.example.application.dto.TaskResponse;

/**
 * Porta de entrada: define o contrato do caso de uso para a camada de adapter.
 * O adapter depende desta interface — nunca da implementação concreta.
 */
public interface CreateTaskUseCase {
    TaskResponse execute(CreateTaskCommand command);
}
