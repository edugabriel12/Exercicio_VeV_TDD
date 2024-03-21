package com.br.processadorboletos.service;

import com.br.processadorboletos.domain.Boleto;
import com.br.processadorboletos.domain.Fatura;
import com.br.processadorboletos.domain.Pagamento;
import com.br.processadorboletos.domain.TipoPagamento;
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
    @Test
    void testFaturaVazia() {
        // TC1: Fatura vazia, somaBoletos = 0
        Fatura fatura = new Fatura(new Date(), 0, "Eduardo Gabriel", false);
        List<Boleto> boletos = Arrays.asList();
        ProcessadorBoletosService service = new ProcessadorBoletosService();

        List<Pagamento> pagamentos = service.processaListaBoletos(boletos, fatura);

        Assertions.assertEquals(0, pagamentos.size());
        Assertions.assertEquals(true, fatura.getFaturaEstaPaga());
    }

    @Test
    void testFaturaMenorQueSomaBoletos() {
        // TC2: Fatura < somaBoletos
        Fatura fatura = new Fatura(new Date(), 1000, "Eduardo Gabriel", false);
        List<Boleto> boletos = Arrays.asList(new Boleto("1", new Date(), 500));
        ProcessadorBoletosService service = new ProcessadorBoletosService();

        List<Pagamento> pagamentos = service.processaListaBoletos(boletos, fatura);

        Assertions.assertEquals(1, pagamentos.size());
        Assertions.assertEquals(false, fatura.getFaturaEstaPaga());
    }

    @Test
    void testFaturaIgualSomaBoletos() {
        // TC3: Fatura == somaBoletos
        Fatura fatura = new Fatura(new Date(), 500, "Eduardo Gabriel", false);
        List<Boleto> boletos = Arrays.asList(new Boleto("1", new Date(), 500));
        ProcessadorBoletosService service = new ProcessadorBoletosService();

        List<Pagamento> pagamentos = service.processaListaBoletos(boletos, fatura);

        Assertions.assertEquals(1, pagamentos.size());
        Assertions.assertEquals(true, fatura.getFaturaEstaPaga());
    }

    @Test
    void testFaturaMaiorQueSomaBoletos() {
        // TC4: Fatura > somaBoletos
        Fatura fatura = new Fatura(new Date(), 1000, "Eduardo Gabriel", false);
        List<Boleto> boletos = Arrays.asList(new Boleto("1", new Date(), 1001));
        ProcessadorBoletosService service = new ProcessadorBoletosService();

        List<Pagamento> pagamentos = service.processaListaBoletos(boletos, fatura);

        Assertions.assertEquals(1, pagamentos.size());
        Assertions.assertEquals(true, fatura.getFaturaEstaPaga());
    }

    @Test
    void testFaturaIgualSomaBoletosLinhaDeLimite() {
        // TC5: Fatura == somaBoletos (linha de limite)
        Fatura fatura = new Fatura(new Date(), 500, "Eduardo Gabriel", false);
        List<Boleto> boletos = Arrays.asList(new Boleto("1", new Date(), 1001));
        ProcessadorBoletosService service = new ProcessadorBoletosService();

        List<Pagamento> pagamentos = service.processaListaBoletos(boletos, fatura);

        Assertions.assertEquals(1, pagamentos.size());
        Assertions.assertEquals(true, fatura.getFaturaEstaPaga());
    }

    @Test
    void testFaturaMenorQueSomaBoletosLinhaDeLimite() {
        // TC6: Fatura < somaBoletos (linha de limite)
        Fatura fatura = new Fatura(new Date(), 1000, "Eduardo Gabriel", false);
        List<Boleto> boletos = Arrays.asList(new Boleto("1", new Date(), 500));
        ProcessadorBoletosService service = new ProcessadorBoletosService();

        List<Pagamento> pagamentos = service.processaListaBoletos(boletos, fatura);

        Assertions.assertEquals(1, pagamentos.size());
        Assertions.assertEquals(false, fatura.getFaturaEstaPaga());
    }

    @Test
    void testFaturaIgualSomaBoletosZero() {
        // TC7: Fatura == somaBoletos (zero)
        Fatura fatura = new Fatura(new Date(), 0, "Eduardo Gabriel", false);
        List<Boleto> boletos = Arrays.asList(new Boleto("1", new Date(), 0));
        ProcessadorBoletosService service = new ProcessadorBoletosService();

        List<Pagamento> pagamentos = service.processaListaBoletos(boletos, fatura);

        Assertions.assertEquals(1, pagamentos.size());
        Assertions.assertEquals(true, fatura.getFaturaEstaPaga());
    }
}
