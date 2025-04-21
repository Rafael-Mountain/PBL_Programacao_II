package controller.search;

import controller.dataBase.IRepository;
import model.Media;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;


public class SearchTitle implements Search {
    IRepository repository;

    @Override
    public SearchResults execute(String searchTerm) {

        List<Media> medias = new ArrayList<> ();
        medias.addAll(repository.getItems());

        medias = medias.stream().filter(media -> media.getTitulo().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());


        if (medias.isEmpty()) {
            return new SearchResults(medias, "Mídias não encontradas");
        } else {
            return new SearchResults(medias, "Busca por Título: " + searchTerm);
        }
    }

    public void setRepository(IRepository repository) {
        this.repository = repository;
    }
}

