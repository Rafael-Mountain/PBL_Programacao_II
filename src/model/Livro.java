package model;

import java.time.LocalDateTime;
import java.util.Date;

public class Livro extends Media {
    private String autor;
    private String editora;
    private boolean possui;
    private String isbn;

    @Override
    public int getPontuacao() {
        return 0;
    }

    @Override
    public void Avaliar(LocalDateTime dataAvaliacao, int pontuacao, String review, LocalDateTime dataConsumo) {

    }
}
