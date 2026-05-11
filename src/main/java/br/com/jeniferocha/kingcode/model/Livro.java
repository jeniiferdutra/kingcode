package br.com.jeniferocha.kingcode.model;

public class Livro {
    private String titulo;
    private Integer anoDeLancamento;
    private Integer numeroDePaginas;

    public Livro(DadosLivro dados) {
        this.titulo = dados.titulo();
        this.anoDeLancamento = dados.anodeLancamento();
        this.numeroDePaginas = dados.numeroDePaginas();
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

    @Override
    public String toString() {
        return
                "Título: " + titulo +
                "\nAno: " + anoDeLancamento +
                "\nPáginas: " + numeroDePaginas +
                "\n---------------------------------------";
    }
}
