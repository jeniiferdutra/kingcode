package br.com.jeniferocha.kingcode.main;

import br.com.jeniferocha.kingcode.model.*;
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
        System.out.println("\n--- LISTA DE TÍTULOS (Stephen King) ---");

        resposta.dadosLivroList().stream()
                .map(DadosLivro::titulo)
                .sorted()               //ordem alfabética
                .forEach(System.out::println);
    }

    public void pesquisaLivro() {
        DadosLivro dados = getDadosLivro();

        if (dados != null) {
            Livro livro = new Livro(dados);
            System.out.println(livro);
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private DadosLivro getDadosLivro() {
        System.out.println("Digite o nome do livro:");
        var nomeLivro = leitura.nextLine();

        System.out.println("\n--- Resultados da Busca ---");

        var json = consumo.obterDados(ENDERECO);
        var resposta = conversor.obterDados(json, RespostaAPI.class);
        return resposta.dadosLivroList().stream()
                .filter(l -> l.titulo().toLowerCase().contains(nomeLivro.toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    public void livroPorAnoDeLancamento() {
        System.out.println("Digite o ano de lançamento:");
        var anoLancamento = leitura.nextInt();
        leitura.nextLine();

        var json = consumo.obterDados(ENDERECO);
        var resposta = conversor.obterDados(json, RespostaAPI.class);

        List<Livro> livrosEncontrados = resposta.dadosLivroList().stream()
                .map(Livro::new)// // Converte o "pacote" da API para um objeto Livro com novas regras/formataçoes
                .filter(l -> l.getAnoDeLancamento() != null && l.getAnoDeLancamento().equals(anoLancamento))
                .toList();
        System.out.println("\n--- Livros lançados em " + anoLancamento + " ---");

        if (livrosEncontrados.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o ano " + anoLancamento + ".");
        } else {
            livrosEncontrados.forEach(System.out::println);
        }
    }

    public void buscaDetalhesVilao() {
        System.out.println("Digite o nome do vilão:");
        var nomeVilao = leitura.nextLine();

        // 1. Buscamos todos os livros para encontrar o resumo do vilão (com a URL de detalhes)
        var jsonLivros = consumo.obterDados(ENDERECO);
        var respostaLivros = conversor.obterDados(jsonLivros, RespostaAPI.class);

        var vilaoResumo = respostaLivros.dadosLivroList().stream()
                .filter(l -> l.viloes() != null)
                .flatMap(l -> l.viloes().stream())
                .filter(v -> v.nome().toLowerCase().contains(nomeVilao.toLowerCase()))
                .findFirst();

        if (vilaoResumo.isPresent()) {
            var url = vilaoResumo.get().urlDetalhes();
            var jsonDetalhe = consumo.obterDados(url);

            // Aqui usamos o DadosVilao (Record) para capturar o JSON
            var resposta = conversor.obterDados(jsonDetalhe, RespostaVilao.class);

            // Convertemos para o objeto Vilao (Classe) para usar o toString formatado
            Vilao vilaoCompleto = new Vilao(resposta.dadosVilao());

            System.out.println("\n--- Ficha Completa do Vilão ---");
            System.out.println(vilaoCompleto);
        } else {
            System.out.println("Vilão não encontrado no Macroverso.");
        }
    }
}
