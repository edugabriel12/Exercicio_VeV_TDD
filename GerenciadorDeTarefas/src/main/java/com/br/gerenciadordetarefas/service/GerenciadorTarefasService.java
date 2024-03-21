package com.br.gerenciadordetarefas.service;

import com.br.gerenciadordetarefas.domain.Prioridade;
import com.br.gerenciadordetarefas.domain.Tarefa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        ordenarListaDeTarefas();
        return listaDeTarefas;
    }

    private void ordenarListaDeTarefas() {
        Collections.sort(listaDeTarefas, new Comparator<Tarefa>() {
            @Override
            public int compare(Tarefa t1, Tarefa t2) {
                // Se a prioridade for alta, a tarefa deve vir primeiro
                if (t1.getPrioridade() == Prioridade.ALTA && t2.getPrioridade() != Prioridade.ALTA) {
                    return -1;
                } else if (t1.getPrioridade() != Prioridade.ALTA && t2.getPrioridade() == Prioridade.ALTA) {
                    return 1;
                }

                // Se a prioridade for média e a outra tarefa for de prioridade baixa, a tarefa deve vir primeiro
                if (t1.getPrioridade() == Prioridade.MEDIA && t2.getPrioridade() == Prioridade.BAIXA) {
                    return -1;
                } else if (t1.getPrioridade() == Prioridade.BAIXA && t2.getPrioridade() == Prioridade.MEDIA) {
                    return 1;
                }

                // Se as prioridades forem iguais, ordena por data
                return t1.getData().compareTo(t2.getData());
            }
        });
    }
}
