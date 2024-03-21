package com.br.processadorboletos.service;

import com.br.processadorboletos.domain.Boleto;
import com.br.processadorboletos.domain.Fatura;
import com.br.processadorboletos.domain.Pagamento;
import com.br.processadorboletos.domain.TipoPagamento;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProcessadorBoletosService {

    public List<Pagamento> processaListaBoletos(List<Boleto> boletos, Fatura fatura) {

        List<Pagamento> pagamentos = new ArrayList<>();
        Integer somaPagamentos = 0;

        for (Boleto boleto : boletos) {
            Pagamento pagamento = new Pagamento(boleto.getValorPago(), new Date(), TipoPagamento.BOLETO);
            pagamentos.add(pagamento);
            somaPagamentos += pagamento.getValorPago();
        }

        if (somaPagamentos >= fatura.getValorTotal()) {
            fatura.setFaturaEstaPaga(true);
        }

        return pagamentos;
    }
}
