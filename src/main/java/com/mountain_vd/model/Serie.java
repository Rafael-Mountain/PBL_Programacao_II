package com.mountain_vd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma série de mídia, estendendo {@link MediaAudioVisual}.
 * Inclui atributos como data de término, temporadas, e métodos para avaliação.
 * Esta classe também implementa {@link Cloneable} para permitir clonagem de instâncias.
 *
 * A classe contém métodos para gerenciar temporadas e calcular pontuação média das temporadas.
 *
 */
public class Serie extends MediaAudioVisual implements Cloneable {

    /** ID da temporada atual. */
    private int temporadaId = 0;

    /** Data de término da série. */
    private LocalDateTime dataFim;

    /** Lista de temporadas da série. */
    private List<Temporada> temporadas;

    /** Tipo de mídia, fixado como "SÉRIE". */
    private TipoMedia tipoMedia = TipoMedia.SERIE;

    /**
     * Construtor padrão que inicializa a lista de temporadas.
     */
    public  Serie() {
        super();
        this.temporadas = new ArrayList<>();
    }

    /**
     * Construtor com parâmetros para inicializar uma série com título, data de lançamento,
     * elenco, e outros atributos específicos de uma série.
     *
     * @param titulo Título da série.
     * @param dataLancamento Data de lançamento da série.
     * @param consumido Indica se a série foi consumida.
     * @param generos Lista de gêneros associados à série.
     * @param tituloOriginal Título original da série.
     * @param localDisponivel Local onde a série está disponível.
     * @param elenco Lista de elenco da série.
     * @param dataFim Data de término da série.
     */
    public Serie(String titulo, LocalDateTime dataLancamento, boolean consumido,
                 List<Genero> generos, String tituloOriginal, String localDisponivel,
                 List<String> elenco, LocalDateTime dataFim) {
        super(titulo, dataLancamento, consumido, generos, tituloOriginal, localDisponivel, elenco);
        this.dataFim = dataFim;
        this.temporadas = new ArrayList<>();
    }

    /**
     * Retorna a data de término da série.
     *
     * @return Data de término.
     */
    public LocalDateTime getDataFim() {
        return dataFim;
    }

    /**
     * Define a data de término da série.
     *
     * @param dataFim Nova data de término.
     */
    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * Retorna a lista de temporadas da série.
     *
     * @return Lista de temporadas.
     */
    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    /**
     * Define as temporadas da série, atribuindo um ID único para cada temporada.
     *
     * @param temporadas Lista de temporadas a ser atribuída.
     */
    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas.stream()
                .peek(temporada -> {
                    temporada.setId(temporadaId++);
                    temporada.setSerieId(getId());
                })
                .collect(java.util.stream.Collectors.toList());
    }

    /**
     * Retorna o tipo de mídia (Série).
     *
     * @return Tipo de mídia (Série).
     */
    @Override
    public TipoMedia getTipoMedia() {
        return this.tipoMedia;
    }

    /**
     * Calcula a pontuação média das temporadas da série.
     * Caso a série tenha temporadas, a média das pontuações será retornada.
     * Caso contrário, retorna 0.
     *
     * @return Pontuação média das temporadas.
     */
    @JsonIgnore
    @Override
    public double getPontuacao() {
        if (!temporadas.isEmpty()) {
            return temporadas.stream()
                    .mapToDouble(Temporada::getPontuacao)
                    .sum() / temporadas.size();
        } else {
            return 0;
        }
    }

    /**
     * Atualiza uma temporada existente na lista de temporadas da série com base no ID da temporada.
     *
     * @param id ID da temporada a ser atualizada.
     * @param temporada Nova temporada a ser inserida.
     */
    public void updateTemporadaById(int id, Temporada temporada) {
        for (int i = 0; i < temporadas.size(); i++) {
            if (temporadas.get(i).getId() == id) {
                temporadas.set(i, temporada);
                break;
            }
        }
    }

    /**
     * Retorna uma temporada específica com base no ID fornecido.
     *
     * @param id ID da temporada a ser retornada.
     * @return Temporada correspondente ao ID fornecido, ou null se não encontrada.
     */
    public Temporada getTemporadaById(int id) {
        for (int i = 0; i < temporadas.size(); i++) {
            if (temporadas.get(i).getId() == id) {
                return temporadas.get(i);
            }
        }
        return null;
    }

    /**
     * Adiciona uma nova temporada à série, atribuindo um ID único e associando-a à série.
     *
     * @param t Temporada a ser adicionada.
     */
    public void addTemporada(Temporada t) {
        t.setId(temporadaId++);
        t.setSerieId(getId());
        temporadas.add(t);
    }
}
