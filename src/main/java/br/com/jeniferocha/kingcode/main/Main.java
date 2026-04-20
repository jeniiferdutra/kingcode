package br.com.jeniferocha.kingcode.main;

import br.com.jeniferocha.kingcode.model.Livro;
import br.com.jeniferocha.kingcode.model.RespostaAPI;
import br.com.jeniferocha.kingcode.model.RespostaVilao;
import br.com.jeniferocha.kingcode.model.Vilao;
import br.com.jeniferocha.kingcode.service.ConsumoAPI;
import br.com.jeniferocha.kingcode.service.ConverteDados;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Main {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    List<Livro> livros = new ArrayList<>();

    private final String ENDERECO = "https://stephen-king-api.onrender.com/api/books";

    public void buscarLivro() {
        int opcaoMenu = -1;

        while(opcaoMenu != 0) {
            exibirOpcoes();
            opcaoMenu = leitura.nextInt();
            leitura.nextLine();

            switch (opcaoMenu) {
                case 1 -> listaTodosOsLivros();
                case 2 -> pesquisaLivro();
                case 3 -> livroPorAnoDeLancamento();
                case 4 -> buscaDetalhesVilao();
                case 5 -> System.out.println("Saindo do Macroverso... Até a próxima!");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    public void exibirOpcoes() {
        System.out.println("""
                \n*** KingCode - Engine ***
                1 - Listar todos os livros
                2 - Buscar livro
                3 - Livro por ano de lançamento
                4 - Detalhes do Vilão
                5 - Sair
                """
                );
    }

    public void listaTodosOsLivros() {
        var json = consumo.obterDados(ENDERECO);
        var resposta = conversor.obterDados(json, RespostaAPI.class);
        this.livros = resposta.livros();

        System.out.println("\n--- BIBLIOTECA STEPHEN KING ---");
        livros.forEach(System.out::println);
    }

    private void carregarDadosSeVazio() {
        if (this.livros.isEmpty()) {
            var json = consumo.obterDados(ENDERECO);
            var resposta = conversor.obterDados(json, RespostaAPI.class);
            this.livros = resposta.livros();
        }
    }

    public void pesquisaLivro() {
        carregarDadosSeVazio(); // Busca na API apenas se a lista estiver vazia, sem dar print em tudo

        System.out.println("Digite o nome do livro:");
        var nomeLivro = leitura.nextLine();

        System.out.println("\n--- Resultados da Busca ---");

        // Filtra e mostra apenas o que foi pedido
        livros.stream()
                .filter(l -> l.titulo().toLowerCase().contains(nomeLivro.toLowerCase()))
                .forEach(System.out::println);
    }

    public void livroPorAnoDeLancamento() {
        System.out.println("Digite o ano de lançamento:");
        var anoLancamento = leitura.nextInt();
        leitura.nextLine();

        System.out.println("\n--- Livros lançados em " + anoLancamento + " ---");

        livros.stream()
                .filter(l -> l.ano().equals(anoLancamento))
                .forEach(System.out::println);
    }

    public void buscaDetalhesVilao() {
        carregarDadosSeVazio();
        System.out.println("Digite o nome do vilão:");
        var nomeVilao = leitura.nextLine();

        // 1. Primeiro, achamos o vilão "resumido" na lista de livros
        var vilaoResumo = livros.stream()
                .filter(l -> l.viloes() != null)
                .flatMap(l -> l.viloes().stream())
                .filter(v -> v.nome().toLowerCase().contains(nomeVilao.toLowerCase()))
                .findFirst(); // o primeiro que encontrar

        if (vilaoResumo.isPresent()) {
            // 2. Usamos a URL que veio no resumo para buscar os detalhes reais
            var url = vilaoResumo.get().urlDetalhes();
            var json = consumo.obterDados(url);
            var resposta = conversor.obterDados(json, RespostaVilao.class);

            // 3. Agora temos o vilão com Gênero e Status preenchidos!
            System.out.println("\n--- Ficha Completa do Vilão ---");
            System.out.println(resposta.vilao());
        } else {
            System.out.println("Vilão não encontrado no Macroverso.");
        }
    }
}
