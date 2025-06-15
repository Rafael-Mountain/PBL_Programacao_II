package com.mountain_vd.controller.search;

import com.mountain_vd.controller.dataBase.repository.IRepository;
import com.mountain_vd.model.Media;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação de {@link Search} que realiza buscas de mídias por ano de lançamento.
 * <p>
 * Executa a pesquisa sobre um repositório de {@link Media}, filtrando aqueles
 * cujo ano de lançamento corresponde ao termo de busca (interpretado como inteiro),
 * e retorna os resultados como uma lista de mídias.
 * </p>
 *
 * @see Search
 * @see SearchResults
 * @see IRepository
 * @see Media
 */
public class SearchYear implements Search {

    /**
     * Repositório de mídias usado na pesquisa.
     */
    private IRepository repository;

    /**
     * Executa a busca de mídias cujo ano de lançamento corresponde ao termo fornecido.
     *
     * @param searchTerm Termo de busca para o ano (deve ser um número inteiro).
     * @return Um {@link SearchResults} contendo:
     *         <ul>
     *           <li>Lista de mídias cujo ano de lançamento corresponde ao termo;</li>
     *           <li>Mensagem de status indicando o resultado da busca.</li>
     *         </ul>
     */
    @Override
    public SearchResults execute(String searchTerm) {
        // Copia todos os itens do repositório
        List<Media> medias = new ArrayList<>(repository.getItems());

        // Filtra pelas mídias cujo ano de lançamento corresponde ao termo
        if (searchTerm.isEmpty() || searchTerm.matches("\\d+")) {
            return new SearchResults(medias,"Mídias não encontradas");
        }

        int year = Integer.parseInt(searchTerm);
        medias = medias.stream()
                .filter(media -> media.getDataLancamento().getYear() == year)
                .collect(Collectors.toList());

        // Monta a mensagem de retorno
        String message = medias.isEmpty()
                ? "Mídias não encontradas"
                : "Busca por Ano: " + searchTerm;

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
