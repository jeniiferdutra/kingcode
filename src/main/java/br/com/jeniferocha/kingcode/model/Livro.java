package br.com.jeniferocha.kingcode.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Livro(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Publisher") String editora,
        @JsonAlias("Pages") Integer paginas,
        @JsonAlias("Year") Integer ano,
        @JsonAlias("villains") List<Vilao> viloes
) {
    @Override
    public String toString() {
        return String.format(
                "| Livro: %s | Editora: %s | Páginas: %4d | Ano: %d | Vilões: %s",
                titulo, editora, paginas, ano, viloes
        );
    }
}
