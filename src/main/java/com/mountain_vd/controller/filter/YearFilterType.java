package com.mountain_vd.controller.filter;

import com.mountain_vd.controller.util.EnumUtils;

/**
 * A enumeração {@code YearFilterType} define os tipos de filtro que podem ser aplicados
 * para comparar o ano de lançamento das mídias.
 *
 * Cada valor da enumeração representa um tipo de condição que pode ser usada para filtrar as mídias
 * com base no ano de lançamento:
 * <ul>
 *     <li>{@code MAIOR}: Filtra as mídias cujo ano de lançamento é maior que o valor fornecido.</li>
 *     <li>{@code MENOR}: Filtra as mídias cujo ano de lançamento é menor que o valor fornecido.</li>
 *     <li>{@code IGUAL}: Filtra as mídias cujo ano de lançamento é igual ao valor fornecido.</li>
 *     <li>{@code DIFERENTE}: Filtra as mídias cujo ano de lançamento é diferente do valor fornecido.</li>
 * </ul>
 */
public enum YearFilterType implements EnumUtils.Descritivel {

    MAIOR("Ano maior que"),
    MENOR("Ano menor que"),
    IGUAL("Ano Igual a"),
    DIFERENTE("Ano Diferente de");

    private String descricao;

    /**
     * Construtor da enumeração {@code YearFilterType}.
     *
     * @param descricao a descrição do tipo de filtro
     */
    YearFilterType(String descricao){
        this.descricao = descricao;
    }

    /**
     * Obtém a descrição associada ao tipo de filtro.
     *
     * @return a descrição do tipo de filtro
     */
    public String getDescricao(){
        return descricao;
    }
}
