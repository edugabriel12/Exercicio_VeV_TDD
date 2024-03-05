package com.br.gerenciadordetarefas.service;
import com.br.gerenciadordetarefas.domain.Tarefa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import  com.br.gerenciadordetarefas.domain.Prioridade;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GerenciadorTarefasTest {

    @Test
    public void deveCriarTarefa(){
        //Criar uma tarefa
        Tarefa tarefa1 = new Tarefa("OS1", "fazer alteracoes na tela", new Date(), Prioridade.ALTA );
        assertNotNull(tarefa1);
    }
}
