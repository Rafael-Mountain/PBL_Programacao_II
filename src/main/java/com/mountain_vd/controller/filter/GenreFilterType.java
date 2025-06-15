package com.mountain_vd.controller.filter;

import com.mountain_vd.controller.util.EnumUtils.Descritivel;

/**
 * Enumeração que define os tipos de filtro de gênero para a busca de mídias.
 * Cada tipo de filtro tem uma descrição que explica o comportamento do filtro.
 */
public enum GenreFilterType implements Descritivel {
    /**
     * Filtro que retorna os resultados que contêm os gêneros especificados.
     */
    CONTEM("Contém o gênero"),

    /**
     * Filtro que retorna os resultados que não contêm os gêneros especificados.
     */
    NAO_CONTEM("Não contém o gênero");

    private String descricao;

    /**
     * Construtor que define a descrição associada ao tipo de filtro.
     *
     * @param descricao Descrição do tipo de filtro.
     */
    GenreFilterType(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna a descrição do tipo de filtro.
     *
     * @return Descrição do filtro.
     */
    public String getDescricao() {
        return descricao;
    }

}
