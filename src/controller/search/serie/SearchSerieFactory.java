package controller.search.serie;

import controller.dataBase.SerieRepository;
import controller.search.*;

public class SearchSerieFactory implements SearchFactory {
    @Override
    public Search createSearch(SearchFields type) {
        SerieRepository serieRepository = SerieRepository.getInstance();

        switch (type) {
            case type.TUDO -> {
                SearchAll searchAll = new SearchAll();
                searchAll.setRepository(serieRepository);
                return searchAll;
            }
            case type.TITULO -> {
                SearchTitle searchTitle = new SearchTitle();
                searchTitle.setRepository(serieRepository);
                return searchTitle;
            }
            case type.ANO_LANCAMENTO -> {
                SearchYear searchYear = new SearchYear();
                searchYear.setRepository(serieRepository);
                return searchYear;
            }
            case type.GENERO -> {
                SearchGenre searchGenre = new SearchGenre();
                searchGenre.setRepository(serieRepository);
                return searchGenre;
            }
//            case type.ELENCO -> {
//                SearchElenco searchElenco = new SearchElenco();
//                searchElenco.setRepository(serieRepository);
//                return searchElenco;
//            }
            default -> {

                return null;
            }
        }
    }
}
