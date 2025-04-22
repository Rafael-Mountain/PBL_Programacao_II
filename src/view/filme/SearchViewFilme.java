package view.filme;

import controller.filter.FilterChain;
import controller.search.SearchFields;
import controller.search.filme.SearchFilmeController;
import controller.search.SearchResults;
import view.SearchView;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

public class SearchViewFilme extends SearchView {
    List<SimpleEntry<String, SearchFields>> fields = new ArrayList<>();

    @Override
    public List<SimpleEntry<String, SearchFields>> getFields() {
        fields.add(new SimpleEntry<>("Tudo", SearchFields.TUDO));
        fields.add(new SimpleEntry<>("Título", SearchFields.TITULO));
        fields.add(new SimpleEntry<>("Gênero", SearchFields.GENERO));
        fields.add(new SimpleEntry<>("Ano de Lançamento", SearchFields.ANO_LANCAMENTO));
        fields.add(new SimpleEntry<>("Diretor", SearchFields.DIRETOR));
        fields.add(new SimpleEntry<>("Duração", SearchFields.DURACAO));
        fields.add(new SimpleEntry<>("Elenco", SearchFields.ELENCO));
        fields.add(new SimpleEntry<>("Roteiro", SearchFields.ROTEIRO));

        return fields;
    }

    @Override
    public SearchResults execute(SearchFields field, FilterChain filterChain, String value, boolean ordenacao) {
        SearchFilmeController searchFilmeController = new SearchFilmeController(value, field);
        searchFilmeController.setFilterChain(filterChain);
        searchFilmeController.setOrdenacao(ordenacao);
        searchFilmeController.execute();
        SearchResults searchResults = searchFilmeController.getSearchResults();
        return searchResults;
    }
}
