package br.com.jeniferocha.kingcode.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Vilao(
        @JsonAlias("name") String nome,
        @JsonAlias("url") String urlDetalhes,
        @JsonAlias("gender") String genero,
        @JsonAlias("status") String status,
        @JsonAlias("books") List<VilaoLivro> tituloLivro
        ) {

    @Override
    public String toString() {
        // Se gênero for nulo, significa que é o resumo que vem na lista de livros
        if (genero == null) {
            return nome;
        }

        // Se gênero existir, é a ficha completa da Opção 4
        String nomeLivro = (tituloLivro != null && !tituloLivro.isEmpty())
                ? tituloLivro.get(0).tituloLivroVilao()
                : "Obra não mapeada";

        return String.format("\n   > Ficha do Vilão: %s\n   > Gênero: %s | Status: %s\n   > Aparição Principal: %s",
                nome, genero, status, nomeLivro);
    }
}