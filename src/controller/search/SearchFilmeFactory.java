package controller.search;

import controller.dataBase.FilmeRepository;

public class SearchFilmeFactory implements SearchFactory {
    @Override
    public Search createSearch(SearchFields type) {
        switch (type){
            case type.TUDO -> {
                FilmeRepository filmeRepository = FilmeRepository.getInstance();
                SearchAll searchAll = new SearchAll();
                searchAll.setRepository(filmeRepository);
                return searchAll;
            }
            case type.TITULO -> {

            }
            case type.ANO_LANCAMENTO -> {

            }
            case type.GENERO -> {

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
