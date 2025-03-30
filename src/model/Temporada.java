package model;

import model.commons.IAvaliavel;
import model.commons.Identifiable;

import java.time.LocalDateTime;

public class Temporada  implements IAvaliavel, Identifiable {
    private LocalDateTime ano;
    private int qEpisodios;
    private int idSerie;

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public void Avaliar(LocalDateTime dataAvaliacao, int pontuacao, String review, LocalDateTime dataConsumo) {

    }

    @Override
    public int getPontuacao() {
        return 0;
    }


    public int getqEpisodios() {
        return qEpisodios;
    }

    public LocalDateTime getAno() {
        return ano;
    }

    public void setAno(LocalDateTime ano) {
        this.ano = ano;
    }

    public void setqEpisodios(int qEpisodios) {
        this.qEpisodios = qEpisodios;
    }

    public int getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(int idSerie) {
        this.idSerie = idSerie;
    }
}

