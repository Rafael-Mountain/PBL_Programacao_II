package com.mountain_vd.view;

import com.mountain_vd.controller.filter.*;
import com.mountain_vd.controller.search.SearchFields;
import com.mountain_vd.controller.search.SearchResults;
import com.mountain_vd.model.Genero;
import com.mountain_vd.view.commons.ViewCommons;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Classe abstrata que representa a visualização de busca de mídias.
 * Permite ao usuário realizar buscas com filtros e opções de ordenação,
 * como gênero e ano, e exibe os resultados conforme os parâmetros fornecidos.
 */
public abstract class SearchView {

    /**
     * Método responsável por desenhar e exibir a tela de busca de mídias.
     * O usuário pode selecionar o campo de busca (por exemplo, por título, autor, etc.),
     * aplicar filtros adicionais por gênero e ano, e escolher a ordem de exibição dos resultados.
     *
     * @param terminal Scanner utilizado para capturar entradas do usuário.
     * @return Resultados da busca com os filtros e parâmetros definidos.
     */
    public SearchResults draw(Scanner terminal) {
        String valor = null;
        int opcao;
        List<SimpleEntry<String, SearchFields>> filterFields = getFields();
        FilterChain filterChain = new FilterChain();

        System.out.println("\n=== Busca de Mídia");

        // Exibe os campos de busca disponíveis para o usuário
        for (int i = 0; i < filterFields.size(); i++) {
            System.out.println((i + 1) + " - " + filterFields.get(i).getKey());
        }

        // Solicita ao usuário a escolha do campo para a busca
        opcao = ViewCommons.inputIntWRange(terminal, "selecione o campo para busca (caso selecione 'tudo' será feita uma busca geral): ", 1, filterFields.size() + 1);

        // Se a opção não for a busca geral, solicita o valor para o filtro
        if (opcao != 1) {
            System.out.print("Digite o valor para busca: ");
            valor = terminal.nextLine();
        }

        // Verifica se o usuário deseja filtrar por gênero
        boolean filterGenre = ViewCommons.inputBoolean(terminal, "\nDeseja filtrar por Genero?");
        if (filterGenre) {
            filterChain.addFilter(filterGenreView(terminal));
        }

        // Verifica se o usuário deseja filtrar por ano
        boolean FilterAno = ViewCommons.inputBoolean(terminal, "\nDeseja filtrar por Ano?");
        if (FilterAno) {
            filterChain.addFilter(filterYearView(terminal));
        }

        // Obtém o campo de busca selecionado pelo usuário
        SearchFields field = (filterFields.get(opcao - 1).getValue());

        // Solicita ao usuário a opção de ordenação dos resultados
        System.out.println("\nVocê deseja ordenar o resultado por: \n(S) Melhor avaliado.\n(N) Pior avaliado.\n");
        boolean ordenacao = ViewCommons.inputBoolean(terminal, "Selecione a opção de ordenação");

        // Executa a busca com os parâmetros definidos
        return execute(field, filterChain, valor, ordenacao);
    }

    /**
     * Método abstrato que deve ser implementado para fornecer os campos de busca disponíveis.
     *
     * @return Lista de campos de busca e suas representações do tipo {@link SearchFields}.
     */
    public abstract List<SimpleEntry<String, SearchFields>> getFields();

    /**
     * Método abstrato que executa a busca utilizando os filtros e parâmetros fornecidos.
     *
     * @param field       Campo de busca selecionado.
     * @param filterChain Filtros aplicados na busca.
     * @param value       Valor para busca (caso o campo não seja "tudo").
     * @param ordenacao   Define se a busca será ordenada por melhor ou pior avaliação.
     * @return Resultados da busca de mídia.
     */
    public abstract SearchResults execute(SearchFields field, FilterChain filterChain, String value, boolean ordenacao);

    /**
     * Método que exibe a tela para filtrar por gênero e retorna o filtro selecionado.
     *
     * @param terminal Scanner utilizado para capturar entradas do usuário.
     * @return O filtro de gênero selecionado.
     */
    public Filter filterGenreView(Scanner terminal) {

        List<SimpleEntry<String, GenreFilterType>> tiposGenero =
                Arrays.stream(GenreFilterType.values())
                        .map(field -> new SimpleEntry<>(field.getDescricao(), field))
                        .collect(Collectors.toList());

        System.out.println("\n=== Tipos de filtro de genero");

        // Exibe as opções de filtro por gênero
        for (int i = 0; i < tiposGenero.size(); i++) {
            System.out.println((i + 1) + " - " + tiposGenero.get(i).getKey());
        }

        // Solicita ao usuário a escolha do tipo de filtro de gênero
        int genreTypeFilter = ViewCommons.inputIntWRange(terminal, "Selecione o tipo de filtro: ", 1, 4);
        GenreFilterType genreFilterType = tiposGenero.get(genreTypeFilter - 1).getValue();

        // Solicita o valor para o filtro de gênero
        System.out.println("\nDigite o valor para filtro: ");
        List<Genero> GenreValue = ViewCommons.inputGenero(terminal);
        GenreFilter genreFilter = new GenreFilter(genreFilterType, GenreValue);
        return genreFilter;
    }

    /**
     * Método que exibe a tela para filtrar por ano e retorna o filtro de ano selecionado.
     *
     * @param terminal Scanner utilizado para capturar entradas do usuário.
     * @return O filtro de ano selecionado.
     */
    public Filter filterYearView(Scanner terminal) {
        List<SimpleEntry<String, YearFilterType>> tiposAno =
                Arrays.stream(YearFilterType.values())
                        .map(field -> new SimpleEntry<>(field.getDescricao(), field))
                        .collect(Collectors.toList());

        System.out.println("=== Tipos de filtro de ano");

        // Exibe as opções de filtro por ano
        for (int i = 0; i < tiposAno.size(); i++) {
            System.out.println((i + 1) + " - " + tiposAno.get(i).getKey());
        }

        // Solicita ao usuário a escolha do tipo de filtro de ano
        int yearTypeFilter = ViewCommons.inputIntWRange(terminal, "Selecione o tipo de filtro: ", 1, 4);
        YearFilterType yearFilterType = tiposAno.get(yearTypeFilter - 1).getValue();

        // Solicita o valor para o filtro de ano
        System.out.println("Digite o valor para filtro de ano: ");
        int anoValue = ViewCommons.inputInt(terminal, "Ano: ");
        YearFilter yearFilter = new YearFilter(yearFilterType, anoValue);
        return yearFilter;
    }
}
