package com.br.processadorboletos.functionalTests;

import com.br.processadorboletos.domain.Boleto;
import com.br.processadorboletos.domain.Fatura;
import com.br.processadorboletos.domain.Pagamento;
import com.br.processadorboletos.service.ProcessadorBoletosService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Date;

public class ProcessadorBoletosServiceFunctionalTests {


    ///// Partições de Equivalencia

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


    //// Tabelas de Decisão

    @Test
    void testTabelaDeDecisao() {

        // Regra 1: Boleto processado, Lista de pagamentos criada
        Fatura fatura1 = new Fatura(new Date(), 1000, "Eduardo Gabriel", false);
        List<Boleto> boletos1 = Arrays.asList(new Boleto("1", new Date(), 500),
        new Boleto("2", new Date(), 501));
        ProcessadorBoletosService service1 = new ProcessadorBoletosService();

        List<Pagamento> pagamentos1 = service1.processaListaBoletos(boletos1, fatura1);

        assertEquals(2, pagamentos1.size());

        // Regra 2: Boleto processado, Soma dos boletos menor que fatura
        Fatura fatura2 = new Fatura(new Date(), 1000, "Eduardo Gabriel", false);
        List<Boleto> boletos2 = Arrays.asList(new Boleto("1", new Date(), 500));
        ProcessadorBoletosService service2 = new ProcessadorBoletosService();

        List<Pagamento> pagamentos2 = service2.processaListaBoletos(boletos2, fatura2);

        Assertions.assertEquals(1, pagamentos2.size());
        Assertions.assertEquals(false, fatura2.getFaturaEstaPaga());

        // Regra 3: Boleto processado, Soma dos boletos maior que fatura
        Fatura fatura3 = new Fatura(new Date(), 500, "Eduardo Gabriel", false);
        List<Boleto> boletos3 = Arrays.asList(new Boleto("1", new Date(), 501));
        ProcessadorBoletosService service3 = new ProcessadorBoletosService();

        List<Pagamento> pagamentos3 = service3.processaListaBoletos(boletos3, fatura3);

        Assertions.assertEquals(1, pagamentos3.size());
        Assertions.assertEquals(true, fatura3.getFaturaEstaPaga());
    }
}
