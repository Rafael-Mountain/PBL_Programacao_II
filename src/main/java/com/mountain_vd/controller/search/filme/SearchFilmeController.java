package com.mountain_vd.controller.search.filme;

import com.mountain_vd.controller.search.SearchController;
import com.mountain_vd.controller.search.SearchFields;

/**
 * A classe {@code SearchFilmeController} é responsável por controlar as buscas específicas para filmes.
 * Ela estende a classe {@code SearchController}, fornecendo um controle especializado para realizar
 * buscas de filmes com base em um termo de busca e um campo de busca específico.
 *
 * @see com.mountain_vd.controller.search.SearchController
 * @see com.mountain_vd.controller.search.SearchFields
 */
public class SearchFilmeController extends SearchController {

    /**
     * Construtor da classe {@code SearchFilmeController}.
     * Inicializa a busca para filmes, configurando o termo de busca e o campo de busca a ser utilizado.
     * A fábrica de busca específica para filmes também é configurada.
     *
     * @param searchTerm o termo de busca (exemplo: título, diretor)
     * @param searchField o campo de busca onde o termo será aplicado (exemplo: nome do filme, diretor)
     */
    public SearchFilmeController(String searchTerm, SearchFields searchField) {
        super(searchTerm, searchField);
        searchFactory = new SearchFilmeFactory();
    }
}
