package controller.filter;

import java.util.List;

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
            System.out.println("Applying filter: " + filterType.getDescricao() + " " + filterValue);
            // Implement logic to filter media items with year greater than filterValue
        } else if (filterType == YearFilterType.MENOR) {
            System.out.println("Applying filter: " + filterType.getDescricao() + " " + filterValue);
            // Implement logic to filter media items with year less than filterValue
        } else if (filterType == YearFilterType.IGUAL) {
            System.out.println("Applying filter: " + filterType.getDescricao() + " " + filterValue);
            // Implement logic to filter media items with year equal to filterValue
        } else if (filterType == YearFilterType.DIFERENTE) {
            System.out.println("Applying filter: " + filterType.getDescricao() + " " + filterValue);
            // Implement logic to filter media items with year not equal to filterValue
        } else {
            System.out.println("Invalid filter type for YearFilter");
        }
    }
}
