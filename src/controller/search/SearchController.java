package controller.search;

import controller.filter.FilterChain;

public abstract class SearchController {
    private SearchFields searchField;
    private String searchTerm;
    private SearchResults searchResults;
    protected SearchFactory searchFactory;
    private FilterChain filterChain;


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

    }
    public SearchResults getSearchResults() {
        return searchResults;
    }

    public void setFilterChain(FilterChain filterChain) {
        this.filterChain = filterChain;
    }
}
