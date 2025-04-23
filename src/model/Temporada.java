package model;

import model.commons.IAvaliavel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Temporada  implements IAvaliavel{
    private int id;
    private LocalDateTime ano;
    private int qEpisodios;
    private int serieId;
    private TipoMedia tipoMedia = TipoMedia.TEMPORADA;
    private List<Avaliacao> avaliacoes;

    public Temporada(int qEpisodios, LocalDateTime ano) {
        this.qEpisodios = qEpisodios;
        this.ano = ano;
        this.avaliacoes = new ArrayList<>();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void Avaliar(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }

    @Override
    public double getPontuacao() {
        return avaliacoes.stream()
                .max(Comparator.comparing(Avaliacao::getDataAvaliacao))
                .map(Avaliacao::getPontuacao)
                .orElse(0);
    }

    @Override
    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes.stream()
                .sorted(Comparator.comparing(Avaliacao::getDataAvaliacao).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public TipoMedia getTipoMedia() {
        return tipoMedia;
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
}

