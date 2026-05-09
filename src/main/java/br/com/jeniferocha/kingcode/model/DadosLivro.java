package br.com.jeniferocha.kingcode.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DadosLivro(@JsonAlias("Title") String titulo,
                         @JsonAlias("Year") Integer anodeLancamento,
                         @JsonAlias("Pages") Integer numeroDePaginas) {
}
