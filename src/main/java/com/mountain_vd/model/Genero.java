package com.mountain_vd.model;

import com.mountain_vd.model.commons.Identifiable;

/**
 * Representa um gênero associado a uma mídia (ex: filme, série, música).
 *
 * Cada gênero possui um identificador único (id) e um nome descritivo (ex: "Drama", "Comédia").
 * Implementa a interface {@link Identifiable}, permitindo ser tratado como uma entidade identificável.
 *
 */
public class Genero implements Identifiable {

    /** Identificador único do gênero. */
    private int id;

    /** Nome do gênero. */
    private String nome;

    /**
     * Construtor que define o nome do gênero.
     *
     * @param nome nome descritivo do gênero.
     */
    public Genero(String nome){
        this.nome = nome;
    }

    public Genero() {}

    /**
     * Retorna o nome do gênero.
     *
     * @return nome do gênero.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna uma representação textual do gênero.
     *
     * @return nome do gênero como string.
     */
    @Override
    public String toString(){
        return this.nome;
    }

    /**
     * Retorna o identificador do gênero.
     *
     * @return identificador inteiro.
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Define o identificador do gênero.
     *
     * @param id novo identificador.
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }
}
