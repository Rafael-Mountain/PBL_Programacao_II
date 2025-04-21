package controller.search;

import controller.dataBase.FilmeRepository;

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

            }
            case type.ELENCO -> {

            }
            default  -> {

            }
        }
        return null;
    }
}
