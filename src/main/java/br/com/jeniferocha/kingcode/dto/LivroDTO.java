package br.com.jeniferocha.kingcode.dto;

import br.com.jeniferocha.kingcode.model.Livro;

import java.util.List;

public record LivroDTO(Long id,
                       String titulo,
                       Integer anoDeLancamento,
                       Integer numeroDePaginas,
                       String editora,
                       String isbn,
                       List<String> viloes) {

    public LivroDTO(Livro livro) {
        this(
                livro.getId(),
                livro.getTitulo(),
                livro.getAnoDeLancamento(),
                livro.getNumeroDePaginas(),
                livro.getEditora(),
                livro.getIsbn(),
                livro.getViloes()
        );
    }
}