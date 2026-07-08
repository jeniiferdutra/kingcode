package br.com.jeniferocha.kingcode.service;

import br.com.jeniferocha.kingcode.model.DadosLivro;
import br.com.jeniferocha.kingcode.model.Livro;
import br.com.jeniferocha.kingcode.model.RespostaAPI;
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

    // add a lógica de salvar o livro (que valida se ele é nulo ou duplicado).
    public void salvarLivro(DadosLivro dados) {
        if (dados == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        boolean jaExiste = repositorio.findAll().stream() // verifica se o título já existe no banco
                .anyMatch(l -> l.getTitulo().equalsIgnoreCase(dados.titulo()));

        if (!jaExiste) {
            Livro livro = new Livro(dados);
            repositorio.save(livro);
            System.out.println(livro);
        } else {
            System.out.println("\nO livro '" + dados.titulo() + "' já está cadastrado no seu banco.");
        }
    }

    public DadosLivro consultarApi(String nomeLivro) {
        ConsumoAPI consumo = new ConsumoAPI();
        ConverteDados conversor = new ConverteDados();
        String endereco = "https://stephen-king-api.onrender.com/api/books";

        var json = consumo.obterDados(endereco);
        var resposta = conversor.obterDados(json, RespostaAPI.class);

        return resposta.dadosLivroList().stream()
                .filter(l -> l.titulo().toLowerCase().contains(nomeLivro.toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    public List<Livro> buscarPorAno(Integer ano) {
        // service delega a busca filtrada para o repository
        return repositorio.findByAnoDeLancamento(ano);
    }
}
