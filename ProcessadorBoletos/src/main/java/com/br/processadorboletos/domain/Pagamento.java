package com.br.processadorboletos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class Pagamento {

    private Integer valorPago;
    private Date data;
    private TipoPagamento tipoPagamento;
}
