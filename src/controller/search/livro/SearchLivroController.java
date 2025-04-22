package controller.search.livro;

import controller.search.SearchController;
import controller.search.SearchFields;

public class SearchLivroController extends SearchController {
    public SearchLivroController(String searchTerm, SearchFields searchField) {
        super(searchTerm, searchField);
        searchFactory = new SearchLivroFactory();
    }
}

