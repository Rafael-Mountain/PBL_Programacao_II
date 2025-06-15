package com.mountain_vd.controller.search;

/**
 * Fábrica para criação de instâncias de {@link Search} conforme o tipo de busca.
 * <p>
 * Implementações dessa interface devem retornar o objeto {@link Search} apropriado
 * para o critério de busca fornecido em {@link SearchFields}.
 * </p>
 *
 * @see Search
 * @see SearchFields
 */
public interface SearchFactory {

    /**
     * Cria uma instância de {@link Search} de acordo com o tipo de busca especificado.
     *
     * @param type Enumeração {@link SearchFields} que representa o critério de busca
     *             (ex.: TUDO, TITULO, ANO_LANCAMENTO, GENERO etc.).
     * @return Objeto {@link Search} configurado para executar a busca desejada,
     *         ou {@code null} se o tipo não for suportado.
     */
    Search createSearch(SearchFields type);
}
