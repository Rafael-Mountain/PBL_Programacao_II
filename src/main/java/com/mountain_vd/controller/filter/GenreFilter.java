package com.mountain_vd.controller.filter;

import com.mountain_vd.controller.dataBase.GeneroRepository;
import com.mountain_vd.controller.dataBase.repository.Repository;
import com.mountain_vd.model.Genero;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A classe {@code GenreFilter} é responsável por filtrar mídias com base
 * nos gêneros associados a elas.
 * Este filtro pode operar de duas formas distintas, definidas pela enumeração
 * {@link GenreFilterType}:
 * <ul>
 *     <li>{@code CONTEM}: mantém apenas as mídias que contêm todos os gêneros informados</li>
 *     <li>{@code NAO_CONTEM}: mantém apenas as mídias que não contêm todos os gêneros informados</li>
 * </ul>
 *
 * Esta classe estende {@link Filter}, utilizando sua lista de mídias para aplicar
 * a lógica de filtragem apropriada.
 */
public class GenreFilter extends Filter {

    private final List<Genero> filterValue;
    private final GenreFilterType filterType;

    /**
     * Construtor da classe {@code GenreFilter}.
     *
     * @param filterType   o tipo de filtragem a ser aplicado (CONTEM ou NAO_CONTEM)
     * @param filterValue  a lista de gêneros a ser usada como critério de filtragem
     */
    public GenreFilter(GenreFilterType filterType, List<Genero> filterValue) {
        super();
        this.filterValue = filterValue;
        this.filterType = filterType;
    }

    public GenreFilter(GenreFilterType filterType, String filterValue) {
        super();
        String trimmedFilter = filterValue.trim();
        this.filterValue = GeneroRepository.getInstance()
                .getItems()
                .stream()
                .filter(genero -> genero.getNome().trim().equalsIgnoreCase(trimmedFilter))
                .collect(Collectors.toList());
        this.filterType = filterType;
    }


    /**
     * Aplica o filtro de gênero à lista de mídias.
     * Dependendo do tipo de filtro, a lista será reduzida apenas às mídias
     * que contêm ou que não contêm todos os gêneros especificados.
     */
    @Override
    void apply() {
        if (filterValue.isEmpty()) {
            return;
        }

        switch (filterType) {
            case CONTEM:
                medias = medias.stream()
                        .filter(media -> {
                            Set<String> nomesGenerosDaMidia = media.getGeneros().stream()
                                    .map(Genero::getNome)
                                    .collect(Collectors.toSet());
                            Set<String> nomesFiltro = filterValue.stream()
                                    .map(Genero::getNome)
                                    .collect(Collectors.toSet());
                            return nomesGenerosDaMidia.containsAll(nomesFiltro);
                        })
                        .collect(Collectors.toList());
                break;
            case NAO_CONTEM:
                medias = medias.stream()
                        .filter(media -> {
                            Set<String> nomesGenerosDaMidia = media.getGeneros().stream()
                                    .map(Genero::getNome)
                                    .collect(Collectors.toSet());
                            Set<String> nomesFiltro = filterValue.stream()
                                    .map(Genero::getNome)
                                    .collect(Collectors.toSet());
                            return !nomesGenerosDaMidia.containsAll(nomesFiltro);
                        })
                        .collect(Collectors.toList());
                break;
        }
    }
}
