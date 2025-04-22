package controller.search.filme;

import controller.search.SearchController;
import controller.search.SearchFields;

public class SearchFilmeController extends SearchController {

    public SearchFilmeController(String searchTerm, SearchFields searchField) {
        super(searchTerm, searchField);
        searchFactory = new SearchFilmeFactory();
    }



}

