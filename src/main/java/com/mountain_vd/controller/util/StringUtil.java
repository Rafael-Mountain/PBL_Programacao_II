package com.mountain_vd.controller.util;

public class StringUtil {
    public static String capitalize(String input) {
        String[] palavras = input.toLowerCase().split("\\s+");
        StringBuilder resultado = new StringBuilder();
        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                resultado.append(Character.toUpperCase(palavra.charAt(0)))
                        .append(palavra.substring(1))
                        .append(" ");
            }
        }
        return resultado.toString().trim();
    }
}
