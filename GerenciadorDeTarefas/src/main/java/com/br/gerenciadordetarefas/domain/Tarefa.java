package com.br.gerenciadordetarefas.domain;

import java.util.Date;

public class Tarefa {
    private String titulo;
    private String descricao;
    private Prioridade prioridade;

    private Date dataVencimento;

    public Tarefa(String titulo, String descricao, Date data, Prioridade prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.dataVencimento = data;
    }


}
