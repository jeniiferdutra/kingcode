package br.com.jeniferocha.kingcode.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVilao(
        @JsonAlias("name") String nome,
        @JsonAlias("url") String urlDetalhes,
        @JsonAlias("gender") String genero,
        @JsonAlias("status") String status,
        @JsonAlias("books") List<DadosLivro> tituloLivro
) {}
