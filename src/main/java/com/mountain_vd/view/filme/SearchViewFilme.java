package com.mountain_vd.view.filme;

import com.mountain_vd.controller.filter.FilterChain;
import com.mountain_vd.controller.search.SearchFields;
import com.mountain_vd.controller.search.filme.SearchFilmeController;
import com.mountain_vd.controller.search.SearchResults;
import com.mountain_vd.view.SearchView;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por exibir a tela de pesquisa específica para filmes, permitindo ao usuário realizar
 * buscas por filmes utilizando diferentes campos de pesquisa e filtros.
 * <p>
 * A classe herda de {@link com.mountain_vd.view.SearchView} e fornece implementações específicas para filmes, incluindo os
 * campos de pesquisa disponíveis e a execução da busca. Ela utiliza a classe {@link SearchFilmeController}
 * para controlar a lógica de busca e retorna os resultados filtrados conforme os critérios fornecidos.
 * </p>
 */
public class SearchViewFilme extends SearchView {

    /**
     * Lista de campos de pesquisa disponíveis para o usuário ao buscar filmes.
     * Cada campo é representado por uma entrada simples contendo o nome do campo e a chave associada
     * da enumeração {@link SearchFields}.
     */
    List<SimpleEntry<String, SearchFields>> fields = new ArrayList<>();

    /**
     * Retorna a lista de campos disponíveis para pesquisa, cada um representando um critério que o usuário pode
     * selecionar para buscar filmes. Esses campos são definidos por meio da enumeração {@link SearchFields}.
     *
     * @return uma lista de entradas contendo o nome do campo e sua chave associada
     */
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

    /**
     * Executa a busca de filmes de acordo com os parâmetros fornecidos. O método utiliza o controlador de busca
     * {@link SearchFilmeController} para processar a busca com base no valor e no campo de pesquisa selecionados,
     * aplicando também qualquer filtro e ordenação especificada.
     *
     * @param field o campo de pesquisa selecionado
     * @param filterChain a cadeia de filtros a ser aplicada na busca
     * @param value o valor de pesquisa fornecido pelo usuário
     * @param ordenacao um valor booleano que indica se a ordenação deve ser aplicada
     * @return os resultados da busca, encapsulados em um objeto {@link SearchResults}
     */
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
