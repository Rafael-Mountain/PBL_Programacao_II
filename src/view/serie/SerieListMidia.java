package view.serie;

import controller.search.SearchResults;
import model.Livro;
import model.Serie;
import view.ListMidia;
import view.SearchView;
import view.livro.LivroDisplay;

import java.util.Scanner;

public class SerieListMidia extends ListMidia {
    public SerieListMidia(SearchResults searchResults) {
        super(searchResults);
    }

    @Override
    public void goToSearch(Scanner terminal) {
        SearchView searchView = new SearchViewSerie();
        SerieListMidia serieListMidia =  new SerieListMidia(searchView.draw(terminal));
        serieListMidia.draw(terminal);
    }

    @Override
    public void goToDisplay(Scanner terminal, int id) {
        new SerieDisplay((Serie) medias.get(id)).draw(terminal);
    }

    @Override
    public String getNomeMidia() {
        return "Serie";
    }
}
