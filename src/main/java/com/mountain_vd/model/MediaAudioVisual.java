package com.mountain_vd.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstrata que representa mídias audiovisuais (como filmes, séries).
 * Extende a classe {@link Media} e adiciona atributos e métodos específicos para mídias audiovisuais.
 *
 * Atributos como elenco, título original e local de disponibilidade são específicos para esse tipo de mídia.
 *
 */
public abstract class MediaAudioVisual extends Media {

    /** Lista de nomes do elenco da mídia. */
    private List<String> elenco;

    /** Título original da mídia (caso seja diferente do título em outro idioma). */
    private String tituloOriginal;

    /** Local onde a mídia está disponível (ex: plataforma de streaming, loja física). */
    private String localDisponivel;

    /**
     * Construtor padrão que inicializa a lista de elenco como vazia.
     */
    public MediaAudioVisual() {
        super();
        this.elenco = new ArrayList<>();
    }

    /**
     * Construtor com parâmetros para inicialização completa da mídia audiovisual.
     *
     * @param titulo Título da mídia.
     * @param dataLancamento Data de lançamento da mídia.
     * @param consumido Indica se a mídia já foi consumida.
     * @param generos Lista de gêneros associados à mídia.
     * @param tituloOriginal Título original da mídia.
     * @param localDisponivel Local onde a mídia está disponível.
     * @param elenco Lista de nomes do elenco da mídia.
     */
    public MediaAudioVisual(String titulo, LocalDateTime dataLancamento, boolean consumido, List<Genero> generos,
                            String tituloOriginal, String localDisponivel, List<String> elenco) {

        super(titulo, dataLancamento, consumido, generos); // Chama o construtor da classe pai
        this.tituloOriginal = tituloOriginal;
        this.localDisponivel = localDisponivel;
        this.elenco = (elenco != null) ? new ArrayList<>(elenco) : new ArrayList<>();
    }

    /**
     * Retorna uma cópia da lista de elenco da mídia.
     *
     * @return Lista do elenco.
     */
    public List<String> getElenco() {
        return new ArrayList<>(elenco); // Retorna uma cópia para evitar modificações externas
    }

    /**
     * Define o elenco da mídia.
     *
     * @param elenco nova lista de atores/atrizes do elenco.
     */
    public void setElenco(List<String> elenco) {
        if (elenco != null) {
            this.elenco = new ArrayList<>(elenco);
        }
    }

    /**
     * Adiciona um ator ou atriz ao elenco da mídia.
     *
     * @param ator Nome do ator ou atriz a ser adicionado.
     */
    public void addElenco(String ator) {
        if (ator != null && !ator.trim().isEmpty()) {
            this.elenco.add(ator);
        }
    }

    /**
     * Retorna o título original da mídia.
     *
     * @return Título original.
     */
    public String getTituloOriginal() {
        return tituloOriginal;
    }

    /**
     * Define o título original da mídia.
     *
     * @param tituloOriginal novo título original.
     */
    public void setTituloOriginal(String tituloOriginal) {
        this.tituloOriginal = tituloOriginal;
    }

    /**
     * Retorna o local onde a mídia está disponível.
     *
     * @return Local de disponibilidade (ex: plataforma de streaming).
     */
    public String getLocalDisponivel() {
        return localDisponivel;
    }

    /**
     * Define o local onde a mídia está disponível.
     *
     * @param localDisponivel novo local de disponibilidade.
     */
    public void setLocalDisponivel(String localDisponivel) {
        this.localDisponivel = localDisponivel;
    }
}
