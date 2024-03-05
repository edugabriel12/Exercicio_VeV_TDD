package com.br.processadorboletos.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class Boleto {

    private String codigoBoleto;
    private Date data;
    private Integer valorPago;

}
