package br.com.jeniferocha.kingcode.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ConverteDados implements IConverteDados {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter o JSON: " + e.getMessage());
        }
    }
}
