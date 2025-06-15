package com.mountain_vd.controller.filter;

import com.mountain_vd.controller.search.SearchResults;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe {@code FilterChain} é responsável por aplicar uma cadeia de filtros
 * sobre uma lista de mídias encapsulada em um objeto {@link SearchResults}.
 *
 * Cada filtro da cadeia realiza uma operação de filtragem específica e modifica
 * a lista de mídias com base em critérios definidos. As mensagens geradas por cada
 * filtro são concatenadas para fornecer feedback ao usuário.
 *
 * Este padrão permite flexibilidade e modularidade ao aplicar múltiplos critérios
 * de filtragem de forma sequencial.
 */
public class FilterChain {
    private List<Filter> filters;
    private SearchResults searchResults;

    /**
     * Construtor padrão. Inicializa a lista de filtros.
     */
    public FilterChain() {
        this.filters = new ArrayList<>();
    }

    /**
     * Adiciona um filtro à cadeia de filtros que serão aplicados.
     *
     * @param filter o filtro a ser adicionado
     */
    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    /**
     * Aplica todos os filtros da cadeia sobre os resultados de busca atuais.
     *
     * Cada filtro opera sobre a lista atual de mídias e a atualiza no {@link SearchResults}.
     * As mensagens geradas pelos filtros são concatenadas ao final de cada aplicação.
     */
    public void applyFilters() {
        for (Filter filter : filters) {
            filter.setMedias(searchResults.getMediaList());
            filter.apply();
            searchResults.setMediaList(filter.getMedias());
            searchResults.setMessage(searchResults.getMessage() + "\n" + filter.getMessage());
        }
    }

    /**
     * Verifica se a cadeia de filtros está vazia.
     *
     * @return {@code true} se não há filtros, {@code false} caso contrário
     */
    public boolean isEmpty() {
        return filters.isEmpty();
    }

    /**
     * Define o objeto {@link SearchResults} que será usado como base
     * para os filtros aplicados.
     *
     * @param searchResults os resultados de busca iniciais
     */
    public void setSearchResults(SearchResults searchResults) {
        this.searchResults = searchResults;
    }

    /**
     * Retorna o objeto {@link SearchResults} com os resultados após os filtros.
     *
     * @return os resultados de busca processados
     */
    public SearchResults getSearchResults() {
        return searchResults;
    }
}
