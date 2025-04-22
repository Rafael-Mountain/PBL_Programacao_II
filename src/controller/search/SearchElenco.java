package controller.search;

import controller.dataBase.IRepository;
import model.Media;
import model.MediaAudioVisual;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchElenco implements Search {
    private IRepository Repository;

    @Override
    public SearchResults execute(String searchTerm) {

        List<MediaAudioVisual> medias = new ArrayList<>();
        medias.addAll(Repository.getItems());

        List<Media> resultados = medias.stream()
                .filter(filme -> filme.getElenco().stream()
                        .anyMatch(ator -> ator.toLowerCase().contains(searchTerm.toLowerCase())))
                .collect(Collectors.toList());

        if (medias.isEmpty()) {
            return new SearchResults(resultados, "Mídias não encontradas");
        } else {
            return new SearchResults(resultados, "Busca por Elenco: " + searchTerm);
        }
    }

    public void setRepository(IRepository repository) {
        this.Repository = repository;
    }
}
