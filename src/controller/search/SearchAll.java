package controller.search;

import controller.dataBase.IRepository;
import model.Media;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação de {@link Search} que retorna todas as mídias armazenadas em um repositório.
 * <p>
 * Este buscador simplesmente obtém todos os itens de mídia disponíveis no
 * repositório configurado e retorna um {@link SearchResults} contendo a lista
 * de mídias e uma mensagem de status.
 * </p>
 *
 * @see Search
 * @see IRepository
 * @see SearchResults
 */
public class SearchAll implements Search {

    /**
     * Repositório de onde serão obtidas todas as mídias.
     */
    private IRepository repository;

    /**
     * Executa a busca retornando todas as mídias do repositório.
     *
     * @param searchTerm Termo de busca (é ignorado nesta implementação, pois
     *                   sempre retornamos todas as mídias).
     * @return Um {@link SearchResults} contendo:
     *         <ul>
     *           <li>Lista de todas as mídias obtidas do repositório;</li>
     *           <li>Mensagem indicando o resultado da operação.</li>
     *         </ul>
     */
    @Override
    public SearchResults execute(String searchTerm) {
        List<Media> medias = new ArrayList<>(repository.getItems());

        String message = medias.isEmpty()
                ? "Mídias não encontradas"
                : "Busca: todas as mídias";

        return new SearchResults(medias, message);
    }

    /**
     * Define o repositório de onde as mídias serão obtidas.
     *
     * @param repository Repositório de mídias (por exemplo, {@link controller.dataBase.FilmeRepository},
     *                   {@link controller.dataBase.LivroRepository} ou {@link controller.dataBase.SerieRepository}).
     */
    public void setRepository(IRepository repository) {
        this.repository = repository;
    }
}
