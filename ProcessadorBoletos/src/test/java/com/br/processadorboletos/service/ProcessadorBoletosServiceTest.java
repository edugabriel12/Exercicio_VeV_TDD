package com.br.processadorboletos.service;

import com.br.processadorboletos.domain.Boleto;
import com.br.processadorboletos.domain.Fatura;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Arrays;
import java.util.Date;

public class ProcessadorBoletosServiceTest {

    @Test
    public void faturaDeveEstarPaga() {

        Boleto boleto1 = new Boleto("1", new Date(), 350);
        Boleto boleto2 = new Boleto("2", new Date(), 400);
        Boleto boleto3 = new Boleto("3", new Date(), 400);

        List<Boleto> boletos = Arrays.asList(boleto1, boleto2, boleto3);

        Fatura fatura = new Fatura(
                new Date(),
                1000,
                "Eduardo Gabriel",
                false);

        ProcessadorBoletosService service = new ProcessadorBoletosService();

        service.processaListaBoletos(boletos, fatura);

        Assertions.assertTrue(fatura.getFaturaEstaPaga());
    }

    @Test
    public void deveCriarListaDePagamentos() {

        Boleto boleto1 = new Boleto("1", new Date(), 450);
        Boleto boleto2 = new Boleto("2", new Date(), 300);
        Boleto boleto3 = new Boleto("3", new Date(), 400);

        List<Boleto> boletos = Arrays.asList(boleto1, boleto2, boleto3);

        Fatura fatura = new Fatura(
                new Date(),
                1000,
                "Eduardo Gabriel",
                false);

        ProcessadorBoletosService service = new ProcessadorBoletosService();

        List<Pagamento> pagamentos = service.processaListaBoletos(boletos, fatura);

        Assertions.assertEquals(3, pagamentos.size());

        Assertions.assertEquals(TipoPagamento.BOLETO, pagamentos.get(0).getTipoPagamento());
        Assertions.assertEquals(450, pagamentos.get(0).getValorPago());

        Assertions.assertEquals(TipoPagamento.BOLETO, pagamentos.get(1).getTipoPagamento());
        Assertions.assertEquals(300, pagamentos.get(1).getValorPago());

        Assertions.assertEquals(TipoPagamento.BOLETO, pagamentos.get(2).getTipoPagamento());
        Assertions.assertEquals(400, pagamentos.get(2).getValorPago());
    }
}
