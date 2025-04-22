package controller.search;

import controller.dataBase.IRepository;
import model.Livro;
import model.Media;

import java.util.List;
import java.util.stream.Collectors;

public class SearchISBN implements Search {
    private IRepository<Livro> livroRepository;

    @Override
    public SearchResults execute(String searchTerm) {

        List<Media> medias = livroRepository.getItems().stream()
                .filter(livro -> livro.getIsbn().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());

        if (medias.isEmpty()) {
            return new SearchResults(medias, "Mídias não encontradas");
        } else {
            return new SearchResults(medias, "Busca por ISBN: " + searchTerm);
        }
    }

    public void setRepository(IRepository<Livro> livroRepository) {
        this.livroRepository = livroRepository;
    }
}
