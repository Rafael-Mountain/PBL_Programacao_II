package controller.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormater {
    public static String DataHora(LocalDateTime date) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
        String dataFormatada = date.format(formato);
        return dataFormatada;
    }
}
