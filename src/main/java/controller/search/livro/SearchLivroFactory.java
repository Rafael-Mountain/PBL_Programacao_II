package controller.search.livro;

import controller.dataBase.LivroRepository;
import controller.search.*;

/**
 * Fábrica de buscas para a entidade {@link model.Livro}.
 * <p>
 * Esta classe implementa {@link SearchFactory} e fornece instâncias de buscas
 * configuradas para pesquisar livros no repositório de livros, de acordo com
 * o critério especificado em {@link SearchFields}.
 * </p>
 *
 * @see SearchFactory
 * @see SearchFields
 * @see LivroRepository
 */
public class SearchLivroFactory implements SearchFactory {

    /**
     * Instância única do repositório de livros utilizada pela fábrica.
     */
    private final LivroRepository livroRepository = LivroRepository.getInstance();

    /**
     * Cria e retorna uma estratégia de busca apropriada para o tipo de campo informado.
     *
     * @param type O campo de busca definido em {@link SearchFields}.
     * @return Uma implementação de {@link Search} configurada com o repositório de livros,
     *         ou {@code null} se o tipo não for reconhecido.
     */
    @Override
    public Search createSearch(SearchFields type) {
        switch (type) {
            case type.TUDO -> {
                SearchAll searchAll = new SearchAll();
                searchAll.setRepository(livroRepository);
                return searchAll;
            }
            case type.TITULO -> {
                SearchTitle searchTitle = new SearchTitle();
                searchTitle.setRepository(livroRepository);
                return searchTitle;
            }
            case type.ANO_LANCAMENTO -> {
                SearchYear searchYear = new SearchYear();
                searchYear.setRepository(livroRepository);
                return searchYear;
            }
            case type.GENERO -> {
                SearchGenre searchGenre = new SearchGenre();
                searchGenre.setRepository(livroRepository);
                return searchGenre;
            }
            case type.AUTOR -> {
                SearchAutor searchAutor = new SearchAutor();
                searchAutor.setRepository(livroRepository);
                return searchAutor;
            }
            case type.ISBN -> {
                SearchISBN searchISBN = new SearchISBN();
                searchISBN.setRepository(livroRepository);
                return searchISBN;
            }
            default -> {
                // Tipo de busca não suportado
            }
        }
        return null;
    }
}
