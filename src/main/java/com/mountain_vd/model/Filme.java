package com.mountain_vd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mountain_vd.model.commons.IAvaliavel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Representa um filme como uma mídia audiovisual, contendo informações específicas
 * como direção, duração, roteiro e avaliações feitas por usuários.
 *
 * Estende a classe {@link MediaAudioVisual} e implementa a interface {@link IAvaliavel},
 * permitindo que seja avaliado por usuários.
 *
 * A pontuação retornada corresponde à mais recente avaliação realizada.
 *
 * @see Avaliacao
 * @see TipoMedia
 * @see MediaAudioVisual
 * @see IAvaliavel
 * @see Genero
 *
 */
public class Filme extends MediaAudioVisual implements IAvaliavel {

    /** Nome do diretor ou diretora do filme. */
    private String direcao;

    /** Duração do filme em minutos. */
    private int duracao;

    /** Informações sobre o roteiro ou roteirista. */
    private String roteiro;

    /** Tipo da mídia, fixado como FILME. */
    private TipoMedia tipoMedia = TipoMedia.FILME;

    /** Lista de avaliações associadas ao filme. */
    private List<Avaliacao> avaliacoes;

    /**
     * Construtor padrão.
     * Inicializa a lista de avaliações como vazia.
     */
    public Filme() {
        super();
        this.avaliacoes = new ArrayList<>();
    }

    /**
     * Construtor completo para criação de um filme com todos os detalhes.
     *
     * @param titulo Título do filme.
     * @param dataLancamento Data e hora do lançamento.
     * @param consumido Indica se o filme já foi consumido pelo usuário.
     * @param generos Lista de gêneros associados ao filme.
     * @param tituloOriginal Título original do filme.
     * @param localDisponivel Plataforma ou local onde o filme está disponível.
     * @param elenco Lista com nomes do elenco.
     * @param direcao Nome do diretor(a).
     * @param duracao Duração em minutos.
     * @param roteiro Detalhes do roteiro.
     */
    public Filme(String titulo, LocalDateTime dataLancamento, boolean consumido, List<Genero> generos,
                 String tituloOriginal, String localDisponivel, List<String> elenco, String direcao,
                 int duracao, String roteiro) {
        super(titulo, dataLancamento, consumido, generos, tituloOriginal, localDisponivel, elenco);
        this.avaliacoes = new ArrayList<>();
        this.direcao = direcao;
        this.duracao = duracao;
        this.roteiro = roteiro;
    }

    /**
     * Retorna o nome do diretor do filme.
     *
     * @return nome do diretor.
     */
    public String getDirecao() {
        return direcao;
    }

    /**
     * Define o nome do diretor do filme.
     *
     * @param direcao nome do diretor.
     */
    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    /**
     * Retorna a duração do filme em minutos.
     *
     * @return duração em minutos.
     */
    public int getDuracao() {
        return duracao;
    }

    /**
     * Define a duração do filme em minutos.
     *
     * @param duracao nova duração.
     */
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    /**
     * Retorna informações sobre o roteiro.
     *
     * @return detalhes do roteiro.
     */
    public String getRoteiro() {
        return roteiro;
    }

    /**
     * Define informações sobre o roteiro.
     *
     * @param roteiro novo roteiro.
     */
    public void setRoteiro(String roteiro) {
        this.roteiro = roteiro;
    }

    /**
     * Retorna o tipo da mídia, que neste caso é sempre {@link TipoMedia#FILME}.
     *
     * @return tipo da mídia.
     */
    public TipoMedia getTipoMedia() {
        return this.tipoMedia;
    }

    /**
     * Adiciona uma nova avaliação ao filme.
     *
     * @param avaliacao avaliação a ser adicionada.
     */
    @Override
    public void avaliar(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }


    /**
     * Retorna a pontuação da avaliação mais recente feita ao filme.
     *
     * @return pontuação mais recente, ou 0 se não houver avaliações.
     */
    @JsonIgnore
    @Override
    public double getPontuacao() {
        return avaliacoes.stream()
                .max(Comparator.comparing(Avaliacao::getDataAvaliacao))
                .map(Avaliacao::getPontuacao)
                .orElse(0);
    }

    /**
     * Retorna a lista de avaliações ordenada da mais recente para a mais antiga.
     *
     * @return lista de avaliações em ordem decrescente de data.
     */
    @Override
    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes.stream()
                .sorted(Comparator.comparing(Avaliacao::getDataAvaliacao).reversed())
                .collect(Collectors.toList());
    }
}
