package br.com.jeniferocha.kingcode.controller;
import br.com.jeniferocha.kingcode.dto.LivroDTO;
import br.com.jeniferocha.kingcode.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController // -> Avisa ao Spring que esta classe é uma API Web que vai retornar dados (JSON), sem HTML.
public class LivroController {

    @Autowired
    LivroService service;

    @GetMapping("/books") // -> Cria a rota na URL.
    public List<LivroDTO> obterTodosOsLivros() {
        return service.findAll()
                .stream()
                .map(l -> new LivroDTO(l.getId(), l.getTitulo(), l.getAnoDeLancamento(), l.getNumeroDePaginas()))
                .collect(Collectors.toList());
    }
}
