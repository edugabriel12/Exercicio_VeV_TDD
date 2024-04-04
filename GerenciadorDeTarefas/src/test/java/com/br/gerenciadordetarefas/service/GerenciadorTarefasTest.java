package com.br.gerenciadordetarefas.service;
import com.br.gerenciadordetarefas.domain.Tarefa;
import org.junit.jupiter.api.Test;
import  com.br.gerenciadordetarefas.domain.Prioridade;

import java.util.Date;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

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

        tarefa1.setTitulo(novoTitulo);
        tarefa1.setDescricao(novaDescricao);
        tarefa1.setPrioridade(novaPrioridade);
        tarefa1.setData(novaData);

        assertEquals(novoTitulo, tarefa1.getTitulo());
        assertEquals(novaDescricao, tarefa1.getDescricao());
        assertEquals(novaPrioridade, tarefa1.getPrioridade());
        assertEquals(novaData, tarefa1.getData());

    }

    @Test
    public void deveExcluirTarefa(){
        Tarefa tarefa1 = new Tarefa("OS1", "fazer alteracoes na tela", new Date(), Prioridade.ALTA );
        Tarefa tarefa2 = new Tarefa("OS2", "fazer alteracoes no back", new Date(), Prioridade.MEDIA );
        Tarefa tarefa3 = new Tarefa("OS3", "fazer alteracoes no BD", new Date(), Prioridade.BAIXA );

        List<Tarefa> tarefas = Arrays.asList(tarefa1, tarefa2, tarefa3);

        GerenciadorTarefasService tarefaService = new GerenciadorTarefasService(tarefas);
        tarefaService.excluirTarefa(tarefa1);

        assertFalse(tarefaService.getListaDeTarefas().contains(tarefa1));

    }

    @Test
    public void deveRetornarOrdenado(){
        Tarefa tarefa3 = new Tarefa("Tarefa 3", "Descrição da tarefa 3",  new Date(2024, 3, 4), Prioridade.BAIXA);
        Tarefa tarefa1 = new Tarefa("Tarefa 1", "Descrição da tarefa 1", new Date(2024, 3, 5), Prioridade.ALTA);
        Tarefa tarefa4 = new Tarefa("Tarefa 1", "Descrição da tarefa 1", new Date(2025, 3, 5), Prioridade.ALTA);
        Tarefa tarefa2 = new Tarefa("Tarefa 2", "Descrição da tarefa 2",  new Date(2024, 3, 6), Prioridade.MEDIA);

        List<Tarefa> tarefas = Arrays.asList(tarefa3, tarefa2, tarefa1,tarefa4);
        GerenciadorTarefasService tarefaService = new GerenciadorTarefasService(tarefas);
        List<Tarefa> listaOrdenada = tarefaService.getListaDeTarefas();
        assertEquals(tarefa1, listaOrdenada.get(0));
        assertEquals(tarefa4, listaOrdenada.get(1));
        assertEquals(tarefa2, listaOrdenada.get(2));
        assertEquals(tarefa3, listaOrdenada.get(3));


    }

}