package controller.search;

import controller.dataBase.repository.IRepository;
import model.Media;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação de {@link Search} que realiza buscas de mídias pelo título.
 * <p>
 * Executa a pesquisa sobre um repositório de {@link Media}, filtrando aqueles
 * cujo título contém o termo de busca (case-insensitive), e retorna os resultados
 * como uma lista de mídias.
 * </p>
 *
 * @see Search
 * @see SearchResults
 * @see IRepository
 * @see Media
 */
public class SearchTitle implements Search {

    /**
     * Repositório de mídias usado na pesquisa.
     */
    private IRepository repository;

    /**
     * Executa a busca de mídias cujo título contém o termo fornecido.
     *
     * @param searchTerm Termo de busca para o título.
     * @return Um {@link SearchResults} contendo:
     *         <ul>
     *           <li>Lista de mídias cujo título corresponde ao termo;</li>
     *           <li>Mensagem de status indicando o resultado da busca.</li>
     *         </ul>
     */
    @Override
    public SearchResults execute(String searchTerm) {
        // Copia todos os itens do repositório
        List<Media> medias = new ArrayList<>(repository.getItems());

        // Filtra pelas mídias cujo título corresponde ao termo
        medias = medias.stream()
                .filter(media -> media.getTitulo()
                        .toLowerCase()
                        .contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());

        // Monta a mensagem de retorno
        String message = medias.isEmpty()
                ? "Mídias não encontradas"
                : "Busca por Título: " + searchTerm;

        return new SearchResults(medias, message);
    }

    /**
     * Define o repositório de mídias que será usado nesta busca.
     *
     * @param repository Repositório de {@link Media}.
     */
    public void setRepository(IRepository repository) {
        this.repository = repository;
    }
}
