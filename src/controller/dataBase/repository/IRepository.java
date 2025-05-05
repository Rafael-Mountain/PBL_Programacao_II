package controller.dataBase.repository;

import java.util.List;

/**
 * {@code IRepository} define um contrato genérico para operações básicas de persistência (CRUD)
 * sobre qualquer tipo de entidade {@code T}.
 *
 * @param <T> o tipo de entidade que será manipulada pelo repositório.
 */
public interface IRepository<T> {

    /**
     * Retorna todos os itens armazenados no repositório.
     *
     * @return uma lista contendo todos os itens.
     */
    List<T> getItems();

    /**
     * Busca um item pelo seu identificador único.
     *
     * @param id o ID do item.
     * @return o item correspondente, ou {@code null} se não for encontrado.
     */
    T getItemById(int id);

    /**
     * Atualiza os dados de um item existente.
     *
     * @param item o item com os dados atualizados.
     */
    void update(T item);

    /**
     * Adiciona um novo item ao repositório.
     *
     * @param item o novo item a ser adicionado.
     */
    void add(T item);

    /**
     * Remove um item do repositório.
     *
     * @param item o item a ser removido.
     */
    void delete(T item);

    void save();

    void load();

    void clear();
}
