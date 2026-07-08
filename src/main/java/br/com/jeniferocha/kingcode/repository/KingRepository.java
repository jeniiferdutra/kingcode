package br.com.jeniferocha.kingcode.repository;

import br.com.jeniferocha.kingcode.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KingRepository extends JpaRepository<Livro, Long> {
    // busca os livros pelo ano de lançamento exato
    List<Livro> findByAnoDeLancamento(Integer anoDeLancamento);
}
