package controller.search.serie;

import controller.search.SearchController;
import controller.search.SearchFields;


public class SearchSerieController extends SearchController {
    public SearchSerieController(String searchTerm, SearchFields searchField) {
        super(searchTerm, searchField);
        searchFactory = new SearchSerieFactory();
    }
}
