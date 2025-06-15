package com.mountain_vd.controller.search;

import com.mountain_vd.controller.filter.FilterChain;
import com.mountain_vd.model.Media;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador abstrato para execução de buscas com suporte a filtros e ordenação.
 * <p>
 * Encapsula o termo de busca e o campo de pesquisa, delega a estratégia de busca à
 * {@link SearchFactory}, aplica filtros encadeados via {@link FilterChain} e
 * realiza ordenação dos resultados com base na pontuação das mídias.
 * </p>
 *
 * @see SearchFactory
 * @see Search
 * @see SearchResults
 * @see FilterChain
 */
public abstract class SearchController {
    private SearchFields searchField;
    private String searchTerm;
    private SearchResults searchResults;
    protected SearchFactory searchFactory;
    private FilterChain filterChain;
    private boolean ordenacao;

    /**
     * Constrói um controlador de busca.
     *
     * @param searchTerm  o termo de busca (por exemplo, título, autor, gênero etc.)
     * @param searchField o campo de pesquisa definido em {@link SearchFields}
     */
    public SearchController(String searchTerm, SearchFields searchField) {
        this.searchTerm = searchTerm;
        this.searchField = searchField;
        this.filterChain = null;
    }

    /**
     * Executa a busca conforme o termo e o campo especificados, aplica filtros e ordena os resultados.
     * <p>
     * O fluxo de execução é:
     * <ol>
     *   <li>Validação de nulo para {@code searchField} e {@code searchTerm};</li>
     *   <li>Criação da estratégia de busca via {@link SearchFactory#createSearch};</li>
     *   <li>Execução da busca e obtenção de {@link SearchResults};</li>
     *   <li>Aplicação de filtros encadeados se presentes;</li>
     *   <li>Ordenação da lista de {@link Media} pela pontuação, crescente ou decrescente;</li>
     *   <li>Atualização dos resultados ordenados em {@code searchResults}.</li>
     * </ol>
     * </p>
     */
    public void execute() {
        if (searchField == null) {
            searchResults = new SearchResults(null, "Error: Campo de pesquisa não pode ser nulo");
            return;
        }
        if (searchTerm == null && searchField != SearchFields.TUDO) {
            searchResults = new SearchResults(null, "Error: Termo de pesquisa não pode ser nulo");
            return;
        }

        Search search = searchFactory.createSearch(searchField);
        searchResults = search.execute(searchTerm);

        if (!searchResults.isEmpty() && filterChain != null && !filterChain.isEmpty()) {
            filterChain.setSearchResults(searchResults);
            filterChain.applyFilters();
            searchResults = filterChain.getSearchResults();
        }

        List<Media> orderedMediaList;
        if (ordenacao) {
            // Ordem decrescente por pontuação
            orderedMediaList = searchResults.getMediaList().stream()
                    .sorted(Comparator.comparing(Media::getPontuacao).reversed())
                    .collect(Collectors.toList());
        } else {
            // Ordem crescente por pontuação
            orderedMediaList = searchResults.getMediaList().stream()
                    .sorted(Comparator.comparing(Media::getPontuacao))
                    .collect(Collectors.toList());
        }

        searchResults.setMediaList(orderedMediaList);
    }

    /**
     * Define a ordem de ordenação dos resultados por pontuação.
     *
     * @param ordem {@code true} para decrescente, {@code false} para crescente
     */
    public void setOrdenacao(boolean ordem) {
        this.ordenacao = ordem;
    }

    /**
     * Retorna os resultados da última busca executada.
     *
     * @return {@link SearchResults} contendo lista de mídias e mensagem de status
     */
    public SearchResults getSearchResults() {
        return searchResults;
    }

    /**
     * Define a cadeia de filtros a ser aplicada aos resultados da busca.
     *
     * @param filterChain objeto {@link FilterChain} contendo filtros a aplicar
     */
    public void setFilterChain(FilterChain filterChain) {
        this.filterChain = filterChain;
    }
}
