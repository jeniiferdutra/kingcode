package br.com.jeniferocha.kingcode.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Vilao(
        @JsonAlias("name") String nome,
        @JsonAlias("url") String urlDetalhes
) {
    @Override
    public String toString() {
        return nome;
    }
}