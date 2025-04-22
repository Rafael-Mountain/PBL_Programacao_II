package view.filme;

import controller.search.SearchResults;
import model.Filme;
import view.ListMidia;
import view.SearchView;

import java.util.Scanner;

public class FilmeListMidia  extends ListMidia {
    public FilmeListMidia(SearchResults searchResults) {
        super(searchResults);
    }

    @Override
    public void goToSearch(Scanner terminal) {
        SearchView searchView = new SearchViewFilme();
        FilmeListMidia filmeListMidia =  new FilmeListMidia(searchView.draw(terminal));
        filmeListMidia.draw(terminal);
    }

    @Override
    public void goToDisplay(Scanner terminal, int id) {
        new FilmeDisplay((Filme) medias.get(id)).draw(terminal);
    }

    @Override
    public String getNomeMidia() {
        return "Filme";
    }
}
