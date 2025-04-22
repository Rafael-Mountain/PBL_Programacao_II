package view.livro;

import controller.filter.FilterChain;
import controller.search.SearchFields;
import controller.search.SearchLivroController;
import controller.search.SearchResults;
import view.SearchView;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class SearchViewLivro extends SearchView {
    List<AbstractMap.SimpleEntry<String, SearchFields>> fields = new ArrayList<>();

    @Override
    public List<AbstractMap.SimpleEntry<String, SearchFields>> getFields() {
        fields.add(new AbstractMap.SimpleEntry<>("Tudo", SearchFields.TUDO));
        fields.add(new AbstractMap.SimpleEntry<>("Título", SearchFields.TITULO));
        fields.add(new AbstractMap.SimpleEntry<>("Gênero", SearchFields.GENERO));
        fields.add(new AbstractMap.SimpleEntry<>("Ano de Lançamento", SearchFields.ANO_LANCAMENTO));
        fields.add(new AbstractMap.SimpleEntry<>("Autor", SearchFields.AUTOR));
        fields.add(new AbstractMap.SimpleEntry<>("Duração", SearchFields.DURACAO));
        fields.add(new AbstractMap.SimpleEntry<>("ISBN", SearchFields.ISBN));

        return fields;
    }

    @Override
    public SearchResults execute(SearchFields field, FilterChain filterChain, String value, boolean ordenacao) {
        SearchLivroController searchLivroController = new SearchLivroController(value, field);
        searchLivroController.setFilterChain(filterChain);
        searchLivroController.setOrdenacao(ordenacao);
        searchLivroController.execute();
        SearchResults searchResults = searchLivroController.getSearchResults();
        return searchResults;
    }
}
