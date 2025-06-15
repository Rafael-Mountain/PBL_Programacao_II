package com.mountain_vd.controller.search;

import com.mountain_vd.controller.dataBase.repository.IRepository;
import com.mountain_vd.model.Filme;
import com.mountain_vd.model.Media;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação de {@link Search} que realiza buscas de filmes pelo nome do diretor.
 * <p>
 * Executa a pesquisa no repositório de {@link Filme}, filtrando aqueles
 * cujo nome do diretor contém o termo de busca (case-insensitive), e retorna
 * os resultados como uma lista de {@link Media}.
 * </p>
 *
 * @see Search
 * @see SearchResults
 * @see IRepository
 * @see Filme
 */
public class SearchDirector implements Search {

    /**
     * Repositório de filmes usado na pesquisa.
     */
    private IRepository<Filme> filmeRepository;

    /**
     * Executa a busca de filmes cujo diretor contém o termo fornecido.
     *
     * @param searchTerm Termo de busca para o nome do diretor.
     * @return Um {@link SearchResults} contendo:
     *         <ul>
     *           <li>Lista de mídias (filmes) cujo diretor corresponde ao termo;</li>
     *           <li>Mensagem de status indicando o resultado da busca.</li>
     *         </ul>
     */
    @Override
    public SearchResults execute(String searchTerm) {
        List<Media> medias = filmeRepository.getItems().stream()
                .filter(filme -> filme.getDirecao()
                        .toLowerCase()
                        .contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());

        String message = medias.isEmpty()
                ? "Mídias não encontradas"
                : "Busca por Diretor: " + searchTerm;

        return new SearchResults(medias, message);
    }

    /**
     * Define o repositório de filmes que será usado nesta busca.
     *
     * @param filmeRepository Repositório de {@link Filme}.
     */
    public void setRepository(IRepository<Filme> filmeRepository) {
        this.filmeRepository = filmeRepository;
    }
}
