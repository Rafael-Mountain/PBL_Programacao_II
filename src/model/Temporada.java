package model;

import model.commons.IAvaliavel;

import java.time.LocalDateTime;

public class Temporada  implements IAvaliavel{
    private LocalDateTime ano;
    private int qEpisodios;
    private int serieId;
    private TipoMedia tipoMedia = TipoMedia.TEMPORADA;

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void setId(int id) {

    }

    @Override
    public void Avaliar(Avaliacao avaliacao) {

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

    public int getSerieId() {
        return serieId;
    }

    public void setSerieId(int serieId) {
        this.serieId = serieId;
    }

    @Override
    public TipoMedia getTipoMedia() {
        return tipoMedia;
    }
}

