package controller.filter;

import java.util.List;
import java.util.stream.Collectors;

public class YearFilter extends Filter{

    private  YearFilterType filterType;
    private  int filterValue;

    public YearFilter (YearFilterType filterType, int filterValue) {
        super();
        this.filterType = filterType;
        this.filterValue = filterValue;

    }

    @Override
    void apply() {
        if (filterType == YearFilterType.MAIOR) {
            medias = medias.stream()
                    .filter(media -> media.getDataLancamento().getYear() > filterValue)
                    .collect(Collectors.toList());
        } else if (filterType == YearFilterType.MENOR) {
            medias = medias.stream()
                    .filter(media -> media.getDataLancamento().getYear() < filterValue)
                    .collect(Collectors.toList());
        } else if (filterType == YearFilterType.IGUAL) {
           medias = medias.stream()
                    .filter(media -> media.getDataLancamento().getYear() == filterValue)
                    .collect(Collectors.toList());
        } else if (filterType == YearFilterType.DIFERENTE) {
            medias = medias.stream()
                    .filter(media -> media.getDataLancamento().getYear() != filterValue)
                    .collect(Collectors.toList());
        } else {
            System.out.println("Invalid filter type for YearFilter");
        }
    }
}
