package com.br.gerenciadordetarefas.service;
import com.br.gerenciadordetarefas.domain.Tarefa;
import org.junit.jupiter.api.Test;
import  com.br.gerenciadordetarefas.domain.Prioridade;

import java.util.Date;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GerenciadorTarefasTest {

    @Test
    public void deveCriarTarefa(){
        //Criar uma tarefa
        Tarefa tarefa1 = new Tarefa("OS1", "fazer alteracoes na tela", new Date(), Prioridade.ALTA );
        assertNotNull(tarefa1);
    }
    @Test
    public void deveEditarTarefa(){
        Tarefa tarefa1 = new Tarefa("OS1", "fazer alteracoes na tela", new Date(), Prioridade.ALTA );

        // Editar a tarefa
        String novoTitulo = "OS2";
        String novaDescricao = "fazer alteracoes no Back";
        Prioridade novaPrioridade = Prioridade.BAIXA;
        Date novaData = new Date();

        tarefa.setTitulo(novoTitulo);
        tarefa.setDescricao(novaDescricao);
        tarefa.setPrioridade(novaPrioridade);
        tarefa.setData(novaData);

        assertEquals(novoTitulo, tarefa.getTitulo());
        assertEquals(novaDescricao, tarefa.getDescricao());
        assertEquals(novaPrioridade, tarefa.getPrioridade());
        assertEquals(novaData, tarefa.getData());



    }
}
