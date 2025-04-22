package controller.search;

import controller.dataBase.FilmeRepository;
import controller.dataBase.LivroRepository;

public class SearchLivroFactory implements SearchFactory {
    LivroRepository livroRepository = LivroRepository.getInstance();
    @Override
    public Search createSearch(SearchFields type) {
        switch (type){
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
            default  -> {

            }
        }
        return null;
    }
}
