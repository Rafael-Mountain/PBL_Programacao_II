package controller.search;

import controller.dataBase.IRepository;
import model.Media;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchYear implements Search{
    IRepository repository;

    @Override
    public SearchResults execute(String searchTerm) {

        List<Media> medias = new ArrayList<>();
        medias.addAll(repository.getItems());

        medias = medias.stream().filter(media -> media.getDataLancamento().getYear() == Integer.parseInt(searchTerm))
                .collect(Collectors.toList());


        if (medias.isEmpty()) {
            return new SearchResults(medias, "Mídias não encontradas");
        } else {
            return new SearchResults(medias, "Busca por Ano: " + searchTerm);
        }
    }

    public void setRepository(IRepository repository) {
        this.repository = repository;
    }
}
