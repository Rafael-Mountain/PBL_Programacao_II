package controller.search;

import controller.dataBase.FilmeRepository;
import controller.dataBase.IRepository;
import model.Filme;
import model.Media;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchDirector implements Search {
     FilmeRepository filmeRepository;

    @Override
    public SearchResults execute(String searchTerm) {

        List<Filme> filmes = new ArrayList<>();
        filmes.addAll(filmeRepository.getItems());

        filmes = filmes.stream().filter(filme -> filme.getDirecao().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());


        if (filmes.isEmpty()) {
            return new SearchResults(filmes, "Mídias não encontradas");
        } else {
            return new SearchResults(filmes, "Busca por Diretor: " + searchTerm);
        }
    }

    public void setFilmeRepository(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }
}
