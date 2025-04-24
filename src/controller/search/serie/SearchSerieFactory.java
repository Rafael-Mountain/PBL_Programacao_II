package controller.search.serie;

import controller.dataBase.SerieRepository;
import controller.search.*;

/**
 * Fábrica para criação de buscas relacionadas a séries.
 * A classe {@code SearchSerieFactory} implementa a interface {@code SearchFactory} e é responsável
 * por criar instâncias de {@code Search} específicas para a busca de séries em diferentes campos,
 * como título, ano de lançamento, gênero, entre outros.
 *
 * @see controller.search.SearchFactory
 * @see controller.search.Search
 * @see controller.dataBase.SerieRepository
 */
public class SearchSerieFactory implements SearchFactory {

    /**
     * Cria uma instância de busca com base no tipo de campo de pesquisa fornecido.
     * Dependendo do campo de pesquisa selecionado, a fábrica cria e retorna uma instância
     * de busca específica para séries (por exemplo, buscar por título, ano de lançamento, gênero, etc.).
     *
     * @param type o campo de pesquisa a ser utilizado (por exemplo, título, ano de lançamento, etc.)
     * @return uma instância de {@code Search} correspondente ao tipo de pesquisa especificado
     */
    @Override
    public Search createSearch(SearchFields type) {
        SerieRepository serieRepository = SerieRepository.getInstance();

        switch (type) {
            case type.TUDO:
                SearchAll searchAll = new SearchAll();
                searchAll.setRepository(serieRepository);
                return searchAll;
            case type.TITULO:
                SearchTitle searchTitle = new SearchTitle();
                searchTitle.setRepository(serieRepository);
                return searchTitle;
            case type.ANO_LANCAMENTO:
                SearchYear searchYear = new SearchYear();
                searchYear.setRepository(serieRepository);
                return searchYear;
            case type.GENERO:
                SearchGenre searchGenre = new SearchGenre();
                searchGenre.setRepository(serieRepository);
                return searchGenre;
            case type.ELENCO:
                SearchElenco searchElenco = new SearchElenco();
                searchElenco.setRepository(serieRepository);
                return searchElenco;
            default:
                return null;
        }
    }
}
