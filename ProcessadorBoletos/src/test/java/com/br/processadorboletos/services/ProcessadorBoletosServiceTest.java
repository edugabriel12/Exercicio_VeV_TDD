package com.br.processadorboletos.services;

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

        List<Object> boletos = Arrays.asList(boleto1, boleto2, boleto3);

        Fatura fatura = new Fatura(new Date(), 1000, "Eduardo Gabriel", false);

        ProcessadorBoletosService service = new ProcessadorBoletosService();

        List<Pagamento> pagamentos = service.processaListaBoletos(boletos, fatura);

        Assertions.assertTrue(fatura.getFaturaEstaPaga());
    }
}
