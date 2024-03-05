package com.br.gerenciadordetarefas.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GerenciadorTarefasTest {

    @Test
    public void deveCriarTarefa(){
        //Criar uma tarefa
        Tarefa tarefa1 = new Tarefa("OS1", "fazer alteracoes na tela", new Date(), Prioridade.Alta );
        assertNotNull(tarefa1);
    }
}
