package controller.search;

public interface SearchFactory {
    public Search createSearch(SearchFields type);
}
