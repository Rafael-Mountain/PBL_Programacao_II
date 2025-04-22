package controller.search;

public class SearchLivroController extends SearchController{
    public SearchLivroController(String searchTerm, SearchFields searchField) {
        super(searchTerm, searchField);
        searchFactory = new SearchLivroFactory();
    }
}

