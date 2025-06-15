package com.mountain_vd.view.livro;

import com.mountain_vd.controller.filter.FilterChain;
import com.mountain_vd.controller.search.SearchFields;
import com.mountain_vd.controller.search.livro.SearchLivroController;
import com.mountain_vd.controller.search.SearchResults;
import com.mountain_vd.view.SearchView;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe responsável por exibir a interface de busca específica para livros.
 * Estende a classe {@link SearchView} para fornecer uma busca personalizada para livros, com filtros e campos específicos.
 */
public class SearchViewLivro extends SearchView {

    /**
     * Lista de campos que podem ser usados para a busca de livros.
     */
    private List<AbstractMap.SimpleEntry<String, SearchFields>> fields = new ArrayList<>();

    /**
     * Retorna a lista de campos de busca disponíveis para livros.
     * Os campos incluem: "Tudo", "Título", "Gênero", "Ano de Lançamento", "Autor", "Duração" e "ISBN".
     *
     * @return A lista de campos de busca disponíveis para livros.
     */
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

    /**
     * Executa a busca de livros com base nos filtros fornecidos.
     * Utiliza o controlador {@link SearchLivroController} para realizar a busca e aplicar filtros.
     *
     * @param field O campo de busca selecionado (ex: "Título", "Gênero", etc.).
     * @param filterChain A cadeia de filtros a ser aplicada na busca.
     * @param value O valor de busca a ser utilizado no campo selecionado.
     * @param ordenacao Indica se os resultados devem ser ordenados.
     * @return Os resultados da busca encapsulados em um objeto {@link SearchResults}.
     */
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
