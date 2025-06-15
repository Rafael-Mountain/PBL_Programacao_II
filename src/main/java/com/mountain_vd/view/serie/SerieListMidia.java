package com.mountain_vd.view.serie;

import com.mountain_vd.controller.search.SearchResults;
import com.mountain_vd.model.Serie;
import com.mountain_vd.view.ListMidia;
import com.mountain_vd.view.SearchView;

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
