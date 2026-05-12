package br.com.jeniferocha.kingcode.repository;

import br.com.jeniferocha.kingcode.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KingRepository extends JpaRepository<Livro, Long> {
}
