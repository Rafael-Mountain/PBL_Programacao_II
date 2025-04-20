package controller.filter;

import model.Genero;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GenreFilter extends Filter {

    private final List<Genero> filterValue;
    private final GenreFilterType filterType;

    public GenreFilter(GenreFilterType filterType, List<Genero> filterValue) {
        super();
        this.filterValue = filterValue;
        this.filterType = filterType;
    }

    @Override
    void apply() {
        switch (filterType) {
             case CONTEM_UM:
                medias = medias.stream()
                        .filter(media -> !Collections.disjoint(media.getGeneros(), filterValue))
                        .collect(Collectors.toList());
                break;

            case CONTEM_TODOS:
                medias = medias.stream()
                        .filter(media -> media.getGeneros().containsAll(filterValue))
                        .collect(Collectors.toList());
                break;

            case NAO_CONTEM_UM:
                medias = medias.stream()
                        .filter(media -> Collections.disjoint(media.getGeneros(), filterValue))
                        .collect(Collectors.toList());
                break;

            case NAO_CONTEM_TODOS:
                medias = medias.stream()
                        .filter(media -> !media.getGeneros().containsAll(filterValue))
                        .collect(Collectors.toList());
                break;
        }
    }
}
