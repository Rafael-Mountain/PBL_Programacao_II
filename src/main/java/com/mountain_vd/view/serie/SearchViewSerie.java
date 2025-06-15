package com.mountain_vd.view.serie;

import com.mountain_vd.controller.filter.FilterChain;
import com.mountain_vd.controller.search.SearchFields;
import com.mountain_vd.controller.search.SearchResults;
import com.mountain_vd.controller.search.serie.SearchSerieController;
import com.mountain_vd.view.SearchView;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class SearchViewSerie extends SearchView {
    List<AbstractMap.SimpleEntry<String, SearchFields>> fields = new ArrayList<>();


    @Override
    public List<AbstractMap.SimpleEntry<String, SearchFields>> getFields() {
        fields.add(new AbstractMap.SimpleEntry<>("Tudo", SearchFields.TUDO));
        fields.add(new AbstractMap.SimpleEntry<>("Título", SearchFields.TITULO));
        fields.add(new AbstractMap.SimpleEntry<>("Gênero", SearchFields.GENERO));
        fields.add(new AbstractMap.SimpleEntry<>("Ano de Lançamento", SearchFields.ANO_LANCAMENTO));
        fields.add(new AbstractMap.SimpleEntry<>("Elenco", SearchFields.ELENCO));

        return fields;
    }

    @Override
    public SearchResults execute(SearchFields field, FilterChain filterChain, String value, boolean ordenacao) {
        SearchSerieController searchSerieController = new SearchSerieController(value, field);
        searchSerieController.setFilterChain(filterChain);
        searchSerieController.setOrdenacao(ordenacao);
        searchSerieController.execute();
        SearchResults searchResults = searchSerieController.getSearchResults();
        return searchResults;
    }
}
