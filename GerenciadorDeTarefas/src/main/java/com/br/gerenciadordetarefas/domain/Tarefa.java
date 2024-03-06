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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }


    public void setData(Date novaData) {
        this.dataVencimento = novaData;
    }

    public Date getData() {
        return this.dataVencimento;
    }
}
