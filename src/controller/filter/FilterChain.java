package controller.filter;

import controller.search.SearchFields;
import controller.search.SearchResults;
import model.Media;

import java.util.ArrayList;
import java.util.List;

public class FilterChain {
    private List<Filter> filters;
    private SearchResults searchResults;

    public FilterChain() {
        this.filters = new ArrayList<>();
    }

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public void applyFilters() {
        for (Filter filter : filters) {
            filter.setMedias(searchResults.getMediaList());
            filter.apply();
            searchResults.setMediaList(filter.getMedias());
            searchResults.setMessage(searchResults.getMessage() + "\n" + filter.getMessage());
        }
    }

    public void setSearchResults(SearchResults searchResults) {
        this.searchResults = searchResults;
    }

    public SearchResults getSearchResults() {
        return searchResults;
    }
}
