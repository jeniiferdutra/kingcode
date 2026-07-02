package br.com.jeniferocha.kingcode.dto;

public record LivroDTO(Long id,
                       String titulo,
                       Integer anoDeLancamento,
                       Integer numeroDePaginas) {
}
