package br.com.jeniferocha.kingcode.main;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Main {
    private Scanner leitura = new Scanner(System.in);

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
                case 0 -> System.out.println("Saindo do Macroverso... Até a próxima!");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    public void exibirOpcoes() {
        System.out.println("""
                *** KingCode - Engine ***
                1 - Listar todos os livros
                2 - Buscar livro
                3 - Livro por ano de lançamento
                4 - Detalhes do Vilão
                5 - Sair
                """
                );
    }

    public void listaTodosOsLivros() {

    }

    public void pesquisaLivro() {

    }

    public void livroPorAnoDeLancamento() {

    }

    public void buscaDetalhesVilao() {

    }
}
