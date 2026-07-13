package br.com.jeniferocha.kingcode.repository;

import br.com.jeniferocha.kingcode.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KingRepository extends JpaRepository<Livro, Long> {
    // busca os livros pelo ano de lançamento exato
    List<Livro> findByAnoDeLancamento(Integer anoDeLancamento);

    // busca livros onde a lista de vilões contém o nome digitado e ignora maiúsculas/minúsculas
    @Query("SELECT l FROM Livro l JOIN l.viloes v WHERE LOWER(v) LIKE LOWER(CONCAT('%', :nomeVilao, '%'))")
    List<Livro> findByViloesContainingIgnoreCase(@Param("nomeVilao") String nomeVilao);
}
