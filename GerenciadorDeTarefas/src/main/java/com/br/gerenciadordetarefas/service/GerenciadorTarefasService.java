package com.br.gerenciadordetarefas.service;

import com.br.gerenciadordetarefas.domain.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorTarefasService {

    private List<Tarefa> listaDeTarefas;

    public GerenciadorTarefasService(List<Tarefa> listaDeTarefas) {
        this.listaDeTarefas = new ArrayList<>(listaDeTarefas);
    }

    // Método para adicionar uma tarefa à lista
    public void adicionarTarefa(Tarefa tarefa) {
        listaDeTarefas.add(tarefa);
    }

    // Método para excluir uma tarefa da lista
    public void excluirTarefa(Tarefa tarefa) {
        listaDeTarefas.remove(tarefa);
    }

    // Método para obter a lista de tarefas
    public List<Tarefa> getListaDeTarefas() {
        return listaDeTarefas;
    }
}
