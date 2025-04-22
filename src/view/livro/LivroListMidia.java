package view.livro;

import controller.search.SearchResults;
import view.ListMidia;
import view.SearchView;
import view.filme.FilmeListMidia;
import view.filme.SearchViewFilme;

import java.util.Scanner;

public class LivroListMidia extends ListMidia {
    public LivroListMidia(SearchResults searchResults) {
        super(searchResults);
    }

    @Override
    public void goToSearch(Scanner terminal) {
        SearchView searchView = new SearchViewLivro();
        LivroListMidia livroListMidia =  new LivroListMidia(searchView.draw(terminal));
        livroListMidia.draw(terminal);
    }

    @Override
    public String getNomeMidia() {
        return "Livro";
    }
}
