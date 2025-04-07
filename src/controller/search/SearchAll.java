package controller.search;

import controller.dataBase.FilmeRepository;
import controller.dataBase.LivroRepository;
import controller.dataBase.SerieRepository;
import model.Media;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchAll implements Search{
    @Override
    public SearchResults execute(String SearchTerm) {
        SearchResults searchResults;

        FilmeRepository filmeRepository = FilmeRepository.getInstance();
        LivroRepository livroRepository = LivroRepository.getInstance();
        SerieRepository serieRepository = SerieRepository.getInstance();
        List<Media> medias = new ArrayList<>();

        medias.addAll(filmeRepository.getItems());
        medias.addAll(livroRepository.getItems());
        medias.addAll(serieRepository.getItems());

        if (medias.isEmpty()){
            searchResults = new SearchResults(medias,"Medias não encontradas");
        }
        searchResults = new SearchResults(medias, "Busca: todos as midias");

        return searchResults;
    }
}
