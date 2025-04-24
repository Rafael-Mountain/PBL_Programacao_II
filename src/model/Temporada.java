package model;

import model.commons.IAvaliavel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Representa uma temporada de uma série de mídia, implementando a interface {@link IAvaliavel}.
 * A temporada tem atributos como o número de episódios, ano de lançamento, avaliações dos usuários,
 * e a associação com uma série específica.
 *
 * Esta classe oferece métodos para avaliar a temporada, calcular sua pontuação e manipular suas avaliações.
 *
 * @author
 */
public class Temporada implements IAvaliavel {

    /** ID da temporada. */
    private int id;

    /** Ano de lançamento da temporada. */
    private LocalDateTime ano;

    /** Quantidade de episódios na temporada. */
    private int qEpisodios;

    /** ID da série à qual esta temporada pertence. */
    private int serieId;

    /** Tipo de mídia, fixado como "TEMPORADA". */
    private TipoMedia tipoMedia = TipoMedia.TEMPORADA;

    /** Lista de avaliações da temporada. */
    private List<Avaliacao> avaliacoes;

    /**
     * Construtor padrão que inicializa a lista de avaliações.
     */
    public Temporada() {
        this.avaliacoes = new ArrayList<>();
    }

    /**
     * Construtor com parâmetros para inicializar uma temporada com quantidade de episódios e ano de lançamento.
     *
     * @param qEpisodios Quantidade de episódios da temporada.
     * @param ano Ano de lançamento da temporada.
     */
    public Temporada(int qEpisodios, LocalDateTime ano) {
        this.qEpisodios = qEpisodios;
        this.ano = ano;
        this.avaliacoes = new ArrayList<>();
    }

    /**
     * Retorna a quantidade de episódios da temporada.
     *
     * @return Quantidade de episódios.
     */
    public int getqEpisodios() {
        return qEpisodios;
    }

    /**
     * Define a quantidade de episódios da temporada.
     *
     * @param qEpisodios Nova quantidade de episódios.
     */
    public void setqEpisodios(int qEpisodios) {
        this.qEpisodios = qEpisodios;
    }

    /**
     * Retorna o ano de lançamento da temporada.
     *
     * @return Ano de lançamento.
     */
    public LocalDateTime getAno() {
        return ano;
    }

    /**
     * Define o ano de lançamento da temporada.
     *
     * @param ano Novo ano de lançamento.
     */
    public void setAno(LocalDateTime ano) {
        this.ano = ano;
    }

    /**
     * Retorna o ID da série à qual a temporada pertence.
     *
     * @return ID da série.
     */
    public int getSerieId() {
        return serieId;
    }

    /**
     * Define o ID da série à qual a temporada pertence.
     *
     * @param serieId ID da série.
     */
    public void setSerieId(int serieId) {
        this.serieId = serieId;
    }

    /**
     * Retorna o ID da temporada.
     *
     * @return ID da temporada.
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Define o ID da temporada.
     *
     * @param id Novo ID da temporada.
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o tipo de mídia (TEMPORADA).
     *
     * @return Tipo de mídia.
     */
    @Override
    public TipoMedia getTipoMedia() {
        return tipoMedia;
    }

    /**
     * Adiciona uma avaliação à temporada.
     *
     * @param avaliacao Avaliação a ser adicionada.
     */
    @Override
    public void avaliar(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }

    /**
     * Retorna a pontuação mais recente da temporada, baseada na avaliação mais recente.
     * Se não houver avaliações, retorna 0.
     *
     * @return Pontuação da temporada.
     */
    @Override
    public double getPontuacao() {
        return avaliacoes.stream()
                .max(Comparator.comparing(Avaliacao::getDataAvaliacao))
                .map(Avaliacao::getPontuacao)
                .orElse(0);
    }

    /**
     * Retorna a lista de avaliações da temporada, ordenada pela data de avaliação em ordem decrescente.
     *
     * @return Lista de avaliações ordenadas.
     */
    @Override
    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes.stream()
                .sorted(Comparator.comparing(Avaliacao::getDataAvaliacao).reversed())
                .collect(Collectors.toList());
    }
}
