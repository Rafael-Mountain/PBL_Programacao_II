package com.mountain_vd.controller.search.livro;

import com.mountain_vd.controller.search.SearchController;
import com.mountain_vd.controller.search.SearchFields;

/**
 * Controlador de buscas para livros.
 * <p>
 * Estende {@link SearchController} fornecendo um ponto de entrada específico
 * para buscas na coleção de livros. Este controlador encapsula o termo de busca
 * e o campo a ser pesquisado e utiliza {@link SearchLivroFactory} para criar
 * a estratégia de busca adequada.
 * </p>
 *
 * @see SearchController
 * @see SearchFields
 * @see SearchLivroFactory
 */
public class SearchLivroController extends SearchController {

    /**
     * Constrói um novo controlador de busca para livros.
     *
     * @param searchTerm  o termo de busca (por exemplo, título, autor, ISBN, etc.)
     * @param searchField o campo de busca definido em {@link SearchFields}
     */
    public SearchLivroController(String searchTerm, SearchFields searchField) {
        super(searchTerm, searchField);
        this.searchFactory = new SearchLivroFactory();
    }
}
