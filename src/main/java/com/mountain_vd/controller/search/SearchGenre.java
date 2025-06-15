package com.mountain_vd.controller.search;

import com.mountain_vd.controller.dataBase.repository.IRepository;
import com.mountain_vd.model.Media;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação de {@link Search} que realiza buscas de mídias por gênero.
 * <p>
 * Executa a pesquisa sobre um repositório de {@link Media}, filtrando aqueles
 * que possuem ao menos um gênero cujo nome corresponde (case-insensitive)
 * ao termo de busca fornecido.
 * </p>
 *
 * @see Search
 * @see SearchResults
 * @see IRepository
 * @see Media
 */
public class SearchGenre implements Search {

    /**
     * Repositório de mídias usado na pesquisa.
     */
    private IRepository repository;

    /**
     * Executa a busca de mídias cujo gênero contém o termo fornecido.
     *
     * @param searchTerm Termo de busca para o nome do gênero.
     * @return Um {@link SearchResults} contendo:
     *         <ul>
     *             <li>Lista de mídias cujo gênero corresponde ao termo;</li>
     *             <li>Mensagem de status indicando o resultado da busca.</li>
     *         </ul>
     */
    @Override
    public SearchResults execute(String searchTerm) {
        // Copia todos os itens do repositório
        List<Media> medias = new ArrayList<>(repository.getItems());

        // Filtra pelas mídias que possuem o gênero buscado
        medias = medias.stream()
                .filter(media -> media.getGeneros().stream()
                        .anyMatch(genero -> genero.getNome()
                                .equalsIgnoreCase(searchTerm)))
                .collect(Collectors.toList());

        // Monta a mensagem de retorno
        String message = medias.isEmpty()
                ? "Mídias não encontradas"
                : "Busca por Gênero: " + searchTerm;

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
