package br.com.jeniferocha.kingcode.service;

import br.com.jeniferocha.kingcode.model.Livro;
import br.com.jeniferocha.kingcode.repository.KingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LivroService {

    @Autowired
    private KingRepository repositorio;

    public List<Livro> findAll() {
        return repositorio.findAll(); // O Service pede os dados brutos para o Repository
    }
}
