package br.com.jeniferocha.kingcode.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class <T> classe);
}
