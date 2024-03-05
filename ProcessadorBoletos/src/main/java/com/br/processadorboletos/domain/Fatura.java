package com.br.processadorboletos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class Fatura {

    private Date data;
    private Integer valorTotal;
    private String nomeCliente;
    private Boolean faturaEstaPaga;
}
