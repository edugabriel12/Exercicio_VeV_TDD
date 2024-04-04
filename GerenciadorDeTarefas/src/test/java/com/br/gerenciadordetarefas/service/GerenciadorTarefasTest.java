package com.br.gerenciadordetarefas.service;
import com.br.gerenciadordetarefas.domain.Tarefa;
import org.junit.jupiter.api.*;
import  com.br.gerenciadordetarefas.domain.Prioridade;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class GerenciadorTarefasTest {

    private Tarefa tarefaT1;
    private Tarefa tarefaT2;
    private Tarefa tarefaT3;
    private GerenciadorTarefasService tarefaService;
    @BeforeEach
    public void setUp() {
        tarefaT1 = new Tarefa("OS1", "fazer alteracoes na tela", new Date(), Prioridade.ALTA);
        tarefaT2 = new Tarefa("OS2", "fazer alteracoes no back", new Date(), Prioridade.MEDIA);
        tarefaT3 = new Tarefa("OS3", "fazer alteracoes no BD", new Date(), Prioridade.BAIXA);

        List<Tarefa> tarefasExcluir = new ArrayList<>();
        tarefasExcluir.add(tarefaT1);
        tarefasExcluir.add(tarefaT2);
        tarefasExcluir.add(tarefaT3);

        tarefaService = new GerenciadorTarefasService(tarefasExcluir);
    }
    static Stream<Tarefa> tarefas() {
        return Stream.of(
                new Tarefa("OS1", "fazer alteracoes na tela", new Date(), Prioridade.ALTA),
                new Tarefa("OS2", "fazer alteracoes no Back", new Date(), Prioridade.ALTA),
                new Tarefa("OS3", "fazer ajustes no layout", new Date(), Prioridade.MEDIA)
        );
    }
    @ParameterizedTest
    @ValueSource(strings = {"OS1","OS2","OS3" })
    public void deveCriarTarefa(String title){
        //Criar uma tarefa
        Tarefa tarefa1 = new Tarefa(title, "fazer alteracoes na tela", new Date(), Prioridade.ALTA );
        assertNotNull(tarefa1);
        assertEquals("fazer alteracoes na tela", tarefa1.getDescricao());
        assertNotNull(tarefa1.getData());
        assertEquals(Prioridade.ALTA, tarefa1.getPrioridade());
    }
    @ParameterizedTest
    @MethodSource("tarefas")
    public void deveEditarTarefa(Tarefa tarefa){
        String novoTitulo = "OS2";
        String novaDescricao = "fazer alteracoes no Back";
        Prioridade novaPrioridade = Prioridade.BAIXA;
        Date novaData = new Date();

        tarefa.setTitulo(novoTitulo);
        tarefa.setDescricao(novaDescricao);
        tarefa.setPrioridade(novaPrioridade);
        tarefa.setData(novaData);

        assertAll("Verificar edição da tarefa",
                () -> assertEquals(novoTitulo, tarefa.getTitulo()),
                () -> assertEquals(novaDescricao, tarefa.getDescricao()),
                () -> assertEquals(novaPrioridade, tarefa.getPrioridade()),
                () -> assertEquals(novaData, tarefa.getData())
        );


    }


    @RepeatedTest(10)
    @DisplayName("Teste para excluir uma tarefa da lista")
    public void deveExcluirTarefa(){
        tarefaService.excluirTarefa(tarefaT1);
        assertFalse(tarefaService.getListaDeTarefas().contains(tarefaT1));

    }


    @RepeatedTest(10)
    @DisplayName("Teste para retornar a lista de tarefas ordenada")
    public void deveRetornarOrdenado(){
        Tarefa tarefa3 = new Tarefa("Tarefa 3", "Descrição da tarefa 3",  new Date(2024, 3, 4), Prioridade.BAIXA);
        Tarefa tarefa1 = new Tarefa("Tarefa 1", "Descrição da tarefa 1", new Date(2024, 3, 5), Prioridade.ALTA);
        Tarefa tarefa4 = new Tarefa("Tarefa 1", "Descrição da tarefa 1", new Date(2025, 3, 5), Prioridade.ALTA);
        Tarefa tarefa2 = new Tarefa("Tarefa 2", "Descrição da tarefa 2",  new Date(2024, 3, 6), Prioridade.MEDIA);

        List<Tarefa> tarefas = Arrays.asList(tarefa3, tarefa2, tarefa1,tarefa4);
        GerenciadorTarefasService tarefaService = new GerenciadorTarefasService(tarefas);
        List<Tarefa> listaOrdenada = tarefaService.getListaDeTarefas();

        assertAll("Verificar lista ordenada",
                () -> assertEquals(tarefa1, listaOrdenada.get(0)),
                () -> assertEquals(tarefa4, listaOrdenada.get(1)),
                () -> assertEquals(tarefa2, listaOrdenada.get(2)),
                () -> assertEquals(tarefa3, listaOrdenada.get(3))
        );


    }

}
