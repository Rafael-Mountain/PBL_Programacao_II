package com.mountain_vd.controller.search;

import com.mountain_vd.controller.dataBase.repository.IRepository;
import com.mountain_vd.model.Livro;
import com.mountain_vd.model.Media;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação de {@link Search} que realiza buscas de livros pelo nome do autor.
 * <p>
 * Executa a pesquisa sobre o repositório de {@link Livro}, filtrando aqueles
 * cujo autor contém o termo de busca (case-insensitive), e retorna os resultados
 * como uma lista de {@link Media}.
 * </p>
 *
 * @see Search
 * @see SearchResults
 * @see IRepository
 * @see Livro
 */
public class SearchAutor implements Search {

    /**
     * Repositório de livros a ser utilizado na pesquisa.
     */
    private IRepository<Livro> livroRepository;

    /**
     * Executa a busca de livros cujo autor contém o termo fornecido.
     *
     * @param searchTerm Termo de busca para o nome do autor.
     * @return Um {@link SearchResults} contendo:
     *         <ul>
     *           <li>Lista de mídias (livros) cujo autor corresponde ao termo;</li>
     *           <li>Mensagem de status sobre o resultado da busca.</li>
     *         </ul>
     */
    @Override
    public SearchResults execute(String searchTerm) {
        List<Media> medias = livroRepository.getItems().stream()
                .filter(livro -> livro.getAutor()
                        .toLowerCase()
                        .contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());

        String message = medias.isEmpty()
                ? "Mídias não encontradas"
                : "Busca por Autor: " + searchTerm;

        return new SearchResults(medias, message);
    }

    /**
     * Define o repositório de livros que será usado nesta busca.
     *
     * @param livroRepository Repositório de {@link Livro}.
     */
    public void setRepository(IRepository<Livro> livroRepository) {
        this.livroRepository = livroRepository;
    }
}
