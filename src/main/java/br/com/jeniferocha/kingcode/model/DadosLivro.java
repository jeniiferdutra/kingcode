package br.com.jeniferocha.kingcode.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DadosLivro(@JsonAlias({"Title", "title"}) String titulo,
                         @JsonAlias("Year") Integer anodeLancamento,
                         @JsonAlias("Pages") Integer numeroDePaginas,
                         @JsonAlias("villains") List<DadosVilao> viloes,
                         @JsonAlias("Publisher") String editora,
                         @JsonAlias("ISBN") String isbn) {
}
