package controller.search;

import controller.dataBase.IRepository;
import model.Media;
import model.MediaAudioVisual;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação de {@link Search} que realiza buscas de mídias audiovisuais
 * pelo nome de um membro do elenco.
 * <p>
 * Executa a pesquisa sobre um repositório de {@link MediaAudioVisual}, filtrando
 * aqueles cujo elenco (lista de {@code String}) contém o termo de busca
 * (case-insensitive), e retorna os resultados como uma lista de {@link Media}.
 * </p>
 *
 * @see Search
 * @see SearchResults
 * @see IRepository
 * @see MediaAudioVisual
 */
public class SearchElenco implements Search {

    /**
     * Repositório de mídias audiovisuais usado na pesquisa.
     */
    private IRepository repository;

    /**
     * Executa a busca de mídias cujo elenco contém o termo fornecido.
     *
     * @param searchTerm Termo de busca para o nome de um membro do elenco.
     * @return Um {@link SearchResults} contendo:
     *         <ul>
     *           <li>Lista de mídias cujo elenco corresponde ao termo;</li>
     *           <li>Mensagem de status indicando o resultado da busca.</li>
     *         </ul>
     */
    @Override
    public SearchResults execute(String searchTerm) {
        List<MediaAudioVisual> medias = new ArrayList<>(repository.getItems());

        List<Media> resultados = medias.stream()
                .filter(m -> m.getElenco().stream()
                        .anyMatch(ator -> ator.toLowerCase()
                                .contains(searchTerm.toLowerCase())))
                .collect(Collectors.toList());

        String message = resultados.isEmpty()
                ? "Mídias não encontradas"
                : "Busca por Elenco: " + searchTerm;

        return new SearchResults(resultados, message);
    }

    /**
     * Define o repositório de mídias audiovisuais que será usado nesta busca.
     *
     * @param repository Repositório de {@link MediaAudioVisual}.
     */
    public void setRepository(IRepository repository) {
        this.repository = repository;
    }
}
