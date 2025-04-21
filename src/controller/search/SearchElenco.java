package controller.search;

import controller.dataBase.IRepository;
import model.Filme;
import model.Media;

import java.util.List;
import java.util.stream.Collectors;

public class SearchElenco implements Search {
    private IRepository<Filme> filmeRepository;

    @Override
    public SearchResults execute(String searchTerm) {

        List<Media> medias = filmeRepository.getItems().stream()
                .filter(filme -> filme.getElenco().stream()
                        .anyMatch(ator -> ator.toLowerCase().contains(searchTerm.toLowerCase()))
                )
                .collect(Collectors.toList());


        if (medias.isEmpty()) {
            return new SearchResults(medias, "Mídias não encontradas");
        } else {
            return new SearchResults(medias, "Busca por Elenco: " + searchTerm);
        }
    }

    public void setRepository(IRepository<Filme> filmeRepository) {
        this.filmeRepository = filmeRepository;
    }
}
