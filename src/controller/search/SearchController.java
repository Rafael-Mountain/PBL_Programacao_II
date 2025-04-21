package controller.search;

import controller.filter.FilterChain;
import model.Avaliacao;
import model.Filme;
import model.Media;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class SearchController {
    private SearchFields searchField;
    private String searchTerm;
    private SearchResults searchResults;
    protected SearchFactory searchFactory;
    private FilterChain filterChain;
    private boolean ordenacao;

    public SearchController(String searchTerm, SearchFields searchField) {
        this.searchTerm = searchTerm;
        this.searchField = searchField;
    }

    public void execute() {
        if (searchField == null){
            searchResults = new SearchResults(null,"Error: Campo de pesquisa não pode ser nulo");
        }
        if (searchTerm == null){
            searchResults = new SearchResults(null,"Error: Termo de pesquisa não pode ser nulo");
        }

        Search search = searchFactory.createSearch(searchField);
        searchResults = search.execute(searchTerm);

        if (filterChain != null) {
            filterChain.setSearchResults(searchResults);
            filterChain.applyFilters();
            searchResults = filterChain.getSearchResults();
        }

        List<Media> orderedMediaList;

        if (ordenacao) {
            // Ordem crescente
            orderedMediaList = searchResults.getMediaList().stream()
                    .sorted(Comparator.comparing(Media::getPontuacao))
                    .collect(Collectors.toList());
        } else {
            // Ordem decrescente
            orderedMediaList = searchResults.getMediaList().stream()
                    .sorted(Comparator.comparing(Media::getPontuacao).reversed())
                    .collect(Collectors.toList());
        }

        searchResults.setMediaList(orderedMediaList);
    }

    public void setOrdenacao(boolean ordem){
        this.ordenacao = ordem;
    }

    public SearchResults getSearchResults() {
        return searchResults;
    }

    public void setFilterChain(FilterChain filterChain) {
        this.filterChain = filterChain;
    }
}
