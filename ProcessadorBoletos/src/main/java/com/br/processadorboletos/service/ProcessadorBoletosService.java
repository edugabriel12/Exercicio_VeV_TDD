package com.br.processadorboletos.service;

import com.br.processadorboletos.domain.Boleto;
import com.br.processadorboletos.domain.Fatura;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessadorBoletosService {

    public void processaListaBoletos(List<Boleto> boletos, Fatura fatura) {

        Integer somaBoletos = 0;

        for (Boleto boleto : boletos) {
            somaBoletos += boleto.getValorPago();
        }

        if (somaBoletos > fatura.getValorTotal()) {
            fatura.setFaturaEstaPaga(true);
        }
    }
}
