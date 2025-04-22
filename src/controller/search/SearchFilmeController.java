package controller.search;

public class SearchFilmeController extends  SearchController{

    public SearchFilmeController(String searchTerm, SearchFields searchField) {
        super(searchTerm, searchField);
        searchFactory = new SearchFilmeFactory();
    }
}

