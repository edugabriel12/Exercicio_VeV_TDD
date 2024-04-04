package com.br.processadorboletos.junit5Tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.*;
import com.br.processadorboletos.domain.Boleto;
import com.br.processadorboletos.domain.Fatura;
import com.br.processadorboletos.domain.Pagamento;
import com.br.processadorboletos.service.ProcessadorBoletosService;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Date;


class ProcessadorBoletosServiceJunit5Tests {

    @Nested
    @DisplayName("Testes para Fatura")
    class FaturaTests {

        @Test
        @DisplayName("Fatura vazia")
        void testFaturaVazia() {
            Fatura fatura = new Fatura(new Date(), 0, "Eduardo Gabriel", false);
            List<Boleto> boletos = Collections.emptyList();
            ProcessadorBoletosService service = new ProcessadorBoletosService();

            List<Pagamento> pagamentos = service.processaListaBoletos(boletos, fatura);

            assertAll(
                    () -> assertEquals(0, pagamentos.size()),
                    () -> assertTrue(fatura.getFaturaEstaPaga())
            );
        }

        @Test
        @DisplayName("Fatura menor que soma dos boletos")
        void testFaturaMenorQueSomaBoletos() {
            Fatura fatura = new Fatura(new Date(), 1000, "Eduardo Gabriel", false);
            List<Boleto> boletos = Collections.singletonList(new Boleto("1", new Date(), 500));
            ProcessadorBoletosService service = new ProcessadorBoletosService();

            List<Pagamento> pagamentos = service.processaListaBoletos(boletos, fatura);

            assertAll(
                    () -> assertEquals(1, pagamentos.size()),
                    () -> assertFalse(fatura.getFaturaEstaPaga())
            );
        }

        @Test
        @DisplayName("Fatura igual soma dos boletos")
        void testFaturaIgualSomaBoletos() {
            Fatura fatura = new Fatura(new Date(), 500, "Eduardo Gabriel", false);
            List<Boleto> boletos = Collections.singletonList(new Boleto("1", new Date(), 500));
            ProcessadorBoletosService service = new ProcessadorBoletosService();

            List<Pagamento> pagamentos = service.processaListaBoletos(boletos, fatura);

            assertAll(
                    () -> assertEquals(1, pagamentos.size()),
                    () -> assertTrue(fatura.getFaturaEstaPaga())
            );
        }

        @Test
        @DisplayName("Fatura maior que soma dos boletos")
        void testFaturaMaiorQueSomaBoletos() {
            Fatura fatura = new Fatura(new Date(), 1000, "Eduardo Gabriel", false);
            List<Boleto> boletos = Collections.singletonList(new Boleto("1", new Date(), 1001));
            ProcessadorBoletosService service = new ProcessadorBoletosService();

            List<Pagamento> pagamentos = service.processaListaBoletos(boletos, fatura);

            assertAll(
                    () -> assertEquals(1, pagamentos.size()),
                    () -> assertTrue(fatura.getFaturaEstaPaga())
            );
        }

        @Test
        @DisplayName("Fatura igual soma dos boletos (linha de limite)")
        void testFaturaIgualSomaBoletosLinhaDeLimite() {
            Fatura fatura = new Fatura(new Date(), 500, "Eduardo Gabriel", false);
            List<Boleto> boletos = Collections.singletonList(new Boleto("1", new Date(), 1001));
            ProcessadorBoletosService service = new ProcessadorBoletosService();

            List<Pagamento> pagamentos = service.processaListaBoletos(boletos, fatura);

            assertAll(
                    () -> assertEquals(1, pagamentos.size()),
                    () -> assertTrue(fatura.getFaturaEstaPaga())
            );
        }

        @Test
        @DisplayName("Fatura menor que soma dos boletos (linha de limite)")
        void testFaturaMenorQueSomaBoletosLinhaDeLimite() {
            Fatura fatura = new Fatura(new Date(), 1000, "Eduardo Gabriel", false);
            List<Boleto> boletos = Collections.singletonList(new Boleto("1", new Date(), 500));
            ProcessadorBoletosService service = new ProcessadorBoletosService();

            List<Pagamento> pagamentos = service.processaListaBoletos(boletos, fatura);

            assertAll(
                    () -> assertEquals(1, pagamentos.size()),
                    () -> assertFalse(fatura.getFaturaEstaPaga())
            );
        }

        @Test
        @DisplayName("Fatura igual soma dos boletos (zero)")
        void testFaturaIgualSomaBoletosZero() {
            Fatura fatura = new Fatura(new Date(), 0, "Eduardo Gabriel", false);
            List<Boleto> boletos = Collections.singletonList(new Boleto("1", new Date(), 0));
            ProcessadorBoletosService service = new ProcessadorBoletosService();

            List<Pagamento> pagamentos = service.processaListaBoletos(boletos, fatura);

            assertAll(
                    () -> assertEquals(1, pagamentos.size()),
                    () -> assertTrue(fatura.getFaturaEstaPaga())
            );
        }
    }

    @Nested
    @DisplayName("Testes para Tabela de Decisão")
    class TabelaDecisaoTests {

        @Test
        @DisplayName("Regra 1: Boleto processado, Lista de pagamentos criada")
        void testRegra1() {
            Fatura fatura = new Fatura(new Date(), 1000, "Eduardo Gabriel", false);
            List<Boleto> boletos = Arrays.asList(new Boleto("1", new Date(), 500),
                    new Boleto("2", new Date(), 501));
            ProcessadorBoletosService service = new ProcessadorBoletosService();

            List<Pagamento> pagamentos = service.processaListaBoletos(boletos, fatura);

            assertEquals(2, pagamentos.size());
        }

        @Test
        @DisplayName("Regra 2: Boleto processado, Soma dos boletos menor que fatura")
        void testRegra2() {
            Fatura fatura = new Fatura(new Date(), 1000, "Eduardo Gabriel", false);
            List<Boleto> boletos = Collections.singletonList(new Boleto("1", new Date(), 500));
            ProcessadorBoletosService service = new ProcessadorBoletosService();

            List<Pagamento> pagamentos = service.processaListaBoletos(boletos, fatura);

            assertAll(
                    () -> assertEquals(1, pagamentos.size()),
                    () -> assertFalse(fatura.getFaturaEstaPaga())
            );
        }

        @Test
        @DisplayName("Regra 3: Boleto processado, Soma dos boletos maior que fatura")
        void testRegra3() {
            Fatura fatura = new Fatura(new Date(), 500, "Eduardo Gabriel", false);
            List<Boleto> boletos = Collections.singletonList(new Boleto("1", new Date(), 501));
            ProcessadorBoletosService service = new ProcessadorBoletosService();

            List<Pagamento> pagamentos = service.processaListaBoletos(boletos, fatura);

            assertAll(
                    () -> assertEquals(1, pagamentos.size()),
                    () -> assertTrue(fatura.getFaturaEstaPaga())
            );
        }
    }
}
