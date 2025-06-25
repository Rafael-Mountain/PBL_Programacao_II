package com.mountain_vd.viewFX.handlers;

import com.mountain_vd.controller.filter.*;
import com.mountain_vd.controller.search.SearchController;
import com.mountain_vd.controller.search.SearchFields;
import com.mountain_vd.controller.search.SearchResults;
import com.mountain_vd.controller.util.EnumUtils;
import com.mountain_vd.viewFX.RootScene;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class SearchPaneController {
    protected RootScene rootScene;
    private SearchResults searchResults;

    SearchFields searchField;
    SimpleEntry<GenreFilterType, String> genreFilter;
    SimpleEntry<YearFilterType, String> yearFilter;
    boolean orderby;


    String searchTerm;

    public SearchPaneController(RootScene rootScene) {
        this.rootScene = rootScene;
    }

    public abstract Map<String, SearchFields> getSearchFields();
    public abstract SearchController getSearchController(String searchTerm, SearchFields searchField);
    public abstract Node getAddScreen();

    public void search(){
        if (searchTerm == null || searchTerm.isEmpty()){
            searchField = SearchFields.TUDO;
        }

        FilterChain chain = new FilterChain();
        if (genreFilter.getValue() != null){
            chain.addFilter(new GenreFilter(genreFilter.getKey(),genreFilter.getValue()));
        }
        if (yearFilter.getKey() != null && yearFilter.getValue() != null && !yearFilter.getValue().trim().isEmpty()) {
            chain.addFilter(new YearFilter(yearFilter.getKey(), Integer.parseInt(yearFilter.getValue())));
        }

        SearchController searchController = getSearchController(searchTerm, searchField);
        searchController.setFilterChain(chain);
        searchController.setOrdenacao(orderby);
        searchController.execute();
        searchResults = searchController.getSearchResults();
    }

    public List<String> getListSearchFields() {
        return new ArrayList<>(getSearchFields().keySet());
    }

    public SearchResults getSearchResults() {
        return searchResults;
    }

    public void setSearchField(String keySearchField) {
        this.searchField = getSearchFields().get(keySearchField);
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public void setYearFilterInfomation(String yearFilterType, String filterTerm) {
        this.yearFilter = new SimpleEntry<>(
                EnumUtils.fromDescricao(YearFilterType.class, yearFilterType),
                filterTerm);
    }

    public void setGenreFilterInfomation(String genreFilterType, String filterTerm) {
        this.genreFilter = new SimpleEntry<>(
                EnumUtils.fromDescricao(GenreFilterType.class, genreFilterType),
                filterTerm);
    }

    public void setOrderby(String orderbyType) {
        this.orderby = orderbyType.equalsIgnoreCase("desc");
    }
}
