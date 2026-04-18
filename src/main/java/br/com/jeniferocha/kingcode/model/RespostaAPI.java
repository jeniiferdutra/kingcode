package br.com.jeniferocha.kingcode.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RespostaAPI(
        @JsonAlias("data") List<Livro> livros
) {}