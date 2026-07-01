package br.com.jeniferocha.kingcode.model;

import java.util.List;
public class Vilao {
    private String nome;
    private String urlDetalhes;
    private String genero;
    private String status;
    private List<DadosLivro> tituloLivro;

    public Vilao(DadosVilao dadosVilao) {
        this.nome = dadosVilao.nome();
        this.urlDetalhes = dadosVilao.urlDetalhes();
        this.genero = dadosVilao.genero();
        this.status = dadosVilao.status();
        this.tituloLivro = dadosVilao.tituloLivro();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlDetalhes() {
        return urlDetalhes;
    }

    public void setUrlDetalhes(String urlDetalhes) {
        this.urlDetalhes = urlDetalhes;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DadosLivro> getTituloLivro() {
        return tituloLivro;
    }

    public void setTituloLivro(List<DadosLivro> tituloLivro) {
        this.tituloLivro = tituloLivro;
    }

    @Override
    public String toString() {
        if (genero == null) {
            return nome;
        }

        String obraPrincipal = (tituloLivro != null && !tituloLivro.isEmpty())
                ? tituloLivro.get(0).titulo()
                : "Obra não mapeada";

        return String.format("""
           
           > Ficha do Vilão: %s 
           > Gênero: %s | Status: %s
           > Aparição Principal: %s""",
                nome, genero, status, obraPrincipal);
    }
}