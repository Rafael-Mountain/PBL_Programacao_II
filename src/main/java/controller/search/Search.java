package controller.search;

/**
 * Interface que define uma operação de busca em um repositório de mídias.
 *
 * Implementações dessa interface devem executar a lógica de pesquisa usando
 * um termo de busca e retornar um objeto {@link SearchResults} contendo os
 * resultados encontrados e eventuais mensagens de status.
 *
 * @see SearchResults
 */
public interface Search {

    /**
     * Executa a pesquisa com base no termo fornecido.
     *
     * @param searchTerm O termo de busca (por exemplo, título, gênero, autor, etc.).
     * @return Um {@link SearchResults} contendo a lista de itens encontrados
     *         e mensagens de feedback sobre a busca.
     */
    SearchResults execute(String searchTerm);
}
