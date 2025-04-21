package model;

import model.commons.IAvaliavel;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Livro extends Media  implements IAvaliavel {
    private String autor;
    private String editora;
    private boolean possui;
    private String isbn;
    private TipoMedia tipoMedia = TipoMedia.LIVRO;
    private List<Avaliacao> avaliacoes;

    @Override
    public int getPontuacao() {
        return avaliacoes.stream()
                .max(Comparator.comparing(Avaliacao::getDataAvaliacao))
                .map(Avaliacao::getPontuacao)
                .orElse(0);
    }


    @Override
    public void Avaliar(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public boolean isPossui() {
        return possui;
    }

    public void setPossui(boolean possui) {
        this.possui = possui;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public TipoMedia getTipoMedia() {
        return this.tipoMedia;
    }

    @Override
    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes.stream()
                .sorted(Comparator.comparing(Avaliacao::getDataAvaliacao).reversed())
                .collect(Collectors.toList());
    }
}
