package model;

import java.time.LocalDateTime;
import java.util.Date;

public class Livro extends Media {
    private String autor;
    private String editora;
    private boolean possui;
    private String isbn;
    private TipoMedia tipoMedia = TipoMedia.LIVRO;

    @Override
    public int getPontuacao() {
        return 0;
    }

    @Override
    public void Avaliar(LocalDateTime dataAvaliacao, int pontuacao, String review, LocalDateTime dataConsumo) {

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
}
