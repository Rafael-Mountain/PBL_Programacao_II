package controller.search;

import controller.dataBase.IRepository;
import model.Livro;
import model.Media;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação de {@link Search} que realiza buscas de livros pelo código ISBN.
 * <p>
 * Executa a pesquisa sobre um repositório de {@link Livro}, filtrando aqueles
 * cujo ISBN contém o termo de busca (case-insensitive), e retorna os resultados
 * como uma lista de {@link Media}.
 * </p>
 *
 * @see Search
 * @see SearchResults
 * @see IRepository
 * @see Livro
 */
public class SearchISBN implements Search {

    /**
     * Repositório de livros usado na pesquisa.
     */
    private IRepository<Livro> livroRepository;

    /**
     * Executa a busca de livros cujo ISBN contém o termo fornecido.
     *
     * @param searchTerm Termo de busca para o código ISBN.
     * @return Um {@link SearchResults} contendo:
     *         <ul>
     *           <li>Lista de mídias (livros) cujo ISBN corresponde ao termo;</li>
     *           <li>Mensagem de status indicando o resultado da busca.</li>
     *         </ul>
     */
    @Override
    public SearchResults execute(String searchTerm) {
        List<Media> medias = livroRepository.getItems().stream()
                .filter(livro -> livro.getIsbn()
                        .toLowerCase()
                        .contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());

        String message = medias.isEmpty()
                ? "Mídias não encontradas"
                : "Busca por ISBN: " + searchTerm;

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
