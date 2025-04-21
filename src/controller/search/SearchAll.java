package controller.search;

import controller.dataBase.FilmeRepository;
import controller.dataBase.IRepository;
import controller.dataBase.LivroRepository;
import controller.dataBase.SerieRepository;
import model.Media;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchAll implements Search{
    IRepository repository;

    @Override
    public SearchResults execute(String SearchTerm) {
        SearchResults searchResults;

        List<Media> medias = new ArrayList<>();

        medias.addAll(repository.getItems());

        if (medias.isEmpty()){
            searchResults = new SearchResults(medias,"Medias não encontradas");
        }
        searchResults = new SearchResults(medias, "Busca: todos as midias");

        return searchResults;
    }

    public void setRepository(IRepository repository) {
        this.repository = repository;
    }
}

