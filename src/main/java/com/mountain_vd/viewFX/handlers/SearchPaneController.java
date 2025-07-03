package com.mountain_vd.viewFX.handlers;

import com.mountain_vd.controller.filter.*;
import com.mountain_vd.controller.search.SearchController;
import com.mountain_vd.controller.search.SearchFields;
import com.mountain_vd.controller.search.SearchResults;
import com.mountain_vd.controller.util.EnumUtils;
import com.mountain_vd.model.Media;
import com.mountain_vd.viewFX.RootScene;
import javafx.scene.Node;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classe abstrata que controla a lógica da tela de busca para diferentes tipos de mídia.
 * Define métodos e propriedades comuns para os controladores de busca específicos,
 * incluindo filtros, ordenação e execução da busca.
 */
public abstract class SearchPaneController {
    /** Referência à cena principal da aplicação para interação com a UI. */
    protected RootScene rootScene;
    /** Resultados da última busca executada. */
    private SearchResults searchResults;

    /** Campo de busca selecionado. */
    SearchFields searchField;
    /** Filtro de gênero aplicado na busca. */
    SimpleEntry<GenreFilterType, String> genreFilter;
    /** Filtro de ano aplicado na busca. */
    SimpleEntry<YearFilterType, String> yearFilter;
    /** Define a ordem da ordenação dos resultados (ascendente/descendente). */
    boolean orderby;

    /** Termo de busca informado pelo usuário. */
    String searchTerm;

    /**
     * Construtor que recebe a cena raiz da aplicação.
     *
     * @param rootScene A cena principal da aplicação.
     */
    public SearchPaneController(RootScene rootScene) {
        this.rootScene = rootScene;
    }

    /**
     * Abstrato - método para navegar para a tela de exibição de detalhes da mídia selecionada.
     *
     * @param media A mídia selecionada para exibição.
     */
    public abstract void goTotDisplayPane(Media media);

    /**
     * Abstrato - obtém os campos disponíveis para busca.
     *
     * @return Mapa contendo nomes dos campos e seus respectivos tipos.
     */
    public abstract Map<String, SearchFields> getSearchFields();

    /**
     * Abstrato - cria o controlador de busca específico para o termo e campo de busca fornecidos.
     *
     * @param searchTerm Termo a ser buscado.
     * @param searchField Campo no qual o termo será buscado.
     * @return Um controlador de busca configurado.
     */
    public abstract SearchController getSearchController(String searchTerm, SearchFields searchField);

    /**
     * Abstrato - obtém o nó da interface gráfica para a tela de adição de nova mídia.
     *
     * @return Nó JavaFX da tela de criação.
     */
    public abstract Node getAddScreen();

    /**
     * Executa a busca utilizando os filtros e parâmetros definidos.
     * Aplica filtros de gênero e ano, além da ordenação.
     * Atualiza os resultados da busca.
     */
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

    /**
     * Retorna uma lista com os nomes dos campos disponíveis para busca.
     *
     * @return Lista de nomes dos campos de busca.
     */
    public List<String> getListSearchFields() {
        return new ArrayList<>(getSearchFields().keySet());
    }

    /**
     * Retorna os resultados da última busca executada.
     *
     * @return Resultados da busca.
     */
    public SearchResults getSearchResults() {
        return searchResults;
    }

    /**
     * Define o campo que será utilizado para a busca baseado na chave.
     *
     * @param keySearchField Nome do campo de busca.
     */
    public void setSearchField(String keySearchField) {
        this.searchField = getSearchFields().get(keySearchField);
    }

    /**
     * Define o termo de busca que será utilizado.
     *
     * @param searchTerm Termo de busca informado pelo usuário.
     */
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Define o filtro de ano para a busca, especificando o tipo do filtro e o valor.
     *
     * @param yearFilterType Tipo do filtro de ano (ex: antes, depois).
     * @param filterTerm Valor do filtro de ano.
     */
    public void setYearFilterInfomation(String yearFilterType, String filterTerm) {
        this.yearFilter = new SimpleEntry<>(
                EnumUtils.fromDescricao(YearFilterType.class, yearFilterType),
                filterTerm);
    }

    /**
     * Define o filtro de gênero para a busca, especificando o tipo do filtro e o valor.
     *
     * @param genreFilterType Tipo do filtro de gênero (ex: incluir, excluir).
     * @param filterTerm Valor do filtro de gênero.
     */
    public void setGenreFilterInfomation(String genreFilterType, String filterTerm) {
        this.genreFilter = new SimpleEntry<>(
                EnumUtils.fromDescricao(GenreFilterType.class, genreFilterType),
                filterTerm);
    }

    /**
     * Define a ordenação da busca, onde "desc" define ordenação decrescente.
     *
     * @param orderbyType Tipo de ordenação ("asc" ou "desc").
     */
    public void setOrderby(String orderbyType) {
        this.orderby = orderbyType.equalsIgnoreCase("desc");
    }
}
