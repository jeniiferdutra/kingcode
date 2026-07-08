package br.com.jeniferocha.kingcode.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private Integer anoDeLancamento;
    private Integer numeroDePaginas;
    private String editora;
    private String isbn;

    // cria uma tabela auxiliar automática no banco para guardar os nomes dos vilões
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "livro_viloes", joinColumns = @JoinColumn(name = "livro_id"))
    @Column(name = "nome_vilao")
    private List<String> viloes = new ArrayList<>();

    public Livro() {}

    public Livro(DadosLivro dados) {
        this.titulo = dados.titulo();
        this.anoDeLancamento = dados.anodeLancamento();
        this.numeroDePaginas = dados.numeroDePaginas();
        this.editora = dados.editora();
        this.isbn = dados.isbn();

        // Mapeia a lista de objetos da API salvando apenas os nomes no nosso banco
        if (dados.viloes() != null) {
            this.viloes = dados.viloes().stream()
                    .map(DadosVilao::nome)
                    .toList();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(Integer anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public Integer getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public void setNumeroDePaginas(Integer numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<String> getViloes() {
        return viloes;
    }

    public void setViloes(List<String> viloes) {
        this.viloes = viloes;
    }

    @Override
    public String toString() {
        return
                "Título: " + titulo +
                "\nEditora: " + editora +
                "\nISBN: " + isbn +
                "\nAno: " + anoDeLancamento +
                "\nPáginas: " + numeroDePaginas +
                "\nVilões: " + (viloes.isEmpty() ? "Nenhum" : String.join(", ", viloes)) +
                "\n---------------------------------------";
    }
}
