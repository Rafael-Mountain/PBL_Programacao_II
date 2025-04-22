package controller.search.filme;

import controller.dataBase.FilmeRepository;
import controller.search.*;

public class SearchFilmeFactory implements SearchFactory {
    FilmeRepository filmeRepository = FilmeRepository.getInstance();
    @Override
    public Search createSearch(SearchFields type) {
        switch (type){
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
            default  -> {

            }
        }
        return null;
    }
}
