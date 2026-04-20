package br.com.jeniferocha.kingcode.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Lista de objeto de vilão
public record RespostaVilao(
        @JsonAlias("data") Vilao vilao
) {}
