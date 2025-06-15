package com.mountain_vd.controller.filter;

import java.util.stream.Collectors;

/**
 * A classe {@code YearFilter} é responsável por filtrar mídias com base no ano
 * de lançamento.
 *
 * O filtro pode ser configurado para aplicar diferentes condições em relação ao
 * ano de lançamento das mídias, de acordo com o tipo de filtro especificado na
 * enumeração {@link YearFilterType}.
 *
 * Os tipos de filtro disponíveis são:
 * <ul>
 *     <li>{@code MAIOR}: mantém as mídias cujo ano de lançamento é maior que o valor fornecido</li>
 *     <li>{@code MENOR}: mantém as mídias cujo ano de lançamento é menor que o valor fornecido</li>
 *     <li>{@code IGUAL}: mantém as mídias cujo ano de lançamento é igual ao valor fornecido</li>
 *     <li>{@code DIFERENTE}: mantém as mídias cujo ano de lançamento é diferente do valor fornecido</li>
 * </ul>
 *
 * Esta classe estende {@link Filter}, utilizando a lista de mídias para aplicar
 * o filtro de acordo com a condição fornecida.
 */
public class YearFilter extends Filter {

    private YearFilterType filterType;
    private int filterValue;

    /**
     * Construtor da classe {@code YearFilter}.
     *
     * @param filterType  o tipo de filtragem a ser aplicado (MAIOR, MENOR, IGUAL ou DIFERENTE)
     * @param filterValue o ano de referência para o filtro
     */
    public YearFilter(YearFilterType filterType, int filterValue) {
        super();
        this.filterType = filterType;
        this.filterValue = filterValue;
    }

    /**
     * Aplica o filtro de ano à lista de mídias.
     *
     * Dependendo do tipo de filtro, a lista será reduzida apenas às mídias
     * cujo ano de lançamento atenda à condição especificada:
     * <ul>
     *     <li>MAIOR: ano de lançamento maior que o valor fornecido</li>
     *     <li>MENOR: ano de lançamento menor que o valor fornecido</li>
     *     <li>IGUAL: ano de lançamento igual ao valor fornecido</li>
     *     <li>DIFERENTE: ano de lançamento diferente do valor fornecido</li>
     * </ul>
     */
    @Override
    void apply() {
        if (filterType == YearFilterType.MAIOR) {
            medias = medias.stream()
                    .filter(media -> media.getDataLancamento().getYear() > filterValue)
                    .collect(Collectors.toList());
        } else if (filterType == YearFilterType.MENOR) {
            medias = medias.stream()
                    .filter(media -> media.getDataLancamento().getYear() < filterValue)
                    .collect(Collectors.toList());
        } else if (filterType == YearFilterType.IGUAL) {
            medias = medias.stream()
                    .filter(media -> media.getDataLancamento().getYear() == filterValue)
                    .collect(Collectors.toList());
        } else if (filterType == YearFilterType.DIFERENTE) {
            medias = medias.stream()
                    .filter(media -> media.getDataLancamento().getYear() != filterValue)
                    .collect(Collectors.toList());
        } else {
            System.out.println("Tipo de filtro inválido para YearFilter");
        }
    }
}
