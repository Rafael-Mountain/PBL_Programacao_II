package com.mountain_vd.controller.search.serie;

import com.mountain_vd.controller.search.SearchController;
import com.mountain_vd.controller.search.SearchFields;

/**
 * Controlador de buscas para séries.
 * <p>
 * Estende {@link SearchController} fornecendo um ponto de entrada específico
 * para pesquisas na coleção de séries. O controlador encapsula o termo de busca
 * e o campo de pesquisa e utiliza {@link SearchSerieFactory} para criar a
 * estratégia de busca apropriada.
 * </p>
 *
 * @see SearchController
 * @see SearchFields
 * @see SearchSerieFactory
 */
public class SearchSerieController extends SearchController {

    /**
     * Constrói um novo controlador de busca para séries.
     *
     * @param searchTerm  o termo de busca (por exemplo, título, gênero, diretor, etc.)
     * @param searchField o campo de busca definido em {@link SearchFields}
     */
    public SearchSerieController(String searchTerm, SearchFields searchField) {
        super(searchTerm, searchField);
        this.searchFactory = new SearchSerieFactory();
    }
}
