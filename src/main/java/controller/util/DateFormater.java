package controller.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe utilitária para formatação de datas.
 */
public class DateFormater {

    // Instância imutável e reutilizável de formatter
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");

    /**
     * Retorna a data e hora formatada no padrão "dd/MM/yyyy - HH:mm".
     *
     * @param date objeto {@link LocalDateTime} a ser formatado
     * @return uma {@link String} representando a data formatada
     */
    public static String DataHora(LocalDateTime date) {
        if (date == null) {
            return "Data inválida";
        }
        return date.format(FORMATTER);
    }
}
