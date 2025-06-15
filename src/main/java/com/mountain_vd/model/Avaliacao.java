package com.mountain_vd.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Representa uma avaliação feita por um usuário sobre um determinado item consumido.
 *
 * Contém informações como a data da avaliação, pontuação atribuída, um comentário (review)
 * e a data em que o item foi consumido.
 *
 * Pode ser utilizada em sistemas de recomendação, avaliações de filmes, produtos, etc.
 *
 */
public class Avaliacao {
    /** Data e hora em que a avaliação foi realizada. */
    private LocalDateTime dataAvaliacao;

    /** Pontuação atribuída na avaliação. */
    private int pontuacao;

    /** Comentário escrito pelo usuário sobre o item avaliado. */
    private String review;

    /** Data em que o item avaliado foi consumido. */
    private LocalDate dataConsumo;

    /**
     * Construtor padrão.
     */
    public Avaliacao() {}

    /**
     * Construtor com todos os atributos.
     *
     * @param dataAvaliacao Data e hora da avaliação.
     * @param pontuacao Pontuação atribuída.
     * @param review Comentário do usuário.
     * @param dataConsumo Data do consumo do item.
     */
    public Avaliacao(LocalDateTime dataAvaliacao, int pontuacao, String review, LocalDate dataConsumo) {
        this.dataAvaliacao = dataAvaliacao;
        this.pontuacao = pontuacao;
        this.review = review;
        this.dataConsumo = dataConsumo;
    }

    /**
     * Retorna a data e hora da avaliação.
     *
     * @return data e hora da avaliação.
     */
    public LocalDateTime getDataAvaliacao() {
        return dataAvaliacao;
    }

    /**
     * Define a data e hora da avaliação.
     *
     * @param dataAvaliacao nova data e hora da avaliação.
     */
    public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    /**
     * Retorna a pontuação da avaliação.
     *
     * @return valor inteiro da pontuação.
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * Define a pontuação da avaliação.
     *
     * @param pontuacao nova pontuação.
     */
    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    /**
     * Retorna o comentário da avaliação.
     *
     * @return texto da review.
     */
    public String getReview() {
        return review;
    }

    /**
     * Define o comentário da avaliação.
     *
     * @param review novo comentário.
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * Retorna a data em que o item foi consumido.
     *
     * @return data de consumo.
     */
    public LocalDate getDataConsumo() {
        return dataConsumo;
    }

    /**
     * Define a data de consumo do item avaliado.
     *
     * @param dataConsumo nova data de consumo.
     */
    public void setDataConsumo(LocalDate dataConsumo) {
        this.dataConsumo = dataConsumo;
    }
}
