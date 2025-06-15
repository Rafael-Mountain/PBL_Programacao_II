package com.mountain_vd.controller.filter;

import com.mountain_vd.model.Media;

import java.util.List;

/**
 * A classe {@code Filter} serve como base para todos os filtros aplicáveis sobre listas de {@code Media}.
 * Os filtros concretos devem estender esta classe e implementar a lógica no método {@code apply()}.
 */
public abstract class Filter {
    /**
     * Lista de mídias sobre a qual o filtro será aplicado.
     */
    protected List<Media> medias;

    /**
     * Mensagem informativa que pode ser usada para retornar o status ou resultado do filtro.
     * (Ainda não implementado totalmente).
     */
    private String message;

    /**
     * Aplica a lógica do filtro sobre a lista de mídias.
     * Deve ser implementado pelas subclasses.
     */
    abstract void apply();

    /**
     * Retorna a lista de mídias associada a este filtro.
     *
     * @return lista de mídias filtradas ou em processo de filtragem.
     */
    List<Media> getMedias() {
        return medias;
    }

    /**
     * Define a lista de mídias que será usada no filtro.
     *
     * @param medias lista de mídias a ser filtrada.
     */
    void setMedias(List<Media> medias) {
        this.medias = medias;
    }

    /**
     * Retorna a mensagem associada à execução do filtro.
     *
     * @return mensagem de status ou descrição do filtro.
     */
    String getMessage() {
        return message;
    }
}
