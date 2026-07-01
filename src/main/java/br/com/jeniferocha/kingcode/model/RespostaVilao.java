package br.com.jeniferocha.kingcode.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Lista de objeto de vilão
@JsonIgnoreProperties(ignoreUnknown = true)
public record RespostaVilao(
        @JsonAlias("data") DadosVilao dadosVilao
) {}
