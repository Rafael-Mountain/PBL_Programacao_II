package view;

import controller.filter.*;
import controller.search.SearchController;
import controller.search.SearchFields;
import controller.search.SearchResults;
import model.Genero;
import model.Media;
import view.commons.ViewCommons;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SearchView {
    public SearchResults draw(Scanner terminal) {
        String valor = null;
        int opcao;
        List<SimpleEntry<String, SearchFields>> filterFields = getSearchFields();
        FilterChain filterChain = new FilterChain();

        System.out.println("\n=== Busca de Mídia");

        for (int i = 0; i < filterFields.size(); i++) {
            System.out.println((i + 1) + " - " + filterFields.get(i).getKey());
        }

        opcao = ViewCommons.inputIntWRange(terminal, "selecione o campo para busca (caso selecione 'tudo' será feita uma busca geral): ", 1, filterFields.size() + 1);

        if (opcao != 1) {
            System.out.println("Digite o valor para busca: ");
            valor = terminal.nextLine();
        }


        boolean filterGenre = ViewCommons.inputBoolean(terminal, "\nDeseja filtrar por Genero?");
        if (filterGenre) {
            filterChain.addFilter(filterGenreView(terminal));
        }

        boolean FilterAno = ViewCommons.inputBoolean(terminal, "\nDeseja filtrar por Ano?");
        if (FilterAno) {
            filterChain.addFilter(filterYearView(terminal));
        }


        SearchFields field = (filterFields.get(opcao - 1).getValue());

        SearchController searchController = new SearchController(valor, field);
        searchController.setFilterChain(filterChain);
        searchController.execute();
        SearchResults searchResults = searchController.getSearchResults();
        return searchResults;
    }

    public List<SimpleEntry<String, SearchFields>> getSearchFields(){
        return Arrays.stream(SearchFields.values()).map(field -> new SimpleEntry<>(field.getFieldName(), field)).collect(Collectors.toList());
    }

    public Filter filterGenreView(Scanner terminal) {

        List<SimpleEntry<String, GenreFilterType>> tiposGenero =
                Arrays.stream(GenreFilterType.values())
                        .map(field -> new SimpleEntry<>(field.getDescricao(), field))
                        .collect(Collectors.toList());

        System.out.println("\n=== Tipos de filtro de genero");

        for (int i = 0; i < tiposGenero.size(); i++) {
            System.out.println((i + 1) + " - " + tiposGenero.get(i).getKey());
        }

        int genreTypeFilter = ViewCommons.inputIntWRange(terminal, "Selecione o tipo de filtro: ", 1, 4);
        GenreFilterType genreFilterType = tiposGenero.get(genreTypeFilter - 1).getValue();

        System.out.println("\nDigite o valor para filtro: ");
        List<Genero> GenreValue = ViewCommons.inputGenero(terminal);
        GenreFilter genreFilter = new GenreFilter(genreFilterType, GenreValue);
        return genreFilter;
    }

    public Filter filterYearView(Scanner terminal) {
        List<SimpleEntry<String, YearFilterType>> tiposAno =
                Arrays.stream(YearFilterType.values())
                        .map(field -> new SimpleEntry<>(field.getDescricao(), field))
                        .collect(Collectors.toList());

        System.out.println("=== Tipos de filtro de ano");

        for (int i = 0; i < tiposAno.size(); i++) {
            System.out.println((i + 1) + " - " + tiposAno.get(i).getKey());
        }

        int yearTypeFilter = ViewCommons.inputIntWRange(terminal, "Selecione o tipo de filtro: ", 1, 4);
        YearFilterType yearFilterType = tiposAno.get(yearTypeFilter - 1).getValue();

        System.out.println("Digite o valor para filtro de ano: ");
        int anoValue = ViewCommons.inputInt(terminal, "Ano: ");
        YearFilter yearFilter = new YearFilter(yearFilterType, anoValue);
        return yearFilter;
    }
}

