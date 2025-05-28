package controller.search.filme;

import controller.dataBase.FilmeRepository;
import controller.search.*;

/**
 * Fábrica de buscas específicas para filmes.
 *
 * Esta classe implementa {@link SearchFactory} e é responsável por criar instâncias
 * de buscas configuradas para filmes, de acordo com o critério definido em {@link SearchFields}.
 * Ela encapsula a obtenção do repositório de filmes e a configuração básica de cada busca.
 *
 * @see SearchFactory
 * @see SearchFields
 * @see FilmeRepository
 */
public class SearchFilmeFactory implements SearchFactory {
    // Instância do repositório de filmes
    private final FilmeRepository filmeRepository = FilmeRepository.getInstance();

    /**
     * Cria e retorna um objeto {@link Search} adequado para o tipo de busca especificado.
     *
     * @param type O campo de busca definido em {@link SearchFields}.
     * @return Uma implementação de {@link Search} configurada com o repositório de filmes,
     *         ou {@code null} se o tipo não for reconhecido.
     */
    @Override
    public Search createSearch(SearchFields type) {
        switch (type) {
            case type.TUDO -> {
                SearchAll searchAll = new SearchAll();
                searchAll.setRepository(filmeRepository);
                return searchAll;
            }
            case type.TITULO -> {
                SearchTitle searchTitle = new SearchTitle();
                searchTitle.setRepository(filmeRepository);
                return searchTitle;
            }
            case type.ANO_LANCAMENTO -> {
                SearchYear searchYear = new SearchYear();
                searchYear.setRepository(filmeRepository);
                return searchYear;
            }
            case type.GENERO -> {
                SearchGenre searchGenre = new SearchGenre();
                searchGenre.setRepository(filmeRepository);
                return searchGenre;
            }
            case type.DIRETOR -> {
                SearchDirector searchDirector = new SearchDirector();
                searchDirector.setRepository(filmeRepository);
                return searchDirector;
            }
            case type.ELENCO -> {
                SearchElenco searchElenco = new SearchElenco();
                searchElenco.setRepository(filmeRepository);
                return searchElenco;
            }
            default -> {
                // Tipo de busca não suportado
            }
        }
        return null;
    }
}
