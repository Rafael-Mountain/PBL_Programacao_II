package com.mountain_vd.controller.util;

import java.util.*;

public class EnumUtils {

    /**
     * Interface que os enums devem implementar para funcionar com o utilitário.
     */
    public interface Descritivel {
        String getDescricao();
    }

    /**
     * Converte um enum com descrição em um mapa: Enum → Descrição.
     */
    public static <E extends Enum<E> & Descritivel> Map<E, String> toMap(Class<E> enumClass) {
        Map<E, String> map = new EnumMap<>(enumClass);
        for (E value : enumClass.getEnumConstants()) {
            map.put(value, value.getDescricao());
        }
        return map;
    }

    /**
     * Converte um enum com descrição em um mapa: Descrição → Enum.
     */
    public static <E extends Enum<E> & Descritivel> Map<String, E> toMapByDesc(Class<E> enumClass) {
        Map<String, E> map = new LinkedHashMap<>();
        for (E value : enumClass.getEnumConstants()) {
            map.put(value.getDescricao(), value);
        }
        return map;
    }

    /**
     * Retorna uma lista com todas as descrições do enum.
     */
    public static <E extends Enum<E> & Descritivel> List<String> getDescricoes(Class<E> enumClass) {
        List<String> descricoes = new ArrayList<>();
        for (E value : enumClass.getEnumConstants()) {
            descricoes.add(value.getDescricao());
        }
        return descricoes;
    }

    /**
     * Converte uma descrição para o valor do enum correspondente.
     */
    public static <E extends Enum<E> & Descritivel> E fromDescricao(Class<E> enumClass, String descricao) {
        for (E value : enumClass.getEnumConstants()) {
            if (value.getDescricao().equals(descricao)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Descrição inválida: " + descricao);
    }
}
