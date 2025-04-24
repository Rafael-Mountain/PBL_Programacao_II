package model.commons;

/**
 * Interface que define um identificador único para uma entidade.
 */
public interface Identifiable {

    /**
     * Retorna o identificador da entidade.
     *
     * @return o ID da entidade
     */
    int getId();

    /**
     * Define o identificador da entidade.
     *
     * @param id o novo ID da entidade
     */
    void setId(int id);
}
